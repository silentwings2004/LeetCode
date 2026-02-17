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
}