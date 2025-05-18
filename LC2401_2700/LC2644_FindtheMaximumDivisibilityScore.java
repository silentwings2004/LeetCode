package LC2401_2700;

public class LC2644_FindtheMaximumDivisibilityScore {
    /**
     * You are given two 0-indexed integer arrays nums and divisors.
     *
     * The divisibility score of divisors[i] is the number of indices j such that nums[j] is divisible by divisors[i].
     *
     * Return the integer divisors[i] with the maximum divisibility score. If there is more than one integer with the
     * maximum score, return the minimum of them.
     *
     * Input: nums = [4,7,9,3,9], divisors = [5,2,3]
     * Output: 3
     *
     * Input: nums = [20,14,21,10], divisors = [5,7,5]
     * Output: 5
     *
     * Input: nums = [12], divisors = [10,16]
     * Output: 10
     *
     * Constraints:
     *
     * 1 <= nums.length, divisors.length <= 1000
     * 1 <= nums[i], divisors[i] <= 10^9
     * @param nums
     * @param divisors
     * @return
     */
    // time = O(m * n), space = O(1)
    public int maxDivScore(int[] nums, int[] divisors) {
        int res = Integer.MAX_VALUE, maxv = 0;
        for (int x : divisors) {
            int cnt = 0;
            for (int y : nums) {
                if (y % x == 0) cnt++;
            }
            if (cnt > maxv) {
                maxv = cnt;
                res = x;
            } else if (cnt == maxv && x < res) res = x;
        }
        return res;
    }
}