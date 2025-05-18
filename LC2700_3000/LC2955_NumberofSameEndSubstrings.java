package LC2700_3000;

public class LC2955_NumberofSameEndSubstrings {
    /**
     * You are given a 0-indexed string s, and a 2D array of integers queries, where queries[i] = [li, ri] indicates a
     * substring of s starting from the index li and ending at the index ri (both inclusive), i.e. s[li..ri].
     *
     * Return an array ans where ans[i] is the number of same-end substrings of queries[i].
     *
     * A 0-indexed string t of length n is called same-end if it has the same character at both of its ends, i.e.,
     * t[0] == t[n - 1].
     *
     * A substring is a contiguous non-empty sequence of characters within a string.
     *
     * Input: s = "abcaab", queries = [[0,0],[1,4],[2,5],[0,5]]
     * Output: [1,5,5,10]
     *
     * Input: s = "abcd", queries = [[0,3]]
     * Output: [4]
     *
     * Constraints:
     *
     * 2 <= s.length <= 3 * 10^4
     * s consists only of lowercase English letters.
     * 1 <= queries.length <= 3 * 10^4
     * queries[i].length == 2
     * queries[i] = [li, ri]
     * 0 <= li <= ri < s.length
     * @param s
     * @param queries
     * @return
     */
    // time = O(n + m), space = O(n)
    public int[] sameEndSubstringCount(String s, int[][] queries) {
        int n = s.length(), m = queries.length;
        int[][] cnt = new int[n + 1][26];
        for (int i = 1; i <= n; i++) {
            cnt[i] = cnt[i - 1].clone();
            int u = s.charAt(i - 1) - 'a';
            cnt[i][u]++;
        }
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int l = queries[i][0], r = queries[i][1];
            for (int j = 0; j < 26; j++) {
                int x = cnt[r + 1][j] - cnt[l][j];
                if (x == 0) continue;
                res[i] += (1 + x) * x / 2;
            }
        }
        return res;
    }
}
