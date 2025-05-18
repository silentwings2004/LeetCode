package LC3301_3600;
import java.util.*;
public class LC3543_MaximumWeightedKEdgePath {
    /**
     * You are given an integer n and a Directed Acyclic Graph (DAG) with n nodes labeled from 0 to n - 1. This is
     * represented by a 2D array edges, where edges[i] = [ui, vi, wi] indicates a directed edge from node ui to vi with
     * weight wi.
     *
     * You are also given two integers, k and t.
     *
     * Your task is to determine the maximum possible sum of edge weights for any path in the graph such that:
     *
     * The path contains exactly k edges.
     * The total sum of edge weights in the path is strictly less than t.
     * Return the maximum possible sum of weights for such a path. If no such path exists, return -1.
     *
     * Input: n = 3, edges = [[0,1,1],[1,2,2]], k = 2, t = 4
     * Output: 3
     *
     * Input: n = 3, edges = [[0,1,2],[0,2,3]], k = 1, t = 3
     * Output: 2
     *
     * Input: n = 3, edges = [[0,1,6],[1,2,8]], k = 1, t = 6
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= n <= 300
     * 0 <= edges.length <= 300
     * edges[i] = [ui, vi, wi]
     * 0 <= ui, vi < n
     * ui != vi
     * 1 <= wi <= 10
     * 0 <= k <= 300
     * 1 <= t <= 600
     * The input graph is guaranteed to be a DAG.
     * There are no duplicate edges.
     * @param n
     * @param edges
     * @param k
     * @param t
     * @return
     */
    // S1: DP
    // time = O(n * k * t), space = O(n * k * t)
    public int maxWeight(int n, int[][] edges, int k, int t) {
        if (k > edges.length) return -1;
        List<int[]>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1], c = e[2];
            adj[a].add(new int[]{b, c});
        }
        boolean[][][] f = new boolean[k + 1][n][t];
        for (int i = 0; i < n; i++) f[0][i][0] = true;
        for (int i = 0; i < k; i++) {
            for (int u = 0; u < n; u++) {
                for (int j = 0; j < t; j++) {
                    if (f[i][u][j]) {
                        for (int[] x : adj[u]) {
                            int v = x[0], w = x[1];
                            if (j + w < t) f[i + 1][v][j + w] = true;
                        }
                    }
                }
            }
        }
        for (int j = t - 1; j >= 0; j--) {
            for (int i = 0; i < n; i++) {
                if (f[k][i][j]) return j;
            }
        }
        return -1;
    }

    // S2: Memoization
    // time = O((n + m) * k * t), space = O(n * k * t)
    List<int[]>[] adj;
    HashSet<Integer> set;
    int res, k, t;
    public int maxWeight2(int n, int[][] edges, int k, int t) {
        if (k == 0) return 0;
        if (k > n - 1) return -1;
        this.k = k;
        this.t = t;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1], c = e[2];
            adj[a].add(new int[]{b, c});
        }
        set = new HashSet<>();
        res = -1;
        for (int i = 0; i < n; i++) dfs(i, 0, 0);
        return res;
    }

    private void dfs(int u, int cnt, int s) {
        if (cnt == k) {
            res = Math.max(res, s);
            return;
        }
        int h = u << 20 | cnt << 10 | s;
        if (!set.add(h)) return;

        for (int[] x : adj[u]) {
            int v = x[0], w = x[1];
            if (s + w < t) dfs(v, cnt + 1, s + w);
        }
    }
}