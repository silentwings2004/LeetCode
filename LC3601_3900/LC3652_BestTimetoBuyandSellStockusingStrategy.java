package LC3601_3900;

public class LC3652_BestTimetoBuyandSellStockusingStrategy {
    /**
     * You are given two integer arrays prices and strategy, where:
     *
     * prices[i] is the price of a given stock on the ith day.
     * strategy[i] represents a trading action on the ith day, where:
     * -1 indicates buying one unit of the stock.
     * 0 indicates holding the stock.
     * 1 indicates selling one unit of the stock.
     * You are also given an even integer k, and may perform at most one modification to strategy. A modification
     * consists of:
     *
     * Selecting exactly k consecutive elements in strategy.
     * Set the first k / 2 elements to 0 (hold).
     * Set the last k / 2 elements to 1 (sell).
     * The profit is defined as the sum of strategy[i] * prices[i] across all days.
     *
     * Return the maximum possible profit you can achieve.
     *
     * Note: There are no constraints on budget or stock ownership, so all buy and sell operations are feasible
     * regardless of past actions.
     *
     * Input: prices = [4,2,8], strategy = [-1,0,1], k = 2
     * Output: 10
     *
     * Input: prices = [5,4,3], strategy = [1,1,0], k = 2
     * Output: 9
     *
     * Constraints:
     *
     * 2 <= prices.length == strategy.length <= 10^5
     * 1 <= prices[i] <= 10^5
     * -1 <= strategy[i] <= 1
     * 2 <= k <= prices.length
     * k is even
     * @param prices
     * @param strategy
     * @param k
     * @return
     */
    // S1: 前缀和
    // time = O(n), space = O(n)
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        long[] ps = new long[n + 1], ss = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            ps[i] = ps[i - 1] + prices[i - 1];
            ss[i] = ss[i - 1] + strategy[i - 1] * prices[i - 1];
        }

        long maxd = 0;
        for (int i = 1, j = 1; i + k - 1 <= n; i++) {
            long cs = ss[i + k - 1] - ss[i - 1];
            long t = ps[i + k - 1] - ps[i + k / 2 - 1];
            maxd = Math.max(maxd, t - cs);
        }
        return ss[n] + maxd;
    }

    // S2
    // time = O(n), space = O(1)
    public long maxProfit2(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        long tot = 0, sum = 0, ms = 0;
        for (int i = 0; i < n; i++) {
            int p = prices[i], s = strategy[i];
            tot += p * s;
            sum += p * (1 - s);
            if (i < k - 1) {
                if (i >= k / 2 - 1) {
                    sum -= prices[i - k / 2 + 1];
                }
                continue;
            }
            ms = Math.max(ms, sum);
            sum -= prices[i - k / 2 + 1] - prices[i - k + 1] * strategy[i - k + 1];
        }
        return tot + ms;
    }
}