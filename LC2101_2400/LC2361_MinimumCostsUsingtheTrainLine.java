package LC2101_2400;

public class LC2361_MinimumCostsUsingtheTrainLine {
    /**
     * A train line going through a city has two routes, the regular route and the express route. Both routes go through
     * the same n + 1 stops labeled from 0 to n. Initially, you start on the regular route at stop 0.
     *
     * You are given two 1-indexed integer arrays regular and express, both of length n. regular[i] describes the cost
     * it takes to go from stop i - 1 to stop i using the regular route, and express[i] describes the cost it takes to
     * go from stop i - 1 to stop i using the express route.
     *
     * You are also given an integer expressCost which represents the cost to transfer from the regular route to the
     * express route.
     *
     * Note that:
     *
     * There is no cost to transfer from the express route back to the regular route.
     * You pay expressCost every time you transfer from the regular route to the express route.
     * There is no extra cost to stay on the express route.
     * Return a 1-indexed array costs of length n, where costs[i] is the minimum cost to reach stop i from stop 0.
     *
     * Note that a stop can be counted as reached from either route.
     *
     * Input: regular = [1,6,9,5], express = [5,2,3,10], expressCost = 8
     * Output: [1,7,14,19]
     *
     * Input: regular = [11,5,13], express = [7,10,6], expressCost = 3
     * Output: [10,15,24]
     *
     * Constraints:
     *
     * n == regular.length == express.length
     * 1 <= n <= 10^5
     * 1 <= regular[i], express[i], expressCost <= 10^5
     * @param regular
     * @param express
     * @param expressCost
     * @return
     */
    // time = O(n), space = O(n)
    public long[] minimumCosts(int[] regular, int[] express, int expressCost) {
        int n = regular.length;
        long[][] f = new long[n + 1][2];
        f[0][0] = 0;
        f[0][1] = expressCost;
        long[] res = new long[n];

        for (int i = 1; i <= n; i++) {
            f[i][0] = Math.min(f[i - 1][0] + regular[i - 1], f[i - 1][1] + express[i - 1]);
            f[i][1] = Math.min(f[i - 1][0] + regular[i - 1] + expressCost, f[i - 1][1] + express[i - 1]);
            res[i - 1] = Math.min(f[i][0], f[i][1]);
        }
        return res;
    }
}
