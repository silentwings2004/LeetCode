package LC2700_3000;
import java.util.*;
public class LC2925_MaximumScoreAfterApplyingOperationsonaTree {
    /**
     * There is an undirected tree with n nodes labeled from 0 to n - 1, and rooted at node 0. You are given a 2D
     * integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai
     * and bi in the tree.
     *
     * You are also given a 0-indexed integer array values of length n, where values[i] is the value associated with the
     * ith node.
     *
     * You start with a score of 0. In one operation, you can:
     *
     * Pick any node i.
     * Add values[i] to your score.
     * Set values[i] to 0.
     * A tree is healthy if the sum of values on the path from the root to any leaf node is different than zero.
     *
     * Return the maximum score you can obtain after performing these operations on the tree any number of times so that
     * it remains healthy.
     *
     * Input: edges = [[0,1],[0,2],[0,3],[2,4],[4,5]], values = [5,2,5,2,1,1]
     * Output: 11
     *
     * Input: edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]], values = [20,10,9,7,4,3,5]
     * Output: 40
     *
     * Constraints:
     *
     * 2 <= n <= 2 * 10^4
     * edges.length == n - 1
     * edges[i].length == 2
     * 0 <= ai, bi < n
     * values.length == n
     * 1 <= values[i] <= 10^9
     * The input is generated such that edges represents a valid tree.
     * @param edges
     * @param values
     * @return
     */
    // time = O(n), space = O(n)
    List<Integer>[] adj;
    int[] values;
    int n;
    public long maximumScoreAfterOperations(int[][] edges, int[] values) {
        this.values = values;
        n = edges.length + 1;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            adj[a].add(b);
            adj[b].add(a);
        }
        long res = 0;
        for (int x : values) res += x;
        return res - dfs(0, -1);
    }

    private long dfs(int u, int fa) {
        long t = 0;
        for (int j : adj[u]) {
            if (j == fa) continue;
            t += dfs(j, u);
        }
        if (t == 0 || values[u] < t) t = values[u];
        return t;
    }
}