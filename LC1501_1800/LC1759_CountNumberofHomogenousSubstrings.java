package LC1501_1800;

public class LC1759_CountNumberofHomogenousSubstrings {
    /**
     * Given a string s, return the number of homogenous substrings of s. Since the answer may be too large, return it
     * modulo 10^9 + 7.
     *
     * A string is homogenous if all the characters of the string are the same.
     *
     * A substring is a contiguous sequence of characters within a string.
     *
     * Input: s = "abbcccaa"
     * Output: 13
     *
     * Input: s = "xy"
     * Output: 2
     *
     * Input: s = "zzzzz"
     * Output: 15
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists of lowercase letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int countHomogenous(String s) {
        int n = s.length();
        long res = 0, mod = (long)(1e9 + 7);
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && s.charAt(j) == s.charAt(i)) j++;
            int len = j - i;
            res = (res + (long)(1 + len) * len / 2) % mod;
            i = j - 1;
        }
        return (int) res;
    }
}
