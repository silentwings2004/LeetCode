package LC301_600;

public class LC516_LongestPalindromicSubsequence {
    /**
     * Given a string s, find the longest palindromic subsequence's length in s.
     *
     * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without
     * changing the order of the remaining elements.
     *
     * Input: s = "bbbab"
     * Output: 4
     *
     * Input: s = "cbbd"
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= s.length <= 1000
     * s consists only of lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n^2), space = O(n^2)
    public int longestPalindromeSubseq(String s) {
        // corner case
        if (s == null || s.length() == 0) return 0;

        int n = s.length();
        char[] chars = s.toCharArray();

        int[][] f = new int[n][n];

        // len = 1
        for (int i = 0; i < n; i++) f[i][i] = 1;

        // len = 2;
        for (int i = 0; i < n - 1; i++) {
            f[i][i + 1] = (chars[i] == chars[i + 1]) ? 2 : 1;
        }

        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) { // j - i + 1 = len => j = len - 1 + i
                int j = len - 1 + i;
                f[i][j] = Math.max(f[i + 1][j], f[i][j - 1]);
                if (chars[i] == chars[j]) {
                    f[i][j] = Math.max(f[i][j], f[i + 1][j - 1] + 2);
                }
            }
        }
        return f[0][n - 1];
    }

    // S23
    // time = O(n^2), space = O(n^2)
    public int longestPalindromeSubseq2(String s) {
        int n = s.length();
        int[][] f = new int[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 1) f[i][j] = 1;
                else {
                    if (s.charAt(i) == s.charAt(j)) {
                        f[i][j] = f[i + 1][j - 1] + 2;
                    }
                    f[i][j] = Math.max(f[i][j], Math.max(f[i + 1][j], f[i][j - 1]));
                }
            }
        }
        return f[0][n - 1];
    }
}
/**
 * i,j都在: f(i+1,j-1)+2
 * i,j都在: f(i+1,j-1)  包含在下面2种情况中
 * i在，j不在 => f(i,j-1)
 * i不在，j在 => f(i+1,j)
 */