package LC901_1200;
import java.util.*;
public class LC918_MaximumSumCircularSubarray {
    /**
     * Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.
     *
     * A circular array means the end of the array connects to the beginning of the array. Formally, the next element of
     * nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].
     *
     * A subarray may only include each element of the fixed buffer nums at most once. Formally, for a subarray nums[i],
     * nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.
     *
     * Input: nums = [1,-2,3,-2]
     * Output: 3
     *
     * Input: nums = [5,-3,5]
     * Output: 10
     *
     * Input: nums = [-3,-2,-3]
     * Output: -2
     *
     * Constraints:
     *
     * n == nums.length
     * 1 <= n <= 3 * 10^4
     * -3 * 10^4 <= nums[i] <= 3 * 10^4
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;
        int[] s = new int[n * 2 + 1];
        for (int i = 1; i <= n * 2; i++) s[i] = s[i - 1] + nums[(i - 1) % n];

        Deque<Integer> dq = new LinkedList<>();
        dq.offer(0);
        int res = Integer.MIN_VALUE;
        for (int i = 1; i <= n * 2; i++) {
            while (!dq.isEmpty() && i - dq.peekFirst() > n) dq.pollFirst();
            res = Math.max(res, s[i] - s[dq.peekFirst()]);
            while (!dq.isEmpty() && s[dq.peek()] >= s[i]) dq.pollLast();
            dq.offer(i);
        }
        return res;
    }
}
/**
 * 破环成链
 * 长度不超过n的最大连续子段和 => 前缀和
 * 滑动窗口求最小值 => 单调队列
 */