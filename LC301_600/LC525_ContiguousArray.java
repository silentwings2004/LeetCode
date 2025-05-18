package LC301_600;
import java.util.*;
public class LC525_ContiguousArray {
    /**
     * Given a binary array nums, return the maximum length of a contiguous subarray with an equal number of 0 and 1.
     *
     * Input: nums = [0,1]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * nums[i] is either 0 or 1.
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int findMaxLength(int[] nums) {
        int n = nums.length, res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0, sum = 0; i < n; i++) {
            sum += nums[i] == 0 ? -1 : 1;
            if (map.containsKey(sum)) res = Math.max(res, i - map.get(sum));
            else map.put(sum, i);
        }
        return res;
    }
}