package LC3601_3900;

public class LC3674_MinimumOperationstoEqualizeArray {
    /**
     * You are given an integer array nums of length n.
     *
     * In one operation, choose any subarray nums[l...r] (0 <= l <= r < n) and replace each element in that subarray
     * with the bitwise AND of all elements.
     *
     * Return the minimum number of operations required to make all elements of nums equal.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,2]
     * Output: 1
     *
     * Input: nums = [5,5,5]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 100
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int minOperations(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            if (nums[i] != nums[i - 1]) return 1;
        }
        return 0;
    }
}