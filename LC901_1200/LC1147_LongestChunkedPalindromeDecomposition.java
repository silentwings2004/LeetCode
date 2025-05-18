package LC901_1200;

public class LC1147_LongestChunkedPalindromeDecomposition {
    /**
     * You are given a string text. You should split it to k substrings (subtext1, subtext2, ..., subtextk) such that:
     *
     * subtexti is a non-empty string.
     * The concatenation of all the substrings is equal to text (i.e., subtext1 + subtext2 + ... + subtextk == text).
     * subtexti == subtextk - i + 1 for all valid values of i (i.e., 1 <= i <= k).
     * Return the largest possible value of k.
     *
     * Input: text = "ghiabcdefhelloadamhelloabcdefghi"
     * Output: 7
     *
     * Input: text = "merchant"
     * Output: 1
     *
     * Input: text = "antaprezatepzapreanta"
     * Output: 11
     *
     * Constraints:
     *
     * 1 <= text.length <= 1000
     * text consists only of lowercase English characters.
     * @param text
     * @return
     */
    // S1
    // time = O(n^2), space = O(n)
    public int longestDecomposition(String text) {
        if (text.length() == 0) return 0;

        int n = text.length();
        for (int i = 1; i * 2 <= n; i++) {
            if (text.substring(0, i).equals(text.substring(n - i))) {
                return 2 + longestDecomposition(text.substring(i, n - i));
            }
        }
        return 1;
    }

    // S2
    // time = O(n), space = O(n)
    final int P = 131;
    long[] h, p;
    public int longestDecomposition2(String text) {
        int n = text.length();
        text = "#" + text;
        h = new long[n + 1];
        p = new long[n + 1];
        h[0] = p[0] = 1;
        for (int i = 1; i <= n; i++) {
            p[i] = p[i - 1] * P;
            h[i] = h[i - 1] * P + text.charAt(i);
        }

        int res = 0;
        for (int i = 1, j = 1; i <= n; i++) {
            if (get(j, i) == get(n + 1 - i, n + 1 - j)) {
                res++;
                j = i + 1;
            }
        }
        return res;
    }

    private long get(int l, int r) {
        return h[r] - h[l - 1] * p[r - l + 1];
    }
}
/**
 * 从前后端找到最短的两段相等，然后删掉，一定能得到最优解，再继续递归
 *
 */