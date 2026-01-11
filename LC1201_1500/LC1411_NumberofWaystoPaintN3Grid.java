package LC1201_1500;

public class LC1411_NumberofWaystoPaintN3Grid {
    /**
     * You have a grid of size n x 3 and you want to paint each cell of the grid with exactly one of the three colors:
     * Red, Yellow, or Green while making sure that no two adjacent cells have the same color (i.e., no two cells that
     * share vertical or horizontal sides have the same color).
     *
     * Given n the number of rows of the grid, return the number of ways you can paint this grid. As the answer may grow
     * large, the answer must be computed modulo 109 + 7.
     *
     * Input: n = 1
     * Output: 12
     *
     * Input: n = 5000
     * Output: 30228214
     *
     * Constraints:
     *
     * n == grid.length
     * 1 <= n <= 5000
     * @param n
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int numOfWays(int n) {
        int mod = (int)1e9 + 7;
        long[][] f = new long[n][2];
        f[0][0] = f[0][1] = 6;
        for (int i = 1; i < n; i++) {
            f[i][0] = (f[i - 1][0] * 3 + f[i - 1][1] * 2) % mod;
            f[i][1] = (f[i - 1][0] * 2 + f[i - 1][1] * 2) % mod;
        }
        return (int)((f[n - 1][0] + f[n - 1][1]) % mod);
    }

    // S2
    // time = O(n), space = O(1)
    public int numOfWays2(int n) {
        long mod = (long)(1e9 + 7);
        long[] f = new long[27];
        for (int p = 0; p < 27; p++) {
            if (selfOK(p)) f[p] = 1;
        }

        for (int i = 1; i < n; i++) {
            long[] g = f.clone();
            for (int p = 0; p < 27; p++) {
                f[p] = 0;
                if (!selfOK(p)) continue;
                for (int q = 0; q < 27; q++) {
                    if (!selfOK(q)) continue;
                    if (crossOK(p, q)) f[p] = (f[p] + g[q]) % mod;
                }
            }
        }
        long res = 0;
        for (int p = 0; p < 27; p++) {
            if (selfOK(p)) res = (res + f[p]) % mod;
        }
        return (int)res;
    }

    private boolean selfOK(int p) {
        int[] t = new int[3];
        for (int i = 0, j = 0; i < 3; i++, j++) {
            t[j] = p % 3;
            p /= 3;
        }
        return t[0] != t[1] && t[1] != t[2];
    }

    private boolean crossOK(int p, int q) {
        int[] t1 = new int[3], t2 = new int[3];
        for (int i = 0, j = 0; i < 3; i++, j++) {
            t1[j] = p % 3;
            p /= 3;
            t2[j] = q % 3;
            q /= 3;
        }
        return t1[0] != t2[0] && t1[1] != t2[1] && t1[2] != t2[2];
    }

    // S3
    // time = O(n), space = O(1)
    public int numOfWays3(int n) {
        long mod = (long)(1e9 + 7);
        long c2 = 6, c3 = 6;
        for (int i = 1; i < n; i++) {
            long x = c2, y = c3;
            c2 = (3 * x + 2 * y) % mod;
            c3 = (2 * x + 2 * y) % mod;
        }
        return (int)((c2 + c3) % mod);
    }
}
/**
 * dp[i][p]: the number of ways you can paint for the first i rows, and the i-th row follows pattern p:
 * dp[i][p] += dp[i-1][q]  q not conflicts with p
 * sum(dp[M-1][p]) over p
 *
 * 0：2 colors
 * 1: 3 colors
 * i-1: 2 colors, 3 colors
 * i: 2 colors, 3 colors
 * ABA => BAB, BCB, CAC
 *     => BAC, CAB
 * ABC => BAB, BCB
 *     => BCA, CAB
 */