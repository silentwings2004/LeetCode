package LC3301_3600;
import java.util.*;
public class LC3539_FindSumofArrayProductofMagicalSequences {
    /**
     * You are given two integers, M and K, and an integer array nums.
     *
     * A sequence of integers seq is called magical if:
     * seq has a size of M.
     * 0 <= seq[i] < nums.length
     * The binary representation of 2seq[0] + 2seq[1] + ... + 2seq[M - 1] has K set bits.
     *
     * The array product of this sequence is defined as prod(seq) = (nums[seq[0]] * nums[seq[1]] * ... * nums[seq[M - 1]]).
     *
     * Return the sum of the array products for all valid magical sequences.
     *
     * Since the answer may be large, return it modulo 10^9 + 7.
     *
     * A set bit refers to a bit in the binary representation of a number that has a value of 1.
     *
     * Input: M = 5, K = 5, nums = [1,10,100,10000,1000000]
     * Output: 991600007
     *
     * Input: M = 2, K = 2, nums = [5,4,3,2,1]
     * Output: 170
     *
     * Input: M = 1, K = 1, nums = [28]
     * Output: 28
     *
     * Constraints:
     *
     * 1 <= K <= M <= 30
     * 1 <= nums.length <= 50
     * 1 <= nums[i] <= 10^8
     * @param M
     * @param K
     * @param nums
     * @return
     */
    // time = O(n * m^3 * k), space = O(n * m^2 * k)
    long mod = (long)(1e9 + 7);
    long[] fact, infact;
    long[][] p;
    long[][][][] f;
    public int magicalSum(int m, int k, int[] nums) {
        int n = nums.length;
        fact = new long[m + 1];
        infact = new long[m + 1];
        fact[0] = infact[0] = 1;
        for (int i = 1; i <= m; i++) {
            fact[i] = fact[i - 1] * i % mod;
            infact[i] = infact[i - 1] * qmi(i, mod - 2) % mod;
        }

        p = new long[n][m + 1];
        for (int i = 0; i < n; i++) {
            p[i][0] = 1;
            for (int j = 1; j <= m; j++) {
                p[i][j] = p[i][j - 1] * nums[i] % mod;
            }
        }

        f = new long[n][m + 1][m / 2 + 1][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= m; j++) {
                for (int u = 0; u <= m / 2; u++) {
                    Arrays.fill(f[i][j][u], -1);
                }
            }
        }
        return (int)(dfs(0, m, 0, k) * fact[m] % mod);
    }

    private long dfs(int i, int leftM, int x, int leftK) {
        int b = Integer.bitCount(x);
        if (x + leftM < leftK) return 0;
        if (i == p.length) return leftM == 0 && b == leftK ? 1 : 0;
        if (f[i][leftM][x][leftK] != -1) return f[i][leftM][x][leftK];

        long res = 0;
        for (int j = 0; j <= leftM; j++) {
            b = (x + j) & 1;
            if (b <= leftK) {
                long r = dfs(i + 1, leftM - j, (x + j) >> 1, leftK - b);
                res = (res + r * p[i][j] % mod * infact[j]) % mod;
            }
        }
        return f[i][leftM][x][leftK] = res;
    }

    private long qmi(long a, long k) {
        long res = 1;
        while (k > 0) {
            if ((k & 1) == 1) res = res * a % mod;
            a = a * a % mod;
            k >>= 1;
        }
        return res;
    }
}
/**
 * 难点1：公式推导
 * 难点2：状态设计 (dp 第一步怎么想)
 * 1. m
 * 2. 0 <= seq[i] < n
 * 3. pop(2^(seq[i])) = k
 * m!(a0^3 / 3!) * (a1^4 / 4!)
 * dfs(i, leftM, S, leftK)
 * i == n:
 * leftM = 0
 * pop(S) = K
 */