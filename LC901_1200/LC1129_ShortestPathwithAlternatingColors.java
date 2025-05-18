package LC901_1200;
import java.util.*;
public class LC1129_ShortestPathwithAlternatingColors {
    /**
     * You are given an integer n, the number of nodes in a directed graph where the nodes are labeled from 0 to n - 1.
     * Each edge is red or blue in this graph, and there could be self-edges and parallel edges.
     *
     * You are given two arrays redEdges and blueEdges where:
     *
     * redEdges[i] = [ai, bi] indicates that there is a directed red edge from node ai to node bi in the graph, and
     * blueEdges[j] = [uj, vj] indicates that there is a directed blue edge from node uj to node vj in the graph.
     * Return an array answer of length n, where each answer[x] is the length of the shortest path from node 0 to node
     * x such that the edge colors alternate along the path, or -1 if such a path does not exist.
     *
     * Input: n = 3, redEdges = [[0,1],[1,2]], blueEdges = []
     * Output: [0,1,-1]
     *
     * Input: n = 3, redEdges = [[0,1]], blueEdges = [[2,1]]
     * Output: [0,1,-1]
     *
     * Constraints:
     *
     * 1 <= n <= 100
     * 0 <= redEdges.length, blueEdges.length <= 400
     * redEdges[i].length == blueEdges[j].length == 2
     * 0 <= ai, bi, uj, vj < n
     * @param n
     * @param red_edges
     * @param blue_edges
     * @return
     */
    // time = O(n + r + b), space = O(n + r + b)
    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] x : red_edges) graph[x[0]].add(new int[]{x[1], 0});
        for (int[] x : blue_edges) graph[x[0]].add(new int[]{x[1], 1});
        int[][] dist = new int[n][2];
        for (int i = 1; i < n; i++) dist[i][0] = dist[i][1] = Integer.MAX_VALUE;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        q.offer(new int[]{0, 1});

        while (!q.isEmpty()) {
            int[] t = q.poll();
            int cur = t[0], color = t[1];
            for (int[] next : graph[cur]) {
                int node = next[0], c = next[1];
                if (c != color && dist[node][c] > dist[cur][color] + 1) {
                    dist[node][c] = dist[cur][color] + 1;
                    q.offer(next);
                }
            }
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = Math.min(dist[i][0], dist[i][1]);
            if (res[i] == Integer.MAX_VALUE) res[i] = -1;
        }
        return res;
    }
}
/**
 * 拆点
 */