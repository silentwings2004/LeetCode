package LC3601_3900;

public class LC3713_LongestBalancedSubstringI {
    /**
     * You are given a string s consisting of lowercase English letters.
     *
     * A substring of s is called balanced if all distinct characters in the substring appear the same number of times.
     *
     * Return the length of the longest balanced substring of s.
     *
     * A substring is a contiguous non-empty sequence of characters within a string.
     *
     * Input: s = "abbac"
     * Output: 4
     *
     * Input: s = "zzabccy"
     * Output: 4
     *
     * Input: s = "aba"
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= s.length <= 1000
     * s consists of lowercase English letters.
     * @param s
     * @return
     */
    // S1
    // time = O(n^2 * 26), space = O(26 * n)
    public int longestBalanced(String s) {
        int n = s.length();
        int[][] cnt = new int[n + 1][26];
        for (int i = 1; i <= n; i++) {
            cnt[i] = cnt[i - 1].clone();
            int u = s.charAt(i - 1) - 'a';
            cnt[i][u]++;
        }

        int res = 1;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (check(cnt, i, j)) res = Math.max(res, j - i + 1);
            }
        }
        return res;
    }

    private boolean check(int[][] cnt, int l, int r) {
        int t = -1;
        for (int i = 0; i < 26; i++) {
            int d = cnt[r + 1][i] - cnt[l][i];
            if (d == 0) continue;
            if (t == -1) t = d;
            else {
                if (d == t) continue;
                return false;
            }
        }
        return true;
    }

    // S2
    // time = O(n^2 * 26), space = O(26)
    public int longestBalanced2(String s) {
        int n = s.length(), res = 0;
        for (int i = 0; i < n; i++) {
            int[] cnt = new int[26];
            int mx = 0, kinds = 0;
            for (int j = i; j < n; j++) {
                int u = s.charAt(j) - 'a';
                if (cnt[u] == 0) kinds++;
                mx = Math.max(mx, ++cnt[u]);
                if (mx * kinds == j - i + 1) res = Math.max(res, j - i + 1);
            }
        }
        return res;
    }
}
