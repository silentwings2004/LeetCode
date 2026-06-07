package LC3901_4200;

public class LC3957_MaximumSumofMNonOverlappingSubarraysII {
    /**
     * You are given an integer array nums of length n, and three integers m, l, and r.
     *
     * Your task is to select at least one and at most m non-overlapping subarrays from nums such that:
     *
     * Each selected subarray has a length between [l, r] (inclusive).
     * The total sum of all selected subarrays is maximized.
     * Return the maximum total sum you can achieve.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [4,1,-5,2], m = 2, l = 1, r = 3
     * Output: 7
     *
     * Input: nums = [1,0,3,4], m = 2, l = 1, r = 2
     * Output: 8
     *
     * Input: nums = [-1,7,-4], m = 1, l = 2, r = 3
     * Output: 6
     *
     * Input: nums = [-3,-4,-1], m = 2, l = 1, r = 2
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 10^5
     * -10^5 <= nums[i] <= 10^5
     * 1 <= m <= n
     * 1 <= l <= r <= n
     * @param nums
     * @param m
     * @param l
     * @param r
     * @return
     */
    // time = O(nlogn), space = O(n)
    final long inf = (long)1E18;
    int n, l, r;
    long[] s;
    int[] cnt;
    long[] f;
    public long maximumSum(int[] nums, int m, int l, int r) {
        this.n = nums.length;
        this.l = l;
        this.r = r;
        s = new long[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + nums[i - 1];
        long best = helper();
        if (best <= 0) return best;

        long lo = 0, hi = (long)2E12;
        while (lo < hi) {
            long mid = lo + hi >> 1;
            work(mid);
            if (cnt[n] <= m) hi = mid;
            else lo = mid + 1;
        }
        work(hi);
        long res = f[n] + hi * m;
        return res;
    }

    private void work(long t) {
        f = new long[n + 1];
        cnt = new int[n + 1];
        int[] dq = new int[n + 1];
        int hh = 0, tt = -1;

        for (int j = 1; j <= n; j++) {
            int k = j - l;
            if (k >= 0) {
                long v = f[k] - s[k];
                while (hh <= tt) {
                    long x = f[dq[tt]] - s[dq[tt]];
                    if (x < v || (x == v && cnt[dq[tt]] > cnt[k])) tt--;
                    else break;
                }
                dq[++tt] = k;
            }
            if (hh <= tt && dq[hh] < j - r) hh++;
            f[j] = f[j - 1];
            cnt[j] = cnt[j - 1];
            if (hh <= tt) {
                long v = f[dq[hh]] - s[dq[hh]] + s[j] - t;
                int c = cnt[dq[hh]] + 1;
                if (v > f[j] || (v == f[j] && c < cnt[j])) {
                    f[j] = v;
                    cnt[j] = c;
                }
            }
        }
    }

    private long helper() {
        int[] dq = new int[n + 1];
        int hh = 0, tt = -1;
        long res = -inf;
        for (int j = 1; j <= n; j++) {
            int k = j - l;
            if (k >= 0) {
                while (hh <= tt && s[dq[tt]] >= s[k]) tt--;
                dq[++tt] = k;
            }
            if (hh <= tt && dq[hh] < j - r) hh++;
            if (hh <= tt && j >= l) res = Math.max(res, s[j] - s[dq[hh]]);
        }
        return res;
    }
}