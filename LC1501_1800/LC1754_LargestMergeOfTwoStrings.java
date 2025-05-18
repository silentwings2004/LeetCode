package LC1501_1800;

public class LC1754_LargestMergeOfTwoStrings {
    /**
     * You are given two strings word1 and word2. You want to construct a string merge in the following way: while
     * either word1 or word2 are non-empty, choose one of the following options:
     *
     * If word1 is non-empty, append the first character in word1 to merge and delete it from word1.
     * For example, if word1 = "abc" and merge = "dv", then after choosing this operation, word1 = "bc" and merge = "dva".
     * If word2 is non-empty, append the first character in word2 to merge and delete it from word2.
     * For example, if word2 = "abc" and merge = "", then after choosing this operation, word2 = "bc" and merge = "a".
     * Return the lexicographically largest merge you can construct.
     *
     * A string a is lexicographically larger than a string b (of the same length) if in the first position where a and
     * b differ, a has a character strictly larger than the corresponding character in b. For example, "abcd" is
     * lexicographically larger than "abcc" because the first position they differ is at the fourth character, and d
     * is greater than c.
     *
     * Input: word1 = "cabaa", word2 = "bcaaa"
     * Output: "cbcabaaaaa"
     *
     * Input: word1 = "abcabc", word2 = "abdcaba"
     * Output: "abdcabcabcaba"
     *
     * Constraints:
     *
     * 1 <= word1.length, word2.length <= 3000
     * word1 and word2 consist only of lowercase English letters.
     * @param word1
     * @param word2
     * @return
     */
    // S1
    // time = O(n^2), space = O(m + n)
    public String largestMerge(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        int m = word1.length(), n = word2.length();
        int i = 0, j = 0;
        while (i < m || j < n) {
            String s1 = word1.substring(i), s2 = word2.substring(j);
            if (s1.compareTo(s2) > 0) sb.append(word1.charAt(i++));
            else sb.append(word2.charAt(j++));
        }
        return sb.toString();
    }

    // S2
    // time = O(n^2), space = O(m + n)
    public String largestMerge2(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        int m = word1.length(), n = word2.length();
        int i = 0, j = 0;
        while (i < m && j < n) {
            char c1 = word1.charAt(i), c2 = word2.charAt(j);
            if (c1 < c2) {
                sb.append(c2);
                j++;
            } else if (c1 > c2) {
                sb.append(c1);
                i++;
            } else {
                String s1 = word1.substring(i), s2 = word2.substring(j);
                if (s1.compareTo(s2) < 0) {
                    sb.append(c2);
                    j++;
                } else {
                    sb.append(c1);
                    i++;
                }
            }
        }
        if (i < m) sb.append(word1.substring(i));
        if (j < n) sb.append(word2.substring(j));
        return sb.toString();
    }
}
