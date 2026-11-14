package LC3901_4200;
import java.util.*;
public class LC4102_CountValidSubstringsI {
    /**
     * You are given a string s consisting only of the characters 'a', 'b', and 'c'.
     *
     * A string is called valid if it does not contain "abc" as a substring.
     *
     * Return the number of valid substrings in s. Substrings with different starting or ending indices are counted
     * separately.
     *
     * A substring is a contiguous non-empty sequence of characters within a string.
     *
     * Input: s = "abac"
     * Output: 10
     *
     * Input: s = "abc"
     * Output: 5
     *
     * Input: s = "ac"
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s only consists of 'a', 'b', and 'c' characters.
     * @param s
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public long countValidSubstrings(String s) {
        int n = s.length();
        long res = 0;
        for (int i = 0, j = 0; i < n; i++) {
            if (i - 2 >= 0 && s.charAt(i - 2) == 'a' && s.charAt(i - 1) == 'b' && s.charAt(i) == 'c') {
                j = Math.max(j, i - 1);
            }
            res += i - j + 1;
        }
        return res;
    }

    // S2
    // time = O(nlogn), space = O(n)
    public long countValidSubstrings2(String s) {
        int n = s.length();
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i + 2 < n; i++) {
            int u = s.charAt(i) - 'a';
            int v = s.charAt(i + 1) - 'a';
            int w = s.charAt(i + 2) - 'a';
            if (u == 0 && v == 1 && w == 2) set.add(i);
        }

        long res = 0;
        for (int i = 0; i < n; i++) {
            Integer ck = set.ceiling(i);
            if (ck == null) res += n - i;
            else res += ck + 2 - i;
        }
        return res;
    }
}