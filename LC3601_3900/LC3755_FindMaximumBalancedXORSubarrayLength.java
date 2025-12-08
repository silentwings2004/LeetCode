package LC3601_3900;
import java.util.*;
public class LC3755_FindMaximumBalancedXORSubarrayLength {
    /**
     * Given an integer array nums, return the length of the longest subarray that has a bitwise XOR of zero and
     * contains an equal number of even and odd numbers. If no such subarray exists, return 0.
     *
     * Input: nums = [3,1,3,2,0]
     * Output: 4
     *
     * Input: nums = [3,2,8,5,4,14,9,15]
     * Output: 8
     *
     * Input: nums = [0]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int maxBalancedSubarray(int[] nums) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put(0 + "#" + 0, -1);
        int n = nums.length, res = 0;
        for (int i = 0, s = 0, d = 0; i < n; i++) {
            s ^= nums[i];
            d += nums[i] % 2 == 0 ? 1 : -1;
            String h = s + "#" + d;
            if (map.containsKey(h)) res = Math.max(res, i - map.get(h));
            else map.put(h, i);
        }
        return res;
    }
}