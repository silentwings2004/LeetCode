package LC3601_3900;
import java.util.*;
public class LC3859_CountSubarraysWithKDistinctIntegers {
    /**
     * You are given an integer array nums and two integers k and m.
     *
     * Return an integer denoting the count of subarrays of nums such that:
     *
     * The subarray contains exactly k distinct integers.
     * Within the subarray, each distinct integer appears at least m times.
     *
     * Input: nums = [1,2,1,2,2], k = 2, m = 2
     * Output: 2
     *
     * Input: nums = [3,1,2,4], k = 2, m = 1
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * 1 <= k, m <= nums.length
     * @param nums
     * @param k
     * @param m
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public long countSubarrays(int[] nums, int k, int m) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        long res = 0;
        for (int i = 0, j = 0, v = 0, extra = 0; i < n; i++) {
            int x = nums[i];
            map.put(x, map.getOrDefault(x, 0) + 1);
            if (map.get(x) == m) v++;
            while (map.size() > k) {
                int y = nums[j++];
                if (map.get(y) == m) v--;
                map.put(y, map.get(y) - 1);
                if (map.get(y) == 0) map.remove(y);
                extra = 0;
            }
            if (map.size() == k && v == k) {
                while (map.getOrDefault(nums[j], 0) > m) {
                    map.put(nums[j], map.get(nums[j]) - 1);
                    if (map.get(nums[j]) == 0) map.remove(nums[j]);
                    j++;
                    extra++;
                }
                res += extra + 1;
            }
        }
        return res;
    }

    // S2： 恰好型滑窗
    // time = O(n), space = O(n)
    public long countSubarrays2(int[] nums, int k, int m) {
        return cal(nums, k, k, m) - cal(nums, k + 1, k, m);
    }

    private long cal(int[]nums, int dl, int k, int m) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        long res = 0;
        for (int i = 0, j = 0, cnt = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            if (map.get(nums[i]) == m) cnt++;
            while (map.size() >= dl && cnt >= k) {
                int y = nums[j++];
                if (map.get(y) == m) cnt--;
                map.put(y, map.get(y) - 1);
                if (map.get(y) == 0) map.remove(y);
            }
            res += j; // 越长越合法
        }
        return res;
    }
}
/**
 * 转化成2个至少的问题
 * “越长越合法”
 * 子数组至少包含k个不同的整数
 * 在子数组中，每个不同整数至少出现 m 次
 */