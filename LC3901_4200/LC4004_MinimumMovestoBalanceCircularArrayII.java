package LC3901_4200;
import java.util.*;
public class LC4004_MinimumMovestoBalanceCircularArrayII {
    /**
     * You are given a circular array balance of length n, where balance[i] is the net balance of person i.
     *
     * In one move, a person can transfer exactly 1 unit of balance to either their left or right neighbor.
     *
     * Return the minimum number of moves required so that every person has a non-negative balance. If it is impossible,
     * return -1.
     *
     * Input: balance = [-1,2,-1]
     * Output: 2
     *
     * Input: balance = [4,-1,-2]
     * Output: 3
     *
     * Input: balance = [-3,-3,5]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= n == balance.length <= 1000
     * -10^5 <= balance[i] <= 10^5
     * @param balance
     * @return
     */
    // time = O(F * E * logV), space = O(V + E)  F: number of augmentations
    final int inf = 0x3f3f3f3f;
    List<int[]>[] adj;
    public long minMoves(int[] balance) {
        int n = balance.length;
        long sum = 0;
        int deficit = 0;
        for (int x : balance) {
            sum += x;
            if (x < 0) deficit -= x;
        }
        if (sum < 0) return -1;
        if (deficit == 0) return 0;

        adj = new List[n + 2];
        for (int i = 0; i < n + 2; i++) adj[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (balance[i] > 0) add(n, i, balance[i], 0);
            else if (balance[i] < 0) add(i, n + 1, -balance[i], 0);
            add(i, (i + 1) % n, inf, 1);
            add(i, (i - 1 + n) % n, inf, 1);
        }
        return minCostFlow(n, n + 1, deficit);
    }

    private long minCostFlow(int s, int t, int need) {
        int n = adj.length;
        long res = 0;
        int flow = 0;

        long[] pot = new long[n];
        while (flow < need) {
            long[] dist = new long[n];
            Arrays.fill(dist, Long.MAX_VALUE / 4);
            dist[s] = 0;
            int[] pv = new int[n];
            int[] pe = new int[n];

            PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[0], o2[0]));
            pq.offer(new long[]{0, s});

            while (!pq.isEmpty()) {
                long[] cur = pq.poll();
                long d = cur[0];
                int u = (int)cur[1];
                if (d != dist[u]) continue;

                for (int i = 0; i < adj[u].size(); i++) {
                    int[] e = adj[u].get(i);
                    if (e[2] > 0) {
                        long nd = d + e[3] + pot[u] - pot[e[0]];
                        if (nd < dist[e[0]]) {
                            dist[e[0]] = nd;
                            pv[e[0]] = u;
                            pe[e[0]] = i;
                            pq.offer(new long[]{nd, e[0]});
                        }
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                if (dist[i] < Long.MAX_VALUE / 4) pot[i] += dist[i];
            }

            int extra = need - flow;
            int v = t;
            while (v != s) {
                int[] e = adj[pv[v]].get(pe[v]);
                extra = Math.min(extra, e[2]);
                v = pv[v];
            }
            v = t;
            while (v != s) {
                int[] e = adj[pv[v]].get(pe[v]);
                e[2] -= extra;
                adj[v].get(e[1])[2] += extra;
                v = pv[v];
            }
            flow += extra;
            res += 1L * extra * pot[t];
        }
        return res;
    }

    private void add(int u, int v, int cap, int cost) {
        adj[u].add(new int[]{v, adj[v].size(), cap, cost});
        adj[v].add(new int[]{u, adj[u].size() - 1, 0, -cost});
    }
}