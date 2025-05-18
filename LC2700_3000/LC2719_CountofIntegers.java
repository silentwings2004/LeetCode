package LC2700_3000;
import java.util.*;
public class LC2719_CountofIntegers {
    /**
     * You are given two numeric strings num1 and num2 and two integers max_sum and min_sum. We denote an integer x to
     * be good if:
     *
     * num1 <= x <= num2
     * min_sum <= digit_sum(x) <= max_sum.
     * Return the number of good integers. Since the answer may be large, return it modulo 109 + 7.
     *
     * Note that digit_sum(x) denotes the sum of the digits of x.
     *
     * Input: num1 = "1", num2 = "12", min_num = 1, max_num = 8
     * Output: 11
     *
     * Input: num1 = "1", num2 = "5", min_num = 1, max_num = 5
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= num1 <= num2 <= 10^22
     * 1 <= min_sum <= max_sum <= 400
     * @param num1
     * @param num2
     * @param min_sum
     * @param max_sum
     * @return
     */
    // time = O(n * m), space = O(n * m)
    final int N = 23, M = 410, mod = (int)1e9 + 7;
    int[][] f;
    int mins, maxs;
    public int count(String num1, String num2, int min_sum, int max_sum) {
        f = new int[N][M];
        mins = min_sum;
        maxs = max_sum;

        int res = work(num2) - work(num1);
        int sum = 0;
        for (char c : num1.toCharArray()) sum += c - '0';
        if (sum >= mins && sum <= maxs) res++;
        return (res + mod) % mod;
    }

    private int work(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        for (int i = 0; i < n; i++) Arrays.fill(f[i], -1);
        return dfs(chars, 0, 0, true); // 一开始一定为true，否则后面都不受约束了
    }

    private int dfs(char[] chars, int u, int sum, boolean flag) {
        if (sum > maxs) return 0;
        if (u == chars.length) return sum >= mins ? 1 : 0;
        if (!flag && f[u][sum] != -1) return f[u][sum];

        int res = 0;
        int r = flag ? chars[u] - '0' : 9;
        for (int i = 0; i <= r; i++) {
            res = (res + dfs(chars, u + 1, sum + i, flag && i == r)) % mod;
        }
        if (!flag) f[u][sum] = res;
        return res;
    }
}
/**
 * 枚举的方向问题：从高位开始填！
 * 写一个类似回溯的东西来构造数字
 * 需要一个参数告诉我是否受到高位的约数 => 最多只能填到s[i]
 * true: 0 ~ s[i]
 * false: 0 ~ 9
 * dfs(i,sum,isLimit)
 */