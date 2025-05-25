package LC3301_3600;

public class LC3560_FindMinimumLogTransportationCost {
    /**
     * You are given integers n, m, and k.
     *
     * There are two logs of lengths n and m units, which need to be transported in three trucks where each truck can
     * carry one log with length at most k units.
     *
     * You may cut the logs into smaller pieces, where the cost of cutting a log of length x into logs of length len1
     * and len2 is cost = len1 * len2 such that len1 + len2 = x.
     *
     * Return the minimum total cost to distribute the logs onto the trucks. If the logs don't need to be cut, the
     * total cost is 0.
     *
     * Input: n = 6, m = 5, k = 5
     * Output: 5
     *
     * Input: n = 4, m = 4, k = 6
     * Output: 0
     *
     * Constraints:
     *
     * 2 <= k <= 10^5
     * 1 <= n, m <= 2 * k
     * The input is generated such that it is always possible to transport the logs.
     * @param n
     * @param m
     * @param k
     * @return
     */
    // S1
    // time = O(1), space = O(1)
    public long minCuttingCost(int n, int m, int k) {
        long res = 0;
        if (n > k) res += 1L * k * (n - k);
        if (m > k) res += 1L * k * (m - k);
        return res;
    }

    // S2
    // time = O(1), space = O(1)
    public long minCuttingCost2(int n, int m, int k) {
        return Math.max(0L, 1L * k * (n - k)) + Math.max(0L, 1L * k * (m - k));
    }
}