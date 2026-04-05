package LC3601_3900;

public class LC3889_MirrorFrequencyDistance {
    /**
     * You are given a string s consisting of lowercase English letters and digits.
     *
     * For each character, its mirror character is defined by reversing the order of its character set:
     *
     * For letters, the mirror of a character is the letter at the same position from the end of the alphabet.
     * For example, the mirror of 'a' is 'z', and the mirror of 'b' is 'y', and so on.
     * For digits, the mirror of a character is the digit at the same position from the end of the range '0' to '9'.
     * For example, the mirror of '0' is '9', and the mirror of '1' is '8', and so on.
     * For each unique character c in the string:
     *
     * Let m be its mirror character.
     * Let freq(x) denote the number of times character x appears in the string.
     * Compute the absolute difference between their frequencies, defined as: |freq(c) - freq(m)|
     * The mirror pairs (c, m) and (m, c) are the same and must be counted only once.
     *
     * Return an integer denoting the total sum of these values over all such distinct mirror pairs.
     *
     * Input: s = "ab1z9"
     * Output: 3
     *
     * Input: s = "4m7n"
     * Output: 2
     *
     * Input: s = "byby"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= s.length <= 5 * 10^5
     * s consists only of lowercase English letters and digits.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int mirrorFrequency(String s) {
        int n = s.length();
        int[] c1 = new int[26];
        int[] c2 = new int[10];
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isLowerCase(c)) c1[c - 'a']++;
            else c2[c - '0']++;
        }
        int res = 0;
        for (int i = 0; i < 13; i++) {
            res += Math.abs(c1[i] - c1[25 - i]);
            if (i < 5) res += Math.abs(c2[i] - c2[9 - i]);
        }
        return res;
    }
}