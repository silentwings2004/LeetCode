package LC2700_3000;

public class LC2825_MakeStringaSubsequenceUsingCyclicIncrements {
    /**
     * You are given two 0-indexed strings str1 and str2.
     *
     * In an operation, you select a set of indices in str1, and for each index i in the set, increment str1[i] to the
     * next character cyclically. That is 'a' becomes 'b', 'b' becomes 'c', and so on, and 'z' becomes 'a'.
     *
     * Return true if it is possible to make str2 a subsequence of str1 by performing the operation at most once, and
     * false otherwise.
     *
     * Note: A subsequence of a string is a new string that is formed from the original string by deleting some
     * (possibly none) of the characters without disturbing the relative positions of the remaining characters.
     *
     * Input: str1 = "abc", str2 = "ad"
     * Output: true
     *
     * Input: str1 = "zc", str2 = "ad"
     * Output: true
     *
     * Input: str1 = "ab", str2 = "d"
     * Output: false
     *
     * Constraints:
     *
     * 1 <= str1.length <= 10^5
     * 1 <= str2.length <= 10^5
     * str1 and str2 consist of only lowercase English letters.
     * @param str1
     * @param str2
     * @return
     */
    // time = O(n), space = O(1)
    public boolean canMakeSubsequence(String str1, String str2) {
        int n = str1.length(), m = str2.length();
        int j = 0;
        for (int i = 0; i < n && j < m; i++) {
            int a = str1.charAt(i) - 'a', b = str2.charAt(j) - 'a';
            if (b - a == 0 || b - a == 1 || a == 25 && b == 0) j++;
        }
        return j == m;
    }
}