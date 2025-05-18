package LC3001_3300;
import java.util.*;
public class LC3269_ConstructingTwoIncreasingArrays {
    /**
     * Given 2 integer arrays nums1 and nums2 consisting only of 0 and 1, your task is to calculate the minimum possible
     * largest number in arrays nums1 and nums2, after doing the following.
     *
     * Replace every 0 with an even positive integer and every 1 with an odd positive integer. After replacement, both
     * arrays should be increasing and each integer should be used at most once.
     *
     * Return the minimum possible largest number after applying the changes.
     *
     * Input: nums1 = [], nums2 = [1,0,1,1]
     * Output: 5
     *
     * Input: nums1 = [0,1,0,1], nums2 = [1,0,0,1]
     * Output: 9
     *
     * Input: nums1 = [0,1,0,0,1], nums2 = [0,0,0,1]
     * Output: 13
     *
     * Constraints:
     *
     * 0 <= nums1.length <= 1000
     * 1 <= nums2.length <= 1000
     * nums1 and nums2 consist only of 0 and 1.
     * @param nums1
     * @param nums2
     * @return
     */
    // time = O(m * n), space = O(m * n)
    public int minLargest(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int inf = 0x3f3f3f3f;
        int[][] f = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) Arrays.fill(f[i], inf);

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 && j == 0) f[i][j] = 0;
                else {
                    if (i > 0) {
                        f[i][j] = Math.min(f[i][j], f[i - 1][j] + (f[i - 1][j] % 2 == nums1[i - 1] ? 2 : 1));
                    }
                    if (j > 0) {
                        f[i][j] = Math.min(f[i][j], f[i][j - 1] + (f[i][j - 1] % 2 == nums2[j - 1] ? 2 : 1));
                    }
                }
            }
        }
        return f[m][n];
    }
}