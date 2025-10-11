package LC3601_3900;

public class LC3700_NumberofZigZagArraysII {
    /**
     * You are given three integers n, l, and r.
     *
     * A ZigZag array of length n is defined as follows:
     *
     * Each element lies in the range [l, r].
     * No two adjacent elements are equal.
     * No three consecutive elements form a strictly increasing or strictly decreasing sequence.
     * Return the total number of valid ZigZag arrays.
     *
     * Since the answer may be large, return it modulo 109 + 7.
     *
     * A sequence is said to be strictly increasing if each element is strictly greater than its previous one (if exists).
     *
     * A sequence is said to be strictly decreasing if each element is strictly smaller than its previous one (if exists).
     *
     * Input: n = 3, l = 4, r = 5
     * Output: 2
     *
     * Input: n = 3, l = 1, r = 3
     * Output: 10
     *
     * Constraints:
     *
     * 3 <= n <= 10^9
     * 1 <= l < r <= 75
     * @param n
     * @param l
     * @param r
     * @return
     */
    // time = O((r - l)^3 * logn), space = O((r - l)^2)
    long mod = (long)(1e9 + 7);
    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        long[][] f = new long[m * 2][m * 2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < i; j++) {
                f[i][m + j] = 1;
            }
            for (int j = i + 1; j < m; j++) {
                f[m + i][j] = 1;
            }
        }

        long[][] f1 = new long[m * 2][1];
        for (int i = 0; i < m * 2; i++) f1[i][0] = 1;
        long[][] fn = qmi(f, n - 1, f1);
        long res = 0;
        for (long[] x : fn) res = (res + x[0]) % mod;
        return (int)res;
    }

    private long[][] qmi(long[][] a, long k, long[][] f) {
        long[][] res = f;
        while (k > 0) {
            if (k % 2 == 1) res = mul(a, res);
            a = mul(a, a);
            k >>= 1;
        }
        return res;
    }

    private long[][] mul(long[][] a, long[][] b) {
        int n = a.length, m = b.length;
        long[][] c = new long[n][m];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (a[i][k] == 0) continue;
                for (int j = 0; j < b[k].length; j++) {
                    c[i][j] = (c[i][j] + a[i][k] * b[k][j]) % mod;
                }
            }
        }
        return c;
    }
}
/**
 * m = r-l+1, [1,m]
 * up[i][x]: the number of zigzag arrays whose i-th element is x, and the last trend is up
 * down[i][x]: the number of zigzag arrays whose i-th element is x, and the last trend is down
 *
 * up[i][x] --> sum{down[i-1][y], y = 1,2,...,x-1}
 * down[i][x] --> sum{up[i-1][y], y = x+1,x+2,...,m}
 * up[i][1] => 0
 * up[i][2] => down[i-1][1]
 * up[i][3] => down[i-1][1] + down[i-1][2] + down[i-1][3]
 *
 * down[i][1] => up[i-1][2] + up[i-1][3] + ... + up[i-1][m]
 */
