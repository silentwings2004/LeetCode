package LC2401_2700;
import java.util.*;
public class LC2548_MaximumPricetoFillaBag {
    /**
     * You are given a 2D integer array items where items[i] = [pricei, weighti] denotes the price and weight of the ith
     * item, respectively.
     *
     * You are also given a positive integer capacity.
     *
     * Each item can be divided into two items with ratios part1 and part2, where part1 + part2 == 1.
     *
     * The weight of the first item is weighti * part1 and the price of the first item is pricei * part1.
     * Similarly, the weight of the second item is weighti * part2 and the price of the second item is pricei * part2.
     * Return the maximum total price to fill a bag of capacity capacity with given items. If it is impossible to fill
     * a bag return -1. Answers within 10-5 of the actual answer will be considered accepted.
     *
     * Input: items = [[50,1],[10,8]], capacity = 5
     * Output: 55.00000
     *
     * Input: items = [[100,30]], capacity = 50
     * Output: -1.00000
     *
     * Constraints:
     *
     * 1 <= items.length <= 10^5
     * items[i].length == 2
     * 1 <= pricei, weighti <= 10^4
     * 1 <= capacity <= 10^9
     * @param items
     * @param capacity
     * @return
     */
    // time = O(nlogn),space = O(1)
    public double maxPrice(int[][] items, int capacity) {
        Arrays.sort(items, (o1, o2) -> Double.compare(o2[0] * 1.0 / o2[1], o1[0] * 1.0 / o1[1]));

        double res = 0;
        int t = capacity;
        for (int[] x : items) {
            int p = x[0], w = x[1];
            if (w <= t) {
                t -= w;
                res += p;
            } else {
                res += t * 1.0 / w * p;
                t = 0;
            }
            if (t == 0) break;
        }
        return t == 0 ? res : -1;
    }
}