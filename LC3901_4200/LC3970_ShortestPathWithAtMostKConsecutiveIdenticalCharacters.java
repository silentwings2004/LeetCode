package LC3901_4200;
import java.util.*;
public class LC3970_ShortestPathWithAtMostKConsecutiveIdenticalCharacters {
    /**
     * You are given an integer n representing the number of nodes in a directed weighted graph, numbered from 0 to
     * n - 1. This is represented by a 2D integer array edges, where edges[i] = [ui, vi, wi] represents a directed edge
     * from node ui to node vi with weight wi.
     *
     * You are also given a string labels of length n, where labels[i] is the character assigned to node i, and an
     * integer k.
     *
     * Return the minimum total edge weight of a path from node 0 to node n - 1 such that the concatenation of the
     * labels of the nodes along the path contains at most k consecutive identical characters. If no valid path exists,
     * return -1.
     *
     * Input: n = 3, edges = [[0,1,1],[1,2,1],[0,2,3]], labels = "aab", k = 1
     * Output: 3
     *
     * Input: n = 3, edges = [[0,1,1],[1,2,1],[0,2,3]], labels = "aab", k = 2
     * Output: 2
     *
     * Input: n = 3, edges = [[0,1,1],[1,2,1]], labels = "aaa", k = 2
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= n == labels.length <= 5 * 10^4
     * 0 <= edges.length <= 5 * 10^4
     * edges[i] == [ui, vi, wi]
     * 0 <= ui, vi <= n - 1
     * ui != vi
     * 1 <= wi <= 10^4
     * labels consists of lowercase English letters
     * 1 <= k <= 50
     * @param n
     * @param edges
     * @param labels
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        final int inf = 0x3f3f3f3f;
        List<int[]>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            adj[u].add(new int[]{v, w});
        }
        int[][] dist = new int[n][k + 1];
        for (int i = 0; i < n; i++) Arrays.fill(dist[i], inf);
        dist[0][1] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        pq.offer(new int[]{0, 1, 0}); // {node, cnt, dist}

        while (!pq.isEmpty()) {
            int[] t = pq.poll();
            int u = t[0], cnt = t[1], d = t[2];
            if (dist[u][cnt] != d) continue;

            for (int[] x : adj[u]) {
                int v = x[0], w = x[1];
                int nct = labels.charAt(v) == labels.charAt(u) ? cnt + 1 : 1;
                if (nct > k) continue;
                int nd = d + w;
                if (nd < dist[v][nct]) {
                    dist[v][nct] = nd;
                    pq.offer(new int[]{v, nct, nd});
                }
            }
        }

        int res = inf;
        for (int i = 0; i <= k; i++) res = Math.min(res, dist[n - 1][i]);
        return res == inf ? -1 : res;
    }
}
/**
 * 分层图最短路
 */
