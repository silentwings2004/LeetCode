package LC3901_4200;

public class LC4021_MinimumOperationstoMakeaRotatedPalindromeI {
    /**
     * You are given a string s consisting of lowercase English letters.
     *
     * You can perform the following operations any number of times (including zero) and in any order:
     *
     * Increment: Choose any index i and replace s[i] with the next lowercase English letter. The letter after 'z' is 'a'.
     * Left rotate: Move the first character of the string to the end.
     * Return the minimum number of operations required to make s a palindrome.
     *
     * A palindrome is a string that reads the same forward and backward.
     *
     * Input: s = "abc"
     * Output: 2
     *
     * Input: s = "yb"
     * Output: 3
     *
     * Constraints:
     *
     * 2 <= s.length <= 2000
     * s consists only of lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n^2), space = O(1)
    public int minOperations(String s) {
        int n = s.length(), res = 0x3f3f3f3f;
        for (int k = 0; k < n; k++) {
            int t = k;
            for (int i = 0; i < n / 2; i++) {
                int l = s.charAt((i + k) % n) - 'a', r = s.charAt((n - 1 - i + k) % n) - 'a';
                int d1 = (l - r + 26) % 26, d2 = (r - l + 26) % 26;
                t += Math.min(d1, d2);
                if (t >= res) break;
            }
            res = Math.min(res, t);
        }
        return res;
    }
}
/**
 * 2 cases:
 * 1. y - x
 * 2. 26 - (y - x)
 */