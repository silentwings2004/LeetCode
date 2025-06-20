package LC3301_3600;
import java.util.*;
public class LC3578_CountPartitionsWithMaxMinDifferenceatMostK {
    /**
     * You are given an integer array nums and an integer k. Your task is to partition nums into one or more non-empty
     * contiguous segments such that in each segment, the difference between its maximum and minimum elements is at most
     * k.
     *
     * Return the total number of ways to partition nums under this condition.
     * Since the answer may be too large, return it modulo 10^9 + 7.
     *
     * Input: nums = [9,4,1,3,7], k = 4
     * Output: 6
     *
     * Input: nums = [3,3,4], k = 0
     * Output: 2
     *
     * Constraints:
     *
     * 2 <= nums.length <= 5 * 10^4
     * 1 <= nums[i] <= 10^9
     * 0 <= k <= 10^9
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public int countPartitions(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> minQ = new LinkedList<>();
        Deque<Integer> maxQ = new LinkedList<>();
        int[] f = new int[n + 1];
        f[0] = 1;
        long mod = (long)(1e9 + 7), s = 0;

        for (int i = 0, l = 0; i < n; i++) {
            s += f[i];
            int x = nums[i];
            while (!minQ.isEmpty() && x <= nums[minQ.peekLast()]) minQ.pollLast();
            minQ.offerLast(i);
            while (!maxQ.isEmpty() && x >= nums[maxQ.peekLast()]) maxQ.pollLast();
            maxQ.offerLast(i);

            while (nums[maxQ.peekFirst()] - nums[minQ.peekFirst()] > k) {
                s -= f[l++];
                if (minQ.peekFirst() < l) minQ.pollFirst();
                if (maxQ.peekFirst() < l) maxQ.pollFirst();
            }
            f[i + 1] = (int)(s % mod);
        }
        return f[n];
    }
}
/**
 * 本题是标准的划分型 DP
 * 一般定义 f[i+1] 表示前缀 nums[0] 到 nums[i] 在题目约束下，分割出的最少（最多）子数组个数，
 * 本题是定义成分割方案数。这里 i+1 是为了把 f[0] 当作初始值
 * 枚举最后一个子数组的左端点 j，那么问题变成前缀 nums[0] 到 nums[j−1] 在题目约束下的分割方案数，即 f[j]
 * 当子数组右端点 i 固定时，由于子数组越长，最大值越大，最小值越小，最大最小的差值越可能大于 k
 * 所以符合要求的左端点 j 一定在一个连续区间 [L,i] 中
 * 初始值 f[0]=1，空子数组算一个方案。也可以从递归的角度理解，递归到空子数组，就表示我们找到了一个合法分割方案
 * 答案为 f[n]
 * 由于 i 越大，L 也越大，可以用 滑动窗口
 * 同时，我们需要计算 239. 滑动窗口最大值 和滑动窗口最小值，这可以用 单调队列
 */