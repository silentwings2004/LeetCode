package LC3601_3900;

public class LC3725_CountWaystoChooseCoprimeIntegersfromRows {
    /**
     * You are given a m x n matrix mat of positive integers.
     *
     * Return an integer denoting the number of ways to choose exactly one integer from each row of mat such that the
     * greatest common divisor of all chosen integers is 1.
     *
     * Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * Input: mat = [[1,2],[3,4]]
     * Output: 3
     *
     * Input: mat = [[2,2],[2,2]]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= m == mat.length <= 150
     * 1 <= n == mat[i].length <= 150
     * 1 <= mat[i][j] <= 150
     * @param mat
     * @return
     */
    // S1
    // time = O(m * n * k), space = O(m * k)
    public int countCoprime(int[][] mat) {
        long mod = (long)(1e9 + 7);
        int m = mat.length, n = mat[0].length;
        int mx = mat[0][0];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mx = Math.max(mx, mat[i][j]);
            }
        }
        long[][] f = new long[m][mx + 1];
        for (int j = 0; j < n; j++) f[0][mat[0][j]]++;
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 1; k <= mx; k++) {
                    int t = gcd(k, mat[i][j]);
                    f[i][t] = (f[i][t] + f[i - 1][k]) % mod;
                }
            }
        }
        return (int)f[m - 1][1];
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // S2: 倍数容斥
    // time = O(m * n * U + UlogU), space = O(U) U:max{mat[i][j]}
    public int countCoprime2(int[][] mat) {
        int m = mat.length, n = mat[0].length, mx = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mx = Math.max(mx, mat[i][j]);
            }
        }

        int[] cnt = new int[mx + 1];
        long mod = (long)(1e9 + 7);
        for (int i = mx ; i > 0; i--) {
            long res = 1;
            for (int[] r : mat) {
                int t = 0;
                for (int x : r) {
                    if (x % i == 0) t++;
                }
                if (t == 0) {
                    res = 0;
                    break;
                }
                res = res * t % mod;
            }
            for (int j = i; j <= mx; j += i) res -= cnt[j];
            cnt[i] = (int)(res % mod);
        }
        return (int)((cnt[1] + mod) % mod);
    }
}