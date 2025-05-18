package LC301_600;
import java.util.*;
public class LC322_CoinChange {
    /**
     * You are given coins of different denominations and a total amount of money amount. Write a function to compute
     * the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by
     * any combination of the coins, return -1.
     *
     * You may assume that you have an infinite number of each kind of coin.
     *
     * Input: coins = [1,2,5], amount = 11
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= coins.length <= 12
     * 1 <= coins[i] <= 2^31 - 1
     * 0 <= amount <= 10^4
     *
     * @param coins
     * @param amount
     * @return
     */
    // time = O(k * n), space = O(k) k: the amount
    final int INF = (int) 1e8;
    public int coinChange(int[] coins, int amount) {
        int k = amount;
        int[] f = new int[k + 1];
        Arrays.fill(f, INF);
        f[0] = 0;

        int n = coins.length;
        for (int i = 0; i < n; i++) {
            for (int j = coins[i]; j <= k; j++) {
                f[j] = Math.min(f[j], f[j - coins[i]] + 1);
            }
        }
        return f[k] == INF ? -1 : f[k];
    }
}
/**
 * 完全背包
 * 体积vi，价值 = 1， m: 背包容量
 */