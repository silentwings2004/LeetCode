package LC3601_3900;
import java.util.*;
public class LC3835_CountSubarraysWithCostLessThanorEqualtoK {
    /**
     * You are given an integer array nums, and an integer k.
     *
     * For any subarray nums[l..r], define its cost as:
     *
     * cost = (max(nums[l..r]) - min(nums[l..r])) * (r - l + 1).
     *
     * Return an integer denoting the number of subarrays of nums whose cost is less than or equal to k.
     *
     * Input: nums = [1,3,2], k = 4
     * Output: 5
     *
     * Input: nums = [5,5,5,5], k = 0
     * Output: 10
     *
     * Input: nums = [1,2,3], k = 0
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 0 <= k <= 10^15
     * @param nums
     * @param k
     * @return
     */
    // S1: Deque
    // time = O(n), space = O(n)
    public long countSubarrays(int[] nums, long k) {
        int n = nums.length;
        int[] dq1 = new int[n + 1];
        int[] dq2 = new int[n + 1];
        int hh1 = 0, hh2 = 0, tt1 = -1, tt2 = -1;
        long res = 0;
        for (int i = 0, j = 0; i < n; i++) {
            while (hh1 <= tt1 && nums[dq1[tt1]] <= nums[i]) tt1--;
            dq1[++tt1] = i;
            while (hh2 <= tt2 && nums[dq2[tt2]] >= nums[i]) tt2--;
            dq2[++tt2] = i;
            while (j <= i) {
                long mx = nums[dq1[hh1]];
                long mn = nums[dq2[hh2]];
                long cost = (mx - mn) * (i - j + 1);
                if (cost <= k) break;
                if (dq1[hh1] == j) hh1++;
                if (dq2[hh2] == j) hh2++;
                j++;
            }
            res += i - j + 1;
        }
        return res;
    }

    // S2: TreeMap
    // time = O(nlogn), space = O(n)
    public long countSubarrays2(int[] nums, long k) {
        int n = nums.length;
        long res = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0, j = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            while (1L * (map.lastKey() - map.firstKey()) * (i - j + 1) > k) {
                map.put(nums[j], map.get(nums[j]) - 1);
                if (map.get(nums[j]) == 0) map.remove(nums[j]);
                j++;
            }
            res += i - j + 1;
        }
        return res;
    }
}
/**
 * ref: LC2762
 * 核心：窗口越短越满足要求
 * 按右端点固定的时候，有多少个合法的左端点来分组
 * [L,R], [L+1,R]...[R,R] => R - L + 1 个
 *
 * 1. 连续的
 * 2. 子数组越长，约不满足题目要求
 *    子数组越短，越满足题目要求
 * RMQ, ST表，线段树
 * 滑动窗口最值 单调队列
 * 单调栈 = 元素在右边进进出出
 * 单调队列 = 单调栈+ 元素从左边出去
 */