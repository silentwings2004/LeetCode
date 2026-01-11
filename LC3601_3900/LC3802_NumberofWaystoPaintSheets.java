package LC3601_3900;
import java.util.*;
public class LC3802_NumberofWaystoPaintSheets {
    /**
     * You are given an integer n representing the number of sheets.
     *
     * You are also given an integer array limit of size m, where limit[i] is the maximum number of sheets that can be
     * painted using color i.
     *
     * You must paint all n sheets under the following conditions:
     *
     * Exactly two distinct colors are used.
     * Each color must cover a single contiguous segment of sheets.
     * The number of sheets painted with color i cannot exceed limit[i].
     * Return an integer denoting the number of distinct ways to paint all sheets. Since the answer may be large, return
     * it modulo 10^9 + 7.
     *
     * Note: Two ways differ if at least one sheet is painted with a different color.
     *
     * Input: n = 4, limit = [3,1,2]
     * Output: 6
     *
     * Input: n = 3, limit = [1,2]
     * Output: 2
     *
     * Input: n = 3, limit = [2,2]
     * Output: 4
     *
     * Constraints:
     *
     * 2 <= n <= 10^9
     * 2 <= m == limit.length <= 10^5
     * 1 <= limit[i] <= 10^9
     * @param n
     * @param limit
     * @return
     */
    // time = O(mlogm), space = O(m)
    public int numberOfWays(int n, int[] limit) {
        long mod = (long)(1e9 + 7);
        int m = limit.length;
        int[] a = new int[m], b = new int[m];
        for (int i = 0; i < m; i++) {
            a[i] = Math.min(limit[i], n - 1);
            b[i] = Math.max(1, n - limit[i]);
        }

        long ss = 0;
        for (int i = 0; i < m; i++) {
            if (a[i] >= b[i]) ss = (ss + (a[i] - b[i] + 1)) % mod;
        }

        Arrays.sort(b);
        long[] s = new long[m + 1];
        for (int i = 1; i <= m; i++) s[i] = s[i - 1] + b[i - 1];

        long ts = 0;
        for (int i = 0; i < m; i++) {
            int cnt = upper_bound(b, a[i] + 1) + 1;
            if (cnt > 0) {
                long t = 1L * cnt * (a[i] + 1) - s[cnt];
                ts = (ts + t) % mod;
            }
        }

        long res = (ts - ss + mod) % mod;
        return (int)res;
    }

    private int upper_bound(int[] nums, int t) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid] <= t) l = mid;
            else r = mid - 1;
        }
        return nums[r] <= t ? r : r - 1;
    }

    // S2
    // time = O(mlogm), space = O(m)
    public int numberOfWays2(int n, int[] limit) {
        Arrays.sort(limit);
        int m = limit.length;
        long mod = (long)(1e9 + 7), res = 0;
        long[] suf = new long[m + 1];
        for (int i = m - 1; i >= 0; i--) {
            limit[i] = Math.min(limit[i], n - 1);
            suf[i] = suf[i + 1] + limit[i];
        }

        for (int x = m - 1, y = 0; x >= 0 && y < m; x--) {
            while (y < m && limit[x] + limit[y] < n) y++;
            if (x >= y) res = (res + (suf[y] - limit[x]) - (n - limit[x] - 1L) * (m - y - 1L)) % mod;
            else res = (res + suf[y] - (n - limit[x] - 1L) * (m - y)) % mod;
        }
        return (int)((res + mod) % mod);
    }
}
/**
 * for any pair (i, j):
 * 1 <= x <= limit[i]
 * 1 <= n - x <= limit[j] => n - limit[j] <= x <= n - 1
 * => Find the Intersection:
 * For a fixed pair $(i, j), the number of valid values for x is the intersection of these two ranges:
 * lower_bound: max(1, n - limit[j])
 * upper_bound: min(limit[i], n - 1)
 * => ways for pair (i,j): max(0, upper_bound - lower_bound + 1)
 * Optimization:
 * let a = min(limit[i], n - 1), b = max(1, n - limit[j]), we need to calculate max(0, upper_bound - lower_bound + 1)
 * as i != j => res = total - # of i
 * # of i => ss
 * total = cnt of valid b(s) * (a[i] + 1) - sum of valid b(s) -> prefix sum
 *
 */