package LC1801_2100;
import java.util.*;
public class LC1833_MaximumIceCreamBars {
    /**
     * It is a sweltering summer day, and a boy wants to buy some ice cream bars.
     *
     * At the store, there are n ice cream bars. You are given an array costs of length n, where costs[i] is the price
     * of the ith ice cream bar in coins. The boy initially has coins coins to spend, and he wants to buy as many ice
     * cream bars as possible.
     *
     * Return the maximum number of ice cream bars the boy can buy with coins coins.
     *
     * Note: The boy can buy the ice cream bars in any order.
     *
     * Input: costs = [1,3,2,4,1], coins = 7
     * Output: 4
     *
     * Input: costs = [10,6,8,7,7,8], coins = 5
     * Output: 0
     *
     * Input: costs = [1,6,3,1,2,5], coins = 20
     * Output: 6
     *
     * Constraints:
     *
     * costs.length == n
     * 1 <= n <= 10^5
     * 1 <= costs[i] <= 10^5
     * 1 <= coins <= 10^8
     * @param costs
     * @param coins
     * @return
     */
    // time = O(nlogn), space = O(1)
    public int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int n = costs.length, res = 0;
        for (int i = 0; i < n; i++) {
            if (coins >= costs[i]) {
                res++;
                coins -= costs[i];
            } else break;
        }
        return res;
    }
}