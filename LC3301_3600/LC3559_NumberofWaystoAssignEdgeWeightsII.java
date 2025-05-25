package LC3301_3600;

import java.util.*;

public class LC3559_NumberofWaystoAssignEdgeWeightsII {
    /**
     * There is an undirected tree with n nodes labeled from 1 to n, rooted at node 1. The tree is represented by a 2D
     * integer array edges of length n - 1, where edges[i] = [ui, vi] indicates that there is an edge between nodes ui
     * and vi.
     *
     * Initially, all edges have a weight of 0. You must assign each edge a weight of either 1 or 2.
     *
     * The cost of a path between any two nodes u and v is the total weight of all edges in the path connecting them.
     *
     * You are given a 2D integer array queries. For each queries[i] = [ui, vi], determine the number of ways to assign
     * weights to edges in the path such that the cost of the path between ui and vi is odd.
     *
     * Return an array answer, where answer[i] is the number of valid assignments for queries[i].
     *
     * Since the answer may be large, apply modulo 10^9 + 7 to each answer[i].
     *
     * Note: For each query, disregard all edges not in the path between node ui and vi.
     *
     * Input: edges = [[1,2]], queries = [[1,1],[1,2]]
     * Output: [0,1]
     *
     * Input: edges = [[1,2],[1,3],[3,4],[3,5]], queries = [[1,4],[3,4],[2,5]]
     * Output: [2,1,4]
     *
     * Constraints:
     *
     * 2 <= n <= 10^5
     * edges.length == n - 1
     * edges[i] == [ui, vi]
     * 1 <= queries.length <= 10^5
     * queries[i] == [ui, vi]
     * 1 <= ui, vi <= n
     * edges represents a valid tree.
     * @param edges
     * @param queries
     * @return
     */
    // time = O(nlogn + mlogk), space = O(n)
    final int inf = 0x3f3f3f3f, mod = (int)1E9 + 7;
    List<Integer>[] adj;
    int[][] fa;
    int[] depth, dist;
    int lg;
    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0] - 1, b = e[1] - 1;
            adj[a].add(b);
            adj[b].add(a);
        }

        lg = 32 - Integer.numberOfLeadingZeros(n);
        depth = new int[n];
        dist = new int[n];
        fa = new int[n][lg];
        bfs(0);

        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int a = queries[i][0] - 1, b = queries[i][1] - 1;
            if (a == b) continue;
            int c = lca(a, b);
            int d = dist[a] + dist[b] - 2 * dist[c];
            res[i] = (int)qmi(2, d - 1);
        }
        return res;
    }

    private long qmi(long a, long k) {
        long res = 1;
        while (k > 0) {
            if ((k & 1) == 1) res = res * a % mod;
            a = a * a % mod;
            k >>= 1;
        }
        return res;
    }

    private int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int t = a;
            a = b;
            b = t;
        }

        for (int k = lg - 1; k >= 0; k--) {
            if (depth[fa[a][k]] >= depth[b]) a = fa[a][k];
        }

        if (a == b) return a;
        for (int k = lg - 1; k >= 0; k--) {
            if (fa[a][k] != fa[b][k]) {
                a = fa[a][k];
                b = fa[b][k];
            }
        }
        return fa[a][0];
    }

    private void bfs(int root) {
        Arrays.fill(depth, inf);
        depth[0] = 0;
        depth[root] = 1;
        Queue<Integer> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj[u]) {
                if (depth[v] > depth[u] + 1) {
                    depth[v] = depth[u] + 1;
                    dist[v] = dist[u] + 1;
                    q.offer(v);
                    fa[v][0] = u;
                    for (int k = 1; k < lg; k++) {
                        fa[v][k] = fa[fa[v][k - 1]][k - 1];
                    }
                }
            }
        }
    }
}