package LC3301_3600;

public class LC3423_MaximumDifferenceBetweenAdjacentElementsinaCircularArray {
    /**
     * Given a circular array nums, find the maximum absolute difference between adjacent elements.
     *
     * Note: In a circular array, the first and last elements are adjacent.
     *
     * Input: nums = [1,2,4]
     * Output: 3
     *
     * Input: nums = [-5,-10,-5]
     * Output: 5
     *
     * Constraints:
     *
     * 2 <= nums.length <= 100
     * -100 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int maxAdjacentDistance(int[] nums) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            if (i < n - 1) res = Math.max(res, Math.abs(nums[i + 1] - nums[i]));
            else res = Math.max(res, Math.abs(nums[0] - nums[n - 1]));
        }
        return res;
    }
}