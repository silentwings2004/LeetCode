package LC3601_3900;

public class LC3733_MinimumTimetoCompleteAllDeliveries {
    /**
     * You are given two integer arrays of size 2: d = [d1, d2] and r = [r1, r2].
     *
     * Two delivery drones are tasked with completing a specific number of deliveries. Drone i must complete di deliveries.
     *
     * Each delivery takes exactly one hour and only one drone can make a delivery at any given hour.
     *
     * Additionally, both drones require recharging at specific intervals during which they cannot make deliveries.
     * Drone i must recharge every ri hours (i.e. at hours that are multiples of ri).
     *
     * Return an integer denoting the minimum total time (in hours) required to complete all deliveries.
     *
     * Input: d = [3,1], r = [2,3]
     * Output: 5
     *
     * Input: d = [1,3], r = [2,2]
     * Output: 7
     *
     * Input: d = [2,1], r = [3,4]
     * Output: 3
     *
     * Constraints:
     *
     * d = [d1, d2]
     * 1 <= di <= 10^9
     * r = [r1, r2]
     * 2 <= ri <= 3 * 10^4
     * @param d
     * @param r
     * @return
     */
    // S1
    // time = O(logn), space = O(1)
    public long minimumTime(int[] d, int[] r) {
        long left = 0, right = (long)1E18;
        while (left < right) {
            long mid = left + right >> 1;
            if (check(d, r, mid)) right = mid;
            else left = mid + 1;
        }
        return right;
    }

    private boolean check(int[] d, int[] r, long mid) {
        long v1 = mid - mid / r[0], v2 = mid - mid / r[1];
        long v3 = mid - mid / lcm(r[0], r[1]);
        return v1 >= d[0] && v2 >= d[1] && v3 >= d[0] + d[1];
    }

    private long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private long lcm(long a, long b) {
        if (a == 0 || b == 0) return 0;
        return a / gcd(a, b) * b;
    }

    // S2: Math
    // time = O(log(max(r1, r2)), space = O(1)
    public long minimumTime2(int[] d, int[] r) {
        return Math.max(Math.max(f(d[0], r[0]), f(d[1], r[1])), f(d[0] + d[1], lcm(r[0], r[1])));
    }

    private long f(long d, long r) {
        return d + (d - 1) / (r - 1);
    }
}
/**
 * 二分
 * t - t / r1 >= d1
 * t - t / r2 >= d2
 * t - t / lcm(r1,r2) >= d1 + d2   lcm(r1,r2) 两架无人机都不能用
 */