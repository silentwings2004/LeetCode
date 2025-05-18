package LC1501_1800;

public class LC1796_SecondLargestDigitinaString {
    /**
     * Given an alphanumeric string s, return the second largest numerical digit that appears in s, or -1 if it does
     * not exist.
     *
     * An alphanumeric string is a string consisting of lowercase English letters and digits.
     *
     * Input: s = "dfa12321afd"
     * Output: 2
     *
     * Input: s = "abc1111"
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= s.length <= 500
     * s consists of only lowercase English letters and/or digits.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int secondHighest(String s) {
        int n = s.length(), max = -1, min = -1;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                int u = c - '0';
                if (u >= max) {
                    if (u > max) min = max;
                    max = u;
                } else if (u >= min) min = u;
            }
        }
        if (max == -1 || min == -1 || max == min) return -1;
        return min;
    }
}
