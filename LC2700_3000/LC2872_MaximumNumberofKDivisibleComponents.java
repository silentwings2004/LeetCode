package LC2700_3000;
import java.util.*;
public class LC2872_MaximumNumberofKDivisibleComponents {
    /**
     * There is an undirected tree with n nodes labeled from 0 to n - 1. You are given the integer n and a 2D integer
     * array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in
     * the tree.
     *
     * You are also given a 0-indexed integer array values of length n, where values[i] is the value associated with the
     * ith node, and an integer k.
     *
     * A valid split of the tree is obtained by removing any set of edges, possibly empty, from the tree such that the
     * resulting components all have values that are divisible by k, where the value of a connected component is the
     * sum of the values of its nodes.
     *
     * Return the maximum number of components in any valid split.
     *
     * Input: n = 5, edges = [[0,2],[1,2],[1,3],[2,4]], values = [1,8,1,4,4], k = 6
     * Output: 2
     *
     * Input: n = 7, edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]], values = [3,0,6,1,5,2,1], k = 3
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= n <= 3 * 10^4
     * edges.length == n - 1
     * edges[i].length == 2
     * 0 <= ai, bi < n
     * values.length == n
     * 0 <= values[i] <= 10^9
     * 1 <= k <= 10^9
     * Sum of values is divisible by k.
     * The input is generated such that edges represents a valid tree.
     * @param n
     * @param edges
     * @param values
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    List<Integer>[] adj;
    int[] values;
    int res, k;
    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        this.k = k;
        this.values = values;
        res = 0;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            adj[a].add(b);
            adj[b].add(a);
        }

        dfs(0, -1);
        return res;
    }

    private long dfs(int u, int fa) {
        long t = values[u];
        for (int ver : adj[u]) {
            if (ver == fa) continue;
            t += dfs(ver, u);
        }
        if (t % k == 0) res++;
        return t;
    }
}
/**
 * 什么样的边可以删除？
 * 删除一条边分成2个连通块，这2个连通块的点权之和要是k的倍数
 * values之和可以被k整除
 * 只需要保证其中一个连通块的点权之和是k的倍数
 * 这意味着，从任意一个点出发，算出的答案都是一样的
 * 从0出发就好了
 */