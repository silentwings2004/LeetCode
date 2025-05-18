package LC3301_3600;

public class LC3504_LongestPalindromeAfterSubstringConcatenationII {
    /**
     * You are given two strings, s and t.
     *
     * You can create a new string by selecting a substring from s (possibly empty) and a substring from t (possibly
     * empty), then concatenating them in order.
     *
     * Return the length of the longest palindrome that can be formed this way.
     *
     * A substring is a contiguous sequence of characters within a string.
     *
     * A palindrome is a string that reads the same forward and backward.
     *
     * Input: s = "a", t = "a"
     * Output: 2
     *
     * Input: s = "abc", t = "def"
     * Output: 1
     *
     * Input: s = "b", t = "aaaa"
     * Output: 4
     *
     * Input: s = "abcde", t = "ecdba"
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= s.length, t.length <= 1000
     * s and t consist of lowercase English letters.
     * @param s
     * @param t
     * @return
     */
    // time = O(m * n + n^2 + m^2), space = O(n^2 + m^2)
    public int longestPalindrome(String s, String t) {
        String rs = new StringBuilder(s).reverse().toString();
        String rt = new StringBuilder(t).reverse().toString();
        return Math.max(cal(s, t), cal(rt, rs));
    }

    private int cal(String s, String t) {
        int n = s.length(), m = t.length(), res = 0;
        int[] mx = new int[n + 1];
        int[][] f = new int[n + 1][m + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    f[i + 1][j] = f[i][j + 1] + 1;
                    mx[i + 1] = Math.max(mx[i + 1], f[i + 1][j]);
                }
            }
            res = Math.max(res, mx[i + 1] * 2);
        }

        for (int i = 0; i < 2 * n - 1; i++) {
            int l = i / 2, r = (i + 1) / 2;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            }
            l++;
            r--;
            if (l <= r) res = Math.max(res, (r - l + 1) + mx[l] * 2);
        }
        return res;
    }
}