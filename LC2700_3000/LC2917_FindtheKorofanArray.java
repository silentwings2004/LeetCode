package LC2700_3000;

public class LC2917_FindtheKorofanArray {
    /**
     * You are given a 0-indexed integer array nums, and an integer k.
     *
     * The K-or of nums is a non-negative integer that satisfies the following:
     *
     * The ith bit is set in the K-or if and only if there are at least k elements of nums in which bit i is set.
     * Return the K-or of nums.
     *
     * Note that a bit i is set in x if (2^i AND x) == 2^i, where AND is the bitwise AND operator.
     *
     * Input: nums = [7,12,9,8,9,15], k = 4
     * Output: 9
     *
     * Input: nums = [2,12,1,11,4,5], k = 6
     * Output: 0
     *
     * Input: nums = [10,8,5,9,11,6,8], k = 1
     * Output: 15
     *
     * Constraints:
     *
     * 1 <= nums.length <= 50
     * 0 <= nums[i] < 2^31
     * 1 <= k <= nums.length
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public int findKOr(int[] nums, int k) {
        int res = 0;
        for (int i = 30; i >= 0; i--) {
            int cnt = 0;
            for (int x : nums) {
                if ((x >> i & 1) == 1) cnt++;
            }
            if (cnt >= k) res |= 1 << i;
        }
        return res;
    }
}