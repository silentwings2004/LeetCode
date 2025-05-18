package LC2700_3000;
import java.util.*;
public class LC2813_MaximumEleganceofaKLengthSubsequence {
    /**
     * You are given a 0-indexed 2D integer array items of length n and an integer k.
     *
     * items[i] = [profiti, categoryi], where profiti and categoryi denote the profit and category of the ith item
     * respectively.
     *
     * Let's define the elegance of a subsequence of items as total_profit + distinct_categories2, where total_profit is
     * the sum of all profits in the subsequence, and distinct_categories is the number of distinct categories from all
     * the categories in the selected subsequence.
     *
     * Your task is to find the maximum elegance from all subsequences of size k in items.
     *
     * Return an integer denoting the maximum elegance of a subsequence of items with size exactly k.
     *
     * Note: A subsequence of an array is a new array generated from the original array by deleting some elements
     * (possibly none) without changing the remaining elements' relative order.
     *
     * Input: items = [[3,2],[5,1],[10,1]], k = 2
     * Output: 17
     *
     * Input: items = [[3,1],[3,1],[2,2],[5,3]], k = 3
     * Output: 19
     *
     * Input: items = [[1,1],[2,1],[3,1]], k = 3
     * Output: 7
     *
     * Constraints:
     *
     * 1 <= items.length == n <= 10^5
     * items[i].length == 2
     * items[i][0] == profiti
     * items[i][1] == categoryi
     * 1 <= profiti <= 10^9
     * 1 <= categoryi <= n
     * 1 <= k <= n
     * @param items
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    final int N = 100010;
    public long findMaximumElegance(int[][] items, int k) {
        Arrays.sort(items, (o1, o2) -> o2[0] - o1[0]);
        int n = items.length;
        HashSet<Integer> set = new HashSet<>();
        int[] stk = new int[N];
        int tt = 0;
        long res = 0, s = 0;
        for (int i = 0; i < n; i++) {
            int a = items[i][0], b = items[i][1];
            if (i < k) {
                s += a;
                if (!set.add(b)) stk[++tt] = a;
            } else {
                if (tt > 0 && set.add(b)) s += a - stk[tt--];
            }
            long sz = set.size();
            res = Math.max(res, s + sz * sz);
        }
        return res;
    }
}
/**
 * t -> t+1: add a new category element, remove an old category element which is not single
 */