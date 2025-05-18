package LC3301_3600;

public class LC3356_ZeroArrayTransformationII {
    /**
     * You are given an integer array nums of length n and a 2D array queries where queries[i] = [li, ri, vali].
     *
     * Each queries[i] represents the following action on nums:
     *
     * Decrement the value at each index in the range [li, ri] in nums by at most vali.
     * The amount by which each value is decremented can be chosen independently for each index.
     *
     * A Zero Array is an array with all its elements equal to 0.
     *
     * Return the minimum possible non-negative value of k, such that after processing the first k queries in sequence,
     * nums becomes a Zero Array. If no such k exists, return -1.
     *
     * Input: nums = [2,0,2], queries = [[0,2,1],[0,2,1],[1,1,3]]
     *
     * Output: 2
     *
     * Input: nums = [4,3,2,1], queries = [[1,3,2],[0,2,1]]
     *
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 5 * 10^5
     * 1 <= queries.length <= 10^5
     * queries[i].length == 3
     * 0 <= li <= ri < nums.length
     * 1 <= vali <= 5
     * @param nums
     * @param queries
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int minZeroArray(int[] nums, int[][] queries) {
        int m = queries.length;
        int l = 0, r = m;
        while (l < r) {
            int mid = l + r >> 1;
            if (check(nums, queries, mid)) r = mid;
            else l = mid + 1;
        }
        return check(nums, queries, r) ? r : -1;
    }

    private boolean check(int[] nums, int[][] queries, int m) {
        int n = nums.length;
        int[] b = new int[n + 1];
        for (int i = 0; i < m; i++) {
            int l = queries[i][0], r = queries[i][1], val = queries[i][2];
            b[l] -= val;
            b[r + 1] += val;
        }

        int s = 0;
        for (int i = 0; i < n; i++) {
            s += b[i];
            if (nums[i] + s > 0) return false;
        }
        return true;
    }
}