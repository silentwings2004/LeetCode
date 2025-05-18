package LC1501_1800;

public class LC1771_MaximizePalindromeLengthFromSubsequences {
    /**
     * You are given two strings, word1 and word2. You want to construct a string in the following manner:
     *
     * Choose some non-empty subsequence subsequence1 from word1.
     * Choose some non-empty subsequence subsequence2 from word2.
     * Concatenate the subsequences: subsequence1 + subsequence2, to make the string.
     * Return the length of the longest palindrome that can be constructed in the described manner. If no palindromes
     * can be constructed, return 0.
     *
     * A subsequence of a string s is a string that can be made by deleting some (possibly none) characters from s
     * without changing the order of the remaining characters.
     *
     * A palindrome is a string that reads the same forward as well as backward.
     *
     * Input: word1 = "cacb", word2 = "cbba"
     * Output: 5
     *
     * Input: word1 = "ab", word2 = "ab"
     * Output: 3
     *
     * Input: word1 = "aa", word2 = "bb"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= word1.length, word2.length <= 1000
     * word1 and word2 consist of lowercase English letters.
     * @param word1
     * @param word2
     * @return
     */
    // S1
    // time = O(m * n), space = O(m * n)
    public int longestPalindrome(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        word2 = reverse(word2);
        word1 = "#" + word1;
        word2 = "#" + word2;

        int[][] f1 = isPalin(word1);
        int[][] f2 = isPalin(word2);

        int res = 0;
        int[][] f = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                    res = Math.max(res, f[i][j] * 2 + Math.max(i + 1 <= m ? f1[i + 1][m] : 0, j + 1 <= n ? f2[j + 1][n] : 0));
                } else f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
            }
        }
        return res;
    }

    private int[][] isPalin(String s) {
        int n = s.length() - 1;
        int[][] f = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) f[i][i] = 1;

        for (int len = 2; len <= n; len++) {
            for (int i = 1; i + len - 1 <= n; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) f[i][j] = f[i + 1][j - 1] + 2;
                else f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
            }
        }
        return f;
    }

    private String reverse(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }

    // S2
    // time = O(m * n), space = O(m * n)
    public int longestPalindrome2(String word1, String word2) {
        int m = word1.length();
        String word = word1 + word2;
        int n = word.length();
        int[][] f = new int[n][n];
        for (int i = 0; i < n; i++) f[i][i] = 1;

        int res = 0;
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                // f[i][j];
                if (word.charAt(i) == word.charAt(j)) {
                    f[i][j] = f[i + 1][j - 1] + 2;
                    if (i < m && j >= m) res = Math.max(res, f[i][j]);
                } else f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
            }
        }
        return res;
    }
}
/**
 * 直接把2个字符串拼一块 => 区间型
 * dp[i][j]: the longest palindrome subsequence within word[i:j]
 * dp[i][j]
 * if (word[i] == word[j])
 *      dp[i][j] = dp[i+1][j-1] + 2;
 *      if (i < m && j >= m) res = max(res, dp[i][j])
 * else
 *      dp[i][j] = max(dp[i+1][j],dp[i][j-1]);
 * return dp[0][n-1]
 * 唯一区别：挑选的字符串要非空 => 如何解决？
 * 要求回文串要跨接2部分
 * XaXXXX YYYaY
 */