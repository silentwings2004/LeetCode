package LC2401_2700;
import java.util.*;
public class LC2642_DesignGraphWithShortestPathCalculator {
    /**
     * There is a directed weighted graph that consists of n nodes numbered from 0 to n - 1. The edges of the graph are
     * initially represented by the given array edges where edges[i] = [fromi, toi, edgeCosti] meaning that there is an
     * edge from fromi to toi with the cost edgeCosti.
     *
     * Implement the Graph class:
     *
     * Graph(int n, int[][] edges) initializes the object with n nodes and the given edges.
     * addEdge(int[] edge) adds an edge to the list of edges where edge = [from, to, edgeCost]. It is guaranteed that
     * there is no edge between the two nodes before adding this one.
     * int shortestPath(int node1, int node2) returns the minimum cost of a path from node1 to node2. If no path exists,
     * return -1. The cost of a path is the sum of the costs of the edges in the path.
     *
     * Input
     * ["Graph", "shortestPath", "shortestPath", "addEdge", "shortestPath"]
     * [[4, [[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]], [3, 2], [0, 3], [[1, 3, 4]], [0, 3]]
     * Output
     * [null, 6, -1, null, 6]
     *
     * Constraints:
     *
     * 1 <= n <= 100
     * 0 <= edges.length <= n * (n - 1)
     * edges[i].length == edge.length == 3
     * 0 <= fromi, toi, from, to, node1, node2 <= n - 1
     * 1 <= edgeCosti, edgeCost <= 10^6
     * There are no repeated edges and no self-loops in the graph at any point.
     * At most 100 calls will be made for addEdge.
     * At most 100 calls will be made for shortestPath.
     * @param n
     * @param edges
     */
    final int N = 110, M = N * N, INF = 0x3f3f3f3f;
    int[] h, e, ne, w;
    int idx;
    int[] dist;
    boolean[] st;
    public LC2642_DesignGraphWithShortestPathCalculator(int n, int[][] edges) {
        h = new int[N];
        e = new int[M];
        ne = new int[M];
        w = new int[M];
        dist = new int[N];
        st = new boolean[N];
        Arrays.fill(h, -1);
        idx = 0;

        for (int[] edge : edges) addEdge(edge);
    }
    // time = O(1), space = O(n)
    public void addEdge(int[] edge) {
        int a = edge[0], b = edge[1], c = edge[2];
        add(a, b, c);
    }
    // time = O(nlogn), space = O(n)
    public int shortestPath(int node1, int node2) {
        return dijkstra(node1, node2);
    }

    private int dijkstra(int start, int end) {
        Arrays.fill(st, false);
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        pq.offer(new int[]{0, start});

        while (!pq.isEmpty()) {
            int[] t = pq.poll();
            int ver = t[1], distance = t[0];
            if (st[ver]) continue;
            st[ver] = true;

            for (int i = h[ver]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] > distance + w[i]) {
                    dist[j] = distance + w[i];
                    pq.offer(new int[]{dist[j], j});
                }
            }
        }
        return dist[end] == INF ? -1 : dist[end];
    }

    private void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }
}