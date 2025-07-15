package LC3601_3900;
import java.util.*;
public class LC3613_MinimizeMaximumComponentCost {
    /**
     * You are given an undirected connected graph with n nodes labeled from 0 to n - 1 and a 2D integer array edges
     * where edges[i] = [ui, vi, wi] denotes an undirected edge between node ui and node vi with weight wi, and an
     * integer k.
     *
     * You are allowed to remove any number of edges from the graph such that the resulting graph has at most k
     * connected components.
     *
     * The cost of a component is defined as the maximum edge weight in that component. If a component has no edges,
     * its cost is 0.
     *
     * Return the minimum possible value of the maximum cost among all components after such removals.
     *
     * Input: n = 5, edges = [[0,1,4],[1,2,3],[1,3,2],[3,4,6]], k = 2
     * Output: 4
     *
     * Input: n = 4, edges = [[0,1,5],[1,2,5],[2,3,5]], k = 1
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= n <= 5 * 10^4
     * 0 <= edges.length <= 10^5
     * edges[i].length == 3
     * 0 <= ui, vi < n
     * 1 <= wi <= 10^6
     * 1 <= k <= n
     * The input graph is connected.
     * @param n
     * @param edges
     * @param k
     * @return
     */
    // time = O(nlogn), space = O(n)
    int[] p;
    public int minCost(int n, int[][] edges, int k) {
        if (k >= n) return 0;
        p = new int[n];
        for (int i = 0; i < n; i++) p[i] = i;
        Arrays.sort(edges, (o1, o2) -> o1[2] - o2[2]);
        int t = n, res = 0;
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            if (t <= k) break;
            if (find(u) != find(v)) {
                p[find(u)] = find(v);
                t--;
                res = Math.max(res, w);
            }
        }
        return res;
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}