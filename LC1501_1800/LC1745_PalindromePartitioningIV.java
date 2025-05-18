package LC1501_1800;

public class LC1745_PalindromePartitioningIV {
    /**
     * Given a string s, return true if it is possible to split the string s into three non-empty palindromic substrings.
     * Otherwise, return false.
     *
     * A string is said to be palindrome if it the same string when reversed.
     *
     * Input: s = "abcbdd"
     * Output: true
     *
     * Input: s = "bcbddxy"
     * Output: false
     *
     * Constraints:
     *
     * 3 <= s.length <= 2000
     * s consists only of lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n^2), space = O(n^2)
    public boolean checkPartitioning(String s) {
        int n = s.length();
        boolean[][] isPalin = new boolean[n + 1][n + 1];

        for (int len = 1; len <= n; len++) {
            for (int i = 1; i + len - 1 <= n; i++) {
                int j = i + len - 1;
                if (s.charAt(i - 1) == s.charAt(j - 1) && (len <= 2 || isPalin[i + 1][j - 1])) {
                    isPalin[i][j] = true;
                }
            }
        }

        for (int i = 1; i <= n - 2; i++) {
            for (int j = i + 1; j <= n - 1; j++) {
                if (isPalin[1][i] && isPalin[i + 1][j] && isPalin[j + 1][n]) {
                    return true;
                }
            }
        }
        return false;
    }
}
