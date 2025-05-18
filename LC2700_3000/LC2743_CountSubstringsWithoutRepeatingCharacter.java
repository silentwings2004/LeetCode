package LC2700_3000;

public class LC2743_CountSubstringsWithoutRepeatingCharacter {
    /**
     * You are given a string s consisting only of lowercase English letters. We call a substring special if it contains
     * no character which has occurred at least twice (in other words, it does not contain a repeating character). Your
     * task is to count the number of special substrings. For example, in the string "pop", the substring "po" is a
     * special substring, however, "pop" is not special (since 'p' has occurred twice).
     *
     * Return the number of special substrings.
     *
     * A substring is a contiguous sequence of characters within a string. For example, "abc" is a substring of "abcd",
     * but "acd" is not.
     *
     * Input: s = "abcd"
     * Output: 10
     *
     * Input: s = "ooo"
     * Output: 3
     *
     * Input: s = "abab"
     * Output: 7
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists of lowercase English letters
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int numberOfSpecialSubstrings(String s) {
        int n = s.length(), res = 0;
        int[] cnt = new int[26];
        for (int i = 0, j = 0; i < n; i++) {
            char c = s.charAt(i);
            cnt[c - 'a']++;
            while (cnt[c - 'a'] > 1) cnt[s.charAt(j++) - 'a']--;
            res += i - j + 1;
        }
        return res;
    }
}