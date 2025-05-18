package LC3301_3600;

public class LC3355_ZeroArrayTransformationI {
    /**
     * You are given an integer array nums of length n and a 2D array queries, where queries[i] = [li, ri].
     *
     * For each queries[i]:
     *
     * Select a subset of indices within the range [li, ri] in nums.
     * Decrement the values at the selected indices by 1.
     * A Zero Array is an array where all elements are equal to 0.
     *
     * Return true if it is possible to transform nums into a Zero Array after processing all the queries sequentially,
     * otherwise return false.
     *
     * A subset of an array is a selection of elements (possibly none) of the array.
     *
     * Input: nums = [1,0,1], queries = [[0,2]]
     * Output: true
     *
     * Input: nums = [4,3,2,1], queries = [[1,3],[0,2]]
     * Output: false
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^5
     * 1 <= queries.length <= 10^5
     * queries[i].length == 2
     * 0 <= li <= ri < nums.length
     * @param nums
     * @param queries
     * @return
     */
    // time = O(n), space = O(n)
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] b = new int[n + 1];
        for (int[] q : queries) {
            int l = q[0], r = q[1];
            b[l]--;
            b[r + 1]++;
        }

        int s = 0;
        for (int i = 0; i < n; i++) {
            s += b[i];
            if (nums[i] + s > 0) return false;
        }
        return true;
    }
}