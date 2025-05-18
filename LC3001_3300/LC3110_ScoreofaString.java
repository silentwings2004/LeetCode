package LC3001_3300;

public class LC3110_ScoreofaString {
    /**
     * You are given a string s. The score of a string is defined as the sum of the absolute difference between the
     * ASCII values of adjacent characters.
     *
     * Return the score of s.
     *
     * Input: s = "hello"
     * Output: 13
     *
     * Input: s = "zaz"
     * Output: 50
     *
     * Constraints:
     *
     * 2 <= s.length <= 100
     * s consists only of lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int scoreOfString(String s) {
        int n = s.length(), res = 0;
        for (int i = 1; i < n; i++) {
            res += Math.abs(s.charAt(i) - s.charAt(i - 1));
        }
        return res;
    }
}