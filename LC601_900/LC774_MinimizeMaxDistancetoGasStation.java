package LC601_900;

import java.util.PriorityQueue;

public class LC774_MinimizeMaxDistancetoGasStation {
    /**
     * You are given an integer array stations that represents the positions of the gas stations on the x-axis. You are
     * also given an integer k.
     *
     * You should add k new gas stations. You can add the stations anywhere on the x-axis, and not necessarily on
     * an integer position.
     *
     * Let penalty() be the maximum distance between adjacent gas stations after adding the k new stations.
     *
     * Return the smallest possible value of penalty(). Answers within 10-6 of the actual answer will be accepted.
     *
     * Input: stations = [1,2,3,4,5,6,7,8,9,10], k = 9
     * Output: 0.50000
     *
     * Constraints:
     *
     * 10 <= stations.length <= 2000
     * 0 <= stations[i] <= 10^8
     * stations is sorted in a strictly increasing order.
     * 1 <= k <= 10^6
     * @param stations
     * @param k
     * @return
     */
    // S1: BS
    // time = O(nlogn), space = O(1)
    public double minmaxGasDist(int[] stations, int k) {
        int n = stations.length;
        double left = 0, right = stations[n - 1] - stations[0];

        while (left + 1e-6 < right) {
            double mid = left + (right - left) / 2;
            if (isOK(mid, stations, k)) right = mid; // 下压
            else left = mid;
        }
        return left;
    }

    private boolean isOK(double d, int[] stations, int k) {
        int n = stations.length, count = 0;
        for (int i = 1; i < n; i++) {
            double t = (stations[i] - stations[i - 1]) / d;
            count += Math.ceil(t) - 1; // 可以分成Math.ceil(t)份 => 需要插入Math.ceil(t) - 1块板子
        }
        return count <= k;
    }

    // S2: PQ
    // time = O(klogn), space = O(n)
    public double minmaxGasDist2(int[] stations, int k) {
        PriorityQueue<double[]> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o2[0], o1[0]));
        int n = stations.length;

        double ub = (stations[n - 1] - stations[0]) * 1.0 / (k + 1);

        for (int i = 1; i < n; i++) {
            double dist = stations[i] - stations[i - 1];
            double t = Math.max(1, Math.floor(dist / ub));
            pq.offer(new double[]{dist / t, t});
            k -= t - 1;
        }

        while (k > 0) {
            double[] x = pq.poll();
            double space = x[0], parts = x[1];
            pq.offer(new double[]{space * parts / (parts + 1), parts + 1});
            k--;
        }
        return pq.peek()[0];
    }
}
/**
 * 正着想比较难，反着想比较容易
 * A ___ B _____|_____ C
 * A ___ B ___|____|___ C
 * A ___ B ___C____D____E
 * 1.5 -> 2 -> 1
 * 2.1 -> 3 -> 2
 * 2 -> 2 -> 1
 *
 * S2: PQ + greedy
 * N -> N + 1
 * {space, parts}
 * 当前谁最大就切谁
 * 一个个加效率有点低，有些可以提前分配好
 * 假设没有老加油站，直接分布新加油站，让它平均分布
 * 初始化的时候，可以提前分配一波
 */