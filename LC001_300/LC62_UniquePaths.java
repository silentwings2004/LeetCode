package LC001_300;
import java.util.*;
public class LC62_UniquePaths {
    /**
     * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
     *
     * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right
     * corner of the grid (marked 'Finish' in the diagram below).
     *
     * How many possible unique paths are there?
     *
     * Input: m = 3, n = 7
     * Output: 28
     *
     * Constraints:
     *
     * 1 <= m, n <= 10^0
     * It's guaranteed that the answer will be less than or equal to 2 * 10^9.
     *
     * @param m
     * @param n
     * @return
     */
    // S1: dp
    // time = O(m * n), space = O(m * n)
    public int uniquePaths(int m, int n) {
        int[][] f = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) f[i][j] = 1;
                else {
                    if (i > 0) f[i][j] += f[i - 1][j];
                    if (j > 0) f[i][j] += f[i][j - 1];
                }
            }
        }
        return f[m - 1][n - 1];
    }

    // S2: Math
    // time = O(m), space = O(1)
    public int uniquePaths2(int m, int n) {
        long res = 1;
        for (int i = n, j = 1; j < m; i++, j++) {
            res = res * i / j;
        }
        return (int) res;
    }
}
/**
 * 组合数学问题 C(m+n-2,m-1)
 */