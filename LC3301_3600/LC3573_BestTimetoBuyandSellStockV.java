package LC3301_3600;

public class LC3573_BestTimetoBuyandSellStockV {
    /**
     * You are given an integer array prices where prices[i] is the price of a stock in dollars on the ith day, and an
     * integer k.
     *
     * You are allowed to make at most k transactions, where each transaction can be either of the following:
     *
     * Normal transaction: Buy on day i, then sell on a later day j where i < j. You profit prices[j] - prices[i].
     *
     * Short selling transaction: Sell on day i, then buy back on a later day j where i < j. You profit
     * prices[i] - prices[j].
     *
     * Note that you must complete each transaction before starting another. Additionally, you can't buy or sell on the
     * same day you are selling or buying back as part of a previous transaction.
     *
     * Return the maximum total profit you can earn by making at most k transactions.
     *
     * Input: prices = [1,7,9,8,2], k = 2
     *
     * Output: 14
     *
     * Input: prices = [12,16,19,19,8,1,19,13,9], k = 3
     *
     * Output: 36
     *
     * Constraints:
     *
     * 2 <= prices.length <= 10^3
     * 1 <= prices[i] <= 10^9
     * 1 <= k <= prices.length / 2
     * @param prices
     * @param k
     * @return
     */
    // time = O(n * k), space = O(n * k)
    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        long[][][] f = new long[n][k + 1][3];
        for (int j = 1; j <= k; j++) {
            f[0][j][1] = -prices[0];
            f[0][j][2] = prices[0];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                f[i][j][0] = Math.max(f[i - 1][j][0], Math.max(f[i - 1][j][1] + prices[i], f[i - 1][j][2] - prices[i]));
                f[i][j][1] = Math.max(f[i - 1][j][1], f[i - 1][j - 1][0] - prices[i]);
                f[i][j][2] = Math.max(f[i - 1][j][2], f[i - 1][j - 1][0] + prices[i]);
            }
        }
        return f[n - 1][k][0];
    }
}
/**
 * 0 - 未持有股票且不处于正在做空中
 * 1 - 普通交易持有股票
 * 2 - 正处于做空中的状态
 */