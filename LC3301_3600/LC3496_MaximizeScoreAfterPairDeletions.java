package LC3301_3600;

public class LC3496_MaximizeScoreAfterPairDeletions {
    /**
     * You are given an array of integers nums. You must repeatedly perform one of the following operations while the
     * array has more than two elements:
     *
     * Remove the first two elements.
     * Remove the last two elements.
     * Remove the first and last element.
     * For each operation, add the sum of the removed elements to your total score.
     *
     * Return the maximum possible score you can achieve.
     *
     * Input: nums = [2,4,1]
     * Output: 6
     *
     * Input: nums = [5,-1,4,2]
     * Output: 7
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * -10^4 <= nums[i] <= 10^4
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int maxScore(int[] nums) {
        int n = nums.length, s = 0, mn = Integer.MAX_VALUE, ms = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            s += nums[i];
            mn = Math.min(mn, nums[i]);
            if (i + 1 < n) ms = Math.min(ms, nums[i] + nums[i + 1]);
        }
        return n % 2 == 1 ? s - mn : s - ms;
    }
}