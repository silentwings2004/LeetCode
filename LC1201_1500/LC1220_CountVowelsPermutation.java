package LC1201_1500;
import java.util.*;
public class LC1220_CountVowelsPermutation {
    /**
     * Given an integer n, your task is to count how many strings of length n can be formed under the following rules:
     *
     * Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
     * Each vowel 'a' may only be followed by an 'e'.
     * Each vowel 'e' may only be followed by an 'a' or an 'i'.
     * Each vowel 'i' may not be followed by another 'i'.
     * Each vowel 'o' may only be followed by an 'i' or a 'u'.
     * Each vowel 'u' may only be followed by an 'a'.
     * Since the answer may be too large, return it modulo 10^9 + 7.
     *
     * Input: n = 1
     * Output: 5
     *
     * Input: n = 5
     * Output: 68
     *
     * Constraints:
     *
     * 1 <= n <= 2 * 10^4
     * @param n
     * @return
     */
    // time = O(n), space = O(1)
    public int countVowelPermutation(int n) {
        long[] dp = new long[5];
        Arrays.fill(dp, 1L);

        long M = (long)(1e9 + 7);
        for (int i = n - 2; i >= 0; i--) {
            long[] dp_temp = dp.clone();
            dp[0] = dp_temp[1];
            dp[1] = (dp_temp[0] + dp_temp[2]) % M;
            dp[2] = (dp_temp[0] + dp_temp[1] + dp_temp[3] + dp_temp[4]) % M;
            dp[3] = (dp_temp[2] + dp_temp[4]) % M;
            dp[4] = dp_temp[0];
        }

        long res = 0;
        for (long x : dp) res = (res + x) % M;
        return (int) res;
    }

    // S2: dp
    // time = O(n), space = O(n)
    public int countVowelPermutation2(int n) {
        int mod = (int)(1e9 + 7);
        int[][] f = new int[n + 1][5];
        boolean[][] g = new boolean[5][5];
        g[0][1] = true;
        g[1][0] = g[1][2] = true;
        g[2][0] = g[2][1] = g[2][3] = g[2][4] = true;
        g[3][2] = g[3][4] = true;
        g[4][0] = true;

        for (int i = 0; i < 5; i++) f[1][i] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (g[k][j]) {
                        f[i][j] = (f[i][j] + f[i - 1][k]) % mod;
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < 5; i++) {
            res = (res + f[n][i]) % mod;
        }
        return res;
    }
}
/**
 * ref:LC935
 * dp[i][0]: how many strings of [i:n-1] can be formed with s[i] = 'a'
 * dp[i][1]: how many strings of [i:n-1] can be formed with s[i] = 'e'
 * dp[i]['a'] = dp[i+1]['e']
 * dp[i]['e'] = dp[i+1]['a'] + dp[i+1]['i']
 * 从后往前构造
 *
 * dp:
 * 状态表示f[i,j]
 * 1. 集合：所有长度为i，最后1位位j的合法序列的集合
 * 2. 属性：数量
 *
 * 状态计算：按照倒数第二位来分类，一共5种：a e i o u
 */