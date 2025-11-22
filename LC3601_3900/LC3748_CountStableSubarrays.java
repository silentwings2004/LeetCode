package LC3601_3900;

public class LC3748_CountStableSubarrays {
    /**
     * You are given an integer array nums.
     *
     * A subarray of nums is called stable if it contains no inversions, i.e., there is no pair of indices i < j such
     * that nums[i] > nums[j].
     *
     * You are also given a 2D integer array queries of length q, where each queries[i] = [li, ri] represents a query.
     * For each query [li, ri], compute the number of stable subarrays that lie entirely within the segment nums[li..ri].
     *
     * Return an integer array ans of length q, where ans[i] is the answer to the ith query.
     *
     * Note:
     *
     * A single element subarray is considered stable.
     *
     * Input: nums = [3,1,2], queries = [[0,1],[1,2],[0,2]]
     * Output: [2,3,4]
     *
     * Input: nums = [2,2], queries = [[0,1],[0,0]]
     * Output: [3,1]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * 1 <= queries.length <= 10^5
     * queries[i] = [li, ri]
     * 0 <= li <= ri <= nums.length - 1
     * @param nums
     * @param queries
     * @return
     */
    // time = O(n + m), space = O(n)
    public long[] countStableSubarrays(int[] nums, int[][] queries) {
        int n = nums.length;
        long[] s = new long[n + 1];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (i > 0 && x < nums[i - 1]) cnt = 0;
            cnt++;
            s[i + 1] = s[i] + cnt;
        }

        int[] nxt = new int[n];
        nxt[n - 1] = n;
        for (int i = n - 2; i >= 0; i--) {
            nxt[i] = nums[i] <= nums[i + 1] ? nxt[i + 1] : i + 1;
        }

        int m = queries.length;
        long[] res = new long[m];
        for (int i = 0; i < m; i++) {
            int l = queries[i][0], r = queries[i][1];
            int l2 = nxt[l];
            if (l2 > r) { // l, r in the same segment
                long len = r - l + 1;
                res[i] = (len + 1) * len / 2;
                continue;
            }
            // l and r in different segments
            // [l, l2) + [l2, r]
            long len = l2 - l;
            res[i] = (len + 1) * len / 2 + s[r + 1] - s[l2];
        }
        return res;
    }
}
