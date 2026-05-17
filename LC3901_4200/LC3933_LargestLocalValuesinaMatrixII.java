package LC3901_4200;

public class LC3933_LargestLocalValuesinaMatrixII {
    /**
     * You are given an n x m integer matrix matrix containing non-negative integers.
     *
     * A non-zero cell (row, col) checks the cells near it as follows:
     *
     * Let x = matrix[row][col].
     * Consider every cell within x rows and x columns of (row, col).
     * Ignore cells that are outside the matrix.
     * Ignore the cells where both the row distance and column distance are exactly x.
     * The cell (row, col) is a local maximum if it is non-zero and no considered cell has a value greater than x.
     *
     * Return an integer denoting the number of local maximums in matrix.
     *
     * Input: matrix = [[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,2,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,0,0],
     * [0,0,0,0,0,0,0]]
     * Output: 1
     *
     * Input: matrix = [[1,2],[3,4]]
     * Output: 1
     *
     * Input: matrix = [[1,0,1],[0,1,0],[1,0,1]]
     * Output: 5
     *
     * Input: matrix = [[1,1],[1,1]]
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= n == matrix.length <= 200
     * 1 <= m == matrix[i].length <= 200
     * 0 <= matrix[i][j] <= 200
     * @param matrix
     * @return
     */
    // time = O(k * m * n), space = O(k * m * n)
    public int countLocalMaximums(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][][] s = new int[201][m + 1][n + 1];
        for (int v = 0; v <= 200; v++) {
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    s[v][i][j] = s[v][i - 1][j] + s[v][i][j - 1] - s[v][i - 1][j - 1] + (matrix[i - 1][j - 1] > v ? 1 : 0);
                }
            }
        }

        int res = 0;
        int[] dx = new int[]{-1, 1, 1, -1}, dy = new int[]{1, 1, -1, -1};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x = matrix[i][j];
                if (x == 0) continue;
                int x1 = Math.max(0, i - x);
                int x2 = Math.min(m - 1, i + x);
                int y1 = Math.max(0, j - x);
                int y2 = Math.min(n - 1, j + x);
                int tot = get(s[x], x1, y1, x2, y2);
                for (int u = 0; u < 4; u++) {
                    int a = i + dx[u] * x, b = j + dy[u] * x;
                    if (a < 0 || a >= m || b < 0 || b >= n) continue;
                    if (matrix[a][b] > x) tot--;
                }
                if (tot == 0) res++;
            }
        }
        return res;
    }

    private int get(int[][] s, int x1, int y1, int x2, int y2) {
        x1++;
        y1++;
        x2++;
        y2++;
        return s[x2][y2] - s[x1 - 1][y2] - s[x2][y1 - 1] + s[x1 - 1][y1 - 1];
    }

    // S2: 二维 ST 表
    // time = O(n * m * logn * logm), space = O(n * m * logn * logm)
    public int countLocalMaximums2(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length;
        int wn = 32 - Integer.numberOfLeadingZeros(n);
        int wm = 32 - Integer.numberOfLeadingZeros(m);

        // st[k1][k2][i][j] 表示左上角在 (i, j)，右下角在 (i+(1<<k1)-1, j+(1<<k2)-1) 的子矩阵最大值
        int[][][][] st = new int[wn][wm][n][m];

        // 初始值
        st[0][0] = matrix;

        // 单独计算 k1 = 0
        for (int k2 = 1; k2 < wm; k2++) {
            int half = 1 << (k2 - 1);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= m - (1 << k2); j++) {
                    st[0][k2][i][j] = Math.max(st[0][k2 - 1][i][j], st[0][k2 - 1][i][j + half]);
                }
            }
        }

        for (int k1 = 1; k1 < wn; k1++) {
            int half = 1 << (k1 - 1);
            for (int k2 = 0; k2 < wm; k2++) {
                for (int i = 0; i <= n - (1 << k1); i++) {
                    for (int j = 0; j <= m - (1 << k2); j++) {
                        st[k1][k2][i][j] = Math.max(st[k1 - 1][k2][i][j], st[k1 - 1][k2][i + half][j]);
                    }
                }
            }
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int x = matrix[i][j];
                if (x == 0) continue;
                int mx1 = query(st, i - x, j - x + 1, i + x + 1, j + x, n, m);
                int mx2 = query(st, i - x + 1, j - x, i + x, j + x + 1, n, m);
                if (Math.max(mx1, mx2) <= x) res++;
            }
        }
        return res;
    }

    // 返回子矩阵最大值
    // 左闭右开，行号范围 [r1, r2)，列号范围 [c1, c2)
    private int query(int[][][][] st, int r1, int c1, int r2, int c2, int n, int m) {
        r1 = Math.max(r1, 0);
        c1 = Math.max(c1, 0);
        r2 = Math.min(r2, n);
        c2 = Math.min(c2, m);
        int k1 = 31 - Integer.numberOfLeadingZeros(r2 - r1);
        int k2 = 31 - Integer.numberOfLeadingZeros(c2 - c1);
        // 视作四个子矩阵的并集
        return Math.max(Math.max(st[k1][k2][r1][c1], st[k1][k2][r2 - (1 << k1)][c1]),
                Math.max(st[k1][k2][r1][c2 - (1 << k2)], st[k1][k2][r2 - (1 << k1)][c2 - (1 << k2)]));
    }
}
/**
 * 稀疏表(ST 表, Sparse Table)
 * 常用语计算静态子数组最值 O(1)
 * 先考虑一维情况：
 * 3 1 4 1 5 9 2 6
 * 采用分治的思想，用动态规划的方式去理解
 * f[i][k] = max[i, i+2^k-1]
 * = max(f[i][k-1],f[i+2^k-1][k-1])
 * 如何用 2 的幂去拼出任意长度的一个子数组呢
 * 长度的二进制长度-1
 * 总结：任意子数组可以表示为，两个长为 2^k 的子数组的并
 * 二维：
 */