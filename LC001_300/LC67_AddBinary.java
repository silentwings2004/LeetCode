package LC001_300;
import java.util.*;
public class LC67_AddBinary {
    /**
     * Given two binary strings a and b, return their sum as a binary string.
     *
     * Input: a = "11", b = "1"
     * Output: "100"
     *
     * Input: a = "1010", b = "1011"
     * Output: "10101"
     *
     * Constraints:
     *
     * 1 <= a.length, b.length <= 10^4
     * a and b consist only of '0' or '1' characters.
     * Each string does not contain leading zeros except for the zero itself.
     *
     * @param a
     * @param b
     * @return
     */
    // time = O(max(m, n)), space = O(max(m, n))
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int m = a.length(), n = b.length(), t = 0;
        for (int i = m - 1, j = n - 1; i >= 0 || j >= 0 || t > 0; i--, j--) {
            if (i >= 0) t += a.charAt(i) - '0';
            if (j >= 0) t += b.charAt(j) - '0';
            sb.append(t % 2);
            t /= 2;
        }
        return sb.reverse().toString();
    }
}
/**
 * very similar to LC2 and LC66
 */