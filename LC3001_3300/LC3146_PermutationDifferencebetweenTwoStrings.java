package LC3001_3300;

public class LC3146_PermutationDifferencebetweenTwoStrings {
    /**
     * You are given two strings s and t such that every character occurs at most once in s and t is a permutation of s.
     *
     * The permutation difference between s and t is defined as the sum of the absolute difference between the index of
     * the occurrence of each character in s and the index of the occurrence of the same character in t.
     *
     * Return the permutation difference between s and t.
     *
     * Input: s = "abc", t = "bac"
     * Output: 2
     *
     * Input: s = "abcde", t = "edbac"
     * Output: 12
     *
     * Constraints:
     *
     * 1 <= s.length <= 26
     * Each character occurs at most once in s.
     * t is a permutation of s.
     * s consists only of lowercase English letters.
     * @param s
     * @param t
     * @return
     */
    // time = O(m + n), space = O(1)
    public int findPermutationDifference(String s, String t) {
        int[] pos = new int[26];
        int m = s.length(), n = t.length();
        for (int i = 0; i < m; i++) {
            pos[s.charAt(i) - 'a'] = i;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            int u = t.charAt(i) - 'a';
            res += Math.abs(i - pos[u]);
        }
        return res;
    }
}