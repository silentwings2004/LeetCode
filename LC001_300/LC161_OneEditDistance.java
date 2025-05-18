package LC001_300;
import java.util.*;
public class LC161_OneEditDistance {
    /**
     * Given two strings s and t, return true if they are both one edit distance apart, otherwise return false.
     *
     * A string s is said to be one distance apart from a string t if you can:
     *
     * Insert exactly one character into s to get t.
     * Delete exactly one character from s to get t.
     * Replace exactly one character of s with a different character to get t.
     *
     * Input: s = "ab", t = "acb"
     * Output: true
     *
     * Constraints:
     *
     * 0 <= s.length <= 10^4
     * 0 <= t.length <= 10^4
     * s and t consist of lower-case letters, upper-case letters and/or digits.
     *
     * @param s
     * @param t
     * @return
     */
    // time = O(min(m, n)), space = O(1)
    public boolean isOneEditDistance(String s, String t) {
        if (s.equals(t)) return false;
        int m = s.length(), n = t.length();
        if (m > n) return isOneEditDistance(t, s);
        if (n - m > 1) return false;

        int i = 0, j = 0;
        boolean flag = false;
        for (i = 0, j = 0; i < m && j < n;) {
            char c1 = s.charAt(i), c2 = t.charAt(j);
            if (c1 == c2) i++;
            else if (m == n && !flag) {
                i++;
                flag = true;
            }
            j++;
        }
        return i == m;
    }
}