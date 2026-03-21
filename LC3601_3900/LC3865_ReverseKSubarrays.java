package LC3601_3900;

public class LC3865_ReverseKSubarrays {
    /**
     * You are given an integer array nums of length n and an integer k.
     *
     * You must partition the array into k contiguous subarrays of equal length and reverse each subarray.
     *
     * It is guaranteed that n is divisible by k.
     *
     * Return the resulting array after performing the above operation.
     *
     * Input: nums = [1,2,4,3,5,6], k = 3
     * Output: [2,1,3,4,6,5]
     *
     * Input: nums = [5,4,4,2], k = 1
     * Output: [2,4,4,5]
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 1000
     * 1 <= nums[i] <= 1000
     * 1 <= k <= n
     * n is divisible by k.
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = (1)
    public int[] reverseSubarrays(int[] nums, int k) {
        int n = nums.length, m = n / k;
        for (int i = 0; i < n; i += m) {
            int l = i, r = i + m - 1;
            while (l < r) {
                int t = nums[l];
                nums[l++] = nums[r];
                nums[r--] = t;
            }
        }
        return nums;
    }
}