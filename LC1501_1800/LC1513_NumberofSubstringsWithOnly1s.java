package LC1501_1800;

public class LC1513_NumberofSubstringsWithOnly1s {
    /**
     * Given a binary string s, return the number of substrings with all characters 1's. Since the answer may be too
     * large, return it modulo 10^9 + 7.
     *
     * Input: s = "0110111"
     * Output: 9
     *
     * Input: s = "101"
     * Output: 2
     *
     * Input: s = "111111"
     * Output: 21
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s[i] is either '0' or '1'.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int numSub(String s) {
        int n = s.length(), mod = (int)1e9 + 7;
        long res = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                int j = i + 1;
                while (j < n && s.charAt(j) == '1') j++;
                int len = j - i;
                long t = (long)(1 + len) * len / 2;
                res = (res + t) % mod;
                i = j;
            }
        }
        return (int)res;
    }
}