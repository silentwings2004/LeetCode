package LC1501_1800;

import java.util.HashMap;
import java.util.HashSet;

public class LC1540_CanConvertStringinKMoves {
    /**
     * Given two strings s and t, your goal is to convert s into t in k moves or less.
     *
     * During the ith (1 <= i <= k) move you can:
     *
     * Choose any index j (1-indexed) from s, such that 1 <= j <= s.length and j has not been chosen in any previous
     * move, and shift the character at that index i times.
     * Do nothing.
     * Shifting a character means replacing it by the next letter in the alphabet (wrapping around so that 'z' becomes
     * 'a'). Shifting a character by i means applying the shift operations i times.
     *
     * Remember that any index j can be picked at most once.
     *
     * Return true if it's possible to convert s into t in no more than k moves, otherwise return false.
     *
     * Input: s = "input", t = "ouput", k = 9
     * Output: true
     *
     * Input: s = "abc", t = "bcd", k = 10
     * Output: false
     *
     * Input: s = "aab", t = "bbb", k = 27
     * Output: true
     *
     * Constraints:
     *
     * 1 <= s.length, t.length <= 10^5
     * 0 <= k <= 10^9
     * s, t contain only lowercase English letters.
     * @param s
     * @param t
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public boolean canConvertString(String s, String t, int k) {
        int m = s.length(), n = t.length();
        if (m != n) return false;
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                int shift = (t.charAt(i) - s.charAt(i) + 26) % 26;
                if (shift > k) return false;
                cnt[shift]++;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (cnt[i] > 0) {
                int v = cnt[i] - 1;
                if (i + 26 * v > k) return false;
            }
        }
        return true;
    }
}
