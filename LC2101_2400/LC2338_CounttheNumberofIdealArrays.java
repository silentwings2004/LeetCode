package LC2101_2400;

public class LC2338_CounttheNumberofIdealArrays {
    /**
     * You are given two integers n and maxValue, which are used to describe an ideal array.
     *
     * A 0-indexed integer array arr of length n is considered ideal if the following conditions hold:
     *
     * Every arr[i] is a value from 1 to maxValue, for 0 <= i < n.
     * Every arr[i] is divisible by arr[i - 1], for 0 < i < n.
     * Return the number of distinct ideal arrays of length n. Since the answer may be very large, return it modulo
     * 10^9 + 7.
     *
     * Input: n = 2, maxValue = 5
     * Output: 10
     *
     * Input: n = 5, maxValue = 3
     * Output: 11
     *
     * Constraints:
     *
     * 2 <= n <= 10^4
     * 1 <= maxValue <= 10^4
     * @param n
     * @param maxValue
     * @return
     */
    // S1
    // time = O(mlogm * logm), space = O(m)
    public int idealArrays(int n, int maxValue) {
        long M = (long)(1e9 + 7);
        long[][] f = new long[maxValue + 1][15];
        for (int i = 1; i <= maxValue; i++) f[i][1] = 1;
        for (int j = 1; j < 14; j++) { // j + 1 <= 14 => j < 14
            for (int i = 1; i <= maxValue; i++) {
                for (int k = 2; k * i <= maxValue; k++) {
                    f[k * i][j + 1] = (f[k * i][j + 1] + f[i][j]) % M;
                }
            }
        }

        long[][] c = new long[n][15];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i && j <= 14; j++) {
                if (j == 0) c[i][j] = 1;
                else c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % M;
            }
        }

        long res = 0;
        for (int i = 1; i <= maxValue; i++) {
            for (int j = 1; j <= 14 && j <= n; j++) {
                res = (res + f[i][j] * c[n - 1][j - 1]) % M;
            }
        }
        return (int) res;
    }

    // S2: dp
    // time = O(n * log(logn), space = O(n)
    public int idealArrays2(int n, int maxValue) {
        long M = (long)(1e9 + 7);
        long[][] f = new long[10010][15];

        f[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            f[i][0] = 1;
            for (int j = 1; j <= 14; j++) {
                for (int t = 0; t <= j; t++) {
                    f[i][j] = (f[i][j] + f[i - 1][j - t]) % M;
                }
            }
        }

        int[] primes = get_primes(maxValue);

        long res = 0;
        for (int m = 1; m <= maxValue; m++) {
            int x = m;
            long ans = 1;
            for (int p : primes) {
                int count = 0;
                while (x > 1 && x % p == 0) {
                    count++;
                    x /= p;
                }
                ans = ans * f[n][count] % M;
            }
            res = (res + ans) % M;
        }
        return (int) res;
    }

    private int[] get_primes(int n) {
        int[] primes = new int[n + 1];
        boolean[] st = new boolean[n + 1];
        int cnt = 0;

        for (int i = 2; i <= n; i++) {
            if (st[i]) continue;
            primes[cnt++] = i;
            for (int j = i + i; j <= n; j += i) {
                st[j] = true;
            }
        }

        int[] res = new int[cnt];
        for (int i = 0; i < cnt; i++) res[i] = primes[i];
        return res;
    }
}
/**
 * 假如第1个数是x,第2个数要和第一个数不同的话，至少得是2x，然后2^2*x,...,是个指数提升的状态
 * 而我们的数的范围一共只有10^4 => 所以这个序列不会很长，只有log2(10^4) = log2(1000)+log2(10)=10+log2(10) <= 14
 * 所以一个序列最多只包括14个数：2^0 ~ 2^14
 * f(i,j): 最后一个数是i，并且长度为j的不同序列数
 * 不同的序列数最多也只有500多万个
 * 可以枚举下一个数是谁？枚举i的倍数
 * 不同状态最多只有14万个
 * 暴搜 + 记忆化搜索
 * a1,a2,...,ak
 * x1,x2,....,xk  总和 = n
 * 隔板法
 * 一种隔板方法对应一个不定方程的解
 * C(n-1,k-1)
 * f(i,j) * C(n-1,j-1)
 * C(a,b) = C(a-1,b) + C(a-1,b-1)
 *
 * dp[i][v] += dp[i-1][w]  v divsible by w
 * 30 = 1 * 2 * 3 * 5  => 1 -> 1, 2, 3, 5
 *    = 5 * 1 * 3 * 2 => 5 5 15 30
 *    = 1 * 15 * 1 * 2
 *    = 1 * 30 * 1 * 1
 * 只要给出一个合法的因数分解，指定分解的长度为n
 * 每个因数可以是其他若干因数的乘积
 * => 质因数分解是唯一的
 * M = a * b * c * ...
 * 30 = 2 * 3 * 5
 * ()(2)(5)(3) => 1, 2, 10, 30
 * ()(3,5)()(2) => 1, 15, 15, 30
 * 一个位置上可以放多个质因数
 * n ^ k
 * 如果有相同的质因数:
 * n = 2, M = 4 => 2 * 2
 * (2a)(2b) => 2,4
 * (2b)(2a) => 2,4
 * (2a2b)() => 4,4
 * ()(2a2b) => 1,4
 * => 前2个是重复的
 * 对于这些一样的质因数，并不是随便放的
 * 对于那些不同的质因数，随便放都可以，主要是那些重复的质因数
 * how many distinct k same factors in n places, (allowing multiple factors in the same place)
 * dp[i][j] = sum{ dp[i-1][j-t] }  t = 0,1,2,...,j
 * for (int i = 1; i <= n; i++) {
 *     for (int j = 0; j <= k; j++) {
 *         for (int t = 0; t <= j; t++) {
 *             dp[i][j] += dp[i-1][j-t]
 *         }
 *     }
 * }
 * O(NKK) = (196n) = 10^6     2^14 > 10000   k 最多14
 */