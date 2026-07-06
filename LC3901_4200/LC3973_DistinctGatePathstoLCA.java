package LC3901_4200;
import java.util.*;
public class LC3973_DistinctGatePathstoLCA {
    /**
     * You are given an undirected tree rooted at node 0 with n nodes numbered from 0 to n - 1, represented by an array
     * parent where parent[i] is the parent of node i.
     *
     * Each node i has three types of gates, given in a 2D array gates where gates[i] = [redi, bluei, whitei] which
     * represents the number of red, blue, and white gates at node i.
     *
     * Red gate: usable only with a red card.
     * Blue gate: usable only with a blue card.
     * White gate: usable with either card, but flips the card color when used.
     * Alice and Bob start at given nodes with either a red or blue card (1 = red, 0 = blue). They must independently
     * move upward to their lowest common ancestor (LCA).
     *
     * At each node, a person may move to their parent only if they can use at least one gate at that node with their
     * current card. White gates may be used any number of times to flip the card color.
     *
     * Movement rules (one move = from u to parent[u]):
     *
     * Movement is only upward toward the root.
     * At node u, pick exactly one specific gate instance. Identical gates are treated as separate and counted
     * individually.
     * If holding a red card: use a red gate to remain red, or a white gate to change to blue.
     * If holding a blue card: use a blue gate to remain blue, or a white gate to change to red.
     * If no usable gate exists at u, the sequence ends.
     * You are also given a 2D array queries where queries[i] = [aNodei, aCardi, bNodei, bCardi]:
     *
     * aNodei, aCardi: Alice's starting node and card.
     * bNodei, bCardi: Bob's starting node and card.
     * For each query, count the number of distinct valid ways modulo 109 + 7 for both to reach their LCA.
     *
     * After computing the result for all queries, return the bitwise XOR of those values.
     *
     * Note:
     *
     * Two ways are distinct if the set of gates used differs for either Alice or Bob.
     * If any person is already at the LCA, then the number of ways for them is 1.
     * The lowest common ancestor (LCA) is defined between two nodes a and b as the lowest node in a tree that has both
     * a and b as descendants (where a node is allowed to be a descendant of itself).
     *
     * Input: n = 3, parent = [-1,0,0], gates = [[1,0,1],[0,1,1],[1,1,0]], queries = [[1,0,2,0],[1,1,2,0],[1,0,2,1]]
     * Output: 1
     *
     * Input: n = 3, parent = [-1,0,1], gates = [[0,1,2],[1,0,1],[0,0,3]], queries = [[2,0,1,0],[2,1,0,0],[1,1,2,1]]
     * Output: 3
     *
     * Constraints:
     *
     * 2 <= n <= 2 * 10^4
     * n == parent.length == gates.length
     * parent[0] == -1
     * 0 <= parent[i] < n for i in [1, n - 1]
     * gates[i] == [redi, bluei, whitei]
     * 0 <= redi, bluei, whitei <= 10
     * 1 <= queries.length <= 2 * 10^4
     * queries[i] = [aNodei, aCardi, bNodei, bCardi]
     * 0 <= aNodei, bNodei <= n - 1
     * 0 <= aCardi, bCardi <= 1
     * The input is generated such that the array parent represents a valid tree.
     * @param n
     * @param parent
     * @param gates
     * @param queries
     * @return
     */
    // time = O((n + q) * logn), space = O(nlogn)
    final long mod = (long)(1e9 + 7);
    int n, LOG;
    int[][] fa;
    long[][][] g;
    int[] depth;
    List<Integer>[] adj;
    public int distinctPaths(int n, int[] parent, int[][] gates, int[][] queries) {
        this.n = n;
        LOG = 16;
        while ((1 << LOG) <= n) LOG++;
        fa = new int[LOG][n];
        g = new long[LOG][n][4];
        depth = new int[n];
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) adj[parent[i]].add(i);

        int[] dq = new int[n + 1];
        int hh = 0, tt = -1;
        dq[++tt] = 0;
        while (hh <= tt) {
            int u = dq[tt--];
            for (int v : adj[u]) {
                depth[v] = depth[u] + 1;
                dq[++tt] = v;
            }
        }

        for (int v = 0; v < n; v++) {
            fa[0][v] = parent[v];
            long red = gates[v][0];
            long blue = gates[v][1];
            long white = gates[v][2];
            g[0][v][0] = blue % mod;
            g[0][v][1] = white % mod;
            g[0][v][2] = white % mod;
            g[0][v][3] = red % mod;
        }

        for (int k = 1; k < LOG; k++) {
            for (int v = 0; v < n; v++) {
                int mid = fa[k - 1][v];
                if (mid == -1) {
                    fa[k][v] = -1;
                    g[k][v][0] = 1;
                    g[k][v][1] = 0;
                    g[k][v][2] = 0;
                    g[k][v][3] = 1;
                } else {
                    fa[k][v] = fa[k - 1][mid];
                    multiply(g[k][v], g[k - 1][mid], g[k - 1][v]);
                }
            }
        }

        long res = 0;
        for (int[] q : queries) {
            int a = q[0], ac = q[1], b = q[2], bc = q[3];
            int l = lca(a, b);
            long wa = pathWays(a, l, ac);
            long wb = pathWays(b, l, bc);
            long v = wa * wb % mod;
            res ^= v;
        }
        return (int)res;
    }

    private long pathWays(int u, int anc, int card) {
        long b = card == 0 ? 1 : 0;
        long r = card == 1 ? 1 : 0;
        int diff = depth[u] - depth[anc];
        int node = u;
        for (int k = 0; k < LOG; k++) {
            if ((diff >> k & 1) != 0) {
                long[] f = g[k][node];
                long nb = (f[0] * b + f[1] * r) % mod;
                long nr = (f[2] * b + f[3] * r) % mod;
                b = nb;
                r = nr;
                node = fa[k][node];
                if (node == -1) break;
            }
        }
        return (b + r) % mod;
    }

    private int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int t = a;
            a = b;
            b = t;
        }
        int diff = depth[a] - depth[b];
        for (int k = 0; k < LOG; k++) {
            if ((diff >> k & 1) != 0) a = fa[k][a];
        }
        if (a == b) return a;
        for (int k = LOG - 1; k >= 0; k--) {
            if (fa[k][a] != -1 && fa[k][a] != fa[k][b]) {
                a = fa[k][a];
                b = fa[k][b];
            }
        }
        return fa[0][a];
    }

    private void multiply(long[] C, long[] A, long[] B) {
        C[0] = (A[0] * B[0] + A[1] * B[2]) % mod;
        C[1] = (A[0] * B[1] + A[1] * B[3]) % mod;
        C[2] = (A[2] * B[0] + A[3] * B[2]) % mod;
        C[3] = (A[2] * B[1] + A[3] * B[3]) % mod;
    }
}