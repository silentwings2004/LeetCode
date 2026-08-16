package LC3901_4200;

public class LC4006_CountValidPrefixes {
    /**
     * You are given a binary string s.
     *
     * A prefix of s is considered valid if its characters can be rearranged to form an alternating string.
     *
     * Return the number of valid prefixes of s.
     *
     * A binary string is a string consisting only of '0' and '1'.
     *
     * A prefix of a string is a substring that starts from the beginning of the string and extends to any point within it.
     *
     * A substring is a contiguous non-empty sequence of characters within a string.
     *
     * A string is considered alternating if no two adjacent characters are equal.
     *
     * Input: s = "00101"
     * Output: 3
     *
     * Input: s = "101"
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * s consists only of '0' and '1'.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int countValidPrefixes(String s) {
        int n = s.length(), res = 0;
        for (int i = 0, t = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '0') t--;
            else t++;
            if (Math.abs(t) < 2) res++;
        }
        return res;
    }
}