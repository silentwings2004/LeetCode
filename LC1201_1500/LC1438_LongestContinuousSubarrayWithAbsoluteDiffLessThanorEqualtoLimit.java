package LC1201_1500;
import java.util.*;
public class LC1438_LongestContinuousSubarrayWithAbsoluteDiffLessThanorEqualtoLimit {
    /**
     * Given an array of integers nums and an integer limit, return the size of the longest non-empty subarray such that
     * the absolute difference between any two elements of this subarray is less than or equal to limit.
     *
     * Input: nums = [8,2,4,7], limit = 4
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * 0 <= limit <= 10^9
     * @param nums
     * @param limit
     * @return
     */
    // S1: TreeMap
    // time = O(nlogn), space = O(n)
    public int longestSubarray(int[] nums, int limit) {
        int n = nums.length, res = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0, j = 0; i < n; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            while (map.lastKey() - map.firstKey() > limit) {
                map.put(nums[j], map.get(nums[j]) - 1);
                if (map.get(nums[j]) == 0) map.remove(nums[j]);
                j++;
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }

    // S2: deque
    // time = O(n), space = O(n)
    public int longestSubarray2(int[] nums, int limit) {
        int n = nums.length, res = 0;
        int[] q1 = new int[n + 1], q2 = new int[n + 1];
        int hh1 = 0, tt1 = -1, hh2 = 0, tt2 = -1;

        for (int i = 0, j = 0; i < n; i++) {
            while (hh1 <= tt1 && nums[q1[tt1]] <= nums[i]) tt1--;
            q1[++tt1] = i;

            while (hh2 <= tt2 && nums[q2[tt2]] >= nums[i]) tt2--;
            q2[++tt2] = i;

            int a = nums[q1[hh1]], b = nums[q2[hh2]];
            while (a - b > limit) {
                if (q1[hh1] == j) hh1++;
                if (q2[hh2] == j) hh2++;
                a = nums[q1[hh1]];
                b = nums[q2[hh2]];
                j++;
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}
/**
 * xx v x p xxx v xx
 * xxxxxxxxxx
 * ^   ^
 * 为什么左端点移动后，右端点不归0
 * 因为我们要求的是个longest subarray
 * res = k
 * 左端点移动后，已经只有k-1，而我们res的长度至少为k，所以没必要让j回到i再从头开始
 * => 右端点不用回退，因为没有意义
 * 左右端点都是单向，O(n)
 * 如何高效求出这个区间里的最大和最小值
 * 最naive的做法就是全部遍历一遍 => O(n^2)
 * 如果是自动排序的容器 => O(nlogn)
 * 能不能用O(1) => deque 来实现一个单调队列，专治sliding window maximum/minimum => diff <= limit
 * 大框架双指针，本质也是个sliding window maximum
 */