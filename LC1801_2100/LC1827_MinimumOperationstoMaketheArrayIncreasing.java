package LC1801_2100;

public class LC1827_MinimumOperationstoMaketheArrayIncreasing {
    /**
     * You are given an integer array nums (0-indexed). In one operation, you can choose an element of the array and
     * increment it by 1.
     *
     * For example, if nums = [1,2,3], you can choose to increment nums[1] to make nums = [1,3,3].
     * Return the minimum number of operations needed to make nums strictly increasing.
     *
     * An array nums is strictly increasing if nums[i] < nums[i+1] for all 0 <= i < nums.length - 1. An array of length
     * 1 is trivially strictly increasing.
     *
     * Input: nums = [1,1,1]
     * Output: 3
     *
     * Input: nums = [1,5,2,4,1]
     * Output: 14
     *
     * Input: nums = [8]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 5000
     * 1 <= nums[i] <= 10^4
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int minOperations(int[] nums) {
        int n = nums.length, res = 0;
        if (n == 1) return 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) continue;
            res += nums[i - 1] - nums[i] + 1;
            nums[i] = nums[i - 1] + 1;
        }
        return res;
    }
}
