package LC1201_1500;
import java.util.*;
public class LC1475_FinalPricesWithaSpecialDiscountinaShop {
    /**
     * Given the array prices where prices[i] is the price of the ith item in a shop. There is a special discount for
     * items in the shop, if you buy the ith item, then you will receive a discount equivalent to prices[j] where j is
     * the minimum index such that j > i and prices[j] <= prices[i], otherwise, you will not receive any discount at all.
     *
     * Return an array where the ith element is the final price you will pay for the ith item of the shop considering
     * the special discount.
     *
     * Input: prices = [8,4,6,2,3]
     * Output: [4,2,4,2,3]
     *
     * Constraints:
     *
     * 1 <= prices.length <= 500
     * 1 <= prices[i] <= 10^3
     * @param prices
     * @return
     */
    // S1
    // time = O(n^2), space = O(1)
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (prices[j] <= prices[i]) {
                    prices[i] -= prices[j];
                    break;
                }
            }
        }
        return prices;
    }

    // S2: Monotonic stack
    // time = O(n), space = O(n)
    public int[] finalPrices2(int[] prices) {
        int n = prices.length;
        int[] stk = new int[n + 1];
        int tt = 0;
        stk[++tt] = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            int x = prices[i];
            while (tt > 0 && stk[tt] > x) tt--;
            if (tt > 0) prices[i] -= stk[tt];
            stk[++tt] = x;
        }
        return prices;
    }
}