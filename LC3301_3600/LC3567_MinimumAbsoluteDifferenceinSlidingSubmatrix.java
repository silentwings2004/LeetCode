package LC3301_3600;
import java.util.*;
public class LC3567_MinimumAbsoluteDifferenceinSlidingSubmatrix {
    /**
     * You are given an m x n integer matrix grid and an integer k.
     *
     * For every contiguous k x k submatrix of grid, compute the minimum absolute difference between any two distinct
     * values within that submatrix.
     *
     * Return a 2D array ans of size (m - k + 1) x (n - k + 1), where ans[i][j] is the minimum absolute difference in
     * the submatrix whose top-left corner is (i, j) in grid.
     *
     * Note: If all elements in the submatrix have the same value, the answer will be 0.
     *
     * A submatrix (x1, y1, x2, y2) is a matrix that is formed by choosing all cells matrix[x][y] where x1 <= x <= x2
     * and y1 <= y <= y2.
     *
     * Input: grid = [[1,8],[3,-2]], k = 2
     * Output: [[2]]
     *
     * Input: grid = [[3,-1]], k = 1
     * Output: [[0,0]]
     *
     * Input: grid = [[1,-2,3],[2,3,5]], k = 2
     * Output: [[1,2]]
     *
     * Constraints:
     *
     * 1 <= m == grid.length <= 30
     * 1 <= n == grid[i].length <= 30
     * -105 <= grid[i][j] <= 10^5
     * 1 <= k <= min(m, n)
     * @param grid
     * @param k
     * @return
     */
    // time = O(m * n * k * k), space = O(m * n)
    public int[][] minAbsDiff(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] res = new int[m - k + 1][n - k + 1];
        for (int i = 0; i < m - k + 1; i++) {
            for (int j = 0; j < n - k + 1; j++) {
                int[] w = new int[k * k];
                int idx = 0;
                for (int a = 0; a < k; a++) {
                    for (int b = 0; b < k; b++) {
                        int x = i + a, y = j + b;
                        w[idx++] = grid[x][y];
                    }
                }
                Arrays.sort(w);
                int minv = Integer.MAX_VALUE;
                for (int u = 1; u < k * k; u++) {
                    if (w[u] == w[u - 1]) continue;
                    int d = w[u] - w[u - 1];
                    if (d < minv) {
                        minv = d;
                        if (minv == 0) break;
                    }
                }
                res[i][j] = minv == Integer.MAX_VALUE ? 0 : minv;
            }
        }
        return res;
    }
}