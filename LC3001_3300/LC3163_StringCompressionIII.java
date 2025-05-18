package LC3001_3300;

public class LC3163_StringCompressionIII {
    /**
     * Given a string word, compress it using the following algorithm:
     *
     * Begin with an empty string comp. While word is not empty, use the following operation:
     * Remove a maximum length prefix of word made of a single character c repeating at most 9 times.
     * Append the length of the prefix followed by c to comp.
     * Return the string comp.
     *
     * Input: word = "abcde"
     * Output: "1a1b1c1d1e"
     *
     * Input: word = "aaaaaaaaaaaaaabb"
     * Output: "9a5a2b"
     *
     * Constraints:
     *
     * 1 <= word.length <= 2 * 10^5
     * word consists only of lowercase English letters.
     * @param word
     * @return
     */
    // time = O(n), space = O(n)
    public String compressedString(String word) {
        int n = word.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            int j = i + 1;
            while (j < n && word.charAt(j) == word.charAt(i)) {
                j++;
                if (j - i == 9) break;
            }
            int len = j - i;
            sb.append(len).append(c);
            i = j - 1;
        }
        return sb.toString();
    }
}