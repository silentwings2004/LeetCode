package LC3601_3900;
import java.util.*;
public class LC3807_MinimumCosttoRepairEdgestoTraverseaGraph {
    /**
     * You are given an undirected graph with n nodes labeled from 0 to n - 1. The graph consists of m edges represented
     * by a 2D integer array edges, where edges[i] = [ui, vi, wi] indicates that there is an edge between nodes ui and
     * vi with a repair cost of wi.
     * <p>
     * You are also given an integer k. Initially, all edges are damaged.
     * <p>
     * You may choose a non-negative integer money and repair all edges whose repair cost is less than or equal to
     * money. All other edges remain damaged and cannot be used.
     * <p>
     * You want to travel from node 0 to node n - 1 using at most k edges.
     * <p>
     * Return an integer denoting the minimum amount of money required to make this possible, or return -1 if it is
     * impossible.
     * <p>
     * Input: n = 3, edges = [[0,1,10],[1,2,10],[0,2,100]], k = 1
     * Output: 100
     * <p>
     * Input: n = 6, edges = [[0,2,5],[2,3,6],[3,4,7],[4,5,5],[0,1,10],[1,5,12],[0,3,9],[1,2,8],[2,4,11]], k = 2
     * Output: 12
     * <p>
     * Input: n = 3, edges = [[0,1,1]], k = 1
     * Output: -1
     * <p>
     * Constraints:
     * <p>
     * 2 <= n <= 5 * 10^4
     * 1 <= edges.length == m <= 10^5
     * edges[i] = [ui, vi, wi]
     * 0 <= ui, vi < n
     * 1 <= wi <= 10^9
     * 1 <= k <= n
     * There are no self-loops or duplicate edges in the graph.
     *
     * @param n
     * @param edges
     * @param k
     * @return
     */
    // time = O((n + m) * logW), space = O(n + m)
    int n, k;
    int[][] edges;
    public int minCost(int n, int[][] edges, int k) {
        this.n = n;
        this.k = k;
        this.edges = edges;
        int mx = 0;
        for (int[] e : edges) mx = Math.max(mx, e[2]);

        int l = 1, r = mx;
        while (l < r) {
            int mid = l + r >> 1;
            if (check(mid, k)) r = mid;
            else l = mid + 1;
        }
        return check(r, k) ? r : -1;
    }

    private boolean check(int t, int k) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1], c = e[2];
            if (c > t) continue;
            adj[a].add(b);
            adj[b].add(a);
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        boolean[] st = new boolean[n];
        st[0] = true;

        int step = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            while (sz-- > 0) {
                int u = q.poll();
                if (u == n - 1) return true;
                for (int v : adj[u]) {
                    if (st[v]) continue;
                    st[v] = true;
                    q.offer(v);
                }
            }
            step++;
            if (step > k) break;
        }
        return false;
    }
}