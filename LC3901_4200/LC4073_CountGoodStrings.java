package LC3901_4200;

public class LC4073_CountGoodStrings {
    /**
     * You are given an integer n.
     *
     * A string is considered good if it consists only of the characters 'a' and 'b', and one of the following holds:
     *
     * It contains exactly one distinct character, and its length is odd.
     * It can be written as s = s1 + s2, where s1 and s2 are non-empty good strings, and the last character of s1 is
     * different from the first character of s2.
     * Return the number of good strings of length n, modulo 10^9 + 7.
     *
     * Here, + denotes string concatenation.
     *
     * Input: n = 4
     * Output: 6
     *
     * Input: n = 3
     * Output: 4
     *
     * Input: n = 2
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= n <= 10^15
     * @param n
     * @return
     */
    // time = O(logn), space = O(logn)
    long mod = (long)(1e9 + 7);
    public int countGoodStrings(long n) {
       return (int)(2 * fib(n)[0] % mod);
    }

    private long[] fib(long n) {
        if (n == 0) return new long[]{0, 1};

        long[] p = fib(n / 2);
        long a = p[0], b = p[1]; // F(k), F(k + 1)
        // F(2k) = F(k) * (2 * F(k + 1) - F(k))
        long c = a * ((2 * b % mod - a + mod) % mod) % mod;
        // F(2k + 1) = F(k)^2 + F(k + 1)^2
        long d = (a * a % mod + b * b % mod) % mod;
        if (n % 2 == 0) return new long[]{c, d};
        return new long[]{d, (c + d) % mod};
    }

    // S2: 矩阵快速幂
    // time = O(logn), space = O(logn)
    public int countGoodStrings2(long n) {
        long[][] m = {
                {1, 1},
                {1, 0},
        };
        long[][] f1 = {{2}, {0}}; // 这里初始化成 2，就不用把答案乘以 2 了
        long[][] fn = qmi(m, n - 1, f1);
        return (int)fn[0][0];
    }

    private long[][] qmi(long[][] a, long k, long[][] f) {
        long[][] res = f;
        while (k > 0) {
            if ((k & 1) > 0) res = mul(res, a);
            a = mul(a, a);
            k >>= 1;
        }
        return res;
    }

    private long[][] mul(long[][] a, long[][] b) {
        long[][] c = new long[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int k = 0; k < a[i].length; k++) {
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
 * 递归定义
 * fi = fi-1 + fi-3 + fi-5 + ...  枚举最后一个数怎么拆
 * fi-2 = fi-3 + fi-5 + ...
 * => fi = fi-1 + fi-2  斐波那契数列 LC509
 * f0 = 0
 * f1 = 1
 * f2 = 1 ab
 * 矩阵运算去优化
 */