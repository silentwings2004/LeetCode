package LC3301_3600;

public class LC3359_FindSortedSubmatricesWithMaximumElementatMostK {
    /**
     * You are given a 2D matrix grid of size m x n. You are also given a non-negative integer k.
     *
     * Return the number of submatrices of grid that satisfy the following conditions:
     *
     * The maximum element in the submatrix less than or equal to k.
     * Each row in the submatrix is sorted in non-increasing order.
     * A submatrix (x1, y1, x2, y2) is a matrix that forms by choosing all cells grid[x][y] where x1 <= x <= x2 and
     * y1 <= y <= y2.
     *
     * Input: grid = [[4,3,2,1],[8,7,6,1]], k = 3
     * Output: 8
     *
     * Input: grid = [[1,1,1],[1,1,1],[1,1,1]], k = 1
     * Output: 36
     *
     * Input: grid = [[1]], k = 1
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= m == grid.length <= 10^3
     * 1 <= n == grid[i].length <= 10^3
     * 1 <= grid[i][j] <= 10^9
     * 1 <= k <= 10^9
     * @param grid
     * @param k
     * @return
     */
    // time = O(m * n), space = O(m * n)
    public long countSubmatrices(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] f = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] <= k) {
                    if (j > 0 && grid[i][j - 1] >= grid[i][j]) f[i][j] = f[i][j - 1] + 1;
                    else f[i][j] = 1;
                }
            }
        }
        return cal(transpose(f));
    }

    private int[][] transpose(int[][] f) {
        int m = f.length, n = f[0].length;
        int[][] g = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                g[j][i] = f[i][j];
            }
        }
        return g;
    }

    private long cal(int[][] f) {
        int m = f.length, n = f[0].length;
        long res = 0;
        for (int i = 0; i < m; i++) {
            int[] stk = new int[n + 1];
            int tt = 0;
            long cnt = 0;
            for (int j = 0; j < n; j++) {
                while (tt > 0 && f[i][stk[tt]] >= f[i][j]) {
                    int r = stk[tt--];
                    int l = tt == 0 ? -1 : stk[tt];
                    cnt -= 1L * (f[i][r] - f[i][j]) * (r - l);
                }
                stk[++tt] = j;
                cnt += f[i][j];
                res += cnt;
            }
        }
        return res;
    }
}