package LC3001_3300;
import java.util.*;
public class LC3108_MinimumCostWalkinWeightedGraph {
    /**
     * There is an undirected weighted graph with n vertices labeled from 0 to n - 1.
     *
     * You are given the integer n and an array edges, where edges[i] = [ui, vi, wi] indicates that there is an edge
     * between vertices ui and vi with a weight of wi.
     *
     * A walk on a graph is a sequence of vertices and edges. The walk starts and ends with a vertex, and each edge
     * connects the vertex that comes before it and the vertex that comes after it. It's important to note that a walk
     * may visit the same edge or vertex more than once.
     *
     * The cost of a walk starting at node u and ending at node v is defined as the bitwise AND of the weights of the
     * edges traversed during the walk. In other words, if the sequence of edge weights encountered during the walk is
     * w0, w1, w2, ..., wk, then the cost is calculated as w0 & w1 & w2 & ... & wk, where & denotes the bitwise AND
     * operator.
     *
     * You are also given a 2D array query, where query[i] = [si, ti]. For each query, you need to find the minimum
     * cost of the walk starting at vertex si and ending at vertex ti. If there exists no such walk, the answer is -1.
     *
     * Return the array answer, where answer[i] denotes the minimum cost of a walk for query i.
     *
     * Input: n = 5, edges = [[0,1,7],[1,3,7],[1,2,1]], query = [[0,3],[3,4]]
     * Output: [1,-1]
     *
     * Input: n = 3, edges = [[0,2,7],[0,1,15],[1,2,6],[1,2,1]], query = [[1,2]]
     * Output: [0]
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * 0 <= edges.length <= 10^5
     * edges[i].length == 3
     * 0 <= ui, vi <= n - 1
     * ui != vi
     * 0 <= wi <= 10^5
     * 1 <= query.length <= 10^5
     * query[i].length == 2
     * 0 <= si, ti <= n - 1
     * @param n
     * @param edges
     * @param query
     * @return
     */
    // time = O(nlogn), space = O(n)
    int[] p;
    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        p = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;
        int[] w = new int[n];
        Arrays.fill(w, -1);
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            if (find(a) != find(b)) p[find(a)] = find(b);
        }
        for (int[] e : edges) {
            int a = e[0], c = e[2];
            if (w[find(a)] == -1) w[find(a)] = c;
            else w[find(a)] &= c;
        }

        int m = query.length;
        int[] res = new int[m];
        Arrays.fill(res, -1);
        for (int i = 0; i < m; i++) {
            int a = query[i][0], b = query[i][1];
            if (find(a) != find(b)) continue;
            if (a == b) res[i] = 0;
            else res[i] = w[find(a)];
        }
        return res;
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }

    // S2: dfs
    // time = O(n + m + q), space = O(n + m)
    public int[] minimumCost2(int n, int[][] edges, int[][] query) {
        List<int[]>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1], c = e[2];
            adj[a].add(new int[]{b, c});
            adj[b].add(new int[]{a, c});
        }

        int[] ids = new int[n]; // 记录每个点所在连通块的编号
        Arrays.fill(ids, -1);
        List<Integer> q = new ArrayList<>(); // 记录每个连通块的边权 AND
        for (int i = 0; i < n; i++) {
            if (ids[i] < 0) q.add(dfs(i, q.size(), adj, ids));
        }

        int[] res = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            int a = query[i][0], b = query[i][1];
            res[i] = ids[a] != ids[b] ? -1 : q.get(ids[a]);
        }
        return res;
    }

    private int dfs(int u, int cur, List<int[]>[] adj, int[] ids) {
        ids[u] = cur;
        int t = -1;
        for (int[] x : adj[u]) {
            t &= x[1];
            if (ids[x[0]] < 0) { // 之前没有访问过
                t &= dfs(x[0], cur, adj, ids);
            }
        }
        return t;
    }
}