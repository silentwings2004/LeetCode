package LC3901_4200;
import java.util.*;
public class LC4063_LongestSubarrayDivisiblebyKwithAtMostOneNegationI {
    /**
     * You are given an integer array nums and an integer k.
     *
     * A subarray is valid if its sum is divisible by k, or can become divisible by k by negating one element within
     * that subarray.
     *
     * Negating an element means replacing its value x with -x.
     *
     * Return the length of the longest valid subarray. If no valid subarray exists, return 0.
     *
     * A subarray is a contiguous, non-empty sequence of elements within an array.
     *
     * Input: nums = [4,1,2], k = 3
     * Output: 3
     *
     * Input: nums = [5,3,4], k = 7
     * Output: 2
     *
     * Input: nums = [2,2,5], k = 6
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * -10^5 <= nums[i] <= 10^5
     * 1 <= k <= 10^5
     * @param nums
     * @param k
     * @return
     */
    // time = O(n^2), space = O(n)
    public int longestSubarray(int[] nums, int k) {
        int res = helper(nums, k);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            nums[i] *= -1;
            res = Math.max(res, helper(nums, k));
            nums[i] *= -1;
        }
        return res;
    }

    private int helper(int[] nums, int k) {
        int n = nums.length, res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0, s = 0; i < n; i++) {
            s += nums[i];
            int r = (s % k + k) % k;
            if (map.containsKey(r)) res = Math.max(res, i - map.get(r));
            else map.put(r, i);
        }
        return res;
    }
}