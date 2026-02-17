package LC3601_3900;

public class LC3840_HouseRobberV {
    /**
     * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money
     * stashed and is protected by a security system with a color code.
     *
     * You are given two integer arrays nums and colors, both of length n, where nums[i] is the amount of money in the
     * ith house and colors[i] is the color code of that house.
     *
     * You cannot rob two adjacent houses if they share the same color code.
     *
     * Return the maximum amount of money you can rob.
     *
     * Input: nums = [1,4,3,5], colors = [1,1,2,2]
     * Output: 9
     *
     * Input: nums = [3,1,2,4], colors = [2,3,2,2]
     * Output: 8
     *
     * Input: nums = [10,1,3,9], colors = [1,1,1,2]
     * Output: 22
     *
     * Constraints:
     *
     * 1 <= n == nums.length == colors.length <= 10^5
     * 1 <= nums[i], colors[i] <= 10^5
     * @param nums
     * @param colors
     * @return
     */
    // time = O(n), space = O(1)
    public long rob(int[] nums, int[] colors) {
        int n = nums.length;
        long[][] f = new long[2][2];
        f[0][1] = nums[0];
        for (int i = 1; i < n; i++) {
            f[i & 1][0] = Math.max(f[i - 1 & 1][0], f[i - 1 & 1][1]);
            f[i & 1][1] = f[i - 1 & 1][0] + nums[i];
            if (colors[i] != colors[i - 1]) f[i & 1][1] = Math.max(f[i & 1][1], f[i - 1 & 1][1] + nums[i]);
        }
        return Math.max(f[n - 1 & 1][0], f[n - 1 & 1][1]);
    }
}