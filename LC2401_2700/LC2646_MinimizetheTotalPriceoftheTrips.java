package LC2401_2700;
import java.util.*;
public class LC2646_MinimizetheTotalPriceoftheTrips {
    /**
     * There exists an undirected and unrooted tree with n nodes indexed from 0 to n - 1. You are given the integer n
     * and a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between
     * nodes ai and bi in the tree.
     *
     * Each node has an associated price. You are given an integer array price, where price[i] is the price of the ith
     * node.
     *
     * The price sum of a given path is the sum of the prices of all nodes lying on that path.
     *
     * Additionally, you are given a 2D integer array trips, where trips[i] = [starti, endi] indicates that you start
     * the ith trip from the node starti and travel to the node endi by any path you like.
     *
     * Before performing your first trip, you can choose some non-adjacent nodes and halve the prices.
     *
     * Return the minimum total price sum to perform all the given trips.
     *
     * Input: n = 4, edges = [[0,1],[1,2],[1,3]], price = [2,2,10,6], trips = [[0,3],[2,1],[2,3]]
     * Output: 23
     *
     * Input: n = 2, edges = [[0,1]], price = [2,2], trips = [[0,0]]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= n <= 50
     * edges.length == n - 1
     * 0 <= ai, bi <= n - 1
     * edges represents a valid tree.
     * price.length == n
     * price[i] is an even integer.
     * 1 <= price[i] <= 1000
     * 1 <= trips.length <= 100
     * 0 <= starti, endi <= n - 1
     * @param n
     * @param edges
     * @param price
     * @param trips
     * @return
     */
    // time = O(m * n), space = O(m * n)
    final int N = 55, M = N * 2;
    int[] h, e, ne, w;
    int[] cnt;
    int idx;
    int[][] f;
    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        h = new int[N];
        e = new int[M];
        ne = new int[M];
        cnt = new int[N];
        f = new int[N][2];
        w = price;
        Arrays.fill(h, -1);
        idx = 0;

        for (int[] e : edges) {
            int a = e[0], b = e[1];
            add(a, b);
            add(b, a);
        }

        for (int[] t : trips) {
            int a = t[0], b = t[1];
            dfs(a, -1, b);
        }

        for (int i = 0; i < n; i++) w[i] *= cnt[i];
        for (int i = 0; i < n; i++) f[i][0] = f[i][1] = -1;
        return dfs2(0, -1, 1);
    }

    private int dfs2(int u, int fa, int flag) {
        if (f[u][flag] != -1) return f[u][flag];

        int res = 0;
        if (flag == 0) {
            res = w[u];
            for (int i = h[u]; i != -1; i = ne[i]) {
                int j = e[i];
                if (j == fa) continue;
                res += dfs2(j, u, 1);
            }
        } else {
            int t1 = w[u] / 2, t2 = w[u];
            for (int i = h[u]; i != -1; i = ne[i]) {
                int j = e[i];
                if (j == fa) continue;
                t1 += dfs2(j, u, 0);
                t2 += dfs2(j, u, 1);
            }
            res = Math.min(t1, t2);
        }
        f[u][flag] = res;
        return res;
    }

    private boolean dfs(int u, int fa, int t) {
        if (u == t) {
            cnt[u]++;
            return true;
        }

        for (int i = h[u]; i != -1; i = ne[i]) {
            int j = e[i];
            if (j == fa) continue;
            if (dfs(j, u, t)) {
                cnt[u]++;
                return true;
            }
        }
        return false;
    }

    private void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }
}
/**
 * not adjacent -> house robber III
 * plan0[node]: the minimum gain if you cannot pick node
 * plan1[node]: the minimum gain if you can pick node
 * plan0[node] = val[node] + sum(plan1[child])
 * plan1[node] = val[node] / 2 + sum(plan0[child])
 *               val[node] + sum(plan1[child])
 */