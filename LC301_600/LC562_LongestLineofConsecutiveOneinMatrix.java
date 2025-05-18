package LC301_600;

public class LC562_LongestLineofConsecutiveOneinMatrix {
    /**
     * Given an m x n binary matrix mat, return the length of the longest line of consecutive one in the matrix.
     *
     * The line could be horizontal, vertical, diagonal, or anti-diagonal.
     *
     * Input: mat = [[0,1,1,0],[0,1,1,0],[0,0,0,1]]
     * Output: 3
     *
     * Input: mat = [[1,1,1,1],[0,1,1,0],[0,0,0,1]]
     * Output: 4
     *
     * Constraints:
     *
     * m == mat.length
     * n == mat[i].length
     * 1 <= m, n <= 10^4
     * 1 <= m * n <= 10^4
     * mat[i][j] is either 0 or 1.
     * @param mat
     * @return
     */
    // S1: dp
    // time = O(m * n), space = O(1)
    public int longestLine(int[][] mat) {
        int m = mat.length, n = mat[0].length, res = 0;
        int[][][] f = new int[m + 2][n + 2][4];
        int[] dx = new int[]{-1, 0, -1, -1}, dy = new int[]{0, -1, -1, 1};
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (mat[i - 1][j - 1] == 0) continue;
                for (int k = 0; k < 4; k++) {
                    int a = i + dx[k], b = j + dy[k];
                    f[i][j][k] = f[a][b][k] + 1;
                    res = Math.max(res, f[i][j][k]);
                }
            }
        }
        return res;
    }

    // S2: dfs
    // time = O(m * n), space = O(m * n)
    int[][] g;
    boolean[][][] st;
    int m, n;
    int[] dx = new int[]{-1, 0, 1, 0, -1, -1, 1, 1}, dy = new int[]{0, 1, 0, -1, -1, 1, 1, -1};
    public int longestLine2(int[][] mat) {
        g = mat;
        m = g.length;
        n = g[0].length;
        st = new boolean[m][n][8];

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 1) {
                    for (int k = 0; k < 8; k++) {
                        if (st[i][j][k]) continue;
                        st[i][j][k] = true;
                        res = Math.max(res, dfs(i, j, k, 1));
                    }
                }
            }
        }
        return res;
    }

    private int dfs(int x, int y, int k, int s) {
        int a = x + dx[k], b = y + dy[k];
        if (a < 0 || a >= m || b < 0 || b >= n) return s;
        if (g[a][b] != 1) return s;
        if (st[a][b][k]) return s;
        st[a][b][k] = true;
        return dfs(a, b, k, s + 1);
    }
}