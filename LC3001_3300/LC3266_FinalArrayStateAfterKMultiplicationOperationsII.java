package LC3001_3300;

import java.util.PriorityQueue;

public class LC3266_FinalArrayStateAfterKMultiplicationOperationsII {
    /**
     * You are given an integer array nums, an integer k, and an integer multiplier.
     *
     * You need to perform k operations on nums. In each operation:
     *
     * Find the minimum value x in nums. If there are multiple occurrences of the minimum value, select the one that
     * appears first.
     * Replace the selected minimum value x with x * multiplier.
     * After the k operations, apply modulo 109 + 7 to every value in nums.
     *
     * Return an integer array denoting the final state of nums after performing all k operations and then applying the
     * modulo.
     *
     * Input: nums = [2,1,3,5,6], k = 5, multiplier = 2
     * Output: [8,4,6,5,6]
     *
     * Input: nums = [100000,2000], k = 2, multiplier = 1000000
     * Output: [999999307,999999993]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^4
     * 1 <= nums[i] <= 10^9
     * 1 <= k <= 10^9
     * 1 <= multiplier <= 10^6
     * @param nums
     * @param k
     * @param multiplier
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int[] getFinalState(int[] nums, int k, int multiplier) {
        long mod = (long)(1e9 + 7);
        if (multiplier == 1) return nums;
        int n = nums.length, midx = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] >= nums[midx]) midx = i;
        }

        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? Long.compare(o1[0], o2[0]) : Long.compare(o1[1], o2[1]));
        for (int i = 0; i < n; i++) pq.offer(new long[]{nums[i], i});

        while (k-- > 0) {
            long[] x = pq.poll();
            x[0] = x[0] * multiplier;
            pq.offer(x);
            if (x[1] == midx) break;
        }

        long[][] w = new long[n][2];
        int idx = 0;
        while (!pq.isEmpty()) w[idx++] = pq.poll();
        int t = k / n, r = k % n;
        for (int i = 0; i < n; i++) w[i][0] = w[i][0] % mod * qmi(multiplier, t + (i < r ? 1 : 0), mod) % mod;
        for (int i = 0; i < n; i++) nums[(int)w[i][1]] = (int)w[i][0];
        return nums;
    }

    private long qmi(long a, long k, long mod) {
        long res = 1;
        while (k > 0) {
            if ((k & 1) == 1) res = res * a % mod;
            a = a * a % mod;
            k >>= 1;
        }
        return res;
    }
}
/**
 * 1. 当 nums 只有2个数的时候，如果我们操作到2个数接近
 * 也就是 x <= y 且 x * m > y
 * 那么后续操作一定是在 x 和 y 两个数之间交替进行的
 * 2. 首先，直接暴力模拟(用最小堆),直到原来的最大值，变成最小值
 * 然后，直接用公式计算出每个数还需要操作多少次
 */