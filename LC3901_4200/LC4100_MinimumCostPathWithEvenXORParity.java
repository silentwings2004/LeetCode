package LC3901_4200;
import java.util.*;
public class LC4100_MinimumCostPathWithEvenXORParity {
    /**
     * You are given a tree with n nodes numbered from 0 to n - 1, represented by a 2D integer array edges. Each
     * edges[i] = [ui, vi, ci] represents an edge between nodes ui and vi with cost ci.
     *
     * You are also given an integer array values of length n, where values[i] is the value of node i.
     *
     * A path between two distinct nodes is Balanced-XOR if the bitwise XOR of the values of all nodes on the path,
     * including both endpoints, has an even number of set bits.
     *
     * The cost of a path is the sum of its edge costs.
     *
     * Return the minimum cost of a Balanced-XOR path. If no such path exists, return -1.
     *
     * A set bit is a bit whose value in the binary representation of a number is 1. The number 0 has zero set bits.
     *
     * Input: edges = [[0,1,4],[0,2,6],[0,3,3],[3,4,5]], values = [3,1,7,1,3]
     * Output: 7
     *
     * Input: edges = [[0,1,5],[1,2,10]], values = [1,7,3]
     * Output: 5
     *
     * Input: edges = [[0,1,5],[0,2,7],[0,3,9]], values = [1,3,5,9]
     * Output: -1
     *
     * Constraints:
     *
     * 2 <= n <= 10^5
     * edges.length == n - 1
     * edges[i] = [ui, vi, ci]
     * 0 <= ui, vi <= n - 1
     * 1 <= ci <= 10^9
     * values.length == n
     * 1 <= values[i] <= 10^9
     * edges represents a valid tree.
     * @param edges
     * @param values
     * @return
     */
    // time = O(n), space = O(n)
    final long inf = (long)1E18;
    List<int[]>[] adj;
    int[] values;
    long res;
    public int minActivationCost(int[][] edges, int[] values) {
        this.values = values;
        res = inf;
        int n = values.length;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1], c = e[2];
            adj[a].add(new int[]{b, c});
            adj[b].add(new int[]{a, c});
        }
        dfs(0, -1, 0);
        return res < inf ? (int)res : -1;
    }

    private long[] dfs(int u, int fa, int pc) {
        int pv = Integer.bitCount(values[u]) % 2; // 0-1 值
        pc ^= pv;
        long[] mins = new long[]{inf, inf};
        mins[pc] = 0;

        for (int[] x : adj[u]) {
            int v = x[0], c = x[1];
            if (v == fa) continue;
            long[] y = dfs(v, u, pc);
            y[0] += c;
            y[1] += c;
            res = Math.min(res, Math.min(mins[0] + y[pv], mins[1] + y[pv ^ 1]));
            mins[0] = Math.min(mins[0], y[0]);
            mins[1] = Math.min(mins[1], y[1]);
        }
        return mins;
    }

    // S2: 脑筋急转弯
    // time = O(n), space = O(n)
    public int minActivationCost2(int[][] edges, int[] values) {
        int n = values.length, res = Integer.MAX_VALUE;
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1], c = e[2];
            int va = Integer.bitCount(values[a]) % 2;
            int vb = Integer.bitCount(values[b]) % 2;
            if (va == vb) res = Math.min(res, c);
            else if (va == 0) adj[a].add(c);
            else adj[b].add(c);
        }

        for (List<Integer> q : adj) {
            int mn = Integer.MAX_VALUE;
            for (int x : q) {
                res = (int)Math.min(res, 1L * mn + x);
                mn = Math.min(mn, x);
            }
        }
        return res < Integer.MAX_VALUE ? res : -1;
    }
}
/**
 * 前置题目：LC543
 * 设 f(x) 为 x 的二进制数中的 1 的个数
 * 结论：f(a^b) % 2 = (f(a) + f(b)) % 2 = f(a) % 2 ^ f(b) % 2
 * 这样就和异或无关了，只需关注每个数的二进制数中的 1 的个数的奇偶性
 * 直接把每个 values[x] 视作一个 0-1 数 f(values[x]) % 2
 *
 * 路径 a - b 点权异或和
 * = 根到 a 的路径点权异或和 ^ 根到 b 的路径点权异或和 ^ LCA(a,b) 的点权
 * = 根到 a 的路径点权异或和的二进制数中的 1 的个数
 * ^ 根到 b 的路径点权异或和的二进制数中的 1 的个数
 * ^ LCA(a,b) 的点权的二进制数中的 1 的个数
 *
 * 设 a 是 x 的后代
 * 维护从根到节点 a 的 0-1 值为 0 或 1 的情况下，从 x 到 a 的路径边权和的最小值
 */
