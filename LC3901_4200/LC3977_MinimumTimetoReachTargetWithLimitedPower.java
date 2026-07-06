package LC3901_4200;
import java.util.*;
public class LC3977_MinimumTimetoReachTargetWithLimitedPower {
    /**
     * You are given a directed weighted graph with n nodes labeled from 0 to n - 1.
     *
     * The graph is represented by a 2D integer array edges, where edges[i] = [ui, vi, ti] indicates a directed edge
     * from node ui to node vi that takes ti seconds to traverse.
     *
     * You are also given an integer power representing the initial available power, and an integer array cost of
     * length n, where cost[u] represents the power required to forward the signal from node u through any one of its
     * outgoing edges.
     *
     * You are given two integers source and target.
     *
     * The signal starts at source at time 0 with power units of power and follows these rules:
     *
     * The signal may traverse a directed edge from node u only if the remaining power is at least cost[u].
     * No power is consumed when the signal arrives at a node, unless it later leaves that node by traversing another
     * edge.
     * When the signal is forwarded from node u, the remaining power is decreased by cost[u] units.
     * Traversing an edge edges[i] = [ui, vi, ti] increases the total time by ti seconds.
     * Return an integer array answer of size 2, where:
     *
     * answer[0] is the minimum time required for the signal to reach node target.
     * answer[1] is the maximum remaining power among all paths that achieve answer[0].
     * If the signal cannot reach target, return [-1, -1].
     *
     * Input: n = 5, edges = [[0,1,1],[1,4,1],[0,2,1],[2,3,1],[3,4,1]], power = 4, cost = [2,3,1,1,1], source = 0,
     * target = 4
     * Output: [3,0]
     *
     * Input: n = 3, edges = [[0,1,2],[1,2,2],[2,0,2]], power = 3, cost = [1,1,1], source = 1, target = 1
     * Output: [0,3]
     *
     * Input: n = 4, edges = [[0,1,3],[2,3,4]], power = 3, cost = [1,1,1,1], source = 0, target = 3
     * Output: [-1,-1]
     *
     * Constraints:
     *
     * 1 <= n <= 1000
     * 0 <= edges.length <= 1000
     * edges[i] = [ui, vi, ti]
     * 0 <= ui, vi <= n - 1
     * 1 <= ti <= 10^9
     * 1 <= power <= 1000
     * cost.length == n
     * 1 <= cost[i] <= 2000
     * 0 <= source, target <= n - 1
     * @param n
     * @param edges
     * @param power
     * @param cost
     * @param source
     * @param target
     * @return
     */
    // time = O(nk + mklog(mk)), space = O(nk + mk)
    public long[] minTimeMaxPower(int n, int[][] edges, int power, int[] cost, int source, int target) {
        if (source == target) return new long[]{0, power};
        List<int[]>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1], t = e[2];
            adj[u].add(new int[]{v, t});
        }
        final long inf = (long)1E18;
        long[][] dist = new long[n][power + 1];
        for (int i = 0; i < n; i++) Arrays.fill(dist[i], inf);
        dist[source][power] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[2], o2[2]));
        pq.offer(new long[]{source, power, 0});

        long mt = inf;
        while (!pq.isEmpty()) {
            long[] x = pq.poll();
            int u = (int)x[0], p = (int)x[1];
            long t = x[2];
            if (t > mt) break;
            if (u == target) {
                mt = Math.min(mt, t);
                continue;
            }
            if (t > dist[u][p] || p < cost[u]) continue;
            int np = p - cost[u];
            for (int[] y : adj[u]) {
                int v = y[0], w = y[1];
                long nt = t + w;
                if (nt < dist[v][np]) {
                    dist[v][np] = nt;
                    pq.offer(new long[]{v, np, nt});
                }
            }
        }
        if (mt == inf) return new long[]{-1, -1};
        int mp = -1;
        for (int p = power; p >= 0; p--) {
            if (dist[target][p] == mt) {
                mp = p;
                break;
            }
        }
        return new long[]{mt, mp};
    }
}