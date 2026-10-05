package LC3901_4200;

public class LC4070_MinimumRotationstoDialaNumberI {
    /**
     * You are given a string s of length 10 consisting of digits.
     *
     * The dial contains the digits 0 through 9 in order and is circular, so 0 and 9 are adjacent. The pointer initially
     * points to 0.
     *
     * To dial each digit of s in order, rotate the pointer until it points to that digit. Each rotation moves the
     * pointer to an adjacent digit, and you may rotate in either direction. Dialing a digit that the pointer already
     * points to requires no rotations.
     *
     * Return the minimum total number of rotations needed to dial every digit of s.
     *
     * Input: s = "0192837465"
     * Output: 25
     *
     * Input: s = "1200210200"
     * Output: 12
     *
     * Constraints:
     *
     * s.length == 10
     * s consists only of digits '0' to '9'
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public int minRotations(String s) {
        int n = s.length(), res = 0;
        for (int i = 0, cur = 0; i < n; i++) {
            int u = s.charAt(i) - '0';
            int d = Math.abs(u - cur);
            res += Math.min(d, 10 - d);
            cur = u;
        }
        return res;
    }
}