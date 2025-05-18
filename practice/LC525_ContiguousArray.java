package practice;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ContiguousArray
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 525. Contiguous Array
 */
public class LC525_ContiguousArray {
    /**
     * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
     *
     * Example 1:
     * Input: [0,1]
     * Output: 2
     * Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
     * Example 2:
     * Input: [0,1,0]
     * Output: 2
     * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
     * Note: The length of the given binary array will not exceed 50,000.
     * @param nums
     * @return
     */
    // S1: brute- force (Time Limit Exceeded!!!)
    // time = O(n^2), space = O(1)
    public int findMaxLength(int[] nums) {
        // corner case
        if (nums == null || nums.length < 2) return 0;

        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int zero = 0, one = 0;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] == 0) zero++;
                else one++;
                if (zero == one) max = Math.max(max, j - i + 1);
            }
        }
        return max;
    }

    // S2: HashMap
    // time = O(n), space = O(n)
    public int findMaxLength2(int[] nums) {
        // corner case
        if (nums == null || nums.length < 2) return 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int max = 0;
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            count += (nums[i] == 0 ? -1 : 1);
            if (map.containsKey(count)) {
                max = Math.max(max, i - map.get(count));
            } else map.put(count, i);
        }
        return max;
    }
}
