package LC3901_4200;
import java.util.*;
public class LC4014_MinimumTotalPriceAfterApplyingDiscounts {
    /**
     * You are given two integer arrays prices and discounts.
     *
     * The value prices[i] represents the price of the ith item, and discounts[j] represents a discount percentage.
     *
     * You may apply discounts subject to the following rules:
     *
     * Each discount can be applied to at most one item.
     * Each item can receive at most one discount.
     * An item may also receive no discount.
     * If a discount of d percent is applied to an item with price p, its final price becomes (p * (100 - d)) / 100. The
     * final price is not rounded.
     *
     * Return the minimum possible sum of final prices after assigning discounts optimally. Answers within 10-5 of the
     * actual answer will be accepted.
     *
     * Input: prices = [10,30,21], discounts = [50,60]
     * Output: 32.50000
     *
     * Input: prices = [100,70], discounts = [10,40,50]
     * Output: 92.00000
     *
     * Input: prices = [7,3,9], discounts = [100,100]
     * Output: 3.00000
     *
     * Constraints:
     *
     * 1 <= prices.length, discounts.length <= 10^5
     * 1 <= prices[i] <= 10^5
     * 1 <= discounts[j] <= 100
     * @param prices
     * @param discounts
     * @return
     */
    // time = O(nlogn + mlogm), space = O(logn + logm)
    public double minPrice(int[] prices, int[] discounts) {
        Arrays.sort(prices);
        Arrays.sort(discounts);
        int n = prices.length, m = discounts.length;
        double res = 0;
        for (int i = n - 1, j = m - 1; i >= 0; i--, j--) {
            if (j >= 0) res += 1.0 * (prices[i] * (100 - discounts[j])) / 100;
            else res += prices[i];
        }
        return res;
    }
}