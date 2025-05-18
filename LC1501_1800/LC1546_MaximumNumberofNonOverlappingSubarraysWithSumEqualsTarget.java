package LC1501_1800;
import java.util.*;
public class LC1546_MaximumNumberofNonOverlappingSubarraysWithSumEqualsTarget {
    /**
     * Given an array nums and an integer target, return the maximum number of non-empty non-overlapping subarrays such
     * that the sum of values in each subarray is equal to target.
     *
     * Input: nums = [1,1,1,1,1], target = 2
     * Output: 2
     *
     * Input: nums = [-1,3,5,1,4,2,-9], target = 6
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * -10^4 <= nums[i] <= 10^4
     * 0 <= target <= 10^6
     * @param nums
     * @param target
     * @return
     */
    // time = O(n), space = O(n)
    public int maxNonOverlapping(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int n = nums.length, res = 0, prev = 0;
        for (int i = 1, s = 0; i <= n; i++) {
            int t = prev;
            s += nums[i - 1];
            if (map.containsKey(s - target)) {
                t = Math.max(t, map.get(s - target) + 1);
            }
            res = Math.max(res, t);
            if (!map.containsKey(s) || map.get(s) < t) map.put(s, t);
            prev = t;
        }
        return res;
    }
}