package LC3601_3900;
import java.util.*;
public class LC3778_MinimumDistanceExcludingOneMaximumWeightedEdge {
    /**
     * You are given a positive integer n and a 2D integer array edges, where edges[i] = [ui, vi, wi].
     *
     * There is a weighted connected simple undirected graph with n nodes labeled from 0 to n - 1. Each [ui, vi, wi] in
     * edges represents an edge between node ui and node vi with positive weight wi.
     *
     * The cost of a path is the sum of weights of the edges in the path, excluding the edge with the maximum weight.
     * If there are multiple edges in the path with the maximum weight, only the first such edge is excluded.
     *
     * Return an integer representing the minimum cost of a path going from node 0 to node n - 1.
     *
     * Input: n = 5, edges = [[0,1,2],[1,2,7],[2,3,7],[3,4,4]]
     * Output: 13
     *
     * Input: n = 3, edges = [[0,1,1],[1,2,1],[0,2,50000]]
     * Output: 0
     *
     * Constraints:
     *
     * 2 <= n <= 5 * 10^4
     * n - 1 <= edges.length <= 10^9
     * edges[i] = [ui, vi, wi]
     * 0 <= ui < vi < n
     * [ui, vi] != [uj, vj]
     * 1 <= wi <= 5 * 10^4
     * The graph is connected.
     * @param n
     * @param edges
     * @return
     */
    // time = O(nlogn), space = O(n)
    public long minCostExcludingMax(int n, int[][] edges) {
        List<int[]>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            adj[u].add(new int[]{v, w});
            adj[v].add(new int[]{u, w});
        }

        long[][] dist = new long[n][2];
        for (int i = 0; i < n; i++) Arrays.fill(dist[i], Long.MAX_VALUE);
        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[0], o2[0]));
        pq.offer(new long[]{0, 0, 0});
        dist[0][0] = 0;

        while (!pq.isEmpty()) {
            long[] t = pq.poll();
            long d = t[0];
            int u = (int)t[1], state = (int)t[2];
            if (d > dist[u][state]) continue;
            if (u == n - 1) return d;

            for (int[] x : adj[u]) {
                int v = x[0], w = x[1];
                if (dist[u][state] + w < dist[v][state]) {
                    dist[v][state] = dist[u][state] + w;
                    pq.offer(new long[]{dist[v][state], v, state});
                }
                if (state == 0) {
                    if (dist[u][0] < dist[v][1]) {
                        dist[v][1] = dist[u][0];
                        pq.offer(new long[]{dist[v][1], v, 1});
                    }
                }
            }
        }
        return -1;
    }
}