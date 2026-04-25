package LC3901_4200;
import java.util.*;
public class LC3911_KthSmallestRemainingEvenIntegerinSubarrayQueries {
    /**
     * You are given an integer array nums where nums is strictly increasing.
     *
     * You are also given a 2D integer array queries, where queries[i] = [li, ri, ki].
     *
     * For each query [li, ri, ki]:
     *
     * Consider the subarray nums[li..ri]
     * From the infinite sequence of all positive even integers: 2, 4, 6, 8, 10, 12, 14, ...
     * Remove all elements that appear in the subarray nums[li..ri].
     * Find the kith smallest integer remaining in the sequence after the removals.
     * Return an integer array ans, where ans[i] is the result for the ith query.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * An array is said to be strictly increasing if each element is strictly greater than its previous one (if exists).
     *
     * Input: nums = [1,4,7], queries = [[0,2,1],[1,1,2],[0,0,3]]
     * Output: [2,6,6]
     *
     * Input: nums = [2,5,8], queries = [[0,1,2],[1,2,1],[0,2,4]]
     * Output: [6,2,12]
     *
     * Input: nums = [3,6], queries = [[0,1,1],[1,1,3]]
     * Output: [2,8]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * nums is strictly increasing
     * 1 <= queries.length <= 10^5
     * queries[i] = [li, ri, ki]
     * 0 <= li <= ri < nums.length
     * 1 <= ki <= 10^9
     * @param nums
     * @param queries
     * @return
     */
    // time = O(n + mlogn), space = O(n)
    public int[] kthRemainingInteger(int[] nums, int[][] queries) {
        int n = nums.length;
        List<Integer> q = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 0) q.add(i);
        }
        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int l = queries[i][0], r = queries[i][1], k = queries[i][2];
            if (q.size() == 0) {
                res[i] = 2 * k;
                continue;
            }
            int L = lowerBound(q, l);
            int R = upperBound(q, r);
            int c = L <= R ? R - L + 1 : 0;
            l = 0;
            r = c;
            while (l < r) {
                int mid = l + r >> 1;
                int nxt = mid == c ? Integer.MAX_VALUE : nums[q.get(L + mid)];
                int need = 2 * (k + mid);
                if (nxt > need) r = mid;
                else l = mid + 1;
            }
            res[i] = 2 * (k + r);
        }
        return res;
    }

    private int lowerBound(List<Integer> q, int t) { // 1st pos >= t
        int l = 0, r = q.size() - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (q.get(mid) >= t) r = mid;
            else l = mid + 1;
        }
        return q.get(r) >= t ? r : r + 1;
    }

    private int upperBound(List<Integer> q, int t) { // last pos <= t
        int l = 0, r = q.size() - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (q.get(mid) <= t) l = mid;
            else r = mid - 1;
        }
        return q.get(r) <= t ? r : r - 1;
    }
}