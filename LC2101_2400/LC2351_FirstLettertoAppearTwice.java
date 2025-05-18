package LC2101_2400;

public class LC2351_FirstLettertoAppearTwice {
    /**
     * Given a string s consisting of lowercase English letters, return the first letter to appear twice.
     *
     * Note:
     *
     * A letter a appears twice before another letter b if the second occurrence of a is before the second occurrence
     * of b.
     * s will contain at least one letter that appears twice.
     *
     * Input: s = "abccbaacz"
     * Output: "c"
     *
     * Constraints:
     *
     * 2 <= s.length <= 100
     * s consists of lowercase English letters.
     * s has at least one repeated letter.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public char repeatedCharacter(String s) {
        int[] count = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            count[c - 'a']++;
            if (count[c - 'a'] == 2) return c;
        }
        return '\0';
    }
}
