package LC1201_1500;
import java.util.*;
public class LC1316_DistinctEchoSubstrings {
    /**
     * Return the number of distinct non-empty substrings of text that can be written as the concatenation of some
     * string with itself (i.e. it can be written as a + a where a is some string).
     *
     * Input: text = "abcabcabc"
     * Output: 3
     *
     * Input: text = "leetcodeleetcode"
     * Output: 2
     *
     * onstraints:
     *
     * 1 <= text.length <= 2000
     * text has only lowercase English letters.
     * @param text
     * @return
     */
    // time = O(n^2), space = O(n)
    final int P = 131;
    long[] h, p;
    public int distinctEchoSubstrings(String text) {
        int n = text.length();
        h = new long[n + 1];
        p = new long[n + 1];
        p[0] = 1;
        for (int i = 1; i <= n; i++) {
            p[i] = p[i - 1] * P;
            h[i] = h[i - 1] * P + text.charAt(i - 1);
        }

        HashSet<Long> set = new HashSet<>();
        for (int len = 1; len <= n / 2; len++) {
            for (int i = 0; i + 2 * len - 1 < n; i++) {
                long l = get(i, i + len - 1), r = get(i + len, i + len * 2 - 1);
                if (l == r) set.add(l);
            }
        }
        return set.size();
    }

    private long get(int l, int r) {
        l++;
        r++;
        return h[r] - h[l - 1] * p[r - l + 1];
    }
}