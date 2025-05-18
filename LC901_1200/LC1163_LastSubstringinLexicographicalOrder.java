package LC901_1200;

public class LC1163_LastSubstringinLexicographicalOrder {
    /**
     * Given a string s, return the last substring of s in lexicographical order.
     *
     * Input: s = "abab"
     * Output: "bab"
     *
     * Input: s = "leetcode"
     * Output: "tcode"
     *
     * Constraints:
     *
     * 1 <= s.length <= 4 * 10^5
     * s contains only lowercase English letters.
     * @param s
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public String lastSubstring(String s) {
        int n = s.length();
        int i = 0, j = 1, k = 0;
        while (j + k < n) {
            if (s.charAt(i + k) == s.charAt(j + k)) {
                k++;
                continue;
            } else if (s.charAt(i + k) > s.charAt(j + k)) j += k + 1;
            else i += k + 1;
            if (i == j) j++;
            k = 0;
        }
        return s.substring(i);
    }

    // S2: 字符串哈希
    // time = O(n * logn), space = O(n)
    final int P = 131, N = 400010;
    long[] h, p;
    public String lastSubstring2(String s) {
        int n = s.length();
        h = new long[N];
        p = new long[N];
        p[0] = 1;
        for (int i = 1; i <= n; i++) {
            p[i] = p[i - 1] * P;
            h[i] = h[i - 1] * P + s.charAt(i - 1);
        }

        int pos = 1;
        for (int i = 2; i <= n; i++) {
            if (cmp(pos, i, s)) pos = i;
        }
        return s.substring(pos - 1);
    }

    private boolean cmp(int a, int b, String s) {
        int n = s.length();
        int l = 0, r = n - b + 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (get(a, a + mid - 1) == get(b, b + mid - 1)) l = mid;
            else r = mid - 1;
        }
        if (r == n - b + 1) return false;
        return s.charAt(a + r - 1) < s.charAt(b + r - 1);
    }

    private long get(int l, int r) {
        return h[r] - h[l - 1] * p[r - l + 1];
    }
}