package LC3601_3900;

public class LC3818_MinimumPrefixRemovaltoMakeArrayStrictlyIncreasing {
    /**
     * You are given an integer array nums.
     *
     * You need to remove exactly one prefix (possibly empty) from nums.
     *
     * Return an integer denoting the minimum length of the removed prefix such that the remaining array is strictly
     * increasing.
     *
     * Input: nums = [1,-1,2,3,3,4,5]
     * Output: 4
     *
     * Input: nums = [4,3,-2,-5]
     * Output: 3
     *
     * Input: nums = [1,2,3,4]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * -10^9 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int minimumPrefixLength(int[] nums) {
        int n = nums.length, res = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i + 1] > nums[i]) continue;
            res = i + 1;
            break;
        }
        return res;
    }
}