package LC901_1200;
import java.util.*;
public class LC1012_NumbersWithRepeatedDigits {
    /**
     * Given an integer n, return the number of positive integers in the range [1, n] that have at least one repeated
     * digit.
     *
     * Input: n = 20
     * Output: 1
     *
     * Input: n = 100
     * Output: 10
     *
     * Input: n = 1000
     * Output: 262
     *
     * Constraints:
     *
     * 1 <= n <= 10^9
     * @param n
     * @return
     */
    // time = O(logn), space = O(1)
    public int numDupDigitsAtMostN(int n) {
        List<Integer> nums = new ArrayList<>();
        int res = n;
        while (n > 0) {
            nums.add(n % 10);
            n /= 10;
        }

        int m = nums.size();
        boolean[] st = new boolean[10];
        for (int i = 1; i < m; i++) res -= 9 * P(9, i - 1);

        res -= (nums.get(m - 1) - 1) * P(9, m - 1);
        st[nums.get(m - 1)] = true;
        for (int i = m - 2; i >= 0; i--) {
            int x = nums.get(i);
            for (int j = 0; j < x; j++) {
                if (!st[j]) {
                    res -= P(10 - (m - i), i);
                }
            }
            if (st[x]) return res;
            st[x] = true;
        }
        return res - 1;
    }

    private int P(int a, int b) {
        int res = 1;
        for (int i = a, j = 0; j < b; i--, j++) {
            res *= i;
        }
        return res;
    }

    // S2: dfs
    // time = O(logn), space = O(1)
    int res = 0;
    public int numDupDigitsAtMostN2(int n) {
        String s = n + "";
        int m = s.length();

        for (int i = 1; i < m; i++) {
            res += P(10, i) - P(9, i - 1);
        }

        boolean[] st = new boolean[10];
        dfs(s, 0, st);
        return n - res;
    }

    private void dfs(String s, int u, boolean[] st) {
        if (u == s.length()) {
            res++;
            return;
        }

        for (int d = 0; d <= 9; d++) {
            if (u == 0 && d == 0) continue;
            if (st[d]) continue;
            if (d < s.charAt(u) - '0') {
                res += P(10 - (u + 1), s.length() - (u + 1));
            } else if (d == s.charAt(u) - '0') {
                st[d] = true;
                dfs(s, u + 1, st);
                st[d] = false;
            }
        }
    }
}
/**
 * 数位dp = 数位统计
 * 总数 - 不重复
 * 分析：画一棵树
 * 516285
 * _____
 * 9 9 8 7 6
 * 5位：9 * P(9,4)
 * 4位: 9 * P(9,3)
 * 3位：9 * P(9,2)
 * ...
 * 1位：9 * P(9,0)
 *
 * 6位：
 * 1~4： 4 * P(9,5)
 * 5： 0   1
 * 0 * P(8,4)
 * 6 * P(7,3)
 * ...
 * 统计整棵树
 * 左边都可以直接算
 * 跟树的高度成正比，O(logn)
 */