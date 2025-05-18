package LC3301_3600;

public class LC3460_LongestCommonPrefixAfteratMostOneRemoval {
    /**
     * You are given two strings s and t.
     *
     * Return the length of the longest common
     * prefix
     *  between s and t after removing at most one character from s.
     *
     * Note: s can be left without any removal.
     *
     * Input: s = "madxa", t = "madam"
     * Output: 4
     *
     * Input: s = "leetcode", t = "eetcode"
     * Output: 7
     *
     * Input: s = "one", t = "one"
     * Output: 3
     *
     * Input: s = "a", t = "b"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * 1 <= t.length <= 10^5
     * s and t contain only lowercase English letters.
     * @param s
     * @param t
     * @return
     */
    // time = O(n), space = O(1)
    public int longestCommonPrefix(String s, String t) {
        int n = s.length(), m = t.length();
        boolean f = false;
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
                continue;
            }
            if (f) return j;
            f = true;
            i++;
        }
        return Math.min(i, j);
    }
}