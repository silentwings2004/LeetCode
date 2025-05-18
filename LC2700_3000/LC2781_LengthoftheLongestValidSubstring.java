package LC2700_3000;
import java.util.*;
public class LC2781_LengthoftheLongestValidSubstring {
    /**
     * You are given a string word and an array of strings forbidden.
     *
     * A string is called valid if none of its substrings are present in forbidden.
     *
     * Return the length of the longest valid substring of the string word.
     *
     * A substring is a contiguous sequence of characters in a string, possibly empty.
     *
     * Input: word = "cbaaaabc", forbidden = ["aaa","cb"]
     * Output: 4
     *
     * Input: word = "leetcode", forbidden = ["de","le","e"]
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= word.length <= 10^5
     * word consists only of lowercase English letters.
     * 1 <= forbidden.length <= 10^5
     * 1 <= forbidden[i].length <= 10
     * forbidden[i] consists only of lowercase English letters.
     * @param word
     * @param forbidden
     * @return
     */
    // time = O(n * k), space = O(n + m)
    final int P = 131, N = 100010;
    long[] h, p;
    public int longestValidSubstring(String word, List<String> forbidden) {
        h = new long[N];
        p = new long[N];
        p[0] = 1;

        int n = word.length();
        for (int i = 1; i <= n; i++) {
            p[i] = p[i - 1] * P;
            h[i] = h[i - 1] * P + word.charAt(i - 1);
        }

        HashSet<Long> set = new HashSet<>();
        for (String s : forbidden) {
            long hash = 0;
            for (char c : s.toCharArray()) hash = hash * P + c;
            set.add(hash);
        }

        int[] ms = new int[n];
        for (int i = 0; i < n; i++) ms[i] = n - i;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 10; j++) {
                long hash = get(i, i + j - 1);
                if (set.contains((hash))) {
                    ms[i - 1] = Math.min(ms[i - 1], j - 1);
                    break;
                }
            }
        }

        int res = ms[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (ms[i] > ms[i + 1]) ms[i] = ms[i + 1] + 1;
            res = Math.max(res, ms[i]);
        }
        return res;
    }

    private long get(int l, int r) {
        return h[r] - h[l - 1] * p[r - l + 1];
    }

    // S2
    // time = O(L + n * k^2), space = O(L)
    public int longestValidSubstring2(String word, List<String> forbidden) {
        HashSet<String> set = new HashSet<>(forbidden);
        int ans = 0, l = 0, n = word.length();
        for (int r = 0; r < n; r++) {
            for (int i = r; i > Math.max(r - 10, l - 1); i--) {
                String t = word.substring(i, r + 1);
                if (set.contains(t)) {
                    l = i + 1;
                    break;
                }
            }
            ans = Math.max(ans, r - l + 1);
        }
        return ans;
    }
}
/**
 * forbidden[i].length <= 10
 * 暴力
 * 左端点一旦右移，绝对不会再左移了
 */