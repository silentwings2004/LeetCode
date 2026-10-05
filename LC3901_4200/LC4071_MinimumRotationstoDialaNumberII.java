package LC3901_4200;

public class LC4071_MinimumRotationstoDialaNumberII {
    /**
     * You are given an integer n and a string s of length n consisting of digits.
     *
     * The dial contains the digits 0 through 9 in order and is circular, so 0 and 9 are adjacent. The pointer initially
     * points to 0.
     *
     * To dial each digit of s in order, rotate the pointer until it points to that digit. Each rotation moves the
     * pointer to an adjacent digit, and you may rotate in either direction. Dialing a digit that the pointer already
     * points to requires no rotations.
     *
     * Before dialing, you may perform the following operation at most once:
     *
     * Choose an index k such that 0 <= k < n and reverse the suffix s[k..n - 1].
     * Return the minimum total number of rotations needed to dial the string after optimally choosing whether to
     * perform the operation and which suffix to reverse.
     *
     * A suffix of a string is a contiguous sequence of characters that begins at any position in the string and extends
     * to its end.
     *
     * Input: n = 4, s = "1502"
     * Output: 9
     *
     * Input: n = 4, s = "2916"
     * Output: 12
     *
     * Input: n = 4, s = "4219"
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= n == s.length <= 10^5
     * s consists only of digits '0' to '9'
     * @param n
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public int minRotations(int n, String s) {
        int[] p = new int[n + 1];
        for (int i = 0, cur = 0; i < n; i++) {
            int u = s.charAt(i) - '0';
            p[i + 1] = p[i] + get(cur, u);
            cur = u;
        }

        int res = p[n];

        int[] r = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            int u = s.charAt(i) - '0', v = s.charAt(i + 1) - '0';
            r[i] = r[i + 1] + get(u, v);
        }

        int last = s.charAt(n - 1) - '0';
        for (int k = 0; k < n; k++) {
            int pre = k == 0 ? 0 : s.charAt(k - 1) - '0';
            int t = p[k] + get(pre, last) + r[k];
            res = Math.min(res, t);
        }
        return res;
    }

    private int get(int a, int b) {
        int d = Math.abs(a - b);
        return Math.min(d, 10 - d);
    }
}