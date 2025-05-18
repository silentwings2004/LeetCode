package LC3301_3600;

public class LC3461_CheckIfDigitsAreEqualinStringAfterOperationsI {
    /**
     * You are given a string s consisting of digits. Perform the following operation repeatedly until the string has
     * exactly two digits:
     *
     * For each pair of consecutive digits in s, starting from the first digit, calculate a new digit as the sum of the
     * two digits modulo 10.
     * Replace s with the sequence of newly calculated digits, maintaining the order in which they are computed.
     * Return true if the final two digits in s are the same; otherwise, return false.
     *
     * Input: s = "3902"
     * Output: true
     *
     * Input: s = "34789"
     * Output: false
     *
     * Constraints:
     *
     * 3 <= s.length <= 100
     * s consists of only digits.
     * @param s
     * @return
     */
    // time = O(n^2), space = O(n)
    public boolean hasSameDigits(String s) {
        while (s.length() > 2) {
            StringBuilder sb = new StringBuilder();
            int n = s.length();
            for (int i = 0; i + 1 < n; i++) {
                int x = s.charAt(i) - '0', y = s.charAt(i + 1) - '0';
                sb.append((x + y) % 10);
            }
            s = sb.toString();
        }
        return s.charAt(0) == s.charAt(1);
    }
}