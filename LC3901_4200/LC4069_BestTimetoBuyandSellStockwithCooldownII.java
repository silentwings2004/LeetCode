package LC3901_4200;
import java.util.*;
public class LC4069_BestTimetoBuyandSellStockwithCooldownII {
    /**
     * You are given an integer array prices of length n, where prices[i] is the price of a stock on day i.
     *
     * You are also given an integer cooldown and an integer array costs of length n. The value costs[k] is the total
     * holding fee for a transaction in which the stock is held for exactly k days.
     *
     * You may perform any number of transactions, including zero, subject to the following rules:
     *
     * You may hold at most one share at a time. You must sell your current share before buying another.
     * If you sell on day j, the earliest day you may buy again is j + cooldown + 1.
     * If you buy on day i and sell on day j, the holding duration is j - i days. You pay the fee costs[j - i] once for
     * that transaction.
     * The profit from buying on day i and selling on day j is prices[j] - prices[i] - costs[j - i].
     *
     * Return the maximum total profit you can achieve. If no profitable transactions are possible, return 0.
     *
     * Input: prices = [1,5,3], cooldown = 1, costs = [2,0,1]
     * Output: 4
     *
     * Input: prices = [1,2,5], cooldown = 0, costs = [0,2,1]
     * Output: 3
     *
     * Input: prices = [4,1,7], cooldown = 2, costs = [3,0,1]
     * Output: 6
     *
     * Input: prices = [3,1,4], cooldown = 0, costs = [2,1,0]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= n == prices.length <= 1500
     * 1 <= prices[i] <= 10^5
     * 0 <= cooldown <= n - 1
     * costs.length == n
     * 0 <= costs[i] <= 10^5
     * @param prices
     * @param cooldown
     * @param costs
     * @return
     */
    // S1
    // time = O(n^2), space = O(n)
    public int maxProfit(int[] prices, int cooldown, int[] costs) {
        final int inf = 0x3f3f3f3f;
        int n = prices.length, res = 0;
        int[][] f = new int[n][2]; // 0: buy, 1: sell
        for (int i = 0; i < n; i++) Arrays.fill(f[i], -inf);
        for (int i = 0; i < n; i++) {
            f[i][0] = -prices[i];
            for (int j = 0; j < i; j++) {
                if (i >= j + cooldown + 1) f[i][0] = Math.max(f[i][0], f[j][1] - prices[i]);
                f[i][1] = Math.max(f[i][1], f[j][0] + prices[i] - costs[i - j]);
            }
            res = Math.max(res, f[i][1]);
        }
        return res;
    }

    // S2
    // time = O(n), space = O(n)
    public int maxProfit2(int[] prices, int cooldown, int[] costs) {
        int n = prices.length, res = 0;
        int[] f = new int[n];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                res = Math.max(res, f[Math.max(0, j - cooldown - 1)] + prices[i] - prices[j] - costs[i - j]);
            }
            f[i] = res;
        }
        return res;
    }
}