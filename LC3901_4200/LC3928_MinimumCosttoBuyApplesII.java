package LC3901_4200;
import java.util.*;
public class LC3928_MinimumCosttoBuyApplesII {
    /**
     * You are given an integer n and an integer array prices of length n, where prices[i] is the price of apples at
     * shop i.
     *
     * You are also given a 2D integer array roads, where roads[i] = [ui, vi, costi, taxi] represents a bidirectional
     * road:
     *
     * ui and vi are the shops connected by the road.
     * costi is the cost to travel the road without carrying apples.
     * taxi is the multiplier applied to costi when traveling with apples.
     * For each shop i, you can either:
     *
     * Buy apples locally at shop i for prices[i].
     * Travel empty to any shop j using any number of roads, buy apples for prices[j], and return to shop i while
     * carrying apples, paying cost * tax on each road used for the return trip.
     * The forward path, where you travel empty, and the return path may be different.
     *
     * Return an integer array ans of length n, where ans[i] is the minimum total cost to buy apples starting from shop
     * i.
     *
     * Input: n = 2, prices = [8,3], roads = [[0,1,1,2]]
     * Output: [6,3]
     *
     * Input: n = 3, prices = [9,4,6], roads = [[0,1,1,3],[1,2,4,2]]
     * Output: [8,4,6]
     *
     * Input: n = 3, prices = [10,11,1], roads = [[0,2,1,3],[1,2,3,4],[0,1,5,2]]
     * Output: [5,11,1]
     *
     * onstraints:
     *
     * 1 <= n <= 1000
     * prices.length == n
     * 1 <= prices[i] <= 10^9
     * 0 <= roads.length <= min(n × (n - 1) / 2, 2000)
     * roads[i] = [ui, vi, costi, taxi]
     * 0 <= ui, vi <= n - 1
     * ui != vi
     * 1 <= costi <= 10^9
     * 1 <= taxi <= 100
     * There are no repeated edges.
     * @param n
     * @param prices
     * @param roads
     * @return
     */
    // time = O((n + m) * logn), space = O(n^2 + m)
    final long inf = (long)1E18;
    public int[] minCost(int n, int[] prices, int[][] roads) {
        List<long[]>[] f = new List[n];
        List<long[]>[] g = new List[n];
        for (int i = 0; i < n; i++) {
            f[i] = new ArrayList<>();
            g[i] = new ArrayList<>();
        }
        for (int[] r : roads) {
            int u = r[0], v = r[1], cost = r[2], tax = r[3];
            f[u].add(new long[]{v, cost});
            f[v].add(new long[]{u, cost});
            long ac = 1L * cost * tax;
            g[u].add(new long[]{v, ac});
            g[v].add(new long[]{u, ac});
        }

        long[][] d1 = new long[n][n];
        long[][] d2 = new long[n][n];
        for (int i = 0; i < n; i++) {
            dijkstra(i, f, d1[i]);
            dijkstra(i, g, d2[i]);
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = prices[i];
            for (int j = 0; j < n; j++) {
                if (d1[i][j] < inf && d2[j][i] < inf) {
                    long tot = d1[i][j] + prices[j] + d2[j][i];
                    res[i] = (int)Math.min(res[i], tot);
                }
            }
        }
        return res;
    }

    private void dijkstra(int start, List<long[]>[] adj, long[] dist) {
        int n = adj.length;
        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[1], o2[1]));
        pq.offer(new long[]{start, 0});
        Arrays.fill(dist, inf);
        dist[start] = 0;

        while (!pq.isEmpty()) {
            long[] t = pq.poll();
            int u = (int)t[0];
            long d = t[1];
            if (d > dist[u]) continue;
            for (long[] x : adj[u]) {
                int v = (int)x[0];
                long nd = d + x[1];
                if (nd < dist[v]) {
                    dist[v] = nd;
                    pq.offer(new long[]{v, nd});
                }
            }
        }
    }
}