package LC3601_3900;
import java.util.*;
public class LC3650_MinimumCostPathwithEdgeReversals {
    /**
     * You are given a directed, weighted graph with n nodes labeled from 0 to n - 1, and an array edges where
     * edges[i] = [ui, vi, wi] represents a directed edge from node ui to node vi with cost wi.
     *
     * Each node ui has a switch that can be used at most once: when you arrive at ui and have not yet used its switch,
     * you may activate it on one of its incoming edges vi → ui reverse that edge to ui → vi and immediately traverse it.
     *
     * The reversal is only valid for that single move, and using a reversed edge costs 2 * wi.
     *
     * Return the minimum total cost to travel from node 0 to node n - 1. If it is not possible, return -1.
     *
     * Input: n = 4, edges = [[0,1,3],[3,1,1],[2,3,4],[0,2,2]]
     * Output: 5
     *
     * Input: n = 4, edges = [[0,2,1],[2,1,1],[1,3,1],[2,3,3]]
     * Output: 3
     *
     * Constraints:
     *
     * 2 <= n <= 5 * 10^4
     * 1 <= edges.length <= 10^5
     * edges[i] = [ui, vi, wi]
     * 0 <= ui, vi <= n - 1
     * 1 <= wi <= 1000
     * @param n
     * @param edges
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int minCost(int n, int[][] edges) {
        List<int[]>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            adj[u].add(new int[]{v, w});
            adj[v].add(new int[]{u, w * 2});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        pq.offer(new int[]{0, 0});
        boolean[] st = new boolean[n];

        while (!pq.isEmpty()) {
            int[] t = pq.poll();
            int cost = t[0], u = t[1];
            if (st[u]) continue;
            st[u] = true;
            if (u == n - 1) return cost;

            for (int[] x : adj[u]) {
                int v = x[0], w = x[1];
                if (st[v]) continue;
                pq.offer(new int[]{cost + w, v});
            }
        }
        return -1;
    }
}