package LC3301_3600;

public class LC3498_ReverseDegreeofaString {
    /**
     * Given a string s, calculate its reverse degree.
     *
     * The reverse degree is calculated as follows:
     *
     * For each character, multiply its position in the reversed alphabet ('a' = 26, 'b' = 25, ..., 'z' = 1) with its
     * position in the string (1-indexed).
     * Sum these products for all characters in the string.
     * Return the reverse degree of s.
     *
     * Input: s = "abc"
     * Output: 148
     *
     * Input: s = "zaza"
     * Output: 160
     *
     * Constraints:
     *
     * 1 <= s.length <= 1000
     * s contains only lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int reverseDegree(String s) {
        int n = s.length(), res = 0;
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            res += (i + 1) * (26 - u);
        }
        return res;
    }
}