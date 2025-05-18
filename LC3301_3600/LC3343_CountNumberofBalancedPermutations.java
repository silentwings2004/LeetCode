package LC3301_3600;
import java.util.*;
public class LC3343_CountNumberofBalancedPermutations {
    /**
     * You are given a string num. A string of digits is called balanced if the sum of the digits at even indices is
     * equal to the sum of the digits at odd indices.
     *
     * Return the number of distinct permutations of num that are balanced.
     *
     * Since the answer may be very large, return it modulo 10^9 + 7.
     *
     * A permutation is a rearrangement of all the characters of a string.
     *
     * Input: num = "123"
     * Output: 2
     *
     * Input: num = "112"
     * Output: 1
     *
     * Input: num = "12345"
     * Output: 0
     *
     * Constraints:
     *
     * 2 <= num.length <= 80
     * num consists of digits '0' to '9' only.
     * @param num
     * @return
     */
    // time = O(n^2 * S), space = O(10 * n * S), S: half sum of num
    final int N = 110;
    public int countBalancedPermutations(String num) {
        long mod = (long)(1e9 + 7);
        long[][] C = new long[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) C[i][j] = 1;
                else C[i][j] = (C[i - 1][j] + C[i - 1][j - 1]) % mod;
            }
        }
        int n = num.length(), s = 0;
        int[] cnt = new int[10];
        for (int i = 0; i < n; i++) {
            int u = num.charAt(i) - '0';
            cnt[u]++;
            s += u;
        }
        if (s % 2 != 0) return 0;

        s /= 2;
        int p = (n + 1) / 2, q = n / 2;
        long[][] f = new long[p + 1][s + 1]; // f[i][j][k]: 来到i号桶，还剩j个奇数位，奇数位还需要的数的累加和为k
        f[0][0] = 1;

        int r = n;
        for (int i = 9; i >= 0; i--) {
            r -= cnt[i];
            for (int j = p; j >= 0; j--) {
                for (int k = s; k >= 0; k--) {
                    int x = q - (r - (p - j));
                    long v = 0;
                    for (int t = Math.max(0, cnt[i] - x); t <= Math.min(j, cnt[i]) && t * i <= k; t++) {
                        v += f[j - t][k - t * i] * C[x][cnt[i] - t] % mod * C[j][t] % mod;
                    }
                    f[j][k] = v % mod;
                }
            }
        }
        return (int)f[p][s];
    }
}
/**
 * 排列问题：
 * 拆分成一个组合问题 + 任意排列问题
 *
 * 组合问题 = 子序列问题 = 恰好型 0-1 背包问题
 * 从一个长为 n 的数组中，选出恰好 floor(n/2) 个数字，这些数字的元素和恰好等于 sum(a) / 2
 * 可重集的排列数
 * n1 = floor(n/2)
 * n1! * (n - n1) ! / (cnt[0]！ * cnt[1]! * ... * cnt[9]!)
 * 最终答案 = 0-1 背包问题的方案数 * n1! * (n - n1) ! / (cnt[0]！ * cnt[1]! * ... * cnt[9]!)
 */