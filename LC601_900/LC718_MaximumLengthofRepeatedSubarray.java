package LC601_900;

import java.util.HashSet;

public class LC718_MaximumLengthofRepeatedSubarray {
    /**
     * Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.
     *
     * Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums1.length, nums2.length <= 1000
     * 0 <= nums1[i], nums2[i] <= 100
     * @param nums1
     * @param nums2
     * @return
     */
    // S1: DP
    // time = O(m * n), space = O(m * n)
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[][] dp= new int[m + 1][n + 1];
        int res = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }

    // S2: BS + hash
    // time = O(m + nlogn), space = O(m + n)
    final int P = 131;
    int n, m;
    long[] ha, hb, p;
    public int findLength2(int[] nums1, int[] nums2) {
        n = nums1.length;
        m = nums2.length;
        ha = new long[n + 1];
        hb = new long[m + 1];
        p = new long[n + 1];
        for (int i = 1; i <= n; i++) ha[i] = ha[i - 1] * P + nums1[i - 1];
        for (int i = 1; i <= m; i++) hb[i] = hb[i - 1] * P + nums2[i - 1];
        p[0] = 1;
        for (int i = 1; i <= n; i++) p[i] = p[i - 1] * P;

        int l = 0, r = n;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(mid)) l = mid;
            else r = mid - 1;
        }
        return r;
    }

    private long get(long[] h, int l, int r) {
        return h[r] - h[l - 1] * p[r - l + 1];
    }

    private boolean check(int mid) {
        HashSet<Long> set = new HashSet<>();
        for (int i = mid; i <= n; i++) set.add(get(ha, i - mid + 1, i));
        for (int i = mid; i <= m; i++) {
            if (set.contains(get(hb, i - mid + 1, i))) return true;
        }
        return false;
    }
}
/**
 * longest common subarray
 * longest common subsequence (LCS): DP
 * dp[i][j]: the longest common subarray that ends at A[i] and ends at B[j]
 * xx[xxx i]
 * yyyyy[yyy j]
 * dp[i][j] => dp[i-1][j-1]+1 if A[i] == b[j] else 0
 *
 * for LCS:
 * dp[i][j] => dp[i-1][j-1]+1 if A[i] == b[j]
 * dp[i][j] => Math.max(dp[i-1][j], dp[i][j-1]) if A[i] != b[j]
 */