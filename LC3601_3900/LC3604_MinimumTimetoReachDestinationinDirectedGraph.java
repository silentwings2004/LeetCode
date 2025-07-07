package LC3601_3900;
import java.util.*;
public class LC3604_MinimumTimetoReachDestinationinDirectedGraph {
    /**
     * You are given an integer n and a directed graph with n nodes labeled from 0 to n - 1. This is represented by a
     * 2D array edges, where edges[i] = [ui, vi, starti, endi] indicates an edge from node ui to vi that can only be
     * used at any integer time t such that starti <= t <= endi.
     *
     * You start at node 0 at time 0.
     *
     * In one unit of time, you can either:
     *
     * Wait at your current node without moving, or
     * Travel along an outgoing edge from your current node if the current time t satisfies starti <= t <= endi.
     * Return the minimum time required to reach node n - 1. If it is impossible, return -1.
     *
     * Input: n = 3, edges = [[0,1,0,1],[1,2,2,5]]
     * Output: 3
     *
     * Input: n = 4, edges = [[0,1,0,3],[1,3,7,8],[0,2,1,5],[2,3,4,7]]
     * Output: 5
     *
     * Input: n = 3, edges = [[1,0,1,3],[1,2,3,5]]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * 0 <= edges.length <= 10^5
     * edges[i] == [ui, vi, starti, endi]
     * 0 <= ui, vi <= n - 1
     * ui != vi
     * 0 <= starti <= endi <= 10^9
     * @param n
     * @param edges
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int minTime(int n, int[][] edges) {
        List<int[]>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1], st = e[2], ed = e[3];
            adj[a].add(new int[]{b, st, ed});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        pq.offer(new int[]{0, 0});
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        while (!pq.isEmpty()) {
            int[] t = pq.poll();
            int ts = t[0], u = t[1];
            if (ts > dist[u]) continue;
            if (u == n - 1) return ts;

            for (int[] x : adj[u]) {
                int v = x[0], st = x[1], ed = x[2];
                int dt = Math.max(st, ts);
                if (dt > ed) continue;
                int at = dt + 1;
                if (dist[v] > at) {
                    dist[v] = at;
                    pq.offer(new int[]{at, v});
                }
            }
        }
        return -1;
    }
}