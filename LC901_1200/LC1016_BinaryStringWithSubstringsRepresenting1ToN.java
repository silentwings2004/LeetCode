package LC901_1200;
import java.util.*;
public class LC1016_BinaryStringWithSubstringsRepresenting1ToN {
    /**
     * Given a binary string s and a positive integer n, return true if the binary representation of all the integers
     * in the range [1, n] are substrings of s, or false otherwise.
     *
     * A substring is a contiguous sequence of characters within a string.
     *
     * Input: s = "0110", n = 3
     * Output: true
     *
     * Input: s = "0110", n = 4
     * Output: false
     *
     * Constraints:
     *
     * 1 <= s.length <= 1000
     * s[i] is either '0' or '1'.
     * 1 <= n <= 10^9
     * @param s
     * @param n
     * @return
     */
    // time = O(m^2), space = O(n)
    public boolean queryString(String s, int n) {
        HashSet<Integer> set = new HashSet<>();
        int m = s.length();
        for (int i = 0; i < m; i++) {
            int x = 0;
            for (int j = i; j < m; j++) {
                x = 2 * x + s.charAt(j) - '0';
                if (x > n) break;
                if (x > 0) set.add(x);
            }
        }
        return set.size() == n;
    }
}
/**
 * 总数最多只有1000 * 30 = 30000
 * 反过来考虑
 */