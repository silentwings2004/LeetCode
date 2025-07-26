package LC3601_3900;
import java.util.*;
public class LC3620_NetworkRecoveryPathways {
    /**
     * You are given a directed acyclic graph of n nodes numbered from 0 to 1. This is represented by a 2D array edges
     * of length m, where edges[i] = [ui, vi, costi] indicates a one‑way communication from node ui to node vi with a
     * recovery cost of costi.
     *
     * Some nodes may be offline. You are given a boolean array online where online[i] = true means node i is online.
     * Nodes 0 and n-1 are always online.
     *
     * A path from 0 to n−1 is valid if:
     *
     * All intermediate nodes on the path are online.
     * The total recovery cost of all edges on the path does not exceed k.
     * For each valid path, define its score as the minimum edge‑cost along that path.
     *
     * Return the maximum path score (i.e., the largest minimum-edge cost) among all valid paths. If no valid path
     * exists, return -1.
     *
     * Input: edges = [[0,1,5],[1,3,10],[0,2,3],[2,3,4]], online = [true,true,true,true], k = 10
     * Output: 3
     *
     * Input: edges = [[0,1,7],[1,4,5],[0,2,6],[2,3,6],[3,4,2],[2,4,6]], online = [true,true,true,false,true], k = 12
     * Output: 6
     *
     * Constraints:
     *
     * n == online.length
     * 2 <= n <= 5 * 10^4
     * 0 <= m == edges.length <= min(105, n * (n - 1) / 2)
     * edges[i] = [ui, vi, costi]
     * 0 <= ui, vi < n
     * ui != vi
     * 0 <= costi <= 10^9
     * 0 <= k <= 5 * 10^13
     * online[i] is either true or false, and both online[0] and online[n − 1] are true.
     * The given graph is a directed acyclic graph.
     * @param edges
     * @param online
     * @param k
     * @return
     */
    // S1
    // time = O(nlogn * logk), space = O(n)
    final long inf = (long)1E18;
    List<int[]>[] adj;
    boolean[] online;
    long k;
    int n;
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        this.online = online;
        this.k = k;
        n = online.length;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1], c = e[2];
            adj[a].add(new int[]{b, c});
        }

        int l = 0, r = (int)Math.min(k, (int)1E9);
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (check(mid)) l = mid;
            else r = mid - 1;
        }
        return check(r) ? r : -1;
    }

    private boolean check(int mid) {
        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[0], o2[0]));
        pq.offer(new long[]{0, 0});
        long[] dist = new long[n];
        Arrays.fill(dist, inf);
        dist[0] = 0;

        while (!pq.isEmpty()) {
            long[] t = pq.poll();
            long cost = t[0];
            int u = (int)t[1];
            if (cost > dist[u]) continue;
            if (u == n - 1) return true;

            for (int[] x : adj[u]) {
                int v = x[0], w = x[1];
                if (!online[v]) continue;
                if (w < mid) continue;
                long nc = cost + w;
                if (nc <= k && dist[v] > nc) {
                    dist[v] = nc;
                    pq.offer(new long[]{nc, v});
                }
            }
        }
        return false;
    }

    // S2
    // time = O((n + m) * logU), space = O(n + m)
    public int findMaxPathScore2(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        List<int[]>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1], c = e[2];
            if (online[a] && online[b]) {
                adj[a].add(new int[]{b, c});
            }
        }

        long[] f = new long[n];
        int l = -1, r = (int)Math.min((int)1E9, k);
        while (l < r) {
            int mid = l + r + 1 >> 1;
            Arrays.fill(f, -1);
            if (dfs(adj, f, 0, mid) <= k) l = mid;
            else r = mid - 1;
        }
        return r;
    }

    private long dfs(List<int[]>[] adj, long[] f, int u, int mid) {
        int n = adj.length;
        if (u == n - 1) return 0;
        if (f[u] != -1) return f[u];

        long res = (long)1E18;
        for (int[] x : adj[u]) {
            int v = x[0], w = x[1];
            if (w >= mid) res = Math.min(res, dfs(adj, f, v, mid) + w);
        }
        return f[u] = res;
    }
}
/**
 * 最小路径和
 * 有向无环图
 */