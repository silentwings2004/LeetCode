package LC3601_3900;
import java.util.*;
public class LC3699_NumberofZigZagArraysI {
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
     * 3 <= n <= 2000
     * 1 <= l < r <= 2000
     * @param n
     * @param l
     * @param r
     * @return
     */
    // time = O(n * (r - l)), space = O(r - l)
    public int zigZagArrays(int n, int l, int r) {
        long mod = (long)(1e9 + 7);
        int m = r - l + 1;
        long[] f = new long[m], g = new long[m];
        Arrays.fill(f, 1);
        Arrays.fill(g, 1);
        long[] s0 = new long[m + 1], s1 = new long[m + 1];
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                s0[j + 1] = s0[j] + f[j];
                s1[j + 1] = s1[j] + g[j];
            }
            for (int j = 0; j < m; j++) {
                f[j] = s1[j] % mod;
                g[j] = (s0[m] - s0[j + 1]) % mod;
            }
        }
        long res = 0;
        for (int i = 0; i < m; i++) res = (res + f[i] + g[i]) % mod;
        return (int)res;
    }
}
/**
 * f0[i][j] 表示包含 i 个数字，且第 i 个数字是 j，且最后两个数是递增的方案数
 * f1[i][j] 表示包含 i 个数字，且第 i 个数字是 j，且最后两个数是递减的方案数
 * f0[i][j] = sum_{k = l, l+1,...,j-1} f1[i-1][k] --> 用前缀和优化到 O(1)
 * f1[i][j] = sum_{k = j+1, j+2,...r} f0[i-1][k]
 * f0 = [1] * k // 后两个数递增
 * f1 = [1] * k // 后两个数递减
 * sum(f0[n]) + sum(f1[n])
 * i <-> r-l-i
 * 只要算增减增减即可
 */