package LC2101_2400;
import java.util.*;
public class LC2376_CountSpecialIntegers {
    /**
     * We call a positive integer special if all of its digits are distinct.
     *
     * Given a positive integer n, return the number of special integers that belong to the interval [1, n].
     *
     * Input: n = 20
     * Output: 19
     *
     * Input: n = 135
     * Output: 110
     *
     * Constraints:
     *
     * 1 <= n <= 2 * 10^9
     * @param n
     * @return
     */
    // time = O(logn), space = O(1)
    public int countSpecialNumbers(int n) {
        List<Integer> nums = new ArrayList<>();
        while (n > 0) {
            nums.add(n % 10);
            n /= 10;
        }

        int m = nums.size(), res = 0;
        boolean[] st = new boolean[10];
        for (int i = 1; i < m; i++) res += 9 * P(9, i - 1); // 预先计算下所有小于m位的情况

        res += (nums.get(m - 1) - 1) * P(9, m - 1); // 处理第m位开头元素的情况
        st[nums.get(m - 1)] = true; // 标记开头元素已经用过
        for (int i = m - 2; i >= 0; i--) { // 枚举剩下的位数
            int x = nums.get(i);
            for (int j = 0; j < x; j++) { // 枚举所有小于当前位数字x的情况
                if (!st[j]) {
                    res += P(10 - (m - i), i); // 还剩10-(m-i)个未用过的数字，放在i个位置上
                }
            }
            if (st[x]) return res; // 之前已经出现过当前位，无法继续枚举下去，直接return
            st[x] = true;
        }
        return res + 1; // 之前从未出现过相同的x，所以原数字n肯定也符合条件，所以要算上自身+1!
    }

    private int P(int a, int b) {
        int res = 1;
        for (int i = a, j = 0; j < b; i--, j++) {
            res *= i;
        }
        return res;
    }

    // S2: dfs
    // time = O(logn), space = O(logn)
    int res = 0;
    public int countSpecialNumbers2(int n) {
        String s = n + "";
        int m = s.length();

        for (int len = 1; len <= m - 1; len++) {
            res += A(10, len) - A(9, len - 1);
        }

        boolean[] visited = new boolean[10];
        dfs(s, 0, visited);
        return res;
    }

    private void dfs(String s, int i, boolean[] visited) {
        int m = s.length();
        if (i == m) {
            // 找到了一模一样的数字，并且保证digit不一样，否则走不到这里！
            res++; // 把自身这个数算上！
            return;
        }

        for (int d = 0; d <= 9; d++) {
            if (i == 0 && d == 0) continue; // no leading 0
            if (visited[d]) continue;
            if (d < s.charAt(i) - '0') {
                res += A(10 - (i + 1), m - i - 1);
            } else if (d == s.charAt(i) - '0') {
                visited[d] = true;
                dfs(s, i + 1, visited);
                visited[d] = false; // 永远只有一个分叉,不必要！
            }
        }
    }

    private int A(int m, int n) {
        int res = 1;
        for (int i = 0; i < n; i++) {
            res *= m - i;
        }
        return res;
    }
}
/**
 * same as LC1012
 * m 位
 * 23104
 * 1____
 *  9 * 8 * 7 * 6
 * 2____
 *  0  8 * 7 * 6
 *  1 8 * 7 * 6 = P(8,3)
 *  2 X
 *  3 0  P(7,2)
 *    1  0  0/1/2/3/4
 *          x x x x 1
 * 树的左儿子 - dp / 公式
 * 树的右儿子 - 单独枚举
 *
 * n = 3541
 * x    1 ~ 9
 * xx   10 ~ 99 - {11, 22, ... 99} = 81
 * A(10, len) - A(9, len - 1)
 * 0 => continue
 * 1 => digitSet{...} pick 3
 * 2 => digitSet{...} pick 3
 * 3 => dfs(xxx)
 *
 * x[xx]
 * 0 => res += digitSet{...} pick 2
 * 1 => res += digitSet{...} pick 2
 * 2 => res += digitSet{...} pick 2
 * 3 => res += digitSet{...} pick 2
 * 4 => res += digitSet{...} pick 2
 * 5 => dfs(xx)
 */
