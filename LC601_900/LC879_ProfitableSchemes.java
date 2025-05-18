package LC601_900;

public class LC879_ProfitableSchemes {
    /**
     * There is a group of n members, and a list of various crimes they could commit. The ith crime generates a
     * profit[i] and requires group[i] members to participate in it. If a member participates in one crime, that
     * member can't participate in another crime.
     *
     * Let's call a profitable scheme any subset of these crimes that generates at least minProfit profit, and the
     * total number of members participating in that subset of crimes is at most n.
     *
     * Return the number of schemes that can be chosen. Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * Input: n = 5, minProfit = 3, group = [2,2], profit = [2,3]
     * Output: 2
     *
     * Input: n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
     * Output: 7
     *
     * Constraints:
     *
     * 1 <= n <= 100
     * 0 <= minProfit <= 100
     * 1 <= group.length <= 100
     * 1 <= group[i] <= 100
     * profit.length == group.length
     * 0 <= profit[i] <= 100
     * @param n
     * @param minProfit
     * @param group
     * @param profit
     * @return
     */
    // time = O(m * n * k), space = O(m * n)
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int m = minProfit, t = group.length;
        int[][] f = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) f[i][0] = 1;

        int mod = (int)(1e9 + 7);
        for (int i = 0; i < t; i++) {
            int g = group[i], p = profit[i];
            for (int j = n; j >= g; j--) {
                for (int k = m; k >= 0; k--) {
                    f[j][k] = (f[j][k] + f[j - g][Math.max(0, k - p)]) % mod;
                }
            }
        }
        return f[n][m];
    }
}
/**
 * f(i,j,k)
 * j <= n
 * profit >= k
 * 不选i: f[i-1][j][k]
 * 选i: 所有 1~i-1，人数 <= j - g[i], 利润 >= k - p[i]
 * f[i-1][j-g[i]][k-p[i]]
 * k-p[i]是可以<0的 => >= -1 等价于 >= 0
 */