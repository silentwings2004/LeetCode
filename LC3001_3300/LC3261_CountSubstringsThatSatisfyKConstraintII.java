package LC3001_3300;

public class LC3261_CountSubstringsThatSatisfyKConstraintII {
    /**
     * You are given a binary string s and an integer k.
     *
     * You are also given a 2D integer array queries, where queries[i] = [li, ri].
     *
     * A binary string satisfies the k-constraint if either of the following conditions holds:
     *
     * The number of 0's in the string is at most k.
     * The number of 1's in the string is at most k.
     * Return an integer array answer, where answer[i] is the number of substrings of s[li..ri] that satisfy the
     * k-constraint.
     *
     * Input: s = "0001111", k = 2, queries = [[0,6]]
     *
     * Output: [26]
     *
     * Input: s = "010101", k = 1, queries = [[0,5],[1,4],[2,3]]
     *
     * Output: [15,9,3]
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s[i] is either '0' or '1'.
     * 1 <= k <= s.length
     * 1 <= queries.length <= 10^5
     * queries[i] == [li, ri]
     * 0 <= li <= ri < s.length
     * All queries are distinct.
     * @param s
     * @param k
     * @param queries
     * @return
     */
    // time = O(n + qlogn), space = O(n)
    public long[] countKConstraintSubstrings(String s, int k, int[][] queries) {
        int n = s.length(), m = queries.length;
        int[] cnt = new int[2];
        int[] left = new int[n];
        long[] sum = new long[n + 1];
        for (int i = 0, j = 0; i < n; i++) {
            int x = s.charAt(i) - '0';
            cnt[x]++;
            while (cnt[0] > k && cnt[1] > k) cnt[s.charAt(j++) - '0']--;
            left[i] = j;
            sum[i + 1] = sum[i] + i - j + 1;
        }

        long[] res = new long[m];
        for (int i = 0; i < m; i++) {
            int l = queries[i][0], r = queries[i][1];
            int p = lower_bound(left, l, r);
            int len = p - l;
            res[i] += sum[r + 1] - sum[p] + (long)(len + 1) * len / 2;
        }
        return res;
    }

    private int lower_bound(int[] left, int l, int r) {
        int t = l;
        while (l < r) {
            int mid = l + r >> 1;
            if (left[mid] >= t) r = mid;
            else l = mid + 1;
        }
        return left[r] >= t ? r : r + 1;
    }
}