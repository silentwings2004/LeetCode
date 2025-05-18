package LC3301_3600;
import java.util.*;
public class LC3367_MaximizeSumofWeightsafterEdgeRemovals {
    /**
     * There exists an undirected tree with n nodes numbered 0 to n - 1. You are given a 2D integer array edges of
     * length n - 1, where edges[i] = [ui, vi, wi] indicates that there is an edge between nodes ui and vi with weight
     * wi in the tree.
     *
     * Your task is to remove zero or more edges such that:
     *
     * Each node has an edge with at most k other nodes, where k is given.
     * The sum of the weights of the remaining edges is maximized.
     * Return the maximum possible sum of weights for the remaining edges after making the necessary removals.
     *
     * Input: edges = [[0,1,4],[0,2,2],[2,3,12],[2,4,6]], k = 2
     * Output: 22
     *
     * Input: edges = [[0,1,5],[1,2,10],[0,3,15],[3,4,20],[3,5,5],[0,6,10]], k = 3
     * Output: 65
     *
     * Constraints:
     *
     * 2 <= n <= 10^5
     * 1 <= k <= n - 1
     * edges.length == n - 1
     * edges[i].length == 3
     * 0 <= edges[i][0] <= n - 1
     * 0 <= edges[i][1] <= n - 1
     * 1 <= edges[i][2] <= 10^6
     * The input is generated such that edges form a valid tree.
     * @param edges
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    List<int[]>[] adj;
    public long maximizeSumOfWeights(int[][] edges, int k) {
        int n = edges.length + 1;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            int a = edges[i][0], b = edges[i][1], c = edges[i][2];
            adj[a].add(new int[]{b, c});
            adj[b].add(new int[]{a, c});
        }

        long[] res = dfs(0, -1, k);
        return Math.max(res[0], res[1]);
    }

    private long[] dfs(int u, int fa, int k) {
        long x = 0, y = 0;
        List<Long> q = new ArrayList<>();
        for (int[] t : adj[u]) {
            int v = t[0], w = t[1];
            if (v == fa) continue;
            long[] res = dfs(v, u, k);
            x += res[0];
            long d = w + res[1] - res[0];
            if (d > 0) q.add(d);
        }
        Collections.sort(q, (o1, o2) -> Long.compare(o2, o1));
        for (int i = 0; i < Math.min(k - 1, q.size()); i++) x += q.get(i);
        y = x;
        if (q.size() >= k) x += q.get(k - 1);
        return new long[]{x, y};
    }
}