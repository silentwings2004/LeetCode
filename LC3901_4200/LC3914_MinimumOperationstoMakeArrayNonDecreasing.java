package LC3901_4200;

public class LC3914_MinimumOperationstoMakeArrayNonDecreasing {
    /**
     * You are given an integer array nums of length n.
     *
     * In one operation, you may choose any subarray nums[l..r] and increase each element in that subarray by x, where
     * x is any positive integer.
     *
     * Return the minimum possible sum of the values of x across all operations required to make the array non-decreasing.
     *
     * An array is non-decreasing if nums[i] <= nums[i + 1] for all 0 <= i < n - 1.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [3,3,2,1]
     * Output: 2
     *
     * Input: nums = [5,1,2,3]
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public long minOperations(int[] nums) {
        int n = nums.length;
        long res = 0;
        for (int i = 1; i < n; i++) res += Math.max(0, nums[i - 1] - nums[i]);
        return res;
    }
}