package LC3601_3900;
import java.util.*;
public class LC3820_PythagoreanDistanceNodesinaTree {
    /**
     * You are given an integer n and an undirected tree with n nodes numbered from 0 to n - 1. The tree is represented
     * by a 2D array edges of length n - 1, where edges[i] = [ui, vi] indicates an undirected edge between ui and vi.
     *
     * You are also given three distinct target nodes x, y, and z.
     *
     * For any node u in the tree:
     *
     * Let dx be the distance from u to node x
     * Let dy be the distance from u to node y
     * Let dz be the distance from u to node z
     * The node u is called special if the three distances form a Pythagorean Triplet.
     *
     * Return an integer denoting the number of special nodes in the tree.
     *
     * A Pythagorean triplet consists of three integers a, b, and c which, when sorted in ascending order, satisfy
     * a^2 + b^2 = c^2.
     *
     * The distance between two nodes in a tree is the number of edges on the unique path between them.
     *
     * Input: n = 4, edges = [[0,1],[0,2],[0,3]], x = 1, y = 2, z = 3
     * Output: 3
     *
     * Input: n = 4, edges = [[0,1],[1,2],[2,3]], x = 0, y = 3, z = 2
     * Output: 0
     *
     * Input: n = 4, edges = [[0,1],[1,2],[1,3]], x = 1, y = 3, z = 0
     * Output: 1
     *
     * Constraints:
     *
     * 4 <= n <= 10^5
     * edges.length == n - 1
     * edges[i] = [ui, vi]
     * 0 <= ui, vi, x, y, z <= n - 1
     * x, y, and z are pairwise distinct.
     * The input is generated such that edges represent a valid tree.
     * @param n
     * @param edges
     * @param x
     * @param y
     * @param z
     * @return
     */
    // time = O(n), space = O(n)
    List<Integer>[] adj;
    int n;
    public int specialNodes(int n, int[][] edges, int x, int y, int z) {
        this.n = n;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            adj[a].add(b);
            adj[b].add(a);
        }

        int[] dx = cal(x);
        int[] dy = cal(y);
        int[] dz = cal(z);

        int res = 0;
        for (int i = 0; i < n; i++) {
            long[] w = new long[]{dx[i], dy[i], dz[i]};
            Arrays.sort(w);
            if (w[0] * w[0] + w[1] * w[1] == w[2] * w[2]) res++;
        }
        return res;
    }

    private int[] cal(int x) {
        int[] dist = new int[n];
        dfs(x, -1, dist);
        return dist;
    }

    private void dfs(int u, int fa, int[] dist) {
        for (int v : adj[u]) {
            if (v == fa) continue;
            dist[v] = dist[u] + 1;
            dfs(v, u, dist);
        }
    }
}