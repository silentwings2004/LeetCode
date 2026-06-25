package LC3901_4200;
import java.util.*;
public class LC3962_MaximumSubarraySumAfteratMostKSwaps {
    /**
     * You are given an integer array nums and an integer k.
     *
     * You are allowed to perform at most k swap operations on the array.
     *
     * In one swap operation, you may choose any two indices i and j and swap nums[i] and nums[j].
     *
     * Return an integer denoting the maximum possible subarray sum after performing the swaps.
     *
     * A subarray is a contiguous sequence of elements within an array.
     *
     * Input: nums = [1,-1,0,2], k = 1
     * Output: 3
     *
     * Input: nums = [4,3,2,4], k = 2
     * Output: 13
     *
     * Input: nums = [-1,-2], k = 0
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1500
     * -10^5 <= nums[i] <= 10^5
     * 0 <= k <= nums.length
     * @param nums
     * @param k
     * @return
     */
    // time = O(n^2 * logn), space = O(n)
    final long inf = (long)1E18;
    public long maxSum(int[] nums, int k) {
        long res = -inf;
        int[] w = nums.clone();
        Arrays.sort(w);

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            long m = 0;
            TreeMap<Integer, Integer> cand = new TreeMap<>();
            TreeMap<Integer, Integer> other = new TreeMap<>();
            for (int j = 0; j < n - k; j++) other.put(w[j], other.getOrDefault(w[j], 0) + 1);
            for (int j = n - k; j < n; j++) cand.put(w[j], cand.getOrDefault(w[j], 0) + 1);
            for (int j = i; j < n; j++) {
                if (other.size() > 0) {
                    int key = other.containsKey(nums[j]) ? nums[j] : other.lastKey();
                    int val = other.get(key);
                    cand.put(key, cand.getOrDefault(key, 0) + 1);
                    if (val == 1) other.remove(key);
                    else other.put(key, other.get(key) - 1);
                }
                m += cand.lastKey();
                int key = cand.lastKey();
                int val = cand.get(key);
                if (val == 1) cand.remove(key);
                else cand.put(key, val - 1);
                res = Math.max(res, m);
            }
        }
        return res;
    }
}
/**
 * 必须精确的计算具体要交换多少次
 * 子数组里的前 k 小，子数组外的前 k 大 => 数据结构：树状数组，对顶堆
 * 二分第 k 小，有个时刻里面的第 k 小 >= 外面的第 k 大，所以可以二分 => 优化：实际不需要二分
 * 不会有任何突变
 * 1. 要么不变
 * 2. 多换 1 次
 * 3. 少换 1 次
 * 子数组里的第 k 小是否 < 外面的第 k 大
 */