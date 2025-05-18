package practice;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: CountNumberofNiceSubarrays
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 1248. Count Number of Nice Subarrays
 */
public class LC1248_CountNumberofNiceSubarrays {
    /**
     * Given an array of integers nums and an integer k. A subarray is called nice if there are k odd numbers on it.
     *
     * Return the number of nice sub-arrays.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,1,2,1,1], k = 3
     * Output: 2
     * Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
     *
     * Example 2:
     *
     * Input: nums = [2,4,6], k = 1
     * Output: 0
     * Explanation: There is no odd numbers in the array.
     *
     * Example 3:
     *
     * Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
     * Output: 16
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 50000
     * 1 <= nums[i] <= 10^5
     * 1 <= k <= nums.length
     * @param nums
     * @param k
     * @return
     */
    // S1: Sliding window
    // time = O(n), space = O(n)
    public int numberOfSubarrays(int[] nums, int k) {
        // corner case --> 因为题目限制条件不存在
        int len = nums.length;
        int[] arr = new int[len + 2];
        int idx = 1;
        int res = 0;

        for (int i = 0; i < len; i++) {
            if (nums[i] % 2 == 1) arr[idx++] = i;
        }

        // left bound
        arr[0] = -1;

        // right bound
        arr[idx] = len;

        for (int i = 1; i < idx + 1 - k; i++) {
            res += (arr[i] - arr[i - 1]) * (arr[i + k] - arr[i + k - 1]);
        }
        return res;
    }
}
