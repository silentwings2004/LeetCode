package LC3601_3900;
import java.util.*;
public class LC3691_MaximumTotalSubarrayValueII {
    /**
     * You are given an integer array nums of length n and an integer k.
     *
     * You must select exactly k distinct non-empty subarrays nums[l..r] of nums. Subarrays may overlap, but the exact
     * same subarray (same l and r) cannot be chosen more than once.
     *
     * The value of a subarray nums[l..r] is defined as: max(nums[l..r]) - min(nums[l..r]).
     *
     * The total value is the sum of the values of all chosen subarrays.
     *
     * Return the maximum possible total value you can achieve.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,3,2], k = 2
     *
     * Output: 4
     *
     * Input: nums = [4,2,5,1], k = 3
     *
     * Output: 12
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 5 * 10^4
     * 0 <= nums[i] <= 10^9
     * 1 <= k <= min(10^5, n * (n + 1) / 2)
     * @param nums
     * @param k
     * @return
     */
    // time = O((nlogn + klog(min(n, k))), space = O(nlogn)
    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        ST st = new ST(nums);
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        for (int i = 0; i < n; i++) pq.offer(new int[]{st.query(i, n), i, n});

        long res = 0;
        while (k-- > 0 && pq.peek()[0] > 0) {
            int[] t = pq.poll();
            res += t[0];
            t[2]--;
            t[0] = st.query(t[1], t[2]);
            pq.offer(t);
        }
        return res;
    }

    class ST {
        private final int[][] stMin;
        private final int[][] stMax;

        public ST(int[] a) {
            int n = a.length;
            int w = 32 - Integer.numberOfLeadingZeros(n);
            stMin = new int[w][n];
            stMax = new int[w][n];

            for (int j = 0; j < n; j++) {
                stMin[0][j] = a[j];
                stMax[0][j] = a[j];
            }

            for (int i = 1; i < w; i++) {
                for (int j = 0; j + (1 << i) <= n; j++) {
                    stMin[i][j] = Math.min(stMin[i - 1][j], stMin[i - 1][j + (1 << (i - 1))]);
                    stMax[i][j] = Math.max(stMax[i - 1][j], stMax[i - 1][j + (1 << (i - 1))]);
                }
            }
        }

        // [l, r) 左闭右开
        public int query(int l, int r) {
            int k = 31 - Integer.numberOfLeadingZeros(r - l);
            int mn = Math.min(stMin[k][l], stMin[k][r - (1 << k)]);
            int mx = Math.max(stMax[k][l], stMax[k][r - (1 << k)]);
            return mx - mn;
        }
    }
}
/**
 * 找到第 k 大的值
 * 做法：二分答案
 * 给定 x, 计算 >= x 的子数组值是否至少有 k 个
 * 这个问题有可以用不定长滑窗 (越长越合法模型) + 滑动窗口最值 (单调双端队列)
 * lowerdiff
 * 计算子数组值 > lowerDiff 的子数组的和以及子数组的个数
 * 计算子数组值 = lowerDiff (保证选择的子数组一共 k 个)
 * lazy 线段树，做区间更新操作
 * 线段树二分，求出 > lowerDiff 的子数组的左端点的最大值
 * 进而求出右端点固定为 i 的时候，左端点的个数
 * 线段树维护区间最小值，组间最大值，区间元素和 区间更新为一个值的 lazy tag
 */