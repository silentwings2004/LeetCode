package LC3301_3600;
import java.util.*;
public class LC3472_LongestPalindromicSubsequenceAfteratMostKOperations {
    /**
     * You are given a string s and an integer k.
     *
     * In one operation, you can replace the character at any position with the next or previous letter in the alphabet
     * (wrapping around so that 'a' is after 'z'). For example, replacing 'a' with the next letter results in 'b', and
     * replacing 'a' with the previous letter results in 'z'. Similarly, replacing 'z' with the next letter results in
     * 'a', and replacing 'z' with the previous letter results in 'y'.
     *
     * Return the length of the longest palindromic subsequence of s that can be obtained after performing at most k
     * operations.
     *
     * A subsequence is a non-empty string that can be derived from another string by deleting some or no characters
     * without changing the order of the remaining characters.
     *
     * A palindrome is a string that reads the same forward and backward.
     *
     * Input: s = "abced", k = 2
     * Output: 3
     *
     * Input: s = "aaazzz", k = 4
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= s.length <= 200
     * 1 <= k <= 200
     * s consists of only lowercase English letters.
     * @param s
     * @param k
     * @return
     */
    // time = O(n^2 * k), space = O(n^2 * k)
    String s;
    int k;
    int[][][] f;
    public int longestPalindromicSubsequence(String s, int k) {
        this.s = s;
        this.k = k;
        int n = s.length();
        f = new int[n][n][k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(f[i][j], -1);
            }
        }
        return dfs(0, n - 1, k);
    }

    private int dfs(int i, int j, int k) {
        if (i > j) return 0;
        if (i == j) return 1;
        if (f[i][j][k] != -1) return f[i][j][k];

        int res = Math.max(dfs(i + 1, j, k), dfs(i, j - 1, k));
        int cost = get(s.charAt(i), s.charAt(j));
        if (cost <= k) res = Math.max(res, 2 + dfs(i + 1, j - 1, k - cost));
        return f[i][j][k] = res;
    }

    private int get(char a, char b) {
        int d = Math.abs(b - a);
        return Math.min(d, 26 - d);
    }
}