package LC2401_2700;
import java.util.*;
public class LC2461_MaximumSumofDistinctSubarraysWithLengthK {
    /**
     * You are given an integer array nums and an integer k. Find the maximum subarray sum of all the subarrays of nums
     * that meet the following conditions:
     *
     * The length of the subarray is k, and
     * All the elements of the subarray are distinct.
     * Return the maximum subarray sum of all the subarrays that meet the conditions. If no subarray meets the
     * conditions, return 0.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,5,4,2,9,9,9], k = 3
     * Output: 15
     *
     * Input: nums = [4,4,4], k = 3
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= k <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @param k
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + nums[i - 1];

        long res = 0;
        for (int i = 0, j = 0; i < n; i++) {
            if (map.containsKey(nums[i])) j = Math.max(j, map.get(nums[i]) + 1);
            j = Math.max(j, i - k + 1);
            if (i - j + 1 == k) res = Math.max(res, s[i + 1] - s[j]);
            map.put(nums[i], i);
        }
        return res;
    }

    // S2: Two Pointers
    // time = O(n), space = O(n)
    public long maximumSubarraySum2(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        long sum = 0, res = 0;
        for (int i = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            sum += nums[i];

            if (i >= k) {
                map.put(nums[i - k], map.get(nums[i - k]) - 1);
                if (map.get(nums[i - k]) == 0) map.remove(nums[i - k]);
                sum -= nums[i - k];
            }
            if (i >= k - 1) {
                if (map.size() == k) res = Math.max(res, sum);
            }
        }
        return res;
    }

    // S3
}
