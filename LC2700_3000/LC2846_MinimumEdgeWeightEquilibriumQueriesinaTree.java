package LC2700_3000;
import java.util.*;
public class LC2846_MinimumEdgeWeightEquilibriumQueriesinaTree {
    /**
     * There is an undirected tree with n nodes labeled from 0 to n - 1. You are given the integer n and a 2D integer
     * array edges of length n - 1, where edges[i] = [ui, vi, wi] indicates that there is an edge between nodes ui and
     * vi with weight wi in the tree.
     *
     * You are also given a 2D integer array queries of length m, where queries[i] = [ai, bi]. For each query, find the
     * minimum number of operations required to make the weight of every edge on the path from ai to bi equal. In one
     * operation, you can choose any edge of the tree and change its weight to any value.
     *
     * Note that:
     *
     * Queries are independent of each other, meaning that the tree returns to its initial state on each new query.
     * The path from ai to bi is a sequence of distinct nodes starting with node ai and ending with node bi such that
     * every two adjacent nodes in the sequence share an edge in the tree.
     * Return an array answer of length m where answer[i] is the answer to the ith query.
     *
     * Input: n = 7, edges = [[0,1,1],[1,2,1],[2,3,1],[3,4,2],[4,5,2],[5,6,2]], queries = [[0,3],[3,6],[2,6],[0,6]]
     * Output: [0,0,1,3]
     *
     * Input: n = 8, edges = [[1,2,6],[1,3,4],[2,4,6],[2,5,3],[3,6,6],[3,0,8],[7,0,2]], queries = [[4,6],[0,4],[6,5],[7,4]]
     * Output: [1,2,2,3]
     *
     * Constraints:
     *
     * 1 <= n <= 10^4
     * edges.length == n - 1
     * edges[i].length == 3
     * 0 <= ui, vi < n
     * 1 <= wi <= 26
     * The input is generated such that edges represents a valid tree.
     * 1 <= queries.length == m <= 2 * 10^4
     * queries[i].length == 2
     * 0 <= ai, bi < n
     * @param n
     * @param edges
     * @param queries
     * @return
     */
    // time = O((n + m) * logn), space = O(nlogn)
    final int N = 10010, M = N * 2, INF = (int)1e9;
    int[] h, e, ne, w;
    int idx;
    boolean[] st;
    int[][] wt, fa;
    int[] depth, q;
    public int[] minOperationsQueries(int n, int[][] edges, int[][] queries) {
        h = new int[N];
        e = new int[M];
        ne = new int[M];
        w = new int[M];
        wt = new int[27][N];
        fa = new int[N][15];
        depth = new int[N];
        q = new int[N];
        st = new boolean[N];

        Arrays.fill(h, -1);
        idx = 0;
        for (int[] e : edges) {
            int a = e[0], b = e[1], c = e[2];
            add(a, b, c);
            add(b, a, c);
        }
        bfs();

        for (int i = 1; i <= 26; i++) {
            Arrays.fill(st, false);
            dfs(0, i);
        }

        int m = queries.length;
        int[] res = new int[m];
        Arrays.fill(res, INF);
        for (int i = 0; i < m; i++) {
            int a = queries[i][0], b = queries[i][1];
            int p = lca(a, b);
            for (int j = 1; j <= 26; j++) {
                int t = wt[j][a] + wt[j][b] - 2 * wt[j][p];
                res[i] = Math.min(res[i], t);
            }
        }
        return res;
    }

    private void dfs(int u, int v) {
        st[u] = true;
        for (int i = h[u]; i != -1; i = ne[i]) {
            int j = e[i], d = w[i];
            if (st[j]) continue;
            wt[v][j] = wt[v][u] + (d == v ? 0 : 1);
            dfs(j, v);
        }
    }

    private int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int t = a;
            a = b;
            b = t;
        }

        for (int k = 14; k >= 0; k--) {
            if (depth[fa[a][k]] >= depth[b]) a = fa[a][k];
        }
        if (a == b) return a;
        for (int k = 14; k >= 0; k--) {
            if (fa[a][k] != fa[b][k]) {
                a = fa[a][k];
                b = fa[b][k];
            }
        }
        return fa[a][0];
    }

    private void bfs() {
        Arrays.fill(depth, INF);
        depth[0] = 1;
        int hh = 0, tt = 0;
        q[0] = 0;

        while (hh <= tt) {
            int t = q[hh++];
            for (int i = h[t]; i != -1; i = ne[i]) {
                int j = e[i];
                if (depth[j] > depth[t] + 1) {
                    depth[j] = depth[t] + 1;
                    q[++tt] = j;
                    fa[j][0] = t;
                    for (int k = 1; k <= 14; k++) {
                        fa[j][k] = fa[fa[j][k - 1]][k - 1];
                    }
                }
            }
        }
    }

    private void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }
}
/**
 * 1. 保留出现次数最多的边，其余的全部修改
 * 2. 从 a 到 b 的路径长度 (边数)
 * 3. 从 a 到 b 出现次数最多的边
 *    1 <= wi <= 26 统计每种边权的出现次数
 *    da + db - 2 * dlca
 *    depth[a] + depth[b] - depth[lca] * 2
 */