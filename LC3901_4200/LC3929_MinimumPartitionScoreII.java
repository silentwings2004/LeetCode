package LC3901_4200;
import java.util.*;
public class LC3929_MinimumPartitionScoreII {
    /**
     * You are given an integer array nums and an integer k.
     *
     * Your task is to partition nums into exactly k subarrays and return an integer denoting the minimum possible score
     * among all valid partitions.
     *
     * The score of a partition is the sum of the values of all its subarrays.
     *
     * The value of a subarray is defined as sumArr * (sumArr + 1) / 2, where sumArr is the sum of its elements.
     *
     * Input: nums = [5,1,2,1], k = 2
     * Output: 25
     *
     * Input: nums = [1,2,3,4], k = 1
     * Output: 55
     *
     * Input: nums = [1,1,1], k = 3
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 5 * 10^4
     * 1 <= nums[i] <= 10^3
     * 1 <= k <= nums.length
     * @param nums
     * @param k
     * @return
     */
    // time = O(nlogC), space = O(n)
    final long inf = Long.MAX_VALUE / 4;
    public long minPartitionScore(int[] nums, int k) {
        int n = nums.length;
        long[] pref = new long[n + 1];
        for (int i = 0; i < n; i++) pref[i + 1] = pref[i] + nums[i];
        long total = pref[n];

        // binary search lambda
        long l = -1, r = (long)2E15; // > max benefit
        while (true) { // ensure r forces 1 segment
            Pair p = solve(pref, r);
            if (p.cnt <= k) break;
            r *= 2;
        }

        while (l < r) {
            long mid = l + r + 1 >> 1;
            Pair p = solve(pref, mid);
            if (p.cnt > k) l = mid;
            else r = mid - 1;
        }
        Pair best = solve(pref, r);
        long sumSq = best.dp - r * k; // F_k
        return (sumSq + total) / 2;
    }

    private Pair solve(long[] pref, long lambda) {
        int n = pref.length - 1;
        long[] f = new long[n + 1];
        int[] cnt = new int[n + 1];

        Deque<Line> dq = new LinkedList<>();
        dq.offerLast(new Line(0L, 0L, 0));
        for (int i = 1; i <= n; i++) {
            long x = pref[i];
            // query
            while (dq.size() >= 2) {
                Iterator<Line> it = dq.iterator();
                Line l1 = it.next();
                Line l2 = it.next();
                long v1 = l1.value(x);
                long v2 = l2.value(x);
                if (v2 < v1 || (v2 == v1 && l2.cnt > l1.cnt)) dq.pollFirst();
                else break;
            }
            Line best = dq.peekFirst();
            long bestVal = best.value(x);
            long cur = bestVal + x * x + lambda;
            if (cur > inf) cur = inf;
            f[i] = cur;
            cnt[i] = best.cnt + 1;

            long m = -2L * x;
            long b = f[i] + x * x;
            if (b > inf) b = inf;
            Line nl = new Line(m, b, cnt[i]);

            // maintain lower hull, slopes decreasing
            while (dq.size() >= 2) {
                Line l2 = dq.pollLast();
                Line l1 = dq.peekLast();
                if (!isBad(l1, l2, nl)) {
                    dq.offerLast(l2);
                    break;
                }
            }
            dq.offerLast(nl);
        }
        return new Pair(f[n], cnt[n]);
    }

    // l2 is unnecessary?
    private boolean isBad(Line l1, Line l2, Line l3) {
        // (b2-b1)/(m1-m2) >= (b3-b2)/(m2-m3)
        // m1 > m2 > m3 -> denominators >0
        double x12 = (l2.b - l1.b) / (1.0 * (l1.m - l2.m));
        double x23 = (l3.b - l2.b) / (1.0 * (l2.m - l3.m));
        return x12 >= x23;
    }

    class Line {
        long m; // slope = -2*P
        long b; // dp + P^2
        int cnt; // segments used
        public Line(long m, long b, int cnt) {
            this.m = m;
            this.b = b;
            this.cnt = cnt;
        }

        private long value(long x) {
            return m * x + b; // m*x + b, fits in 64 bit after capping
        }
    }

    class Pair {
        long dp;
        int cnt;
        public Pair(long dp, int cnt) {
            this.dp = dp;
            this.cnt = cnt;
        }
    }
}