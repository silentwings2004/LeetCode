package LC2700_3000;
import java.util.*;
public class LC2819_MinimumRelativeLossAfterBuyingChocolates {
    /**
     * You are given an integer array prices, which shows the chocolate prices and a 2D integer array queries, where
     * queries[i] = [ki, mi].
     *
     * Alice and Bob went to buy some chocolates, and Alice suggested a way to pay for them, and Bob agreed.
     *
     * The terms for each query are as follows:
     *
     * If the price of a chocolate is less than or equal to ki, Bob pays for it.
     * Otherwise, Bob pays ki of it, and Alice pays the rest.
     * Bob wants to select exactly mi chocolates such that his relative loss is minimized, more formally, if, in total,
     * Alice has paid ai and Bob has paid bi, Bob wants to minimize bi - ai.
     *
     * Return an integer array ans where ans[i] is Bob's minimum relative loss possible for queries[i].
     *
     * Input: prices = [1,9,22,10,19], queries = [[18,4],[5,2]]
     * Output: [34,-21]
     *
     * Input: prices = [1,5,4,3,7,11,9], queries = [[5,4],[5,7],[7,3],[4,5]]
     * Output: [4,16,7,1]
     *
     * Constraints:
     *
     * 1 <= prices.length == n <= 10^5
     * 1 <= prices[i] <= 10^9
     * 1 <= queries.length <= 10^5
     * queries[i].length == 2
     * 1 <= ki <= 10^9
     * 1 <= mi <= n
     * @param prices
     * @param queries
     * @return
     */
    // time = O((m + n) * logn), space = O(n)
    public long[] minimumRelativeLosses(int[] prices, int[][] queries) {
        Arrays.sort(prices);
        int n = prices.length;
        long[] s = new long[n + 1];
        for (int i = 1;  i<= n; i++) s[i] = s[i - 1] + prices[i - 1];
        int m = queries.length;
        long[] res = new long[m];
        for (int i = 0; i < m; i++) {
            int k = queries[i][0], t = queries[i][1];
            res[i] = helper(prices, s, k, t);
        }
        return res;
    }

    private long helper(int[] w, long[] s, int k, int m) {
        int n = w.length, p = find(w, k);
        int l = 0, r = Math.min(p, m);
        while (l < r) {
            int mid = l + r >> 1;
            if (w[mid] < 2 * k - w[n - (m - mid)]) l = mid + 1;
            else r = mid;
        }
        return s[r] + get(s, n - (m - r) + 1, n, k);
    }

    private long get(long[] s, int l, int r, int k) {
        long sum = s[r] - s[l - 1], v = (long)k * (r - l + 1);
        return v - (sum - v);
    }

    private int find(int[] w, int t) {
        int l = 0, r = w.length - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (w[mid] <= t) l = mid + 1;
            else r = mid;
        }
        return w[r] > t ? r : r + 1;
    }
}