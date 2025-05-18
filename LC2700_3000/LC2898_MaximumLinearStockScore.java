package LC2700_3000;
import java.util.*;
public class LC2898_MaximumLinearStockScore {
    /**
     * Given a 1-indexed integer array prices, where prices[i] is the price of a particular stock on the ith day, your
     * task is to select some of the elements of prices such that your selection is linear.
     *
     * A selection indexes, where indexes is a 1-indexed integer array of length k which is a subsequence of the array
     * [1, 2, ..., n], is linear if:
     *
     * For every 1 < j <= k, prices[indexes[j]] - prices[indexes[j - 1]] == indexes[j] - indexes[j - 1].
     * A subsequence is an array that can be derived from another array by deleting some or no elements without changing
     * the order of the remaining elements.
     *
     * The score of a selection indexes, is equal to the sum of the following array: [prices[indexes[1]],
     * prices[indexes[2]], ..., prices[indexes[k]].
     *
     * Return the maximum score that a linear selection can have.
     *
     * Input: prices = [1,5,3,7,8]
     * Output: 20
     *
     * Input: prices = [5,6,7,8,9]
     * Output: 35
     *
     * Constraints:
     *
     * 1 <= prices.length <= 10^5
     * 1 <= prices[i] <= 10^9
     * @param prices
     * @return
     */
    // time = O(n), space = O(n)
    public long maxScore(int[] prices) {
        int n = prices.length;
        long res = 0;
        int[] w = new int[n];
        for (int i = 0; i < n; i++) w[i] = prices[i] - i;
        HashMap<Integer, Long> map = new HashMap<>();
        for (int i = 0; i < n; i++) map.put(w[i], map.getOrDefault(w[i], 0L) + prices[i]);
        for (long v : map.values()) res = Math.max(res, v);
        return res;
    }
}
/**
 * All elements in a subsequence must have the same prices[i] - i value.
 */