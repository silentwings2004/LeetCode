package LC301_600;

public class LC541_ReverseStringII {
    /**
     * Given a string s and an integer k, reverse the first k characters for every 2k characters counting from the start
     * of the string.
     *
     * If there are fewer than k characters left, reverse all of them. If there are less than 2k but greater than or
     * equal to k characters, then reverse the first k characters and leave the other as original.
     *
     * Input: s = "abcdefg", k = 2
     * Output: "bacdfeg"
     *
     * Input: s = "abcd", k = 2
     * Output: "bacd"
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^4
     * s consists of only lowercase English letters.
     * 1 <= k <= 10^4
     * @param s
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int n = s.length();
        for (int i = 0; i < n; i += k * 2) {
            int l = i, r = Math.min(i + k - 1, n - 1);
            reverse(chars, l, r);
        }
        return String.valueOf(chars);
    }

    private void reverse(char[] chars, int i, int j) {
        while (i < j) {
            char t = chars[i];
            chars[i++] = chars[j];
            chars[j--] = t;
        }
    }
}
