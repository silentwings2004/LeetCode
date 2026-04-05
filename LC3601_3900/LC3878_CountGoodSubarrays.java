package LC3601_3900;
import java.util.*;
public class LC3878_CountGoodSubarrays {
    /**
     * You are given an integer array nums.
     *
     * A subarray is called good if the bitwise OR of all its elements is equal to at least one element present in that
     * subarray.
     *
     * Return the number of good subarrays in nums.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Here, the bitwise OR of two integers a and b is denoted by a | b.
     *
     * Input: nums = [4,2,3]
     * Output: 4
     *
     * Input: nums = [1,3,1]
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(nlogU), space = O(n + logU)  U: max(nums)
    public long countGoodSubarrays(int[] nums) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        List<int[]> q = new ArrayList<>();
        long res = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            map.put(x, i);
            for (int[] y : q) y[0] |= x;
            q.add(new int[]{x, i});
            int k = 0;
            for (int j = 1; j < q.size(); j++) {
                if (q.get(k)[0] != q.get(j)[0]) q.set(++k, q.get(j));
            }
            while (q.size() > k + 1) q.remove(q.size() - 1);
            int m = q.size();
            for (int j = 0; j < m; j++) {
                int v = q.get(j)[0], l = q.get(j)[1];
                int r = j == m - 1 ? i : q.get(j + 1)[1] - 1;
                if (map.containsKey(v)) {
                    r = Math.min(r, map.get(v));
                    res += Math.max(0, r - l + 1);
                }
            }
        }
        return res;
    }
}
/**
 * log trick
 * 暴力：枚举子数组右端点
 * 统计有多少个合法左端点
 * 性质 1：固定右端点，向左扩大子数组(减小左端点),子数组的 OR 是越来越大的
 * 性质 2：[3,3] 包含于[2,3] 包含于 [1,3] 包含于 [0,3]
 * 性质 3：固定右端点时，只有 O(logU)种不同的 OR，其中 U=max(nums)
 *        在整个计算过程中，一共只有O(nlogU)种不同的 OR
 *        把这些 OR 全部算出来，然后统计有多少个合法的
 * 性质 4：重复的 OR，一定是连续出现的
 *        每个 OR 对应一段范围
 *        右端点时固定的，范围指的是左端点的范围
 *        [4,9] [5,9] [6,9] 的子数组 OR 都是 7
 *        右端点为 9，左端点为 4，5，6 的时候，这三个子数组，每个子数组的 OR 都是 7
 * 对于本题
 * 用一个hashmap统计每个元素最近一次出现的位置
 * last[key] = value
 * key 是元素值
 * value 是 key 最近一次出现的位置
 * 总结：有重复计算的地方，就有优化空间，比如滑动窗口
 */