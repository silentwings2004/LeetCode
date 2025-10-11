package LC3601_3900;

public class LC3707_EqualScoreSubstrings {
    /**
     * You are given a string s consisting of lowercase English letters.
     *
     * The score of a string is the sum of the positions of its characters in the alphabet, where 'a' = 1, 'b' = 2, ...,
     * 'z' = 26.
     *
     * Determine whether there exists an index i such that the string can be split into two non-empty substrings s[0..i]
     * and s[(i + 1)..(n - 1)] that have equal scores.
     *
     * Return true if such a split exists, otherwise return false.
     *
     * A substring is a contiguous non-empty sequence of characters within a string.
     *
     * Input: s = "adcb"
     *
     * Output: true
     *
     * Input: s = "bace"
     *
     * Output: false
     *
     * Constraints:
     *
     * 2 <= s.length <= 100
     * s consists of lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public boolean scoreBalance(String s) {
        int n = s.length(), sum = 0;
        for (int i = 0; i < n; i++) sum += s.charAt(i) - 'a' + 1;
        if (sum % 2 == 1) return false;
        for (int i = 0, t = 0; i < n - 1; i++) {
            t += s.charAt(i) - 'a' + 1;
            if (t * 2 == sum) return true;
        }
        return false;
    }
}