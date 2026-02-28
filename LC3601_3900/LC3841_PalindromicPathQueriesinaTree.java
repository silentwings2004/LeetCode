package LC3601_3900;
import java.util.*;
public class LC3841_PalindromicPathQueriesinaTree {
    /**
     * You are given an undirected tree with n nodes labeled 0 to n - 1. This is represented by a 2D array edges of
     * length n - 1, where edges[i] = [ui, vi] indicates an undirected edge between nodes ui and vi.
     *
     * You are also given a string s of length n consisting of lowercase English letters, where s[i] represents the
     * character assigned to node i.
     *
     * You are also given a string array queries, where each queries[i] is either:
     *
     * "update ui c": Change the character at node ui to c. Formally, update s[ui] = c.
     * "query ui vi": Determine whether the string formed by the characters on the unique path from ui to vi
     * (inclusive) can be rearranged into a palindrome.
     * Return a boolean array answer, where answer[j] is true if the jth query of type "query ui vi" can be rearranged
     * into a palindrome, and false otherwise.
     *
     * A palindrome is a string that reads the same forward and backward.
     *
     * Input: n = 3, edges = [[0,1],[1,2]], s = "aac", queries = ["query 0 2","update 1 b","query 0 2"]
     * Output: [true,false]
     *
     * Input: n = 4, edges = [[0,1],[0,2],[0,3]], s = "abca", queries = ["query 1 2","update 0 b","query 2 3","update 3 a","query 1 3"]
     * Output: [false,false,true]
     *
     * Constraints:
     *
     * 1 <= n == s.length <= 5 * 10^4
     * edges.length == n - 1
     * edges[i] = [ui, vi]
     * 0 <= ui, vi <= n - 1
     * s consists of lowercase English letters.
     * The input is generated such that edges represents a valid tree.
     * 1 <= queries.length <= 5 * 10^4
     * queries[i] = "update ui c" or
     * queries[i] = "query ui vi"
     * 0 <= ui, vi <= n - 1
     * c is a lowercase English letter.
     * @param n
     * @param edges
     * @param s
     * @param queries
     * @return
     */
    // time = O(nlogn), space = O(n)
    List<Integer>[] adj;
    int[] fa, dep, heavy, sz, h, pos;
    int[] base;
    SegmentTree seg;
    int n, cur;
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            adj[u].add(v);
            adj[v].add(u);
        }

        fa = new int[n];
        dep = new int[n];
        heavy = new int[n];
        sz = new int[n];
        h = new int[n];
        pos = new int[n];
        Arrays.fill(heavy, -1);
        fa[0] = -1;

        dfs(0, -1);
        cur = 0;
        dfs2(0, 0);

        base = new int[n];
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            base[pos[i]] = 1 << u;
        }
        seg = new SegmentTree(base);

        List<Boolean> res = new ArrayList<>();
        for (String q : queries) {
            String[] strs = q.split(" ");
            if (strs[0].equals("update")) {
                int u = Integer.parseInt(strs[1]);
                char c = strs[2].charAt(0);
                int mask = 1 << (c - 'a');
                seg.modify(pos[u], mask);
            } else if (strs[0].equals("query")) {
                int u = Integer.parseInt(strs[1]);
                int v = Integer.parseInt(strs[2]);
                int x = queryPath(u, v);
                res.add((x & (x - 1)) == 0);
            }
        }
        return res;
    }

    private void dfs(int u, int p) {
        fa[u] = p;
        sz[u] = 1;
        int mx = 0;
        for (int v : adj[u]) {
            if (v == p) continue;
            dep[v] = dep[u] + 1;
            dfs(v, u);
            sz[u] += sz[v];
            if (sz[v] > mx) {
                mx = sz[v];
                heavy[u] = v;
            }
        }
    }

    private void dfs2(int u, int head) {
        h[u] = head;
        pos[u] = cur++;
        if (heavy[u] != -1) dfs2(heavy[u], head);
        for (int v : adj[u]) {
            if (v == fa[u] || v == heavy[u]) continue;
            dfs2(v, v);
        }
    }

    private int queryPath(int a, int b) {
        int res = 0;
        while (h[a] != h[b]) {
            if (dep[h[a]] < dep[h[b]]) {
                int t = a;
                a = b;
                b = t;
            }
            int head = h[a];
            res ^= seg.query(pos[head], pos[a]);
            a = fa[head];
        }
        if (dep[a] > dep[b]) {
            int t = a;
            a = b;
            b = t;
        }
        res ^= seg.query(pos[a], pos[b]);
        return res;
    }

    class SegmentTree {
        int n;
        int[] tr;
        SegmentTree(int[] a) {
            this.n = a.length;
            tr = new int[n << 2];
            build(1, 0, n - 1, a);
        }

        private void build(int u, int l, int r, int[] a) {
            if (l == r) {
                tr[u] = a[l];
                return;
            }
            int mid = l + r >> 1;
            build(u << 1, l, mid, a);
            build(u << 1 | 1, mid + 1, r, a);
            tr[u] = tr[u << 1] ^ tr[u << 1 | 1];
        }

        private void modify(int x, int v) {
            modify(1, 0, n - 1, x, v);
        }

        private void modify(int u, int l, int r, int x, int v) {
            if (l == r) {
                tr[u] = v;
                return;
            }
            int mid = l + r >> 1;
            if (x <= mid) modify(u << 1, l, mid, x, v);
            else modify(u << 1 | 1, mid + 1, r, x, v);
            tr[u] = tr[u << 1] ^ tr[u << 1 | 1];
        }

        private int query(int L, int R) {
            return query(1, 0, n - 1, L, R);
        }

        private int query(int u, int l, int r, int L, int R) {
            if (L > r || R < l) return 0;
            if (L <= l && r <= R) return tr[u];
            int mid = l + r >> 1;
            return query(u << 1, l, mid, L, R) ^ query(u << 1 | 1, mid + 1, r, L, R);
        }
    }

    // S2
    // time = O((n + q) * logn), space = O(nlogn)
    public List<Boolean> palindromePath2(int n, int[][] edges, String s, String[] queries) {
        char[] t = s.toCharArray();
        LcaBinaryLifting g = new LcaBinaryLifting(edges, t);
        FenwickTree f = new FenwickTree(n);
        List<Boolean> res = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");
            int x = Integer.parseInt(parts[1]);
            if (parts[0].charAt(0) == 'u') {
                char c = parts[2].charAt(0);
                int val = (1 << (t[x] - 'a')) ^ (1 << (c - 'a'));
                t[x] = c;
                f.update(g.tin[x], val);
                f.update(g.tout[x] + 1, val);
            } else {
                int y = Integer.parseInt(parts[2]);
                int lca = g.getLCA(x, y);
                int v = g.pathXorFromRoot[x] ^ g.pathXorFromRoot[y] ^ f.pre(g.tin[x]) ^ f.pre(g.tin[y]) ^ (1 << (t[lca] - 'a'));
                res.add((v & (v - 1)) == 0);
            }
        }
        return res;
    }

    // 模板来自我的题单 https://leetcode.cn/circle/discuss/mOr1u6/
    class FenwickTree {
        private final int[] tree;

        public FenwickTree(int n) {
            tree = new int[n + 1]; // 使用下标 1 到 n
        }

        // a[i] ^= val
        // 1 <= i <= n
        // 时间复杂度 O(log n)
        public void update(int i, int val) {
            for (; i < tree.length; i += i & -i) {
                tree[i] ^= val;
            }
        }

        // 求前缀异或和 a[1] ^ ... ^ a[i]
        // 1 <= i <= n
        // 时间复杂度 O(log n)
        public int pre(int i) {
            int res = 0;
            for (; i > 0; i &= i - 1) {
                res ^= tree[i];
            }
            return res;
        }
    }

    // 模板来自我的题单 https://leetcode.cn/circle/discuss/K0n2gO/
    class LcaBinaryLifting {
        private final int[] depth;
        private final int[][] pa;
        private int clock = 0;

        public final int[] tin;
        public final int[] tout;
        public final int[] pathXorFromRoot;

        LcaBinaryLifting(int[][] edges, char[] s) {
            int n = edges.length + 1;
            int m = 32 - Integer.numberOfLeadingZeros(n); // n 的二进制长度
            List<Integer>[] g = new ArrayList[n];
            Arrays.setAll(g, e -> new ArrayList<>());
            for (int[] e : edges) {
                int x = e[0];
                int y = e[1];
                g[x].add(y);
                g[y].add(x);
            }

            depth = new int[n];
            pa = new int[m][n];
            tin = new int[n]; // DFS 时间戳
            tout = new int[n];
            pathXorFromRoot = new int[n]; // 从根开始的路径的字母出现次数的奇偶性
            pathXorFromRoot[0] = 1 << (s[0] - 'a');

            dfs(0, -1, g, s);

            for (int i = 0; i < m - 1; i++) {
                for (int x = 0; x < n; x++) {
                    int p = pa[i][x];
                    pa[i + 1][x] = p < 0 ? -1 : pa[i][p];
                }
            }
        }

        private void dfs(int x, int fa, List<Integer>[] g, char[] s) {
            pa[0][x] = fa;
            tin[x] = ++clock;
            for (int y : g[x]) {
                if (y != fa) {
                    depth[y] = depth[x] + 1;
                    pathXorFromRoot[y] = pathXorFromRoot[x] ^ (1 << (s[y] - 'a'));
                    dfs(y, x, g, s);
                }
            }
            tout[x] = clock;
        }

        // 返回 node 的第 k 个祖先节点
        // 如果不存在，返回 -1
        private int getKthAncestor(int node, int k) {
            for (; k > 0 && node >= 0; k &= k - 1) {
                node = pa[Integer.numberOfTrailingZeros(k)][node];
            }
            return node;
        }

        // 返回 x 和 y 的最近公共祖先（节点编号从 0 开始）
        public int getLCA(int x, int y) {
            if (depth[x] > depth[y]) {
                int tmp = y;
                y = x;
                x = tmp;
            }
            // 使 y 和 x 在同一深度
            y = getKthAncestor(y, depth[y] - depth[x]);
            if (y == x) {
                return x;
            }
            for (int i = pa.length - 1; i >= 0; i--) {
                int px = pa[i][x], py = pa[i][y];
                if (px != py) {
                    x = px;
                    y = py; // 同时往上跳 2^i 步
                }
            }
            return pa[0][x];
        }
    }
}
/**
 * 1.讲题意
 * 至多有一种字母的出现次数是奇数
 * 用二进制数字来表示每个字母出现奇数还是偶数
 * 2.如何处理 query
 * 异或本质是模2加减法 => (a - b) % 2 => a' ^ b'
 * 2.如何处理 update
 * 定义XOR[i]表示从根到 i 的路径的每个字母的出现次数的奇偶性
 * 当我修改一个节点的字母时，哪些XOR[i]会变
 * 如果修改的节点是 x,那么子树 x 中的所有结点的 XOR[i] 都会变
 * 树上问题
 * 子树修改，查询 => 时间戳转成数组的区间修改、查询
 * 路径修改，查询 => 重链剖分转成多个数组的区间修改、查询
 *
 * 由于本题是区间更新、单点查询
 * 可以用 Lazy 线段树，也可以用差分树状数组，转换成单点更新、前缀查询 => 树状数组
 *
 * 树状数组 + 差分 / Lazy 线段树
 * DFS 时间戳
 * 树 LCA
 * 位运算 (可选？如果用长为 26 的数组代替，常数 x 26)
 */
