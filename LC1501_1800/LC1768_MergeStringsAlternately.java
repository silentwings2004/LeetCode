package LC1501_1800;

public class LC1768_MergeStringsAlternately {
    /**
     * You are given two strings word1 and word2. Merge the strings by adding letters in alternating order, starting
     * with word1. If a string is longer than the other, append the additional letters onto the end of the merged string.
     *
     * Return the merged string.
     *
     * Input: word1 = "abc", word2 = "pqr"
     * Output: "apbqcr"
     *
     * Input: word1 = "ab", word2 = "pqrs"
     * Output: "apbqrs"
     *
     * Input: word1 = "abcd", word2 = "pq"
     * Output: "apbqcd"
     *
     * Constraints:
     *
     * 1 <= word1.length, word2.length <= 100
     * word1 and word2 consist of lowercase English letters.
     * @param word1
     * @param word2
     * @return
     */
    // time = O(max(m, n)), space = O(m + n)
    public String mergeAlternately(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = 0; i < m || j < n;) {
            if (i < m) sb.append(word1.charAt(i++));
            if (j < n) sb.append(word2.charAt(j++));
        }
        return sb.toString();
    }
}