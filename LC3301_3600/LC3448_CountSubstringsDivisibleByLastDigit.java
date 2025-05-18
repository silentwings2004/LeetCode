package LC3301_3600;

public class LC3448_CountSubstringsDivisibleByLastDigit {
    /**
     * You are given a string s consisting of digits.
     *
     * Return the number of substrings of s divisible by their non-zero last digit.
     *
     * A substring is a contiguous non-empty sequence of characters within a string.
     *
     * Note: A substring may contain leading zeros.
     *
     * Input: s = "12936"
     * Output: 11
     *
     * Input: s = "5701283"
     * Output: 18
     *
     * Input: s = "1010101010"
     * Output: 25
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists of digits only.
     * @param s
     * @return
     */
    // S1: DP
    // time = O(n * k^2), space = O(k)
    public long countSubstrings(String s) {
        int n = s.length();
        long res = 0;
        int[][] f = new int[10][9];
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - '0';
            for (int j = 1; j < 10; j++) {
                int[] g = new int[j];
                g[u % j] = 1;
                for (int k = 0; k < j; k++) {
                    g[(k * 10 + u) % j] += f[j][k];
                }
                f[j] = g;
            }
            res += f[u][0];
        }
        return res;
    }

    // S1.2: DP
    // time = O(n), space = O(n)
    public long countSubstrings2(String s) {
        int n = s.length();
        int[][][] f = new int[n + 1][10][9];
        long res = 0;

        for (int i = 1; i <= n; i++) {
            int u = s.charAt(i - 1) - '0';
            for (int j = 1; j < 10; j++) {
                f[i][j][u % j] = 1;
                for (int k = 0; k < j; k++) {
                    f[i][j][(k * 10 + u) % j] += f[i - 1][j][k];
                }
            }
            res += f[i][u][0];
        }
        return res;
    }
}
/**
 * 定义 f[i + 1][m][rem] 表示以 s[i] 结尾的，模 m 结果是 rem 的子串个数
 * 以 s[i - 1] 结尾的子串，末尾再添加上 s[i]， 就是以 s[i] 结尾的子串
 * 计算以 s[i] 结尾的模 m 的子串个数
 * (rem_{i-1} * 10 + s[i]) % m
 * f[i+1][m][(rem * 10 + s[i]) % m] += f[i][m][rem]
 * s[i] 单独形成一个子串
 * f[i+1][m][s[i] % m] += 1
 * 初始值 f[i+1][m][s[i] % m] = 1
 * 答案是什么？
 * 以 s[i] 结尾的模 s[i] 的余数为 0 的子串个数
 * sum(f[i+1][s[i]][0] for i in range(n))
 */