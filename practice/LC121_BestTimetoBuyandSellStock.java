package practice;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: BestTimetoBuyandSellStock
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 121. Best Time to Buy and Sell Stock
 */
public class LC121_BestTimetoBuyandSellStock {
    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     *
     * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock),
     * design an algorithm to find the maximum profit.
     *
     * Note that you cannot sell a stock before you buy one.
     *
     * Example 1:
     *
     * Input: [7,1,5,3,6,4]
     * Output: 5
     * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
     *              Not 7-1 = 6, as selling price needs to be larger than buying price.
     * Example 2:
     *
     * Input: [7,6,4,3,1]
     * Output: 0
     * Explanation: In this case, no transaction is done, i.e. max profit = 0.
     * @param prices
     * @return
     */
    // S1: brute-force
    // time = O(n^2), space = O(1)
    public int maxProfit(int[] prices) {
        // corner case
        if (prices == null || prices.length < 2) return 0;

        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
            }
        }
        return maxProfit;
    }

    // S2: DP
    // time = O(n), space = O(n)
    public int maxProfit2(int[] prices) {
        // corner case
        if (prices == null || prices.length < 2) return 0;

        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]); // 只能买卖一次
        }

        return dp[prices.length - 1][0];
    }

    // S3: Greedy
    // time = O(n), space = O(1)
    public int maxProfit3(int[] prices) {
        // corner case
        if (prices == null || prices.length < 2) return 0;

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) minPrice = prices[i];
            else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }
}
