package LC3601_3900;

public class LC3887_IncrementalEvenWeightedCycleQueries {
    /**
     * You are given a positive integer n.
     *
     * There is an undirected graph with n nodes labeled from 0 to n - 1. Initially, the graph has no edges.
     *
     * You are also given a 2D integer array edges, where edges[i] = [ui, vi, wi] represents an edge between nodes ui
     * and vi with weight wi. The weight wi is either 0 or 1.
     *
     * Process the edges in edges in the given order. For each edge, add it to the graph only if, after adding it, the
     * sum of the weights of the edges in every cycle in the resulting graph is even.
     *
     * Return an integer denoting the number of edges that are successfully added to the graph.
     *
     * Input: n = 3, edges = [[0,1,1],[1,2,1],[0,2,1]]
     * Output: 2
     *
     * Input: n = 3, edges = [[0,1,1],[1,2,1],[0,2,0]]
     * Output: 3
     *
     * Constraints:
     *
     * 3 <= n <= 5 * 10^4
     * 1 <= edges.length <= 5 * 10^4
     * edges[i] = [ui, vi, wi]
     * 0 <= ui < vi < n
     * All edges are distinct.
     * wi = 0 or wi = 1
     * @param n
     * @param edges
     * @return
     */
    // time = O(nlogn), space = O(n)
    int[] p, q;
    public int numberOfEdgesAdded(int n, int[][] edges) {
        p = new int[n];
        q = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;

        int res = 0;
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            int fu = find(u), fv = find(v);
            if (fu != fv) {
                p[fu] = fv;
                q[fu] = q[u] ^ q[v] ^ w;
                res++;
            } else {
                if ((q[u] ^ q[v]) == w) res++;
            }
        }
        return res;
    }

    private int find(int x) {
        if (x != p[x]) {
            int fa = p[x];

            p[x] = find(p[x]);
            q[x] ^= q[fa];
        }
        return p[x];
    }

    // S2
    // time = O(nlogn), space = O(n)
    public int numberOfEdgesAdded2(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        int res = 0;
        for (int[] e : edges) {
            if (uf.merge(e[0], e[1], e[2])) res++;
        }
        return res;
    }

    class UnionFind {
        private final int[] fa; // fa[x] 是 x 的代表元
        private final int[] dis; // dis[x] = 从 x 到 fa[x] 的路径异或和

        public UnionFind(int n) {
            fa = new int[n];
            dis = new int[n];
            for (int i = 0; i < n; i++) {
                fa[i] = i;
            }
        }

        public int find(int x) {
            if (fa[x] != x) {
                int root = find(fa[x]);
                dis[x] ^= dis[fa[x]];
                fa[x] = root;
            }
            return fa[x];
        }

        public boolean merge(int from, int to, int value) {
            int x = find(from), y = find(to);
            if (x == y) {
                return (dis[from] ^ dis[to]) == value;
            }
            dis[x] = value ^ dis[to] ^ dis[from];
            fa[x] = y;
            return true;
        }
    }
}
/**
 * 加法运算可以被异或代替
 * 添加一条边之后，异或和必须为 0
 * 1. 环的边权之和是偶数 => 环的边权的异或和是 0
 * 2. 对于环上的两个点 A 和 B，从 A 到 B 的任意路径的异或和必须相等
 * s1 ^ s2 = 0 => s1 = s2
 * 如果连接两个点 x 和 y，会升成新的环，那么 x-y 的边权，必须等于连边之前 x 到 y 的路径异或和（根据性质 2，路径异或和是唯一）
 *
 * 带权并查集
 * A -> B => A -> Root + B -> Root XOR
 * 一条边 XOR 两次，刚好抵消
 * 先找 A，B 各自的root，然后合并 => merge(A,B,1)
 * A -> B -> Rb  再加上 A -> Ra
 *
 */