package LC3601_3900;

public class LC3708_LongestFibonacciSubarray {
    /**
     * You are given an array of positive integers nums.
     *
     * A Fibonacci array is a contiguous sequence whose third and subsequent terms each equal the sum of the two
     * preceding terms.
     *
     * Return the length of the longest Fibonacci subarray in nums.
     *
     * Note: Subarrays of length 1 or 2 are always Fibonacci.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,1,1,1,2,3,5,1]
     *
     * Output: 5
     *
     * Input: nums = [5,2,7,9,16]
     *
     * Output: 5
     *
     * Input: nums = [1000000000,1000000000,1000000000]
     *
     * Output: 2
     *
     * Constraints:
     *
     * 3 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int longestSubarray(int[] nums) {
        int n = nums.length, j = 0, res = 0;
        for (int i = 2; i < n; i++) {
            if (nums[i] == nums[i - 1] + nums[i - 2]) continue;
            res = Math.max(res, i - j);
            j = i - 1;
        }
        res = Math.max(res, n - j);
        return res;
    }
}