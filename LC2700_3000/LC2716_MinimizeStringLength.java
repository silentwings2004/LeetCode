package LC2700_3000;
import java.util.*;
public class LC2716_MinimizeStringLength {
    /**
     * Given a 0-indexed string s, repeatedly perform the following operation any number of times:
     *
     * Choose an index i in the string, and let c be the character in position i. Delete the closest occurrence of c to
     * the left of i (if any) and the closest occurrence of c to the right of i (if any).
     * Your task is to minimize the length of s by performing the above operation any number of times.
     *
     * Return an integer denoting the length of the minimized string.
     *
     * Input: s = "aaabc"
     * Output: 3
     *
     * Input: s = "cbbd"
     * Output: 3
     *
     * Input: s = "dddaaa"
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * s contains only lowercase English letters
     * @param s
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int minimizedStringLength(String s) {
        HashSet<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) set.add(c);
        return set.size();
    }

    // S2: bit
    // time = O(n), space = O(1)
    public int minimizedStringLength2(String s) {
        int n = s.length(), x = 0;
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            x |= 1 << u;
        }
        return Integer.bitCount(x);
    }
}