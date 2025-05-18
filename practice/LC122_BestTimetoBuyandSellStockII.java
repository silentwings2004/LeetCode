package practice;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: BestTimetoBuyandSellStockII
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 122. Best Time to Buy and Sell Stock II
 */
public class LC122_BestTimetoBuyandSellStockII {
    /**
     * Say you have an array for which the ith element is the price of a given stock on day i.
     *
     * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one
     * and sell one share of the stock multiple times).
     *
     * Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy
     * again).
     *
     * Example 1:
     *
     * Input: [7,1,5,3,6,4]
     * Output: 7
     * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
     *              Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
     * Example 2:
     *
     * Input: [1,2,3,4,5]
     * Output: 4
     * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
     *              Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
     *              engaging multiple transactions at the same time. You must sell before buying again.
     * Example 3:
     *
     * Input: [7,6,4,3,1]
     * Output: 0
     * Explanation: In this case, no transaction is done, i.e. max profit = 0.
     * @param prices
     * @return
     */
    // S1: brute-force 1 --> recursion
    // time = O(n^n), space = O(n)
    int res;
    public int maxProfit(int[] prices) {
        // corner case
        if (prices == null || prices.length < 2) return 0;
        res = 0;
        int day = 0;
        int status = 0; // 0 表示不持有股票，1表示持有股票
        dfs(prices, day, status, res);
        return res;
    }

    private void dfs(int[] prices, int day, int status, int profit) {
        if (day == prices.length) {
            res = Math.max(res, profit);
            return;
        }

        dfs(prices, day + 1, status, profit);

        if (status == 0) {
            dfs(prices, day + 1, 1, profit - prices[day]);
        } else {
            dfs(prices, day + 1, 0, profit + prices[day]);
        }
    }

    // S2: brute-force 2
    // time = O(n^n), space = O(n)
    public int maxProfit2(int[] prices) {
        // corner case
        if (prices == null || prices.length < 2) return 0;

        return helper(prices, 0);
    }

    private int helper(int[] prices, int day) {
        if (day >= prices.length) return 0;

        int res = 0;
        for (int i = day; i < prices.length; i++) {
            int max = 0;
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[i] < prices[j]) {
                    int profit = helper(prices, j + 1) + prices[j] - prices[i];
                    if (profit > max) max = profit;
                }
            }
            if (max > res) res = max;
        }
        return res;
    }

    // S3: Greedy (最优解)
    // 多次买卖的条件下，怎样才能收益最高呢？从上图来说，股票曲线中所有斜率为正的线段恰好为你买入卖出的时间。将结果累加，即为最大收益。
    // 贪心算法解决此题目；判断每两天的价格差，价格差大于0 就进行一次买卖，如果期间天数连续价格差为正，则视为最低点买入，最高点卖出。
    // 从第 i 天（这里 i >= 1）开始，与第 i - 1 的股价进行比较，如果股价有上升（严格上升），就将升高的股价
    // （prices[i] - prices[i- 1]）记入总利润，按照这种算法，得到的结果就是符合题意的最大利润。
    // 按照贪心算法，在索引为 1、2、3 的这三天，我们做的操作应该是买进昨天的，卖出今天的，虽然这种操作题目并不允许，
    // 但是它等价于：“在索引为 0 的那一天买入，在索引为 3 的那一天卖出”。
    // time = O(n), space = O(1)
    public int maxProfit3(int[] prices) {
        // corner case
        if (prices == null || prices.length < 2) return 0;

        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }

    // S4: DP
    // 状态从持有现金（cash）开始，到最后一天我们关心的状态依然是持有现金（cash）, 每一天状态可以转移，也可以不动。
    // 因为不限制交易次数，除了最后一天，每一天的状态可能不变化，也可能转移;
    // 写代码的时候，可以不用对最后一天单独处理，输出最后一天，状态为 0 的时候的值即可, 因为一定有 dp[len - 1][0] > dp[len - 1][1]。
    // 起始的时候: 如果什么都不做，dp[0][0] = 0; 如果买入股票，当前收益是负数，即 dp[0][1] = -prices[i]。
    // time = O(n), space = O(n)
    public int maxProfit4(int[] prices) {
        // corner case
        if (prices == null || prices.length < 2) return 0;

        // 0：持有现金  1：持有股票
        // 状态转移：0 → 1 → 0 → 1 → 0 → 1 → 0
        int[][] dp = new int[prices.length][2];

        dp[0][0] = 0;
        dp[0][1] = - prices[0];

        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] , dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }

        return dp[prices.length - 1][0];
    }
}
