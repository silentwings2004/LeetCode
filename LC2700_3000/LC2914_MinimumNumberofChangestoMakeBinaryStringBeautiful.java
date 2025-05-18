package LC2700_3000;

public class LC2914_MinimumNumberofChangestoMakeBinaryStringBeautiful {
    /**
     * You are given a 0-indexed binary string s having an even length.
     *
     * A string is beautiful if it's possible to partition it into one or more substrings such that:
     *
     * Each substring has an even length.
     * Each substring contains only 1's or only 0's.
     * You can change any character in s to 0 or 1.
     *
     * Return the minimum number of changes required to make the string s beautiful.
     *
     * Input: s = "1001"
     * Output: 2
     *
     * Input: s = "10"
     * Output: 1
     *
     * Input: s = "0000"
     * Output: 0
     *
     * Constraints:
     *
     * 2 <= s.length <= 10^5
     * s has an even length.
     * s[i] is either '0' or '1'.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int minChanges(String s) {
        int n = s.length(), res = 0;
        for (int i = 0; i + 1 < n; i += 2) {
            if (s.charAt(i) != s.charAt(i + 1)) res++;
        }
        return res;
    }
}