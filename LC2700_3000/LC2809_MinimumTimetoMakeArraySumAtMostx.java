package LC2700_3000;
import java.util.*;
public class LC2809_MinimumTimetoMakeArraySumAtMostx {
    /**
     * You are given two 0-indexed integer arrays nums1 and nums2 of equal length. Every second, for all indices
     * 0 <= i < nums1.length, value of nums1[i] is incremented by nums2[i]. After this is done, you can do the following
     * operation:
     *
     * Choose an index 0 <= i < nums1.length and make nums1[i] = 0.
     * You are also given an integer x.
     *
     * Return the minimum time in which you can make the sum of all elements of nums1 to be less than or equal to x,
     * or -1 if this is not possible.
     *
     * Input: nums1 = [1,2,3], nums2 = [1,2,3], x = 4
     * Output: 3
     *
     * Input: nums1 = [1,2,3], nums2 = [3,3,3], x = 4
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= nums1.length <= 10^3
     * 1 <= nums1[i] <= 10^3
     * 0 <= nums2[i] <= 10^3
     * nums1.length == nums2.length
     * 0 <= x <= 10^6
     * @param nums1
     * @param nums2
     * @param x
     * @return
     */
    // time = O(n^2), space = O(n^2)
    final int INF = 0x3f3f3f3f;
    public int minimumTime(List<Integer> nums1, List<Integer> nums2, int x) {
        int n = nums1.size();
        int[][] w = new int[n][2];
        for (int i = 0; i < n; i++) w[i] = new int[]{nums1.get(i), nums2.get(i)};
        Arrays.sort(w, (o1, o2) -> o1[1] - o2[1]);

        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + w[i - 1][1];

        int[][] f = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                f[i][j] = INF;
                if (j >= 1) f[i][j] = Math.min(f[i][j], f[i - 1][j - 1] + s[i - 1]);
                f[i][j] = Math.min(f[i][j], f[i - 1][j] + w[i - 1][0] + w[i - 1][1] * j);
            }
        }

        for (int i = 0; i <= n; i++) {
            if (f[n][i] <= x) return i;
        }
        return -1;
    }
}
/**
 * n => T, n - T
 * sum = {0, nums2[a], nums2[b]*2,..., nums2[c]*(T-1)
 * nums1[x]+nums2[x]*T, nums1[y]+nums2[y]*T,...,nums1[z]+nums2[z]*T}  <= x
 *
 * x x x x i x ...
 * dp[i][j]: the minimum cost you pay if you apply j clearance among the first i elements
 * 1. if we apply clearance to nums[i]
 * dp[i][j] = dp[i-1][j-1] + 0 + nums2[1:i-1]
 * 2. if we do not apply clearance to nums[i]
 * dp[i][j] = d[i-1][j] + nums1[i] + nums2[i]*j
 * min{dp[i][j]}
 * => dp[n][T] <= x
 * 清0次数与最终结果并无单调性
 */