package LC3601_3900;

public class LC3849_MaximumBitwiseXORAfterRearrangement {
    /**
     * You are given two binary strings s and t, each of length n.
     *
     * You may rearrange the characters of t in any order, but s must remain unchanged.
     *
     * Return a binary string of length n representing the maximum integer value obtainable by taking the bitwise XOR
     * of s and rearranged t.
     *
     * Input: s = "101", t = "011"
     * Output: "110"
     *
     * Input: s = "0110", t = "1110"
     * Output: "1101"
     *
     * Input: s = "0101", t = "1001"
     * Output: "1111"
     *
     * Constraints:
     *
     * 1 <= n == s.length == t.length <= 2 * 10^5
     * s[i] and t[i] are either '0' or '1'.
     * @param s
     * @param t
     * @return
     */
    // time = O(n), space = O(n)
    public String maximumXor(String s, String t) {
        int n = s.length(), c0 = 0, c1 = 0;
        for (int i = 0; i < n; i++) {
            char c = t.charAt(i);
            if (c == '0') c0++;
            else c1++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '0') {
                if (c1 > 0) {
                    sb.append(1);
                    c1--;
                } else {
                    sb.append(0);
                    c0--;
                }
            } else {
                if (c0 > 0) {
                    sb.append(1);
                    c0--;
                } else {
                    sb.append(0);
                    c1--;
                }
            }
        }
        return sb.toString();
    }
}