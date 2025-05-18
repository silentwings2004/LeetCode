package LC2700_3000;

public class LC2730_FindtheLongestSemiRepetitiveSubstring {
    /**
     * You are given a 0-indexed string s that consists of digits from 0 to 9.
     *
     * A string t is called a semi-repetitive if there is at most one consecutive pair of the same digits inside t. For
     * example, 0010, 002020, 0123, 2002, and 54944 are semi-repetitive while 00101022, and 1101234883 are not.
     *
     * Return the length of the longest semi-repetitive substring inside s.
     *
     * A substring is a contiguous non-empty sequence of characters within a string.
     *
     * Input: s = "52233"
     * Output: 4
     *
     * Input: s = "5494"
     * Output: 4
     *
     * Input: s = "1111111"
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= s.length <= 50
     * '0' <= s[i] <= '9'
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int longestSemiRepetitiveSubstring(String s) {
        int n = s.length(), res = 0;
        for (int i = 0; i < n; i++) {
            int j = i + 1, c = 0;
            while (j < n) {
                if (s.charAt(j) == s.charAt(j - 1)) {
                    c++;
                    if (c == 2) break;
                }
                j++;
            }
            res = Math.max(res, j - i);
            int k = i;
            while (i < j && c == 2) {
                i++;
                if (s.charAt(i) == s.charAt(i - 1)) c--;
            }
            if (k != i) i--;
        }
        return res;
    }
}