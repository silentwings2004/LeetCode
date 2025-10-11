package LC3601_3900;

public class LC3702_LongestSubsequenceWithNonZeroBitwiseXOR {
    /**
     * You are given an integer array nums.
     *
     * Return the length of the longest subsequence in nums whose bitwise XOR is non-zero. If no such subsequence
     * exists, return 0.
     *
     * A subsequence is a non-empty array that can be derived from another array by deleting some or no elements
     * without changing the order of the remaining elements.
     *
     * Input: nums = [1,2,3]
     * Output: 2
     *
     * Input: nums = [2,3,4]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int longestSubsequence(int[] nums) {
        int n = nums.length, t = 0;
        boolean f = false;
        for (int x : nums) {
            t ^= x;
            if (x != 0) f = true;
        }
        if (t != 0) return n;
        return f ? n - 1 : 0;
    }
}