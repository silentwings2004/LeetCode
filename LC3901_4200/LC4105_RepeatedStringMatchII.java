package LC3901_4200;
import java.util.*;
public class LC4105_RepeatedStringMatchII {
    /**
     * You are given two strings a and b consisting of lowercase English letters, and two
     * integers x and y.
     *
     * Return true if the string formed by repeating b exactly y times (b * y) is a substring of the string formed by
     * repeating a exactly x times (a * x). Otherwise, return false.
     *
     * A substring is a contiguous non-empty sequence of characters within a string.
     *
     * Input: a = "ab", b = "abab", x = 2, y = 1
     * Output: true
     *
     * Input: a = "abc", b = "bc", x = 2, y = 3
     * Output: false
     *
     * Input: a = "abcd", b = "cdab", x = 2, y = 1
     * Output: true
     *
     * Constraints:
     *
     * 1 <= a.length <= 100
     * 1 <= b.length <= 100
     * 1 <= x <= 10^9
     * 1 <= y <= 10^9
     * a and b consist of lowercase English letters.
     * @param a
     * @param b
     * @param x
     * @param y
     * @return
     */
    // time = O(n * m + n^2), space = O(n)
    public boolean repeatedStringMatch(String a, String b, int x, int y) {
        int n = a.length(), m = b.length();
        long len1 = 1L * n * x, len2 = 1L * m * y;
        if (len2 > len1) return false;

        boolean[] st = new boolean[n];
        Arrays.fill(st, true);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (a.charAt((i + j) % n) != b.charAt(j)) {
                    st[i] = false;
                    break;
                }
            }
        }

        int cnt = Math.min(y, n / gcd(n, m));
        for (int i = 0; i < n; i++) {
            if (i + len2 > len1) break;
            boolean ok = true;
            int p = i;
            for (int j = 0; j < cnt; j++) {
                if (!st[p]) {
                    ok = false;
                    break;
                }
                p = (p + m) % n;
            }
            if (ok) return true;
        }
        return false;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}