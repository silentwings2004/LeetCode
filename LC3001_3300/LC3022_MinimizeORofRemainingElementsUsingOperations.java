package LC3001_3300;

public class LC3022_MinimizeORofRemainingElementsUsingOperations {
    /**
     * You are given a 0-indexed integer array nums and an integer k.
     *
     * In one operation, you can pick any index i of nums such that 0 <= i < nums.length - 1 and replace nums[i] and
     * nums[i + 1] with a single occurrence of nums[i] & nums[i + 1], where & represents the bitwise AND operator.
     *
     * Return the minimum possible value of the bitwise OR of the remaining elements of nums after applying at most k
     * operations.
     *
     * Input: nums = [3,5,3,2,7], k = 2
     * Output: 3
     *
     * Input: nums = [7,3,15,14,2,8], k = 4
     * Output: 2
     *
     * Input: nums = [10,7,10,3,9,14,9,4], k = 1
     * Output: 15
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] < 2^30
     * 0 <= k < nums.length
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public int minOrAfterOperations(int[] nums, int k) {
        int res = 0, mask = 0;
        for (int i = 29; i >= 0; i--) {
            mask |= 1 << i;
            int cnt = 0, t = -1;
            for (int x : nums) {
                t &= x & mask;
                if (t != 0) cnt++;
                else t = -1;
            }
            if (cnt > k) {
                res |= 1 << i;
                mask ^= 1 << i;
            }
        }
        return res;
    }
}