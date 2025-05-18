package LC2700_3000;

public class LC2815_MaxPairSuminanArray {
    /**
     * You are given a 0-indexed integer array nums. You have to find the maximum sum of a pair of numbers from nums
     * such that the maximum digit in both numbers are equal.
     *
     * Return the maximum sum or -1 if no such pair exists.
     *
     * Input: nums = [51,71,17,24,42]
     * Output: 88
     *
     * Input: nums = [1,2,3,4]
     * Output: -1
     *
     * Constraints:
     *
     * 2 <= nums.length <= 100
     * 1 <= nums[i] <= 10^4
     * @param nums
     * @return
     */
    // time = O(n^2), space = O(1)
    public int maxSum(int[] nums) {
        int n = nums.length, res = -1;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] > res && check(nums[i], nums[j])) res = nums[i] + nums[j];
            }
        }
        return res;
    }

    private boolean check(int a, int b) {
        int s = 0, t = 0;
        while (a > 0) {
            s = Math.max(s, a % 10);
            a /= 10;
        }
        while (b > 0) {
            t = Math.max(t, b % 10);
            b /= 10;
        }
        return s == t;
    }
}