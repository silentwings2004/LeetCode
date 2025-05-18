package LC3301_3600;

public class LC3456_FindSpecialSubstringofLengthK {
    /**
     * You are given a string s and an integer k.
     *
     * Determine if there exists a
     * substring
     *  of length exactly k in s that satisfies the following conditions:
     *
     * The substring consists of only one distinct character (e.g., "aaa" or "bbb").
     * If there is a character immediately before the substring, it must be different from the character in the substring.
     * If there is a character immediately after the substring, it must also be different from the character in the
     * substring.
     * Return true if such a substring exists. Otherwise, return false.
     *
     * Input: s = "aaabaaa", k = 3
     * Output: true
     *
     * Input: s = "abc", k = 2
     * Output: false
     *
     * Constraints:
     *
     * 1 <= k <= s.length <= 100
     * s consists of lowercase English letters only.
     * @param s
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public boolean hasSpecialSubstring(String s, int k) {
        int n = s.length();
        for (int i = 0; i + k - 1 < n; i++) {
            int j = i + 1;
            while (j < n && s.charAt(j) == s.charAt(j - 1)) j++;
            int len = j - i;
            if (len == k) {
                if ((i == 0 || s.charAt(i - 1) != s.charAt(i)) && (j == n || s.charAt(j) != s.charAt(j - 1))) return true;
            }
            i = j - 1;
        }
        return false;
    }
}