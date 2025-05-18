package practice;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: SubarraySumEqualsK
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 560. Subarray Sum Equals K
 */
public class LC560_SubarraySumEqualsK {
    /**
     * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum
     * equals to k.
     *
     * Example 1:
     * Input:nums = [1,1,1], k = 2
     * Output: 2
     * Note:
     * The length of the array is in range [1, 20,000].
     * The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
     * @param nums
     * @param k
     * @return
     */
    // S1: for loop
    // time = O(n^2), space = O(1)
    public int subarraySum(int[] nums, int k) {
        // corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) count++;
            }
        }
        return count;
    }

    // S2: HashMap
    // time = O(n), space = O(n)
    public int subarraySum2(int[] nums, int k) {
        // corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int count = 0, sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int n : nums) {
            sum += n;
            count += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
