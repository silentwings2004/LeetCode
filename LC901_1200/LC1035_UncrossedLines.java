package LC901_1200;

public class LC1035_UncrossedLines {
    /**
     * You are given two integer arrays nums1 and nums2. We write the integers of nums1 and nums2 (in the order they
     * are given) on two separate horizontal lines.
     *
     * We may draw connecting lines: a straight line connecting two numbers nums1[i] and nums2[j] such that:
     *
     * nums1[i] == nums2[j], and
     * the line we draw does not intersect any other connecting (non-horizontal) line.
     * Note that a connecting line cannot intersect even at the endpoints (i.e., each number can only belong to one
     * connecting line).
     *
     * Return the maximum number of connecting lines we can draw in this way.
     *
     * Input: nums1 = [1,4,2], nums2 = [1,2,4]
     * Output: 2
     *
     * Input: nums1 = [2,5,1,2,5], nums2 = [10,5,2,1,5,2]
     * Output: 3
     *
     * Input: nums1 = [1,3,7,1,7,5], nums2 = [1,9,2,5,1]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums1.length, nums2.length <= 500
     * 1 <= nums1[i], nums2[j] <= 2000
     * @param nums1
     * @param nums2
     * @return
     */
    // time = O(n * m), space = O(n * m)
    public int maxUncrossedLines(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int[][] f = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                if (nums1[i - 1] == nums2[j - 1]) f[i][j] = Math.max(f[i][j], f[i - 1][j - 1] + 1);
            }
        }
        return f[n][m];
    }
}
/**
 * LCS 最长公共子序列问题
 */