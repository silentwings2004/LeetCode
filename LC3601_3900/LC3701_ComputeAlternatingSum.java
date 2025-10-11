package LC3601_3900;

public class LC3701_ComputeAlternatingSum {
    /**
     * You are given an integer array nums.
     *
     * The alternating sum of nums is the value obtained by adding elements at even indices and subtracting elements at
     * odd indices. That is, nums[0] - nums[1] + nums[2] - nums[3]...
     *
     * Return an integer denoting the alternating sum of nums.
     *
     * Input: nums = [1,3,5,7]
     *
     * Output: -4
     *
     * Input: nums = [100]
     *
     * Output: 100
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int alternatingSum(int[] nums) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) res += nums[i];
            else res -= nums[i];
        }
        return res;
    }
}