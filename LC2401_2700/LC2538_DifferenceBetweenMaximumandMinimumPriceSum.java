package LC2401_2700;
import java.util.*;
public class LC2538_DifferenceBetweenMaximumandMinimumPriceSum {
    /**
     * There exists an undirected and initially unrooted tree with n nodes indexed from 0 to n - 1. You are given the
     * integer n and a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge
     * between nodes ai and bi in the tree.
     *
     * Each node has an associated price. You are given an integer array price, where price[i] is the price of the ith
     * node.
     *
     * The price sum of a given path is the sum of the prices of all nodes lying on that path.
     *
     * The tree can be rooted at any node root of your choice. The incurred cost after choosing root is the difference
     * between the maximum and minimum price sum amongst all paths starting at root.
     *
     * Return the maximum possible cost amongst all possible root choices.
     *
     * Input: n = 6, edges = [[0,1],[1,2],[1,3],[3,4],[3,5]], price = [9,8,7,6,10,5]
     * Output: 24
     *
     * Input: n = 3, edges = [[0,1],[1,2]], price = [1,1,1]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * edges.length == n - 1
     * 0 <= ai, bi <= n - 1
     * edges represents a valid tree.
     * price.length == n
     * 1 <= price[i] <= 10^5
     * @param n
     * @param edges
     * @param price
     * @return
     */
    // time = O(n), space = O(n)
    List<Integer>[] graph;
    long res = 0;
    int[] price;
    public long maxOutput(int n, int[][] edges, int[] price) {
        this.price = price;
        graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] x : edges) {
            int a = x[0], b = x[1];
            graph[a].add(b);
            graph[b].add(a);
        }
        graph[0].add(-1);
        dfs(0, -1);
        return res;
    }

    private long[] dfs(int u, int fa) {
        long[] m = new long[2];
        if (graph[u].size() == 1) return new long[]{price[u], 0};
        for (int ver : graph[u]) {
            if (ver == fa) continue;
            long[] t = dfs(ver, u);
            if (m[0] == 0) res = Math.max(res, t[0]);
            else res = Math.max(res, t[0] + price[u] + m[1]);
            res = Math.max(res, m[0] + price[u] + t[1]);
            m[0] = Math.max(m[0], t[0]);
            m[1] = Math.max(m[1], t[1]);
        }
        return new long[]{m[0] + price[u], m[1] + price[u]};
    }
}