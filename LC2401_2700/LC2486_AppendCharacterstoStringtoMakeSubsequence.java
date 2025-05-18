package LC2401_2700;

public class LC2486_AppendCharacterstoStringtoMakeSubsequence {
    /**
     * You are given two strings s and t consisting of only lowercase English letters.
     *
     * Return the minimum number of characters that need to be appended to the end of s so that t becomes a subsequence
     * of s.
     *
     * A subsequence is a string that can be derived from another string by deleting some or no characters without
     * changing the order of the remaining characters.
     *
     * Input: s = "coaching", t = "coding"
     * Output: 4
     *
     * Input: s = "abcde", t = "a"
     * Output: 0
     *
     * Input: s = "z", t = "abcde"
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= s.length, t.length <= 10^5
     * s and t consist only of lowercase English letters.
     * @param s
     * @param t
     * @return
     */
    // time = O(n), space = O(1)
    public int appendCharacters(String s, String t) {
        int n = s.length(), m = t.length();
        int j = 0;
        for (int i = 0; i < n && j < m;) {
            if (s.charAt(i) == t.charAt(j)) j++;
            i++;
        }
        return m - j;
    }
}