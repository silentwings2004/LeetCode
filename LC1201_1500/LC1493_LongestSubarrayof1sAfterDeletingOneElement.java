package LC1201_1500;

public class LC1493_LongestSubarrayof1sAfterDeletingOneElement {
    /**
     * Given a binary array nums, you should delete one element from it.
     *
     * Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there
     * is no such subarray.
     *
     * Input: nums = [1,1,0,1]
     * Output: 3
     *
     * Input: nums = [0,1,1,1,0,1,1,0,1]
     * Output: 5
     *
     * Input: nums = [1,1,1]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * nums[i] is either 0 or 1.
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int longestSubarray(int[] nums) {
        int n = nums.length, res = 0;
        for (int i = 0, j = 0, s = 0; i < n; i++) {
            if (nums[i] == 0) s++;
            while (s > 1) s -= nums[j++] == 0 ? 1 : 0;
            res = Math.max(res, i - j);
        }
        return res;
    }
}