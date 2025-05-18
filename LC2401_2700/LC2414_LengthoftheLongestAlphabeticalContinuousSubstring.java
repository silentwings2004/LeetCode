package LC2401_2700;

public class LC2414_LengthoftheLongestAlphabeticalContinuousSubstring {
    /**
     * An alphabetical continuous string is a string consisting of consecutive letters in the alphabet. In other words,
     * it is any substring of the string "abcdefghijklmnopqrstuvwxyz".
     *
     * For example, "abc" is an alphabetical continuous string, while "acb" and "za" are not.
     * Given a string s consisting of lowercase letters only, return the length of the longest alphabetical continuous
     * substring.
     *
     * Input: s = "abacaba"
     * Output: 2
     *
     * Input: s = "abcde"
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists of only English lowercase letters.
     * @param s
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int longestContinuousSubstring(String s) {
        int n = s.length(), res = 0;
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && s.charAt(j) - s.charAt(j - 1) == 1) j++;
            int len = j - i;
            res = Math.max(res, len);
            i = j - 1;
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int longestContinuousSubstring2(String s) {
        char last = 'a';
        int res = 0, cnt = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c - last == 1) cnt++;
            else cnt = 1;
            res = Math.max(res, cnt);
            last = c;
        }
        return res;
    }
}