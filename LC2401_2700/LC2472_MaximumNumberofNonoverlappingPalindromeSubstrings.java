package LC2401_2700;

public class LC2472_MaximumNumberofNonoverlappingPalindromeSubstrings {
    /**
     * You are given a string s and a positive integer k.
     *
     * Select a set of non-overlapping substrings from the string s that satisfy the following conditions:
     *
     * The length of each substring is at least k.
     * Each substring is a palindrome.
     * Return the maximum number of substrings in an optimal selection.
     *
     * A substring is a contiguous sequence of characters within a string.
     *
     * Input: s = "abaccdbbd", k = 3
     * Output: 2
     *
     * Input: s = "adbcda", k = 2
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= k <= s.length <= 2000
     * s consists of lowercase English letters.
     * @param s
     * @param k
     * @return
     */
    // time = O(n^2), space = O(n^2)
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        boolean[][] g = new boolean[n + 1][n + 1];
        for (int len = 1; len <= n; len++) {
            for (int i = 1; i + len - 1 <= n; i++) {
                int j = i + len - 1;
                if (s.charAt(i - 1) == s.charAt(j - 1) && (len <= 2 || g[i + 1][j - 1])) g[i][j] = true;
            }
        }

        int[] f = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            f[i] = f[i - 1];
            for (int j = i - k; j >= 0; j--) {
                if (g[j + 1][i]) f[i] = Math.max(f[i], f[j] + 1);
            }
        }
        return f[n];
    }
}
/**
 * 处理g[i][j]是否是回文串 => 递推
 * Si = Sj && g[i][j]
 * 第二问用dp来做
 * f[i]:
 * 1. 不选i => f[i] = f[i - 1]
 * 2. 枚举各种情况 j = 1, 2, ... 要满足长度 >= k
 * f[i] = f[l] + 1
 *
 * dp[i]: the maximum number of substrings from s[0:i]
 * x x x [x x x x]
 *        j     i     遍历j
 * dp[i] = dp[i - 1]
 * dp[i] = dp[j - 1] + 1;  for j = 0, 1, 2, ... i-k+1
 * 如何快速的知道s[j:i]是回文串？ isPalin[i][j]
 */