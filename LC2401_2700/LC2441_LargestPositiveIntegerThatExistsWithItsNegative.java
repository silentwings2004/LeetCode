package LC2401_2700;
import java.util.*;
public class LC2441_LargestPositiveIntegerThatExistsWithItsNegative {
    /**
     * Given an integer array nums that does not contain any zeros, find the largest positive integer k such that -k
     * also exists in the array.
     *
     * Return the positive integer k. If there is no such integer, return -1.
     *
     * Input: nums = [-1,2,-3,3]
     * Output: 3
     *
     * Input: nums = [-1,10,6,7,-7,1]
     * Output: 7
     *
     * Input: nums = [-10,8,6,7,-2,-3]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * -1000 <= nums[i] <= 1000
     * nums[i] != 0
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int findMaxK(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int x : nums) set.add(x);

        int res = -1;
        for (int x : nums) {
            if (x > 0 && set.contains(-x)) res = Math.max(res, x);
        }
        return res;
    }
}