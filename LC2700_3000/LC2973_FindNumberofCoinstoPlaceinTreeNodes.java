package LC2700_3000;
import java.util.*;
public class LC2973_FindNumberofCoinstoPlaceinTreeNodes {
    /**
     * You are given an undirected tree with n nodes labeled from 0 to n - 1, and rooted at node 0. You are given a 2D
     * integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai
     * and bi in the tree.
     *
     * You are also given a 0-indexed integer array cost of length n, where cost[i] is the cost assigned to the ith node.
     *
     * You need to place some coins on every node of the tree. The number of coins to be placed at node i can be
     * calculated as:
     *
     * If size of the subtree of node i is less than 3, place 1 coin.
     * Otherwise, place an amount of coins equal to the maximum product of cost values assigned to 3 distinct nodes in
     * the subtree of node i. If this product is negative, place 0 coins.
     * Return an array coin of size n such that coin[i] is the number of coins placed at node i.
     *
     * Input: edges = [[0,1],[0,2],[0,3],[0,4],[0,5]], cost = [1,2,3,4,5,6]
     * Output: [120,1,1,1,1,1]
     *
     * Input: edges = [[0,1],[0,2],[1,3],[1,4],[1,5],[2,6],[2,7],[2,8]], cost = [1,4,2,3,5,7,8,-4,2]
     * Output: [280,140,32,1,1,1,1,1,1]
     *
     * Input: edges = [[0,1],[0,2]], cost = [1,2,-2]
     * Output: [0,1,1]
     *
     * Constraints:
     *
     * 2 <= n <= 2 * 10^4
     * edges.length == n - 1
     * edges[i].length == 2
     * 0 <= ai, bi < n
     * cost.length == n
     * 1 <= |cost[i]| <= 10^4
     * The input is generated such that edges represents a valid tree.
     * @param edges
     * @param cost
     * @return
     */
    // time = O(nlogn), space = O(n)
    List<Integer>[] adj;
    long[] res;
    int[] cost;
    public long[] placedCoins(int[][] edges, int[] cost) {
        this.cost = cost;
        int n = cost.length;
        res = new long[n];
        Arrays.fill(res, 1);
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

    private List<Integer> dfs(int u, int fa) {
        List<Integer> q = new ArrayList<>();
        q.add(cost[u]);
        for (int j : adj[u]) {
            if (j == fa) continue;
            List<Integer> t = dfs(j, u);
            q.addAll(t);
        }

        Collections.sort(q);
        int m = q.size();
        if (m >= 3) {
            int a = q.get(0), b = q.get(1), c = q.get(m - 1), d = q.get(m - 2), e = q.get(m - 3);
            res[u] = Math.max(0L, Math.max(1L * a * b * c, 1L * c * d * e));
            if (m > 5) q = Arrays.asList(a, b, c, d, e);
        }
        return q;
    }
}
/**
 * 给你m个数，从中选3个数，这三数之积最大，如果最大值是负数就返回0
 * -9 -8 -7
 * + + +
 * - - +
 * 1.0个负数，3个最大的正数
 * 2.2个最小的负数，1个最大的正数 => 总共值需要考虑数组中的5个数
 * 3. [0]
 * 这三个取最大值
 * 贪心的问题放到树上
 *
 */