package LC3601_3900;

public class LC3794_ReverseStringPrefix {
    /**
     * You are given a string s and an integer k.
     *
     * Reverse the first k characters of s and return the resulting string.
     *
     * Input: s = "abcd", k = 2
     * Output: "bacd"
     *
     * Input: s = "xyz", k = 3
     * Output: "zyx"
     *
     * Input: s = "hey", k = 1
     * Output: "hey"
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * s consists of lowercase English letters.
     * 1 <= k <= s.length
     * @param s
     * @param k
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public String reversePrefix(String s, int k) {
        StringBuilder sb = new StringBuilder(s.substring(0, k));
        sb.reverse();
        sb.append(s.substring(k));
        return sb.toString();
    }

    // S2
    // time = O(k), space = O(n)
    public String reversePrefix2(String s, int k) {
        char[] t = s.toCharArray();
        for (int i = 0, j = k - 1; i < j; i++, j--) {
            char c = t[i];
            t[i] = t[j];
            t[j] = c;
        }
        return String.valueOf(t);
    }
}