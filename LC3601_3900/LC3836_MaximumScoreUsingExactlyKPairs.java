package LC3601_3900;
import java.util.*;
public class LC3836_MaximumScoreUsingExactlyKPairs {
    /**
     * You are given two integer arrays nums1 and nums2 of lengths n and m respectively, and an integer k.
     *
     * You must choose exactly k pairs of indices (i1, j1), (i2, j2), ..., (ik, jk) such that:
     *
     * 0 <= i1 < i2 < ... < ik < n
     * 0 <= j1 < j2 < ... < jk < m
     * For each chosen pair (i, j), you gain a score of nums1[i] * nums2[j].
     *
     * The total score is the sum of the products of all selected pairs.
     *
     * Return an integer representing the maximum achievable total score.
     *
     * Input: nums1 = [1,3,2], nums2 = [4,5,1], k = 2
     * Output: 22
     *
     * Input: nums1 = [-2,0,5], nums2 = [-3,4,-1,2], k = 2
     * Output: 26
     *
     * Input: nums1 = [-3,-2], nums2 = [1,2], k = 2
     * Output: -7
     *
     * Constraints:
     *
     * 1 <= n == nums1.length <= 100
     * 1 <= m == nums2.length <= 100
     * -106 <= nums1[i], nums2[i] <= 10^6
     * 1 <= k <= min(n, m)
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    // time = O(n * m * k), space = O(n * m * k)
    public long maxScore(int[] nums1, int[] nums2, int k) {
        final long inf = (long)1E18;
        int n = nums1.length, m = nums2.length;
        long[][][] f = new long[n + 1][m + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                Arrays.fill(f[i][j], -inf);
            }
        }
        f[0][0][0] = 0;

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                for (int u = 0; u <= k; u++) {
                    if (f[i][j][u] == -inf) continue;
                    if (i < n) f[i + 1][j][u] = Math.max(f[i + 1][j][u], f[i][j][u]);
                    if (j < m) f[i][j + 1][u] = Math.max(f[i][j + 1][u], f[i][j][u]);
                    if (i < n && j < m && u < k) {
                        f[i + 1][j + 1][u + 1] = Math.max(f[i + 1][j + 1][u + 1], f[i][j][u] + 1L * nums1[i] * nums2[j]);
                    }
                }
            }
        }
        return f[n][m][k];
    }
}