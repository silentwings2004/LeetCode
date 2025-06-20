package LC3301_3600;

public class LC3571_FindtheShortestSuperstringII {
    /**
     * You are given two strings, s1 and s2. Return the shortest possible string that contains both s1 and s2 as
     * substrings. If there are multiple valid answers, return any one of them.
     *
     * A substring is a contiguous sequence of characters within a string.
     *
     * Input: s1 = "aba", s2 = "bab"
     * Output: "abab"
     *
     * Input: s1 = "aa", s2 = "aaa"
     * Output: "aaa"
     *
     * Constraints:
     *
     * 1 <= s1.length <= 100
     * 1 <= s2.length <= 100
     * s1 and s2 consist of lowercase English letters only.
     * @param s1
     * @param s2
     * @return
     */
    // time = O(n^2), space = O(n)
    public String shortestSuperstring(String s1, String s2) {
        int m = s1.length(), n = s2.length(), k = Math.min(m, n);
        if (m >= n && s1.contains(s2) || m < n && s2.contains(s1)) return m > n ? s1 : s2;
        for (int i = k; i > 0; i--) {
            if (s1.substring(0, i).equals(s2.substring(n - i))) return s2 + s1.substring(i);
            if (s2.substring(0, i).equals(s1.substring(m - i))) return s1 + s2.substring(i);
        }
        return s1 + s2;
    }
}