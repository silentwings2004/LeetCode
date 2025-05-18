package LC1501_1800;

public class LC1682_LongestPalindromicSubsequenceII {
    /**
     * A subsequence of a string s is considered a good palindromic subsequence if:
     *
     * It is a subsequence of s.
     * It is a palindrome (has the same value if reversed).
     * It has an even length.
     * No two consecutive characters are equal, except the two middle ones.
     * For example, if s = "abcabcabb", then "abba" is considered a good palindromic subsequence, while "bcb" (not even
     * length) and "bbbb" (has equal consecutive characters) are not.
     *
     * Given a string s, return the length of the longest good palindromic subsequence in s.
     *
     * Input: s = "bbabab"
     * Output: 4
     *
     * Input: s = "dcbccacdb"
     * Output: 4
     *
     *
     Constraints:

     1 <= s.length <= 250
     s consists of lowercase English letters.
     * @param s
     * @return
     */
    // time = O(26 * n^2), space = O(26 * n^2)
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][][] f = new int[n][n][26];

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 2 && s.charAt(i) == s.charAt(j)) f[i][j][s.charAt(i) - 'a'] = 2;
                else {
                    int a = s.charAt(i) - 'a', b = s.charAt(j) - 'a';
                    if (s.charAt(i) == s.charAt(j)) {
                        for (int k = 0; k < 26; k++) {
                            if (a != k) f[i][j][a] = Math.max(f[i][j][a], f[i + 1][j - 1][k] + 2);
                        }
                        for (int k = 0; k < 26; k++) {
                            if (a != k) f[i][j][k] = f[i + 1][j - 1][k];
                        }
                    } else {
                        f[i][j][a] = f[i][j - 1][a];
                        f[i][j][b] = f[i + 1][j][b];
                        for (int k = 0; k < 26; k++) {
                            if (k != a && k != b) f[i][j][k] = f[i + 1][j - 1][k];
                        }
                    }
                }
            }
        }

        int res = 0;
        for (int i = 0; i < 26; i++) res = Math.max(res, f[0][n - 1][i]);
        return res;
    }
}