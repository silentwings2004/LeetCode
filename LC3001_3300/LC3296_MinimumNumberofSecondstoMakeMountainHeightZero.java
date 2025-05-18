package LC3001_3300;

import java.util.PriorityQueue;

public class LC3296_MinimumNumberofSecondstoMakeMountainHeightZero {
    /**
     * You are given an integer mountainHeight denoting the height of a mountain.
     *
     * You are also given an integer array workerTimes representing the work time of workers in seconds.
     *
     * The workers work simultaneously to reduce the height of the mountain. For worker i:
     *
     * To decrease the mountain's height by x, it takes workerTimes[i] + workerTimes[i] * 2 + ... +
     * workerTimes[i] * x seconds. For example:
     * To reduce the height of the mountain by 1, it takes workerTimes[i] seconds.
     * To reduce the height of the mountain by 2, it takes workerTimes[i] + workerTimes[i] * 2 seconds, and so on.
     * Return an integer representing the minimum number of seconds required for the workers to make the height of the
     * mountain 0.
     *
     * Input: mountainHeight = 4, workerTimes = [2,1,1]
     * Output: 3
     *
     * Input: mountainHeight = 10, workerTimes = [3,2,2,4]
     * Output: 12
     *
     * Input: mountainHeight = 5, workerTimes = [1]
     * Output: 15
     *
     * Constraints:
     *
     * 1 <= mountainHeight <= 10^5
     * 1 <= workerTimes.length <= 10^4
     * 1 <= workerTimes[i] <= 10^6
     * @param mountainHeight
     * @param workerTimes
     * @return
     */
    // S1
    // time = O(nlogn), space = O(1)
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long l = 1, r = (long)1e18;
        while (l < r) {
            long mid = l + r >> 1;
            if (check(workerTimes, mountainHeight, mid)) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    private boolean check(int[] a, int h, long mid) {
        long res = 0;
        for (int x : a) res += get(x, mid);
        return res >= h;
    }

    private long get(int x, long mid) {
        long v = mid * 2 / x;
        return ((long)Math.sqrt(4 * v + 1) - 1) / 2;
    }

    // S2
    // time = O(klogn), space = O(n)
    public long minNumberOfSeconds2(int mountainHeight, int[] workerTimes) {
        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[0], o2[0]));
        for (int x : workerTimes) pq.offer(new long[]{x, x, 1});
        long res = 0;
        while (mountainHeight > 0) {
            long[] t = pq.poll();
            mountainHeight--;
            res = Math.max(res, t[0]);
            t[2]++;
            t[0] += t[1] * t[2];
            pq.offer(t);
        }
        return res;
    }
}