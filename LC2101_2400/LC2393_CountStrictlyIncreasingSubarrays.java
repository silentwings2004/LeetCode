package LC2101_2400;

public class LC2393_CountStrictlyIncreasingSubarrays {
    /**
     * You are given an array nums consisting of positive integers.
     *
     * Return the number of subarrays of nums that are in strictly increasing order.
     *
     * A subarray is a contiguous part of an array.
     *
     * Input: nums = [1,3,5,4,4,6]
     * Output: 10
     *
     * Input: nums = [1,2,3,4,5]
     * Output: 15
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^6
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public long countSubarrays(int[] nums) {
        int n = nums.length;
        long res = 0;
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && nums[j] > nums[j - 1]) j++;
            int m = j - i;
            res += (long)(1 + m) * m / 2;
            i = j - 1;
        }
        return res;
    }
}
