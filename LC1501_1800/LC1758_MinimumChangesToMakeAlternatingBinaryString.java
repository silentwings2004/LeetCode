package LC1501_1800;

public class LC1758_MinimumChangesToMakeAlternatingBinaryString {
    /**
     * You are given a string s consisting only of the characters '0' and '1'. In one operation, you can change any '0'
     * to '1' or vice versa.
     *
     * The string is called alternating if no two adjacent characters are equal. For example, the string "010" is
     * alternating, while the string "0100" is not.
     *
     * Return the minimum number of operations needed to make s alternating.
     *
     * Input: s = "0100"
     * Output: 1
     *
     * Input: s = "10"
     * Output: 0
     *
     * Input: s = "1111"
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^4
     * s[i] is either '0' or '1'.
     * @param s
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int minOperations(String s) {
        int n = s.length(), res = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 != s.charAt(i) - '0') res++;
        }
        return Math.min(res, n - res);
    }

    // S2
    // time = O(n), space = O(n)
    public int minOperations2(String s) {
        char[] chars = s.toCharArray();
        return Math.min(helper(chars, 1), helper(chars, 0));
    }

    private int helper(char[] chars, int t) {
        int n = chars.length, res = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0 && chars[i] - '0' != t || i % 2 == 1 && chars[i] - '0' == t) res++;
        }
        return res;
    }
}
