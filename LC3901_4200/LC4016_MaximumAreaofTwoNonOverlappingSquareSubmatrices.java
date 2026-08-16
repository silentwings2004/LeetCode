package LC3901_4200;
import java.util.*;
public class LC4016_MaximumAreaofTwoNonOverlappingSquareSubmatrices {
    /**
     * You are given a 2D integer matrix mat of size m × n, where:
     *
     * mat[r][c] == 1 means the cell at row r and column c is usable.
     * mat[r][c] == 0 means it is not usable.
     * Your task is to find two submatrices that satisfy the following conditions:
     *
     * Both submatrices must be squares of the same side length k.
     * The two submatrices must not share any cell.
     * Each submatrix can only cover cells where mat[r][c] == 1.
     * Return the maximum possible area of each of the two squares. If it is not possible to choose two such squares,
     * return 0.
     *
     * A submatrix (x1, y1, x2, y2) is a matrix that forms by choosing all cells mat[x][y] where x1 <= x <= x2 and
     * y1 <= y <= y2.
     *
     * Input: mat = [[1,1,1,0],[1,1,1,1],[0,0,1,1]]
     * Output: 4
     *
     * Input: mat = [[0,0],[0,1]]
     * Output: 0
     *
     * Constraints:
     *
     * mat.length == m
     * mat[i].length == n
     * 1 <= m, n <= 500
     * mat[i][j] is either 0 or 1.
     * @param mat
     * @return
     */
    // time = O(m * n), space = O(m * n)
    public int maxArea(int[][] mat) {
        return Math.max(cal(mat), cal(transpose(mat)));
    }

    private int cal(int[][] g) {
        int m = g.length, n = g[0].length;
        // 计算 g 下半部分的最大正方形边长
        int[] suf = new int[m], f = new int[n + 1];
        int mx = 0;
        for (int i = m - 1; i > 0; i--) {
            int last = 0;
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 1) {
                    int t = f[j + 1];
                    f[j + 1] = Math.min(Math.min(last, f[j + 1]), f[j]) + 1;
                    last = t;
                    mx = Math.max(mx, f[j + 1]);
                } else {
                    f[j + 1] = 0;
                    last = 0;
                }
            }
            suf[i] = mx;
        }

        int res = 0, preMax = 0;
        Arrays.fill(f, 0);
        for (int i = 0; i < m - 1; i++) {
            int last = 0;
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 1) {
                    int t = f[j + 1];
                    f[j + 1] = Math.min(Math.min(last, f[j + 1]), f[j]) + 1;
                    last = t;
                    preMax = Math.max(preMax, f[j + 1]);
                } else {
                    f[j + 1] = 0;
                    last = 0;
                }
            }
            if (suf[i + 1] <= res) break;
            res = Math.max(res, Math.min(preMax, suf[i + 1]));
        }
        return res * res;
    }

    private int[][] transpose(int[][] g) {
        int m = g.length, n = g[0].length;
        int[][] f = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                f[i][j] = g[j][i];
            }
        }
        return f;
    }
}