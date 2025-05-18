package LC2700_3000;

public class LC2770_MaximumNumberofJumpstoReachtheLastIndex {
    /**
     * You are given a 0-indexed array nums of n integers and an integer target.
     *
     * You are initially positioned at index 0. In one step, you can jump from index i to any index j such that:
     *
     * 0 <= i < j < n
     * -target <= nums[j] - nums[i] <= target
     * Return the maximum number of jumps you can make to reach index n - 1.
     *
     * If there is no way to reach index n - 1, return -1.
     *
     * Input: nums = [1,3,6,4,1,2], target = 2
     * Output: 3
     *
     * Input: nums = [1,3,6,4,1,2], target = 3
     * Output: 5
     *
     * Input: nums = [1,3,6,4,1,2], target = 0
     * Output: -1
     *
     * Constraints:
     *
     * 2 <= nums.length == n <= 1000
     * -10^9 <= nums[i] <= 10^9
     * 0 <= target <= 2 * 10^9
     * @param nums
     * @param target
     * @return
     */
    // time = O(n^2), space = O(n)
    public int maximumJumps(int[] nums, int target) {
        int n = nums.length;
        int[] s = new int[n];
        for (int i = 0; i < n; i++) {
            if (i > 0 && s[i] == 0) continue;
            long a = (long)nums[i] - target, b = (long)nums[i] + target;
            for (int j = i + 1; j < n; j++) {
                if (nums[j] >= a && nums[j] <= b) s[j] = Math.max(s[j], s[i] + 1);
            }
        }
        return s[n - 1] == 0 ? -1 : s[n - 1];
    }
}