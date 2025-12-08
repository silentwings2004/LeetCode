package LC3601_3900;

public class LC3756_ConcatenateNonZeroDigitsandMultiplybySumII {
    /**
     * You are given a string s of length m consisting of digits. You are also given a 2D integer array queries, where
     * queries[i] = [li, ri].
     *
     * For each queries[i], extract the substring s[li..ri]. Then, perform the following:
     *
     * Form a new integer x by concatenating all the non-zero digits from the substring in their original order. If
     * there are no non-zero digits, x = 0.
     * Let sum be the sum of digits in x. The answer is x * sum.
     * Return an array of integers answer where answer[i] is the answer to the ith query.
     *
     * Since the answers may be very large, return them modulo 10^9 + 7.
     *
     * Input: s = "10203004", queries = [[0,7],[1,3],[4,6]]
     * Output: [12340, 4, 9]
     *
     * Input: s = "1000", queries = [[0,3],[1,1]]
     * Output: [1, 0]
     *
     * Input: s = "9876543210", queries = [[0,9]]
     * Output: [444444137]
     *
     * Constraints:
     *
     * 1 <= m == s.length <= 10^5
     * s consists of digits only.
     * 1 <= queries.length <= 10^5
     * queries[i] = [li, ri]
     * 0 <= li <= ri < m
     * @param s
     * @param queries
     * @return
     */
    // time = O(n + m + q), space = O(n + m)
    public int[] sumAndMultiply(String s, int[][] queries) {
        long mod = (long)(1e9 + 7);
        int n = s.length(), m = 0;
        int[] tmp = new int[n], cnt = new int[n + 1];
        for (int i = 0; i < n; i++) {
            cnt[i + 1] = cnt[i];
            int u = s.charAt(i) - '0';
            if (u > 0) {
                tmp[m++] = u;
                cnt[i + 1]++;
            }
        }
        int[] a = new int[m];
        for (int i = 0; i < m; i++) a[i] = tmp[i];
        long[] p = new long[m + 2];
        p[0] = 1;
        for (int i = 1; i <= m + 1; i++) p[i] = p[i - 1] * 10 % mod;

        long[] pr = new long[m + 1], ps = new long[m + 1];
        for (int i = 1; i <= m; i++) {
            pr[i] = (pr[i - 1] * 10 + a[i - 1]) % mod;
            ps[i] = ps[i - 1] + a[i - 1];
        }

        int q = queries.length;
        int[] res = new int[q];
        for (int i = 0; i < q; i++) {
            int l = queries[i][0], r = queries[i][1];
            int d = cnt[r + 1] - cnt[l];
            if (d == 0) continue;
            long x = (pr[cnt[r + 1]] - (pr[cnt[l]] * p[d] % mod) + mod) % mod;
            long sum = (ps[cnt[r + 1]] - ps[cnt[l]] + mod) % mod;
            res[i] = (int)(x * sum % mod);
        }
        return res;
    }
}