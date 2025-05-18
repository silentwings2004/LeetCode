package LC601_900;
import java.util.*;
public class LC699_FallingSquares {
    /**
     * There are several squares being dropped onto the X-axis of a 2D plane.
     *
     * You are given a 2D integer array positions where positions[i] = [lefti, sideLengthi] represents the ith square
     * with a side length of sideLengthi that is dropped with its left edge aligned with X-coordinate lefti.
     *
     * Each square is dropped one at a time from a height above any landed squares. It then falls downward
     * (negative Y direction) until it either lands on the top side of another square or on the X-axis.
     * A square brushing the left/right side of another square does not count as landing on it. Once it lands,
     * it freezes in place and cannot be moved.
     *
     * After each square is dropped, you must record the height of the current tallest stack of squares.
     *
     * Return an integer array ans where ans[i] represents the height described above after dropping the ith square.
     *
     * Input: positions = [[1,2],[2,3],[6,1]]
     * Output: [2,5,5]
     *
     * Constraints:
     *
     * 1 <= positions.length <= 1000
     * 1 <= lefti <= 10^8
     * 1 <= sideLengthi <= 10^6
     * @param positions
     * @return
     */
    // time = O(nlogn), space = O(n)
    public List<Integer> fallingSquares(int[][] positions) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int[] rect : positions) {
            set.add(rect[0]);
            set.add(rect[0] + rect[1]);
        }
        int idx = 0;
        HashMap<Integer, Integer> pos2idx = new HashMap<>();
        for (int x : set) {
            pos2idx.put(x, idx);
            idx++;
        }
        int n = pos2idx.size();

        SegTreeNode root = new SegTreeNode(0, n - 1);
        init(root, 0, n - 1);

        int maxH = 0;
        List<Integer> res = new ArrayList<>();
        for (int[] rect : positions) {
            int a = pos2idx.get(rect[0]);
            int b = pos2idx.get(rect[0] + rect[1]);
            int h = queryRange(root, a, b - 1); // [ ) 左闭右开区间
            updateRange(root, a, b - 1, h + rect[1]);
            maxH = Math.max(maxH, h + rect[1]);
            res.add(maxH);
        }
        return res;
    }

    private class SegTreeNode {
        private SegTreeNode left, right;
        int start, end;
        int info; // the max height of the range
        boolean tag;
        public SegTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private void init(SegTreeNode node, int a, int b) {
        // base case -> single point
        if (a == b) {
            node.info = 0;
            return;
        }

        int mid = (a + b) / 2;
        if (node.left == null) {
            node.left = new SegTreeNode(a, mid);
            node.right = new SegTreeNode(mid + 1, b);
        }
        init(node.left, a, mid);
        init(node.right, mid + 1, b);
        // info
        node.info = 0;
    }

    private void updateRange(SegTreeNode node, int a, int b, int val) {
        if (b < node.start || a > node.end) return; // no intersection
        if (a <= node.start && node.end <= b) {
            node.info = val;
            node.tag = true; // 做个lazyTag
            return;
        }

        // recursion
        pushdown(node); // erase lazy tag and propagate down
        updateRange(node.left, a, b, val);
        updateRange(node.right, a, b, val);
        // update info
        node.info = Math.max(node.left.info, node.right.info);
    }

    private void pushdown(SegTreeNode node) {
        if (node.tag) {
            node.left.info = node.info;
            node.right.info = node.info;
            node.left.tag = true;
            node.right.tag = true;
            node.tag = false;
        }
    }

    private int queryRange(SegTreeNode node, int a, int b) { // 一个区间上的最大值
        if (b < node.start || a > node.end) return 0;
        if (a <= node.start && node.end <= b) return node.info;
        // recursion
        pushdown(node); // 一旦下放，必须要pushdown！
        node.info = Math.max(queryRange(node.left, a, b) , queryRange(node.right, a, b));
        return node.info;
    }

    // S2:
    // time = O(n^2), space = O(n)
    public List<Integer> fallingSquares2(int[][] pos) {
        List<Integer> res = new ArrayList<>();
        int n = pos.length;
        int[] h = new int[n];
        for (int i = 0; i < n; i++) {
            int a = pos[i][0], b = pos[i][1];
            h[i] += b;
            for (int j = i + 1; j < n; j++) {
                int c = pos[j][0], d = pos[j][1];
                if (isIntersect(a, a + b, c, c + d)) h[j] = Math.max(h[j], h[i]);
            }
        }
        int cur = 0;
        for (int x : h) {
            cur = Math.max(cur, x);
            res.add(cur);
        }
        return res;
    }

    private boolean isIntersect(int l1, int r1, int l2, int r2) {
        return r2 > l1 && l2 < r1;
    }

    // S3: 线段树
    class Solution {
        final int N = 3010;
        Node[] tr;
        List<Integer> xs;
        public List<Integer> fallingSquares(int[][] positions) {
            tr = new Node[N << 2];
            xs = new ArrayList<>();

            for (int[] p : positions) {
                int a = p[0], b = a + p[1];
                xs.add(a * 2);
                xs.add(b * 2);
                xs.add(a + b);
            }
            xs = new ArrayList<>(new HashSet<>(xs));
            Collections.sort(xs);
            build(1, 0, xs.size() - 1);

            List<Integer> res = new ArrayList<>();
            for (int[] p : positions) {
                int a = p[0], b = a + p[1];
                a = get(a * 2);
                b = get(b * 2);
                int h = query(1, a + 1, b - 1);
                update(1, a, b, h + p[1]);
                res.add(tr[1].v);
            }
            return res;
        }

        private void build(int u, int l, int r) {
            tr[u] = new Node();
            tr[u].l = l;
            tr[u].r = r;
            if (l == r) return;

            int mid = l + r >> 1;
            build(u << 1, l, mid);
            build(u << 1 | 1, mid + 1, r);
        }

        private int get(int x) {
            return lower_bound(x);
        }

        private int lower_bound(int x) {
            int l = 0, r = xs.size() - 1;
            while (l < r) {
                int mid = l + r >> 1;
                if (xs.get(mid) >= x) r = mid;
                else l = mid + 1;
            }
            return r;
        }

        private void pushup(int u) {
            tr[u].v = Math.max(tr[u << 1].v, tr[u << 1 | 1].v);
        }

        private void pushdown(int u) {
            int c = tr[u].c;
            if (c != 0) {
                tr[u].c = 0;
                tr[u << 1].v = tr[u << 1 | 1].v = c;
                tr[u << 1].c = tr[u << 1 | 1].c = c;
            }
        }

        private void update(int u, int l, int r, int c) {
            if (tr[u].l >= l && tr[u].r <= r) {
                tr[u].c = tr[u].v = c;
                return;
            }

            pushdown(u);
            int mid = tr[u].l + tr[u].r >> 1;
            if (l <= mid) update(u << 1, l, r, c);
            if (r > mid) update(u << 1 | 1, l, r, c);
            pushup(u);
        }

        private int query(int u, int l, int r) {
            if (tr[u].l >= l && tr[u].r <= r) return tr[u].v;

            pushdown(u);
            int res = 0;
            int mid = tr[u].l + tr[u].r >> 1;
            if (l <= mid) res = query(u << 1, l, r);
            if (r > mid) res = Math.max(res, query(u << 1 | 1, l, r));
            return res;
        }

        private class Node {
            private int l, r, v, c;
        }
    }
}
/**
 * 共同点，叶子结点一开始都是知道的，整体什么样是可以提前建立起来的，而LC715形状一直在变，容易TLE，效率不高，需要动态开辟空间。
 * h = queryRange(a, b)
 * updateRange(a, b, h + d)
 * 线段树来做比较直观，无脑套模板
 * 用一个左闭右开区间来表示区间的高度
 * 不管是update还是query,一旦你要越过这个node到达下面去的时候，特别要小心
 * node本身可能截留住一些信息，如果是被lazyTag过，它下面的left, right信息都是不准确的
 * 所以在访问前要把lazyTag清理掉，同时把一些信息提前下放下去
 *
 * 本质上2个操作：
 * 1. 求区间最大值
 * 2. 将[l,r]变为c
 * => 经典线段树的问题
 * 区间查询，区间修改 => 懒标记
 * 10^8 => 需要离散化，三个点 => 左右端点+中点
 *
 * 掉落正方形会被卡在哪个地方，就看其左右边界的区域里最高的点 => 某个区间内的最大值
 * h + l => 让某个区间内的数变成某个数
 * 本质上就2个操作：
 * 1. 求区间最大值
 * 2. 将某个区间变成同一个数
 * => 线段树  区间查询和区间修改=> 懒标记
 * 10^8 => 离散化 保序
 * 查询开区间 => 查(a,b) => 离散化左右端点和中点这3个点
 * 1000个正方形，离散化后有3000个点
 */