package LC3601_3900;
import java.util.*;
public class LC3772_MaximumSubgraphScoreinaTree {
    /**
     * You are given an undirected tree with n nodes, numbered from 0 to n - 1. It is represented by a 2D integer array
     * edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the
     * tree.
     *
     * You are also given an integer array good of length n, where good[i] is 1 if the ith node is good, and 0 if it is
     * bad.
     *
     * Define the score of a subgraph as the number of good nodes minus the number of bad nodes in that subgraph.
     *
     * For each node i, find the maximum possible score among all connected subgraphs that contain node i.
     *
     * Return an array of n integers where the ith element is the maximum score for node i.
     *
     * A subgraph is a graph whose vertices and edges are subsets of the original graph.
     *
     * A connected subgraph is a subgraph in which every pair of its vertices is reachable from one another using only
     * its edges.
     *
     * Input: n = 3, edges = [[0,1],[1,2]], good = [1,0,1]
     * Output: [1,1,1]
     *
     * Input: n = 5, edges = [[1,0],[1,2],[1,3],[3,4]], good = [0,1,0,1,1]
     * Output: [2,3,2,3,3]
     *
     * Input: n = 2, edges = [[0,1]], good = [0,0]
     * Output: [-1,-1]
     *
     * Constraints:
     *
     * 2 <= n <= 10^5
     * edges.length == n - 1
     * edges[i] = [ai, bi]
     * 0 <= ai, bi < n
     * good.length == n
     * 0 <= good[i] <= 1
     * The input is generated such that edges represents a valid tree.
     * @param n
     * @param edges
     * @param good
     * @return
     */
    // time = O(n), space = O(n)
    List<Integer>[] adj;
    int[] w, f, res;
    public int[] maxSubgraphScore(int n, int[][] edges, int[] good) {
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            adj[a].add(b);
            adj[b].add(a);
        }

        w = new int[n];
        for (int i = 0; i < n; i++) w[i] = good[i] == 1 ? 1 : -1;
        f = new int[n];
        res = new int[n];
        dfs(0, -1);
        reroot(0, -1);
        return res;
    }

    private void dfs(int u, int fa) {
        f[u] = w[u];
        for (int v : adj[u]) {
            if (v == fa) continue;
            dfs(v, u);
            f[u] += Math.max(0, f[v]);
        }
    }

    private void reroot(int u, int fa) {
        if (fa == -1) res[u] = f[u];
        for (int v : adj[u]) {
            if (v == fa) continue;
            int vc = Math.max(0, f[v]);
            res[v] = f[v] + Math.max(0, res[u] - vc);
            reroot(v, u);
        }
    }
}