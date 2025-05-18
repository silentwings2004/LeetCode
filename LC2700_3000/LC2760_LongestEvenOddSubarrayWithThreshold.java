package LC2700_3000;

public class LC2760_LongestEvenOddSubarrayWithThreshold {
    /**
     * You are given a 0-indexed integer array nums and an integer threshold.
     *
     * Find the length of the longest subarray of nums starting at index l and ending at index r
     * (0 <= l <= r < nums.length) that satisfies the following conditions:
     *
     * nums[l] % 2 == 0
     * For all indices i in the range [l, r - 1], nums[i] % 2 != nums[i + 1] % 2
     * For all indices i in the range [l, r], nums[i] <= threshold
     * Return an integer denoting the length of the longest such subarray.
     *
     * Note: A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [3,2,5,4], threshold = 5
     * Output: 3
     *
     * Input: nums = [1,2], threshold = 2
     * Output: 1
     *
     * Input: nums = [2,3,4,5], threshold = 4
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * 1 <= threshold <= 100
     * @param nums
     * @param threshold
     * @return
     */
    // time = O(n), space = O(1)
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] % 2 == 0 && nums[i] <= threshold) {
                int j = i;
                while (j + 1 < n && nums[j] % 2 != nums[j + 1] % 2 && nums[j + 1] <= threshold) j++;
                res = Math.max(res, j - i + 1);
                i = j;
            }
        }
        return res;
    }
}