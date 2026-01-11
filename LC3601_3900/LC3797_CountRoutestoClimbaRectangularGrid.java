package LC3601_3900;

public class LC3797_CountRoutestoClimbaRectangularGrid {
    /**
     * You are given a string array grid of size n, where each string grid[i] has length m. The character grid[i][j] is
     * one of the following symbols:
     *
     * '.': The cell is available.
     * '#': The cell is blocked.
     *
     * You want to count the number of different routes to climb grid. Each route must start from any cell in the bottom
     * row (row n - 1) and end in the top row (row 0).
     *
     * However, there are some constraints on the route.
     *
     * You can only move from one available cell to another available cell.
     * The Euclidean distance of each move is at most d, where d is an integer parameter given to you. The Euclidean
     * distance between two cells (r1, c1), (r2, c2) is sqrt((r1 - r2)2 + (c1 - c2)2).
     * Each move either stays on the same row or moves to the row directly above (from row r to r - 1).
     * You cannot stay on the same row for two consecutive turns. If you stay on the same row in a move (and this move
     * is not the last move), your next move must go to the row above.
     * Return an integer denoting the number of such routes. Since the answer may be very large, return it modulo
     * 10^9 + 7.
     *
     * Input: grid = ["..","#."], d = 1
     * Output: 2
     *
     * Input: grid = ["..","#."], d = 2
     * Output: 4
     *
     * Input: grid = ["#"], d = 750
     * Output: 0
     *
     * Input: grid = [".."], d = 1
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= n == grid.length <= 750
     * 1 <= m == grid[i].length <= 750
     * grid[i][j] is '.' or '#'.
     * 1 <= d <= 750
     * @param grid
     * @param d
     * @return
     */
    // time = O(m * n), space = O(m * n)
    public int numberOfRoutes(String[] grid, int d) {
        int n = grid.length, m = grid[0].length();
        boolean[][] st = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                st[i][j] = grid[i].charAt(j) == '.';
            }
        }

        long mod = (long)(1e9 + 7);
        long[][][] f = new long[n][m][2];
        for (int j = 0; j < m; j++) {
            if (st[n - 1][j]) {
                f[n - 1][j][1] = 1;
                for (int k = 1; j - k >= 0 && k <= d; k++) {
                    if (st[n - 1][j - k]) f[n - 1][j][0]++;
                }
                for (int k = 1; j + k < m && k <= d; k++) {
                    if (st[n - 1][j + k]) f[n - 1][j][0]++;
                }
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            long[][] s = new long[m + 1][2];
            for (int j = 1; j <= m; j++) {
                s[j][0] = s[j - 1][0] + f[i + 1][j - 1][0];
                s[j][1] = s[j - 1][1] + f[i + 1][j - 1][1];
            }
            for (int j = 0; j < m; j++) {
                if (!st[i][j]) continue;
                // case 1
                int t = (int)Math.sqrt(d * d - 1);
                int l = Math.max(0, j - t), r = Math.min(m - 1, j + t);
                f[i][j][1] = (f[i][j][1] + s[r + 1][0] - s[l][0]) % mod;
                f[i][j][1] = (f[i][j][1] + s[r + 1][1] - s[l][1]) % mod;
            }

            // case 2
            long[] pre = new long[m + 1];
            for (int j = 1; j <= m; j++) pre[j] = (pre[j - 1] + f[i][j - 1][1]) % mod;
            for (int j = 0; j < m; j++) {
                if (!st[i][j]) continue;
                int l = Math.max(0, j - d);
                int r = Math.min(m - 1, j + d);
                f[i][j][0] = (f[i][j][0] + pre[r + 1] - pre[l] - f[i][j][1] + mod) % mod;
            }
        }
        long res = 0;
        for (int j = 0; j < m; j++) {
            for (int k = 0; k < 2; k++) {
                res = (res + f[0][j][k]) % mod;
            }
        }
        return (int)res;
    }

    private int cal(int x1, int y1, int x2, int y2) {
        return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
    }

    // S2
    // time = O(n * m), space = O(m)
    public int numberOfRoutes2(String[] grid, int d) {
        long mod = (long)(1e9 + 7);
        int n = grid.length, m = grid[0].length();
        long[] sf = new long[m + 1], s = new long[m + 1];
        for (int i = 0; i < n; i++) {
            // case 1: row i - 1 -> i
            long[] f = new long[m];
            for (int j = 0; j < m; j++) {
                if (grid[i].charAt(j) == '#') continue;
                if (i == 0) f[j] = 1;
                else f[j] = s[Math.min(j + d, m)] - s[Math.max(j - d + 1, 0)];
            }
            for (int j = 0; j < m; j++) sf[j + 1] = (sf[j] + f[j]) % mod;

            // case 2: row i -> i
            long[] g = new long[m];
            for (int j = 0; j < m; j++) {
                if (grid[i].charAt(j) == '#') continue;
                g[j] = sf[Math.min(j + d + 1, m)] - sf[Math.max(j - d, 0)] - f[j];
            }
            for (int j = 0; j < m; j++) s[j + 1] = (s[j] + f[j] + g[j]) % mod;
        }
        return (int)((s[m] + mod) % mod);
    }
}
/**
 * 前缀和优化 DP
 * (k - j)^2 + 1 <= d^2 => abs(k - j) <= sqrt(d^2 - 1) <= d - 1
 * since d >= 1 => (d-1)^2 = d^2 - 2 * d + 1 <= d^2 - 1
 */
