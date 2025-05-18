package LC2700_3000;
import java.util.*;
public class LC2931_MaximumSpendingAfterBuyingItems {
    /**
     * You are given a 0-indexed m * n integer matrix values, representing the values of m * n different items in m
     * different shops. Each shop has n items where the jth item in the ith shop has a value of values[i][j].
     * Additionally, the items in the ith shop are sorted in non-increasing order of value. That is,
     * values[i][j] >= values[i][j + 1] for all 0 <= j < n - 1.
     *
     * On each day, you would like to buy a single item from one of the shops. Specifically, On the dth day you can:
     *
     * Pick any shop i.
     * Buy the rightmost available item j for the price of values[i][j] * d. That is, find the greatest index j such
     * that item j was never bought before, and buy it for the price of values[i][j] * d.
     * Note that all items are pairwise different. For example, if you have bought item 0 from shop 1, you can still buy
     * item 0 from any other shop.
     *
     * Return the maximum amount of money that can be spent on buying all m * n products.
     *
     * Input: values = [[8,5,2],[6,4,1],[9,7,3]]
     * Output: 285
     *
     * Input: values = [[10,8,6,4,2],[9,7,5,3,2]]
     * Output: 386
     *
     * Constraints:
     *
     * 1 <= m == values.length <= 10
     * 1 <= n == values[i].length <= 10^4
     * 1 <= values[i][j] <= 10^6
     * values[i] are sorted in non-increasing order.
     * @param values
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long maxSpending(int[][] values) {
        long res = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        int m = values.length, n = values[0].length;
        for (int i = 0; i < m; i++) pq.offer(new int[]{values[i][n - 1], i, n - 1});
        long day = 1;
        while (!pq.isEmpty()) {
            int[] t = pq.poll();
            res += t[0] * day;
            int id = t[1], p = t[2];
            if (p > 0) pq.offer(new int[]{values[id][p - 1], id, p - 1});
            day++;
        }
        return res;
    }
}