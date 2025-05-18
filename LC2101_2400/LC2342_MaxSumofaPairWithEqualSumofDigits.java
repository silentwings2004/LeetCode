package LC2101_2400;
import java.util.*;
public class LC2342_MaxSumofaPairWithEqualSumofDigits {
    /**
     * You are given a 0-indexed array nums consisting of positive integers. You can choose two indices i and j, such
     * that i != j, and the sum of digits of the number nums[i] is equal to that of nums[j].
     *
     * Return the maximum value of nums[i] + nums[j] that you can obtain over all possible indices i and j that satisfy
     * the conditions.
     *
     * Input: nums = [18,43,36,13,7]
     * Output: 54
     *
     * Input: nums = [10,12,19,14]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int maximumSum(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = -1;
        for (int x : nums) {
            int s = 0, y = x;
            while (y > 0) {
                s += y % 10;
                y /= 10;
            }
            if (map.containsKey(s)) res = Math.max(res, x + map.get(s));
            map.put(s, Math.max(map.getOrDefault(s, 0), x));
        }
        return res;
    }
}