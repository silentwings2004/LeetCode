package LC2401_2700;
import java.util.*;
public class LC2608_ShortestCycleinaGraph {
    /**
     * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1. The edges in the
     * graph are represented by a given 2D integer array edges, where edges[i] = [ui, vi] denotes an edge between vertex
     * ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
     *
     * Return the length of the shortest cycle in the graph. If no cycle exists, return -1.
     *
     * A cycle is a path that starts and ends at the same node, and each edge in the path is used only once.
     *
     * Input: n = 7, edges = [[0,1],[1,2],[2,0],[3,4],[4,5],[5,6],[6,3]]
     * Output: 3
     *
     * Input: n = 4, edges = [[0,1],[0,2]]
     * Output: -1
     *
     * Constraints:
     *
     * 2 <= n <= 1000
     * 1 <= edges.length <= 1000
     * edges[i].length == 2
     * 0 <= ui, vi < n
     * ui != vi
     * There are no repeated edges.
     * @param n
     * @param edges
     * @return
     */
    // time = O(n^2), space = O(n)
    final int N = 1010, M = N * 2, INF = 0x3f3f3f3f;
    int[] h, e, ne;
    int[] q, dist;
    int idx, res;
    public int findShortestCycle(int n, int[][] edges) {
        h = new int[N];
        e = new int[M];
        ne = new int[M];
        q = new int[N];
        dist = new int[N];
        Arrays.fill(h, -1);
        idx = 0;
        res = INF;

        for (int[] e : edges) {
            int a = e[0], b = e[1];
            add(a, b);
            add(b, a);
        }

        for (int i = 0; i < n; i++) bfs(i);
        return res == INF ? -1 : res;
    }

    private void bfs(int start) {
        Arrays.fill(dist, -1);
        dist[start] = 0;
        int hh = 0, tt = 0;
        q[0] = start;

        while (hh <= tt) {
            int t = q[hh++];
            for (int i = h[t]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] == -1) {
                    dist[j] = dist[t] + 1;
                    q[++tt] = j;
                } else if (dist[j] != dist[t] - 1) {
                    res = Math.min(res, dist[j] + dist[t] + 1);
                }
            }
        }
    }

    private void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }
}