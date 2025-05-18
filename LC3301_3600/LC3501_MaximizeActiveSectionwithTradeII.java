package LC3301_3600;
import java.util.*;
public class LC3501_MaximizeActiveSectionwithTradeII {
    /**
     * You are given a binary string s of length n, where:
     *
     * '1' represents an active section.
     * '0' represents an inactive section.
     *
     * You can perform at most one trade to maximize the number of active sections in s. In a trade, you:
     *
     * Convert a contiguous block of '1's that is surrounded by '0's to all '0's.
     * Afterward, convert a contiguous block of '0's that is surrounded by '1's to all '1's.
     * Additionally, you are given a 2D array queries, where queries[i] = [li, ri] represents a substring s[li...ri].
     *
     * For each query, determine the maximum possible number of active sections in s after making the optimal trade on
     * the substring s[li...ri].
     *
     * Return an array answer, where answer[i] is the result for queries[i].
     *
     * A substring is a contiguous non-empty sequence of characters within a string.
     *
     * Note
     *
     * For each query, treat s[li...ri] as if it is augmented with a '1' at both ends, forming t = '1' + s[li...ri] +
     * '1'. The augmented '1's do not contribute to the final count.
     * The queries are independent of each other.
     *
     * Input: s = "01", queries = [[0,1]]
     * Output: [1]
     *
     * Input: s = "0100", queries = [[0,3],[0,2],[1,3],[2,3]]
     * Output: [4,3,1,1]
     *
     * Input: s = "1000100", queries = [[1,5],[0,6],[0,4]]
     * Output: [6,7,2]
     *
     * Input: s = "01010", queries = [[0,3],[1,4],[1,3]]
     * Output: [4,4,2]
     *
     * Constraints:
     *
     * 1 <= n == s.length <= 10^5
     * 1 <= queries.length <= 10^5
     * s[i] is either '0' or '1'.
     * queries[i] = [li, ri]
     * 0 <= li <= ri < n
     * @param s
     * @param queries
     * @return
     */
    // time = O(nlogn), space = O(n)
    final int M = 20, inf = 0x3f3f3f3f;
    int[][] f;
    List<int[]> q;
    String s;
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        this.s = s;
        int n = s.length(), sum = 0;
        for (int i = 0; i < n; i++) sum += s.charAt(i) - '0';
        q = new ArrayList<>();
        for (int i = 0, j = 0; i < n; i++) {
            if (i == n - 1 || s.charAt(i) != s.charAt(i + 1)) {
                q.add(new int[]{j, i - j + 1});
                j = i + 1;
            }
        }
        int m = q.size();
        f = new int[M][m];
        for (int i = 0; i < M; i++) Arrays.fill(f[i], -inf);
        for (int i = 0; i < m; i++) {
            if (s.charAt(q.get(i)[0]) == '0' && i + 2 < m) f[0][i] = q.get(i)[1] + q.get(i + 2)[1];
        }

        for (int p = 1, len = 2; p < M; p++, len *= 2) {
            for (int i = 0, j = len - 1; j < m; i++, j++) {
                f[p][i] = Math.max(f[p - 1][i], f[p - 1][i + len / 2]);
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int[] que : queries) {
            int l = que[0], r = que[1];
            int bl = find(l);
            int br = find(r);
            if (br - bl + 1 <= 2) {
                res.add(sum);
                continue;
            }
            int best = Math.max(query(bl + 1, br - 3), 0);
            best = Math.max(best, cal(l, r, bl, br, bl));
            best = Math.max(best, cal(l, r, bl, br, br - 2));
            res.add(sum + best);
        }
        return res;
    }

    private int find(int x) {
        int l = 0, r = q.size() - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (q.get(mid)[0] <= x) l = mid + 1;
            else r = mid;
        }
        return q.get(r)[0] <= x ? r : r - 1;
    }

    private int query(int l, int r) {
        if (l > r) return -inf;
        int p = 31 - Integer.numberOfLeadingZeros(r - l + 1);
        return Math.max(f[p][l], f[p][r - (1 << p) + 1]);
    }

    private int get(int l, int r, int bl, int br, int i) {
        if (i == bl) return q.get(bl)[1] - (l - q.get(bl)[0]);
        if (i == br) return r - q.get(br)[0] + 1;
        return q.get(i)[1];
    }

    private int cal(int l, int r, int bl, int br, int i) {
        if (i < 0 || i + 2 >= q.size() || s.charAt(q.get(i)[0]) == '1') return -inf;
        return get(l, r, bl, br, i) + get(l, r, bl, br, i + 2);
    }

    // S2: Sparse Table
    // time = O((n + q) * logn), space = O(nlogn)
    public List<Integer> maxActiveSectionsAfterTrade2(String s, int[][] queries) {
        int n = s.length(), base = 0;
        List<int[]> q = new ArrayList<>();
        q.add(new int[]{-1, -1}); // 哨兵
        int start = 0;
        for (int i = 0; i < n; i++) {
            if (i == n - 1 || s.charAt(i) != s.charAt(i + 1)) {
                if (s.charAt(i) == '1') base += i - start + 1;
                else q.add(new int[]{start, i + 1});
                start = i + 1;
            }
        }
        q.add(new int[]{n + 1, n + 1}); // 哨兵

        SparseTable st = new SparseTable(q);
        List<Integer> res = new ArrayList<>();
        for (int u = 0; u < queries.length; u++) {
            int l = queries[u][0], r = queries[u][1] + 1; // 左闭右开
            // a 中没有重复的区间左右端点，可以直接用库函数二分
            // 找第一个区间，左端点 >= l
            int i = Collections.binarySearch(q, new int[]{l, 0}, (o1, o2) -> o1[0] - o2[0]);
            if (i < 0) i = ~i;
            // 找最后一个区间，右端点 <= r
            int j = Collections.binarySearch(q, new int[]{0, r + 1}, (o1, o2) -> o1[1] - o2[1]);
            if (j < 0) j = ~j;
            j--;

            int mx = 0;
            if (i <= j) { // [l, r) 中有完整的区间
                int full = st.query(i, j);
                int sl = merge(q.get(i - 1)[1] - l, q.get(i)[1] - q.get(i)[0]); // 残缺区间 i-1 + 完整区间 i
                int sr = merge(r - q.get(j + 1)[0], q.get(j)[1] - q.get(j)[0]); // // 残缺区间 j+1 + 完整区间 j
                mx = Math.max(full, Math.max(sl, sr));
            } else if (i == j + 1) { // [l, r) 中有两个相邻的残缺区间
                mx = merge(q.get(i - 1)[1] - l, r - q.get(j + 1)[0]);
            }
            res.add(base + mx);
        }
        return res;
    }

    private int merge(int x, int y) {
        return x > 0 && y > 0 ? x + y : 0;
    }

    class SparseTable {
        private final int[][] st;
        SparseTable(List<int[]> a) { // 左闭右开
            int n = a.size() - 1;
            int sz = 32 - Integer.numberOfLeadingZeros(n);
            st = new int[n][sz];
            for (int i = 0; i < n; i++) {
                st[i][0] = a.get(i)[1] - a.get(i)[0] + a.get(i + 1)[1] - a.get(i + 1)[0];
            }
            for (int j = 1; j < sz; j++) {
                for (int i = 0; i + (1 << j) <= n; i++) {
                    st[i][j] = Math.max(st[i][j - 1], st[i + (1 << j - 1)][j - 1]);
                }
            }
        }

        // 查询区间最大值，[l,r) 左闭右开
        int query(int l, int r) {
            if (l >= r) return 0;
            int k = 32 - Integer.numberOfLeadingZeros(r - l) - 1;
            return Math.max(st[l][k], st[r - (1 << k)][k]);
        }
    }
}
/**
 * RMQ: Segment Tree, ST Table
 */