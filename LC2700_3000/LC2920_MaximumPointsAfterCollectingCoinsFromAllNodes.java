package LC2700_3000;
import java.util.*;
public class LC2920_MaximumPointsAfterCollectingCoinsFromAllNodes {
    /**
     * There exists an undirected tree rooted at node 0 with n nodes labeled from 0 to n - 1. You are given a 2D integer
     * array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in
     * the tree. You are also given a 0-indexed array coins of size n where coins[i] indicates the number of coins in
     * the vertex i, and an integer k.
     *
     * Starting from the root, you have to collect all the coins such that the coins at a node can only be collected if
     * the coins of its ancestors have been already collected.
     *
     * Coins at nodei can be collected in one of the following ways:
     *
     * Collect all the coins, but you will get coins[i] - k points. If coins[i] - k is negative then you will lose
     * abs(coins[i] - k) points.
     * Collect all the coins, but you will get floor(coins[i] / 2) points. If this way is used, then for all the nodej
     * present in the subtree of nodei, coins[j] will get reduced to floor(coins[j] / 2).
     * Return the maximum points you can get after collecting the coins from all the tree nodes.
     *
     * Input: edges = [[0,1],[1,2],[2,3]], coins = [10,10,3,3], k = 5
     * Output: 11
     *
     * Input: edges = [[0,1],[0,2]], coins = [8,4,4], k = 0
     * Output: 16
     *
     * Constraints:
     *
     * n == coins.length
     * 2 <= n <= 10^5
     * 0 <= coins[i] <= 10^4
     * edges.length == n - 1
     * 0 <= edges[i][0], edges[i][1] < n
     * 0 <= k <= 10^4
     * @param edges
     * @param coins
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    List<Integer>[] adj;
    int[] coins;
    int k;
    int[][] f;
    public int maximumPoints(int[][] edges, int[] coins, int k) {
        this.coins = coins;
        this.k = k;
        int n = coins.length;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            adj[a].add(b);
            adj[b].add(a);
        }
        f = new int[n][15];
        for (int i = 0; i < n; i++) Arrays.fill(f[i], -1);
        return dfs(0, -1, 0);
    }

    private int dfs(int u, int fa, int ts) {
        if (ts > 14) return 0;
        if (f[u][ts] != -1) return f[u][ts];

        int v = coins[u] / (int)Math.pow(2, ts);
        int t1 = v - k, t2 = v / 2;
        for (int j : adj[u]) {
            if (j == fa) continue;
            t1 += dfs(j, u, ts);
            t2 += dfs(j, u, ts + 1);
        }
        f[u][ts] = Math.max(t1, t2);
        return Math.max(t1, t2);
    }
}