package LC2700_3000;
import java.util.*;
public class LC2714_FindShortestPathwithKHops {
    /**
     * You are given a positive integer n which is the number of nodes of a 0-indexed undirected weighted connected
     * graph and a 0-indexed 2D array edges where edges[i] = [ui, vi, wi] indicates that there is an edge between nodes
     * ui and vi with weight wi.
     *
     * You are also given two nodes s and d, and a positive integer k, your task is to find the shortest path from s to
     * d, but you can hop over at most k edges. In other words, make the weight of at most k edges 0 and then find the
     * shortest path from s to d.
     *
     * Return the length of the shortest path from s to d with the given condition.
     *
     * Input: n = 4, edges = [[0,1,4],[0,2,2],[2,3,6]], s = 1, d = 3, k = 2
     * Output: 2
     *
     * Input: n = 7, edges = [[3,1,9],[3,2,4],[4,0,9],[0,5,6],[3,6,2],[6,0,4],[1,2,4]], s = 4, d = 1, k = 2
     * Output: 6
     *
     * Input: n = 5, edges = [[0,4,2],[0,1,3],[0,2,1],[2,1,4],[1,3,4],[3,4,7]], s = 2, d = 3, k = 1
     * Output: 3
     *
     * Constraints:
     *
     * 2 <= n <= 500
     * n - 1 <= edges.length <= n * (n - 1) / 2
     * edges[i].length = 3
     * 0 <= edges[i][0], edges[i][1] <= n - 1
     * 1 <= edges[i][2] <= 10^6
     * 0 <= s, d, k <= n - 1
     * s != d
     * The input is generated such that the graph is connected and has no repeated edges or self-loops
     * @param n
     * @param edges
     * @param s
     * @param d
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n * k)
    final int N = 510, M = N * N, INF = 0x3f3f3f3f;
    int[] h, e, ne, w;
    int idx;
    int[][] dist;
    boolean[][] st;
    public int shortestPathWithHops(int n, int[][] edges, int s, int d, int k) {
        h = new int[N];
        e = new int[M];
        ne = new int[M];
        w = new int[M];
        dist = new int[N][N];
        st = new boolean[N][N];
        Arrays.fill(h, -1);
        idx = 0;

        for (int[] e : edges) {
            int a = e[0], b = e[1], c = e[2];
            add(a, b, c);
            add(b, a, c);
        }
        return dijkstra(s, d, k);
    }

    private int dijkstra(int s, int d, int k) {
        for (int i = 0; i < N; i++) Arrays.fill(dist[i], INF);
        dist[s][0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        pq.offer(new int[]{0, s, k});

        while (!pq.isEmpty()) {
            int[] t = pq.poll();
            int distance = t[0], u = t[1], p = t[2];
            if (u == d) return distance;
            if (st[u][p]) continue;
            st[u][p] = true;

            for (int i = h[u]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j][p] > distance + w[i]) {
                    dist[j][p] = distance + w[i];
                    pq.offer(new int[]{dist[j][p], j, p});
                }
                if (p > 0 && dist[j][p - 1] > distance) {
                    dist[j][p - 1] = distance;
                    pq.offer(new int[]{dist[j][p - 1], j, p - 1});
                }
            }
        }
        return -1;
    }

    private void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }
}