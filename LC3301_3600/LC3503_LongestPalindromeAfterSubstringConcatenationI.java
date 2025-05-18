package LC3301_3600;

public class LC3503_LongestPalindromeAfterSubstringConcatenationI {
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
     * 1 <= s.length, t.length <= 30
     * s and t consist of lowercase English letters.
     * @param s
     * @param t
     * @return
     */
    // time = O(n^2 * m^2 * (n + m)), space = O(n + m)
    public int longestPalindrome(String s, String t) {
        int n = s.length(), m = t.length();
        int res = Math.max(get(s), get(t));
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                for (int a = 0; a < m; a++) {
                    for (int b = a; b < m; b++) {
                        String sub = s.substring(i, j + 1) + t.substring(a, b + 1);
                        if (check(sub, 0, sub.length() - 1)) res = Math.max(res, sub.length());
                    }
                }
            }
        }
        return res;
    }

    private int get(String s) {
        int n = s.length(), res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (check(s, i, j)) res = Math.max(res, j - i + 1);
            }
        }
        return res;
    }

    private boolean check(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}