package LC3001_3300;
import java.util.*;
public class LC3161_BlockPlacementQueries {
    /**
     * There exists an infinite number line, with its origin at 0 and extending towards the positive x-axis.
     *
     * You are given a 2D array queries, which contains two types of queries:
     *
     * For a query of type 1, queries[i] = [1, x]. Build an obstacle at distance x from the origin. It is guaranteed
     * that there is no obstacle at distance x when the query is asked.
     * For a query of type 2, queries[i] = [2, x, sz]. Check if it is possible to place a block of size sz anywhere in
     * the range [0, x] on the line, such that the block entirely lies in the range [0, x]. A block cannot be placed if
     * it intersects with any obstacle, but it may touch it. Note that you do not actually place the block. Queries are
     * separate.
     * Return a boolean array results, where results[i] is true if you can place the block specified in the ith query
     * of type 2, and false otherwise.
     *
     * Input: queries = [[1,2],[2,3,3],[2,3,1],[2,2,2]]
     *
     * Output: [false,true,true]
     *
     * Input: queries = [[1,7],[2,7,6],[1,2],[2,7,5],[2,7,6]]
     *
     * Output: [true,true,false]
     *
     * Constraints:
     *
     * 1 <= queries.length <= 15 * 10^4
     * 2 <= queries[i].length <= 3
     * 1 <= queries[i][0] <= 2
     * 1 <= x, sz <= min(5 * 104, 3 * queries.length)
     * The input is generated such that for queries of type 1, no obstacle exists at distance x when the query is asked.
     * The input is generated such that there is at least one query of type 2.
     * @param queries
     * @return
     */
    // S1
    // time = O(nlogk), space = O(k)
    Node[] tr;
    public List<Boolean> getResults(int[][] queries) {
        List<Boolean> res = new ArrayList<>();
        int m = 0;
        for (int[] q : queries) m = Math.max(m, q[1]);
        tr = new Node[(m + 1) * 4];
        build(1, 1, m + 1);

        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        set.add(m + 1);
        modify(1, m + 1, m + 1);
        int n = queries.length;
        for (int i = 0; i < n; i++) {
            int o = queries[i][0];
            if (o == 1) {
                int x = queries[i][1];
                int l = set.lower(x), r = set.higher(x);
                modify(1, x, x - l);
                modify(1, r, r - x);
                set.add(x);
            } else {
                int x = queries[i][1], y = queries[i][2];
                if (x - y < 0) res.add(false);
                else {
                    int l = set.lower(x);
                    res.add(Math.max(x - l, query(1, 1, x)) >= y ? true : false);
                }
            }
        }
        return res;
    }

    private void pushup(int u) {
        tr[u].v = Math.max(tr[u << 1].v, tr[u << 1 | 1].v);
    }

    private int query(int u, int l, int r) {
        if (tr[u].l >= l && tr[u].r <= r) return tr[u].v;

        int mid = tr[u].l + tr[u].r >> 1;
        int v = 0;
        if (l <= mid) v = query(u << 1, l, r);
        if (r > mid) v = Math.max(v, query(u << 1 | 1, l, r));
        return v;
    }

    private void modify(int u, int x, int v) {
        if (tr[u].l == x && tr[u].r == x) tr[u].v = v;
        else {
            int mid = tr[u].l + tr[u].r >> 1;
            if (x <= mid) modify(u << 1, x, v);
            else modify(u << 1 | 1, x , v);
            pushup(u);
        }
    }

    private void build(int u, int l, int r) {
        tr[u] = new Node(l, r);
        if (l == r) return;

        int mid = l + r >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
    }

    private class Node {
        private int l, r;
        private int v;
        public Node(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    // S1.2
    // time = O(nlogk), space = O(k)
    class Solution {
        public List<Boolean> getResults(int[][] queries) {
            int n = Math.min(50000, queries.length * 3) + 10;
            SegTreeNode root = new SegTreeNode(0, n, 0);
            TreeSet<Integer> set = new TreeSet<>();
            set.add(0);

            List<Boolean> res = new ArrayList<>();
            for (int[] q : queries) {
                if (q[0] == 1) {
                    int x = q[1];
                    set.add(x);
                    int a = set.lower(x);
                    root.updateRange(x, x, x - a);
                    Integer b = set.higher(x);
                    if (b != null) root.updateRange(b, b, b - x);
                } else {
                    int x = q[1], sz = q[2];
                    int len = root.queryRange(0, x);
                    if (!set.contains(x)) {
                        int a = set.lower(x);
                        len = Math.max(len, x - a);
                    }
                    res.add(len >= sz);
                }
            }
            return res;
        }


        class SegTreeNode {
            SegTreeNode left, right;
            int start, end;
            int info;
            boolean tag;

            public SegTreeNode(int a, int b, int val) {
                start = a;
                end = b;
                tag = false;
                if (a == b) {
                    info = val;
                    return;
                }

                int mid = (a + b) / 2;
                if (left == null) {
                    left = new SegTreeNode(a, mid, val);
                    right = new SegTreeNode(mid + 1, b, val);
                    info = Math.max(left.info, right.info);
                }
            }

            private void pushDown() {
                if (tag == true && left != null) {
                    left.info = info;
                    right.info = info;
                    left.tag = true;
                    right.tag = true;
                    tag = false;
                }
            }

            private void updateRange(int a, int b, int val) {
                if (b < start || a > end) return;
                if (a <= start && end <= b) {
                    info = val;
                    tag = true;
                    return;
                }

                if (left != null) {
                    pushDown();
                    left.updateRange(a, b, val);
                    right.updateRange(a, b, val);
                    info = Math.max(left.info, right.info);
                }
            }

            private int queryRange(int a, int b) {
                if (b < start || a > end) return Integer.MIN_VALUE;
                if (a <= start && end <= b) return info;
                if (left != null) {
                    pushDown();
                    int res = Math.max(left.queryRange(a, b), right.queryRange(a, b));
                    info = Math.max(left.info, right.info);
                    return res;
                }
                return info;
            }
        }
    }
}
/**
 * 1.维护什么信息？
 * 维护区域的长度，计算区域长度的最大值
 *
 * 2.这个信息要保存在哪？
 * 把区域长度，保存在区域的右端点上
 * d[7] = 7
 * d[2] = 2, d[7] = 5
 *
 * 3.怎么回答询问(操作2)
 * 支持：单点修改 d[i] => 新值，区间查询 => 线段树
 */