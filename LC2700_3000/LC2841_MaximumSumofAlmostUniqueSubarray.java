package LC2700_3000;
import java.util.*;
public class LC2841_MaximumSumofAlmostUniqueSubarray {
    /**
     * You are given an integer array nums and two positive integers m and k.
     *
     * Return the maximum sum out of all almost unique subarrays of length k of nums. If no such subarray exists,
     * return 0.
     *
     * A subarray of nums is almost unique if it contains at least m distinct elements.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [2,6,7,3,1,7], m = 3, k = 4
     * Output: 18
     *
     * Input: nums = [5,9,9,2,4,5,4], m = 1, k = 3
     * Output: 23
     *
     * Input: nums = [1,2,1,2,1,2,1], m = 3, k = 3
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 2 * 10^4
     * 1 <= m <= k <= nums.length
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @param m
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public long maxSum(List<Integer> nums, int m, int k) {
        int n = nums.size();
        HashMap<Integer, Integer> map = new HashMap<>();
        long res = 0, s = 0;
        for (int i = 0, j = 0; i < n; i++) {
            int x = nums.get(i);
            s += x;
            map.put(x, map.getOrDefault(x, 0) + 1);
            if (i >= k - 1) {
                if (map.size() >= m) res = Math.max(res, s);
                int y = nums.get(j++);
                s -= y;
                map.put(y, map.get(y) - 1);
                if (map.get(y) == 0) map.remove(y);
            }
        }
        return res;
    }
}
/**
 * 长度固定的子数组 => 滑动窗口
 */