package LC3901_4200;

public class LC3915_MaximumSumofAlternatingSubsequenceWithDistanceatLeastK {
    /**
     * You are given an integer array nums of length n and an integer k.
     *
     * Pick a subsequence with indices 0 <= i1 < i2 < ... < im < n such that:
     *
     * For every 1 <= t < m, it+1 - it >= k.
     * The selected values form a strictly alternating sequence. In other words, either:
     * nums[i1] < nums[i2] > nums[i3] < ..., or
     * nums[i1] > nums[i2] < nums[i3] > ...
     * A subsequence of length 1 is also considered strictly alternating. The score of a valid subsequence is the sum
     * of its selected values.
     *
     * Return an integer denoting the maximum possible score of a valid subsequence.
     *
     * A subsequence is an array that can be derived from another array by deleting some or no elements without changing
     * the order of the remaining elements.
     *
     * Input: nums = [5,4,2], k = 2
     * Output: 7
     *
     * Input: nums = [3,5,4,2,4], k = 1
     * Output: 14
     *
     * Input: nums = [5], k = 1
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * 1 <= k <= n
     * @param nums
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long maxAlternatingSum(int[] nums, int k) {
        int n = nums.length, mx = nums[0];
        for (int x : nums) mx = Math.max(mx, x);
        Fenwick fenv = new Fenwick(mx + 1);
        Fenwick fenp = new Fenwick(mx + 1);
        long[] p = new long[n];
        long[] v = new long[n];

        long res = 0;
        for (int i = 0; i < n; i++) {
            int j = i - k;
            if (j >= 0) {
                fenv.add(nums[j], v[j]);
                fenp.add(mx - nums[j], p[j]);
            }
            p[i] = v[i] = nums[i];
            long bv = fenv.sum(nums[i]);
            if (bv > 0) p[i] = Math.max(p[i], nums[i] + bv);
            long bp = fenp.sum(mx - nums[i]);
            if (bp > 0) v[i] = Math.max(v[i], nums[i] + bp);
            res = Math.max(res, Math.max(p[i], v[i]));
        }
        return res;
    }

    class Fenwick {
        private int n;
        private long[] a;
        public Fenwick(int n) {
            this.n = n;
            this.a = new long[n + 1];
        }

        private void add(int x, long v) {
            for (int i = x + 1; i <= n; i += i & -i) {
                if (v > a[i - 1]) a[i - 1] = v;
            }
        }

        private long sum(int x) {
            long ans = 0;
            for (int i = x; i > 0; i -= i & -i) {
                if (a[i - 1] > ans) ans = a[i - 1];
            }
            return ans;
        }
    }
}