package LC3001_3300;
import java.util.*;
public class LC3123_FindEdgesinShortestPaths {
    /**
     * You are given an undirected weighted graph of n nodes numbered from 0 to n - 1. The graph consists of m edges
     * represented by a 2D array edges, where edges[i] = [ai, bi, wi] indicates that there is an edge between nodes ai
     * and bi with weight wi.
     *
     * Consider all the shortest paths from node 0 to node n - 1 in the graph. You need to find a boolean array answer
     * where answer[i] is true if the edge edges[i] is part of at least one shortest path. Otherwise, answer[i] is false.
     *
     * Return the array answer.
     *
     * Note that the graph may not be connected.
     *
     * Input: n = 6, edges = [[0,1,4],[0,2,1],[1,3,2],[1,4,3],[1,5,1],[2,3,1],[3,5,3],[4,5,2]]
     * Output: [true,true,true,false,true,true,true,false]
     *
     * Input: n = 4, edges = [[2,0,1],[0,1,1],[0,3,4],[3,2,2]]
     * Output: [true,false,false,true]
     *
     * Constraints:
     *
     * 2 <= n <= 5 * 10^4
     * m == edges.length
     * 1 <= m <= min(5 * 104, n * (n - 1) / 2)
     * 0 <= ai, bi < n
     * ai != bi
     * 1 <= wi <= 10^5
     * There are no repeated edges.
     * @param n
     * @param edges
     * @return
     */
    // time = O(n + mlogm), space = O(n + m)
    final long inf = (long) 1e18;
    List<int[]>[] adj;
    public boolean[] findAnswer(int n, int[][] edges) {
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        long[] ds = new long[n], dt = new long[n];
        for (int[] e : edges) {
            int a = e[0], b = e[1], c = e[2];
            adj[a].add(new int[]{b, c});
            adj[b].add(new int[]{a, c});
        }

        dijkstra(0, ds);
        dijkstra(n - 1, dt);

        int m = edges.length;
        boolean[] res = new boolean[m];
        for (int i = 0; i < m; i++) {
            int a = edges[i][0], b = edges[i][1], c = edges[i][2];
            if (ds[a] + dt[b] + c == ds[n - 1]) res[i] = true;
            else if (ds[b] + dt[a] + c == ds[n - 1]) res[i] = true;
        }
        return res;
    }

    private void dijkstra(int start, long[] dis) {
        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[0], o2[0]));
        pq.offer(new long[]{0, start});
        Arrays.fill(dis, inf);
        dis[start] = 0;

        while (!pq.isEmpty()) {
            long[] t = pq.poll();
            long d = t[0];
            int u = (int)t[1];

            for (int[] nxt : adj[u]) {
                int v = nxt[0], w = nxt[1];
                if (dis[v] > dis[u] + w) {
                    dis[v] = dis[u] + w;
                    pq.offer(new long[]{dis[v], v});
                }
            }
        }
    }
}