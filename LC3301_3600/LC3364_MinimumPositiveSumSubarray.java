package LC3301_3600;
import java.util.*;
public class LC3364_MinimumPositiveSumSubarray {
    /**
     * You are given an integer array nums and two integers l and r. Your task is to find the minimum sum of a subarray
     * whose size is between l and r (inclusive) and whose sum is greater than 0.
     *
     * Return the minimum sum of such a subarray. If no such subarray exists, return -1.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [3, -2, 1, 4], l = 2, r = 3
     * Output: 1
     *
     * Input: nums = [-2, 2, -3, 1], l = 2, r = 3
     * Output: -1
     *
     * Input: nums = [1, 2, 3, 4], l = 2, r = 4
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= l <= r <= nums.length
     * -1000 <= nums[i] <= 1000
     * @param nums
     * @param l
     * @param r
     * @return
     */
    // S1: brute-force
    // time = O(n^2), space = O(1)
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size(), res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int s = 0;
            for (int j = i; j < n; j++) {
                s += nums.get(j);
                int len = j - i + 1;
                if (s > 0 && len >= l && len <= r) res = Math.min(res, s);
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    // S2: TreeMap
    // time = O(nlogn), space = O(n)
    public int minimumSumSubarray2(List<Integer> nums, int l, int r) {
        int n = nums.size();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + nums.get(i - 1);
        int res = Integer.MAX_VALUE;
        for (int i = l; i <= n; i++) {
            map.put(s[i - l], map.getOrDefault(s[i - l], 0) + 1);
            Integer lk = map.lowerKey(s[i]);
            if (lk != null) res = Math.min(res, s[i] - lk);
            if (i - r >= 0) {
                map.put(s[i - r], map.get(s[i - r]) - 1);
                if (map.get(s[i - r]) == 0) map.remove(s[i - r]);
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}