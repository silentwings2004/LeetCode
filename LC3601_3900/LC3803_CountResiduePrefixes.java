package LC3601_3900;

public class LC3803_CountResiduePrefixes {
    /**
     * You are given a string s consisting only of lowercase English letters.
     *
     * A prefix of s is called a residue if the number of distinct characters in the prefix is equal to len(prefix) % 3.
     *
     * Return the count of residue prefixes in s.
     *
     * A prefix of a string is a non-empty substring that starts from the beginning of the string and extends to any
     * point within it.
     *
     * Input: s = "abc"
     * Output: 2
     *
     * Input: s = "dd"
     * Output: 1
     *
     * Input: s = "bob"
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * s contains only lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int residuePrefixes(String s) {
        int n = s.length(), res = 0;
        for (int i = 0, t = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            t |= 1 << u;
            if ((i + 1) % 3 == Integer.bitCount(t)) res++;
        }
        return res;
    }
}