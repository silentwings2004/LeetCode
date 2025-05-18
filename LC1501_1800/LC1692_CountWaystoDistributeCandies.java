package LC1501_1800;

public class LC1692_CountWaystoDistributeCandies {
    /**
     * There are n unique candies (labeled 1 through n) and k bags. You are asked to distribute all the candies into the
     * bags such that every bag has at least one candy.
     *
     * There can be multiple ways to distribute the candies. Two ways are considered different if the candies in one bag
     * in the first way are not all in the same bag in the second way. The order of the bags and the order of the
     * candies within each bag do not matter.
     *
     * For example, (1), (2,3) and (2), (1,3) are considered different because candies 2 and 3 in the bag (2,3) in the
     * first way are not in the same bag in the second way (they are split between the bags (2) and (1,3)). However,
     * (1), (2,3) and (3,2), (1) are considered the same because the candies in each bag are all in the same bags in
     * both ways.
     *
     * Given two integers, n and k, return the number of different ways to distribute the candies. As the answer may be
     * too large, return it modulo 10^9 + 7.
     *
     * Input: n = 3, k = 2
     * Output: 3
     *
     * Input: n = 4, k = 2
     * Output: 7
     *
     * Input: n = 20, k = 5
     * Output: 206085257
     *
     * Constraints:
     *
     * 1 <= k <= n <= 1000
     * @param n
     * @param k
     * @return
     */
    // time = O(n * k), space = O(n * k)
    public int waysToDistribute(int n, int k) {
        long mod = (long)(1e9 + 7);
        long[][] f = new long[n + 1][k + 1];
        f[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, k); j++) {
                f[i][j] = (f[i][j] + f[i - 1][j] * j % mod) % mod;
                f[i][j] = (f[i][j] + f[i - 1][j - 1]) % mod;
            }
        }
        return (int)f[n][k];
    }
}