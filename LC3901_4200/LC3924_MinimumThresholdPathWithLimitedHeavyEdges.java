package LC3901_4200;
import java.util.*;
public class LC3924_MinimumThresholdPathWithLimitedHeavyEdges {
    /**
     * There is an undirected weighted graph with n nodes labeled from 0 to n - 1.
     *
     * The graph is represented by a 2D integer array edges, where each edge edges[i] = [ui, vi, wi] indicates that
     * there is an undirected edge between nodes ui and vi with weight wi.
     *
     * You are also given integers source, target and k.
     *
     * A threshold value determines whether an edge is considered light or heavy:
     *
     * An edge is light if its weight is less than or equal to threshold.
     *
     * An edge is heavy if its weight is greater than threshold.
     *
     * A path from source to target is valid if it contains at most k heavy edges.
     *
     * Return the minimum integer threshold such that at least one valid path exists from source to target. If no such
     * path exists, return -1.
     *
     * Input: n = 6, edges = [[0,1,5],[1,2,3],[3,4,4],[4,5,1],[1,4,2]], source = 0, target = 3, k = 1
     * Output: 4
     *
     * Input: n = 6, edges = [[0,1,3],[1,2,4],[3,4,5],[4,5,6]], source = 0, target = 4, k = 1
     * Output: -1
     *
     * Input: n = 4, edges = [[0,1,2],[1,2,2],[2,3,2],[3,0,2]], source = 0, target = 0, k = 0
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n <= 10^3
     * 0 <= edges.length <= 10^3
     * edges[i] = [ui, vi, wi]
     * 0 <= ui, vi <= n - 1
     * 1 <= wi <= 10^9
     * 0 <= source, target <= n - 1
     * 0 <= k <= edges.length
     * @param n
     * @param edges
     * @param source
     * @param target
     * @param k
     * @return
     */
    // time = O((n + m) logU), space = O(n + m)
    List<int[]>[] adj;
    int n, source, target, k;
    public int minimumThreshold(int n, int[][] edges, int source, int target, int k) {
        this.n = n;
        this.source = source;
        this.target = target;
        this.k = k;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            adj[u].add(new int[]{v, w});
            adj[v].add(new int[]{u, w});
        }

        int l = 0, r = (int)1E9;
        while (l < r) {
            int mid = l + r >> 1;
            if (check(mid)) r = mid;
            else l = mid + 1;
        }
        return check(r) ? r : -1;
    }

    private boolean check(int mid) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.offer(new int[]{source, 0});
        boolean[] st = new boolean[n];

        while (!pq.isEmpty()) {
            int[] t = pq.poll();
            int u = t[0], cnt = t[1];
            if (u == target) return true;
            if (st[u]) continue;
            st[u] = true;

            for (int[] x : adj[u]) {
                int v = x[0], w = x[1];
                if (st[v]) continue;
                int cnt2 = w > mid ? cnt + 1 : cnt;
                if (cnt2 <= k) pq.offer(new int[]{v, cnt2});
            }
        }
        return false;
    }
}