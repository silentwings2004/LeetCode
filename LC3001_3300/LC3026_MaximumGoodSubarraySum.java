package LC3001_3300;
import java.util.*;
public class LC3026_MaximumGoodSubarraySum {
    /**
     * You are given an array nums of length n and a positive integer k.
     *
     * A subarray of nums is called good if the absolute difference between its first and last element is exactly k,
     * in other words, the subarray nums[i..j] is good if |nums[i] - nums[j]| == k.
     *
     * Return the maximum sum of a good subarray of nums. If there are no good subarrays, return 0.
     *
     * Input: nums = [1,2,3,4,5,6], k = 1
     * Output: 11
     *
     * Input: nums = [-1,3,2,4,5], k = 3
     * Output: 11
     *
     * Input: nums = [-1,-2,-3,-4], k = 2
     * Output: -6
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^5
     * -109 <= nums[i] <= 10^9
     * 1 <= k <= 10^9
     * @param nums
     * @param k
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + nums[i - 1];
        HashMap<Integer, int[]> map = new HashMap<>();
        long inf = (long)1e18, res = -inf;
        for (int i = 0; i < n; i++) {
            int a = nums[i] + k, b = nums[i] - k;
            if (map.containsKey(a)) {
                int x = map.get(a)[0], y = map.get(a)[1];
                if (x != n + 1) res = Math.max(res, s[i + 1] - s[x]);
                if (y != n + 1) res = Math.max(res, s[i + 1] - s[y]);
            }
            if (map.containsKey(b)) {
                int x = map.get(b)[0], y = map.get(b)[1];
                if (x != n + 1) res = Math.max(res, s[i + 1] - s[x]);
                if (y != n + 1) res = Math.max(res, s[i + 1] - s[y]);
            }
            if (!map.containsKey(nums[i])) map.put(nums[i], new int[]{i, n + 1});
            else {
                int x = map.get(nums[i])[0], y = map.get(nums[i])[1];
                if (s[i + 1] > s[x + 1]) {
                    map.get(nums[i])[0] = i;
                    if (y == n + 1) map.get(nums[i])[1] = x;
                } else {
                    if (y == n + 1 || s[i + 1] < s[y + 1]) map.get(nums[i])[1] = i;
                }
            }
        }
        return res == -inf ? 0 : res;
    }

    // S2
    // time = O(n), space = O(n)
    public long maximumSubarraySum2(int[] nums, int k) {
        HashMap<Integer, Long> map = new HashMap<>();
        long inf = (long)1e18;
        long s = 0, res = -inf;
        for (int x : nums) {
            if (map.containsKey(x)) map.put(x, Math.min(map.get(x), s));
            else map.put(x, s);
            s += x;
            if (map.containsKey(x - k)) res = Math.max(res, s - map.get(x - k));
            if (map.containsKey(x + k)) res = Math.max(res, s - map.get(x + k));
        }
        return res == -inf ? 0 : res;
    }
}
/**
 * 前缀和与哈希表
 * s[0] = 0
 * s[1] = nums[0]
 * s[2] = nums[0] + nums[1]
 * s[i+1] = nums[0] + ... + nums[i]
 * nums[i] + ... + nums[j] = s[j+1] - s[i]
 * 满足|nums[i] - nums[j]| == k
 * 计算s[j+1] - s[i] 的最大值
 * 枚举 j 问题变成计算 s[i] 的最小值
 * nums[i] = nums[j] - k 或者 nums[j] + k
 * 维护一个hashmap, key 是 nums[i], value 是 s[i] 的最小值
 */