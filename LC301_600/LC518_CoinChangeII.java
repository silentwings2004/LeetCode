package LC301_600;

public class LC518_CoinChangeII {
    /**
     * You are given an integer array coins representing coins of different denominations and an integer amount
     * representing a total amount of money.
     *
     * Return the number of combinations that make up that amount. If that amount of money cannot be made up by any
     * combination of the coins, return 0.
     *
     * You may assume that you have an infinite number of each kind of coin.
     *
     * The answer is guaranteed to fit into a signed 32-bit integer.
     *
     * Input: amount = 5, coins = [1,2,5]
     * Output: 4
     *
     * Input: amount = 3, coins = [2]
     * Output: 0
     *
     * Input: amount = 10, coins = [10]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= coins.length <= 300
     * 1 <= coins[i] <= 5000
     * All the values of coins are unique.
     * 0 <= amount <= 5000
     * @param amount
     * @param coins
     * @return
     */
    // time = O(n * t), space = O(t)
    public int change(int amount, int[] coins) {
        int n = coins.length, t = amount;
        int[] f = new int[t + 1];
        f[0] = 1;
        for (int x : coins) {
            for (int j = x; j <= t; j++) {
                f[j] = f[j] + f[j - x];
            }
        }
        return f[t];
    }
}