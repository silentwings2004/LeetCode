package LC3601_3900;
import java.util.*;
public class LC3719_LongestBalancedSubarrayI {
    /**
     * You are given an integer array nums.
     *
     * A subarray is called balanced if the number of distinct even numbers in the subarray is equal to the number of
     * distinct odd numbers.
     *
     * Return the length of the longest balanced subarray.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [2,5,4,3]
     * Output: 4
     *
     * Input: nums = [3,2,2,5,4]
     * Output: 5
     *
     * Input: nums = [1,2,3,2]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1500
     * 1 <= nums[i] <= 10^5
     *
     * @param nums
     * @return
     */
    // time = O(n^2), space = O(n)
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        for (int len = n; len >= 0; len--) {
            HashMap<Integer, Integer> mp1 = new HashMap<>();
            HashMap<Integer, Integer> mp2 = new HashMap<>();
            for (int i = 0, j = 0; i < n; i++) {
                int x = nums[i];
                if (x % 2 == 1) mp1.put(x, mp1.getOrDefault(x, 0) + 1);
                else mp2.put(x, mp2.getOrDefault(x, 0) + 1);
                if (i - j + 1 == len) {
                    if (mp1.size() == mp2.size()) return len;
                    int y = nums[j++];
                    if (y % 2 == 1) {
                        mp1.put(y, mp1.get(y) - 1);
                        if (mp1.get(y) == 0) mp1.remove(y);
                    } else {
                        mp2.put(y, mp2.get(y) - 1);
                        if (mp2.get(y) == 0) mp2.remove(y);
                    }
                }
            }
        }
        return 0;
    }
}