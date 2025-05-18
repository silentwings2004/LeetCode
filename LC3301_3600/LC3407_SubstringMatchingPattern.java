package LC3301_3600;

public class LC3407_SubstringMatchingPattern {
    /**
     * You are given a string s and a pattern string p, where p contains exactly one '*' character.
     *
     * The '*' in p can be replaced with any sequence of zero or more characters.
     *
     * Return true if p can be made a substring of s, and false otherwise.
     *
     * A substring is a contiguous non-empty sequence of characters within a string.
     *
     * Input: s = "leetcode", p = "ee*e"
     * Output: true
     *
     * Input: s = "car", p = "c*v"
     * Output: false
     *
     * Input: s = "luck", p = "u*"
     * Output: true
     *
     * Constraints:
     *
     * 1 <= s.length <= 50
     * 1 <= p.length <= 50
     * s contains only lowercase English letters.
     * p contains only lowercase English letters and exactly one '*'
     * @param s
     * @param p
     * @return
     */
    // time = O(n * m), space = O(n + m)
    public boolean hasMatch(String s, String p) {
        String[] strs = p.split("\\*");
        int n = s.length(), i = 0;
        for (String t : strs) {
            int m = t.length();
            while (i + m <= n && !s.substring(i, i + m).equals(t)) i++;
            if (i + m > n) return false;
            i += m;
        }
        return true;
    }
}