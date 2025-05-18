package LC1801_2100;
import java.util.*;
public class LC1814_CountNicePairsinanArray {
    /**
     * You are given an array nums that consists of non-negative integers. Let us define rev(x) as the reverse of the
     * non-negative integer x. For example, rev(123) = 321, and rev(120) = 21. A pair of indices (i, j) is nice if it
     * satisfies all of the following conditions:
     *
     * 0 <= i < j < nums.length
     * nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
     * Return the number of nice pairs of indices. Since that number can be too large, return it modulo 10^9 + 7.
     *
     * Input: nums = [13,10,35,24,76]
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int countNicePairs(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int t : nums) {
            int x = t;
            String s = String.valueOf(x);
            StringBuilder sb = new StringBuilder(s);
            s = sb.reverse().toString();
            int y = Integer.parseInt(s);
            map.put(x - y, map.getOrDefault(x - y, 0) + 1);
        }

        long mod = (long)(1e9 + 7), res = 0;
        for (int v : map.values()) {
            res = (res + (long) v * (v - 1) / 2) % mod;
        }
        return (int) res;
    }
}
/**
 * nums[i] + rev(nums[j]) == nums[j] + rev(nums[i]) =>
 * nums[i] - rev(nums[i] = nums[j] - rev(nums[j])
 */
