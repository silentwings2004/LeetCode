package LC3001_3300;

public class LC3300_MinimumElementAfterReplacementWithDigitSum {
    /**
     * You are given an integer array nums.
     *
     * You replace each element in nums with the sum of its digits.
     *
     * Return the minimum element in nums after all replacements.
     *
     * Input: nums = [10,12,13,14]
     * Output: 1
     *
     * Input: nums = [1,2,3,4]
     * Output: 1
     *
     * Input: nums = [999,19,199]
     * Output: 10
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 10^4
     * @param nums
     * @return
     */
    // time = O(nlogk), space = O(1)
    public int minElement(int[] nums) {
        int res = 0x3f3f3f3f;
        for (int x : nums) {
            int t = 0;
            while (x > 0) {
                t += x % 10;
                x /= 10;
            }
            res = Math.min(res, t);
        }
        return res;
    }
}