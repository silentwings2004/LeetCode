package LC1501_1800;

public class LC1708_LargestSubarrayLengthK {
    /**
     * An array A is larger than some array B if for the first index i where A[i] != B[i], A[i] > B[i].
     *
     * For example, consider 0-indexing:
     *
     * [1,3,2,4] > [1,2,2,4], since at index 1, 3 > 2.
     * [1,4,4,4] < [2,1,1,1], since at index 0, 1 < 2.
     * A subarray is a contiguous subsequence of the array.
     *
     * Given an integer array nums of distinct integers, return the largest subarray of nums of length k.
     *
     * Input: nums = [1,4,5,2,3], k = 3
     * Output: [5,2,3]
     *
     * Input: nums = [1,4,5,2,3], k = 4
     * Output: [4,5,2,3]
     *
     * Input: nums = [1,4,5,2,3], k = 1
     * Output: [5]
     *
     * Constraints:
     *
     * 1 <= k <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * All the integers of nums are unique.
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public int[] largestSubarray(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[k];
        int pos = 0;
        for (int i = 0; i + k - 1 < n; i++) {
            if (nums[i] > nums[pos]) pos = i;
        }
        for (int i = pos, j = 0; j < k; i++, j++) res[j] = nums[i];
        return res;
    }
}
