package LC2700_3000;
import java.util.*;
public class LC2983_PalindromeRearrangementQueries {
    /**
     * You are given a 0-indexed string s having an even length n.
     *
     * You are also given a 0-indexed 2D integer array, queries, where queries[i] = [ai, bi, ci, di].
     *
     * For each query i, you are allowed to perform the following operations:
     *
     * Rearrange the characters within the substring s[ai:bi], where 0 <= ai <= bi < n / 2.
     * Rearrange the characters within the substring s[ci:di], where n / 2 <= ci <= di < n.
     * For each query, your task is to determine whether it is possible to make s a palindrome by performing the
     * operations.
     *
     * Each query is answered independently of the others.
     *
     * Return a 0-indexed array answer, where answer[i] == true if it is possible to make s a palindrome by performing
     * operations specified by the ith query, and false otherwise.
     *
     * A substring is a contiguous sequence of characters within a string.
     * s[x:y] represents the substring consisting of characters from the index x to index y in s, both inclusive.
     *
     * Input: s = "abcabc", queries = [[1,1,3,5],[0,2,5,5]]
     * Output: [true,true]
     *
     * Input: s = "abbcdecbba", queries = [[0,2,7,9]]
     * Output: [false]
     *
     * Input: s = "acbcab", queries = [[1,2,4,5]]
     * Output: [true]
     *
     * Constraints:
     *
     * 2 <= n == s.length <= 10^5
     * 1 <= queries.length <= 10^5
     * queries[i].length == 4
     * ai == queries[i][0], bi == queries[i][1]
     * ci == queries[i][2], di == queries[i][3]
     * 0 <= ai <= bi < n / 2
     * n / 2 <= ci <= di < n
     * n is even.
     * s consists of only lowercase English letters.
     * @param s
     * @param queries
     * @return
     */
    // time = O(n + 26m), space = O(26n)
    public boolean[] canMakePalindromeQueries(String s, int[][] queries) {
        int n = s.length() / 2;
        String s1 = s.substring(0, n), s2 = reverse(s.substring(n));
        int[][] cnt1 = new int[n + 1][26], cnt2 = new int[n + 1][26];
        for (int i = 1; i <= n; i++) {
            cnt1[i] = cnt1[i - 1].clone();
            cnt2[i] = cnt2[i - 1].clone();
            int u = s1.charAt(i - 1) - 'a', v = s2.charAt(i - 1) - 'a';
            cnt1[i][u]++;
            cnt2[i][v]++;
        }

        int[] w = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            w[i] = w[i - 1] + (s1.charAt(i - 1) == s2.charAt(i - 1) ? 0 : 1);
        }

        int m = queries.length;
        boolean[] res = new boolean[m];
        for (int i = 0; i < m; i++) {
            int a = queries[i][0], b = queries[i][1], c = queries[i][2], d = queries[i][3];
            // convert c, d
            c -= n;
            d -= n;
            c = n - 1 - c;
            d = n - 1 - d;
            // swap c & d
            int t = c;
            c = d;
            d = t;
            if (a <= c) res[i] = check(a, b, c, d, cnt1, cnt2, w);
            else res[i] = check(c, d, a, b, cnt2, cnt1, w);
        }
        return res;
    }

    private boolean check(int a, int b, int c, int d, int[][] cnt1, int[][] cnt2, int[] w) {
        int n = w.length - 1;
        if (w[a] > 0 || w[n] - w[Math.max(b, d) + 1] > 0) return false;
        // case 1: contain
        if (b >= d) return Arrays.equals(cal(cnt1, a, b), cal(cnt2, a, b));
        // case 2: no intersection
        if (b < c) {
            boolean op1 = w[c] - w[b + 1] == 0; // [b + 1, c - 1]
            boolean op2 = Arrays.equals(cal(cnt1, a, b), cal(cnt2, a, b));
            boolean op3 = Arrays.equals(cal(cnt1, c, d), cal(cnt2, c, d));
            return op1 && op2 && op3;
        }
        // case 3: intersect but not contain
        int[] s1 = sub(cal(cnt1, a, b), cal(cnt2, a, c - 1));
        int[] s2 = sub(cal(cnt2, c, d), cal(cnt1, b + 1, d));
        return s1 != null && s2 != null && Arrays.equals(s1, s2);
    }

    private int[] cal(int[][] cnt, int l, int r) {
        int[] res = new int[26];
        for (int i = 0; i < 26; i++) res[i] = cnt[r + 1][i] - cnt[l][i];
        return res;
    }

    private int[] sub(int[] a, int[] b) {
        int[] res = new int[26];
        for (int i = 0; i < 26; i++) {
            res[i] = a[i] - b[i];
            if (res[i] < 0) return null;
        }
        return res;
    }

    private String reverse(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }
}