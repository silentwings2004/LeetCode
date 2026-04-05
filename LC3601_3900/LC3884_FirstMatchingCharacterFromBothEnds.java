package LC3601_3900;

public class LC3884_FirstMatchingCharacterFromBothEnds {
    /**
     * You are given a string s of length n consisting of lowercase English letters.
     *
     * Return the smallest index i such that s[i] == s[n - i - 1].
     *
     * If no such index exists, return -1.
     *
     * Input: s = "abcacbd"
     * Output: 1
     *
     * Input: s = "abc"
     * Output: 1
     *
     * Input: s = "abcdab"
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= n == s.length <= 100
     * s consists of lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int firstMatchingIndex(String s) {
        int n = s.length();
        for (int i = 0; i <= n - 1 - i; i++) {
            if (s.charAt(i) == s.charAt(n - 1 - i)) return i;
        }
        return -1;
    }
}