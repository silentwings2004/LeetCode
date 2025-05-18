package LC1201_1500;
import java.util.*;
public class LC1458_MaxDotProductofTwoSubsequences {
    /**
     * Given two arrays nums1 and nums2.
     *
     * Return the maximum dot product between non-empty subsequences of nums1 and nums2 with the same length.
     *
     * A subsequence of a array is a new array which is formed from the original array by deleting some (can be none)
     * of the characters without disturbing the relative positions of the remaining characters. (ie, [2,3,5] is a
     * subsequence of [1,2,3,4,5] while [1,5,3] is not).
     *
     * Input: nums1 = [2,1,-2,5], nums2 = [3,0,-6]
     * Output: 18
     *
     * Input: nums1 = [3,-2], nums2 = [2,-6,7]
     * Output: 21
     *
     * Input: nums1 = [-1,-1], nums2 = [1,1]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= nums1.length, nums2.length <= 500
     * -1000 <= nums1[i], nums2[i] <= 1000
     * @param nums1
     * @param nums2
     * @return
     */
    // S1
    // time = O(m * n), space = O(m * n)
    public int maxDotProduct(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[][] f = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int t = nums1[i] * nums2[j];
                f[i][j] = t;
                if (i > 0) f[i][j] = Math.max(f[i][j], f[i - 1][j]);
                if (j > 0) f[i][j] = Math.max(f[i][j], f[i][j - 1]);
                if (i > 0 && j > 0) f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + t);
            }
        }
        return f[m - 1][n - 1];
    }

    // S2
    // time = O(m * n), space = O(m * n)
    public int maxDotProduct2(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length, INF = 0x3f3f3f3f;
        int res = -INF;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, nums1[i] * nums2[j]);
            }
        }
        boolean flag = false;
        if (res >= 0) flag = true;

        int[][] f = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) Arrays.fill(f[i], -INF);
        for (int i = 0; i <= m; i++) f[i][0] = 0;
        for (int i = 0; i <= n; i++) f[0][i] = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + nums1[i - 1] * nums2[j - 1]);
            }
        }
        if (f[m][n] == 0 && !flag) f[m][n] = res;
        return f[m][n];
    }
}