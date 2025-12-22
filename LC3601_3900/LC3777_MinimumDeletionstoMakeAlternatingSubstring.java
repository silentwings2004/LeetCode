package LC3601_3900;
import java.util.*;
public class LC3777_MinimumDeletionstoMakeAlternatingSubstring {
    /**
     * You are given a string s of length n consisting only of the characters 'A' and 'B'.
     *
     * You are also given a 2D integer array queries of length q, where each queries[i] is one of the following:
     *
     * [1, j]: Flip the character at index j of s i.e. 'A' changes to 'B' (and vice versa). This operation mutates s and
     * affects subsequent queries.
     * [2, l, r]: Compute the minimum number of character deletions required to make the substring s[l..r] alternating.
     * This operation does not modify s; the length of s remains n.
     * A substring is alternating if no two adjacent characters are equal. A substring of length 1 is always alternating.
     *
     * Return an integer array answer, where answer[i] is the result of the ith query of type [2, l, r].
     *
     * Input: s = "ABA", queries = [[2,1,2],[1,1],[2,0,2]]
     * Output: [0,2]
     *
     * Input: s = "ABB", queries = [[2,0,2],[1,2],[2,0,2]]
     * Output: [1,0]
     *
     * Input: s = "BABA", queries = [[2,0,3],[1,1],[2,1,3]]
     * Output: [0,1]
     *
     * Constraints:
     *
     * 1 <= n == s.length <= 10^5
     * s[i] is either 'A' or 'B'.
     * 1 <= q == queries.length <= 10^5
     * queries[i].length == 2 or 3
     * queries[i] == [1, j] or,
     * queries[i] == [2, l, r]
     * 0 <= j <= n - 1
     * 0 <= l <= r <= n - 1
     * @param s
     * @param queries
     * @return
     */
    // time = O((n + q) * logn), space = O(n + q)
    public int[] minDeletions(String s, int[][] queries) {
        int n = s.length();
        Fenwick fen = new Fenwick(n);
        char[] t = s.toCharArray();
        for (int i = 1; i < n; i++) {
            if (t[i] == t[i - 1]) fen.add(i, 1);
        }

        List<Integer> p = new ArrayList<>();
        for (int[] q : queries) {
            if (q[0] == 1) {
                int j = q[1];
                if (j > 0 && t[j] == t[j - 1]) fen.add(j, -1);
                if (j + 1 < n && t[j] == t[j + 1]) fen.add(j + 1, -1);
                t[j] = t[j] == 'A' ? 'B' : 'A';
                if (j > 0 && t[j] == t[j - 1]) fen.add(j, 1);
                if (j + 1 < n && t[j] == t[j + 1]) fen.add(j + 1, 1);
            } else {
                int l = q[1], r = q[2];
                p.add(fen.rangeSum(l + 1, r + 1));
            }
        }
        int[] res = new int[p.size()];
        for (int i = 0; i < p.size(); i++) res[i] = p.get(i);
        return res;
    }

    class Fenwick {
        private int n;
        private int[] a;
        public Fenwick(int n) {
            this.n = n;
            this.a = new int[n + 1];
        }

        private void add(int x, int v) {
            for (int i = x + 1; i <= n; i += i & -i) {
                a[i - 1] = a[i - 1] + v;
            }
        }

        private int sum(int x) {
            int ans = 0;
            for (int i = x; i > 0; i -= i & -i) {
                ans = ans + a[i - 1];
            }
            return ans;
        }

        private int rangeSum(int l, int r) { // 左开右闭
            return sum(r) - sum(l);
        }
    }
}
/**
 * 如果发现 s[i−1] != s[i]，人为规定删除右边的 s[i]。
 * 这启发我们定义长为 n−1 的数组 b（下标从 1 到 n−1）：
 * b[i] = 0 if s[i-1] != s[i]
 * b[i] = 1 if s[i-1] == s[i]
 * 子串 [l,r] 的删除次数，等于 b 的子数组 [l+1,r] 中的 1 的个数，也等于 b 的子数组 [l+1,r] 的元素和。
 * 由于反转 s[i] 会影响 b[i] 和 b[i+1]，需要用树状数组维护。
 * 反转 s[i] 时，先撤销被影响的 b[i] 和 b[i+1]，然后反转，然后添加新的 b[i] 和 b[i+1]。
 */