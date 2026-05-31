package LC3901_4200;

public class LC3937_MinimumOperationstoMakeArrayModuloAlternatingI {
    /**
     * You are given an integer array nums and an integer k.
     *
     * In one operation, you can increase or decrease any element of nums by 1.
     *
     * An array is called modulo alternating if there exist two distinct integers x and y (0 <= x, y < k) such that:
     *
     * For every even index i, nums[i] % k == x
     * For every odd index i, nums[i] % k == y
     * Return the minimum number of operations required to make nums modulo alternating.
     *
     * Input: nums = [1,4,2,8], k = 3
     * Output: 2
     *
     * Input: nums = [1,1,1], k = 3
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 10^9
     * 2 <= k <= 100
     * @param nums
     * @param k
     * @return
     */
    // S1
    // time = O(k^2 * n), space = O(1)
    public int minOperations(int[] nums, int k) {
        int n = nums.length;
        for (int i = 0; i < n; i++) nums[i] %= k;
        int res = Integer.MAX_VALUE;
        for (int x = 0; x < k; x++) {
            for (int y = 0; y < k; y++) {
                if (x == y) continue;
                int cnt = 0;
                for (int i = 0; i < n; i++) {
                    int r = i % 2 == 0 ? x : y;
                    int d = Math.abs(r - nums[i]);
                    cnt += Math.min(d, k - d);
                }
                res = Math.min(res, cnt);
            }
        }
        return res;
    }

    // S2
    // time = O(n + k), space = O(k)
    public int minOperations2(int[] nums, int k) {
        int n = nums.length;
        int[] c0 = new int[k]; // even
        int[] c1 = new int[k]; // odd
        for (int i = 0; i < n; i++) {
            int r = nums[i] % k;
            if (i % 2 == 0) c0[r]++;
            else c1[r]++;
        }
        int[] cost0 = circularCost(c0, k);
        int[] cost1 = circularCost(c1, k);

        // best 2 for even
        int ep1 = -1, ep2 = -1;
        int ev1 = Integer.MAX_VALUE, ev2 = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            int v = cost0[i];
            if (v < ev1) {
                ev2 = ev1;
                ep2 = ep1;
                ev1 = v;
                ep1 = i;
            } else if (v < ev2) {
                ev2 = v;
                ep2 = i;
            }
        }

        // best 2 for odd
        int op1 = -1, op2 = -1;
        int ov1 = Integer.MAX_VALUE, ov2 = Integer.MAX_VALUE;
        for (int i = 0; i < k; i++) {
            int v = cost1[i];
            if (v < ov1) {
                ov2 = ov1;
                ov1 = v;
                op2 = op1;
                op1 = i;
            } else if (v < ov2) {
                ov2 = v;
                op2 = i;
            }
        }
        int res = Integer.MAX_VALUE;
        if (ep1 != op1) res = Math.min(res, ev1 + ov1);
        res = Math.min(res, ev1 + ov2);
        res = Math.min(res, ev2 + ov1);
        return res;
    }

    // sum of circular distances for all i, O(k)
    private int[] circularCost(int[] cnt, int k) {
        int m = 3 * k;
        int[] a = new int[m];
        for (int i = 0; i < m; i++) a[i] = cnt[i % k];
        long[] pf = new long[m + 1];
        long[] pw = new long[m + 1];
        for (int i = 0; i < m; i++) {
            pf[i + 1] = pf[i] + a[i];
            pw[i + 1] = pw[i] + 1L * a[i] * i;
        }

        int l = (k - 1) / 2, r = k / 2;
        int[] cost = new int[k];
        for (int i = k; i < 2 * k; i++) {
            int L = i - l, R = i + r;
            // left part [L, i - 1]
            long cl = pf[i] - pf[L];
            long sl = pw[i] - pw[L];
            // right part [i + 1, R]
            long cr = pf[R + 1] - pf[i + 1];
            long sr = pw[R + 1] - pw[i + 1];

            long cur = i * cl - sl + sr - i * cr;
            cost[i - k] = (int)cur;
        }
        return cost;
    }
}