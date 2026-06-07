package LC3901_4200;
import java.util.*;
public class LC3949_SubtreeInversionSumII {
    /**
     * You are given an undirected tree rooted at node 0, with n nodes numbered from 0 to n - 1. The tree is represented
     * by a 2D integer array edges of length n - 1, where edges[i] = [ui, vi] indicates an edge between nodes ui and vi.
     *
     * You are also given an integer array nums of length n, where nums[i] represents the value at node i, and an
     * integer k.
     *
     * You may perform inversion operations on a subset of nodes subject to the following rules:
     *
     * Subtree Inversion Operation:
     *
     * When you invert a node, every value in the subtree rooted at that node is multiplied by -1.
     *
     * Distance Constraint on Inversions:
     *
     * You may only invert a node if it is “sufficiently far” from any other inverted node.
     *
     * If you invert two nodes a and b, the distance (the number of edges on the unique path between them) must be at
     * least k.
     *
     * Return the maximum possible sum of the tree’s node values after applying inversion operations.
     *
     * Input: edges = [[0,1],[0,2],[0,3],[1,4],[1,5]], nums = [1,0,-10,3,4,5], k = 2
     * Output: 23
     *
     * Input: edges = [[0,1],[1,2]], nums = [5,-10,-10], k = 1
     * Output: 25
     *
     * Input: edges = [[0,1],[0,2]], nums = [1,-5,-6], k = 2
     * Output: 12
     *
     * Input: edges = [[0,1],[0,2]], nums = [1,-5,-6], k = 3
     * Output: 10
     *
     * Constraints:
     *
     * nums.length == n
     * edges.length == n - 1
     * 2 <= n <= 5 * 10^4
     * edges[i].length == 2
     * 0 <= edges[i][0], edges[i][1] < n
     * -5 * 10^4 <= nums[i] <= 5 * 10^4
     * 1 <= k <= 50
     * It is guaranteed that edges forms a tree.
     * @param edges
     * @param nums
     * @param k
     * @return
     */
    // time = O(n * k^2), space = O(n * k)
    final long inf = Long.MIN_VALUE / 4;
    public int subtreeInversionSum(int[][] edges, int[] nums, int k) {
        int n = nums.length;
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            adj[u].add(v);
            adj[v].add(u);
        }

        int[] fa = new int[n];
        Arrays.fill(fa, -1);
        int[] order = new int[n];
        int top = 1;
        fa[0] = 0;
        for (int i = 0; i < top; i++) {
            int u = order[i];
            for (int v : adj[u]) {
                if (fa[v] == -1) {
                    fa[v] = u;
                    order[top++] = v;
                }
            }
        }

        int m = k + 1;
        long[][] f = new long[n][m];
        long[][] g = new long[n][m];
        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];
            for (int j = 0; j < 2; j++) {
                long[] cur = new long[m];
                Arrays.fill(cur, inf);
                long val = j == 0 ? nums[u] : -nums[u];
                cur[k] = val;
                for (int v : adj[u]) {
                    if (v == fa[u]) continue;
                    long[] child = j == 0 ? f[v] : g[v];
                    long[] nxt = new long[m];
                    Arrays.fill(nxt, inf);
                    for (int d1 = 0; d1 <= k; d1++) {
                        if (cur[d1] == inf) continue;
                        for (int d2 = 0; d2 <= k; d2++) {
                            if (child[d2] == inf) continue;
                            int r = d2 >= k ? k : d2 + 1;
                            boolean ok = false;
                            if (d1 == k || r == k) ok = true;
                            else ok = d1 + r >= k;
                            if (!ok) continue;
                            int nd = Math.min(d1, r);
                            nxt[nd] = Math.max(nxt[nd], cur[d1] + child[d2]);
                        }
                    }
                    cur = nxt;
                }
                long[] res = cur;

                int cp = j ^ 1;
                long sv = cp == 0 ? nums[u] : -nums[u];
                boolean ok = true;
                for (int v : adj[u]) {
                    if (v == fa[u]) continue;
                    long[] child = cp == 0 ? f[v] : g[v];
                    long best = inf;
                    int need = Math.max(0, k - 1);
                    for (int d = need; d <= k; d++) {
                        best = Math.max(best, child[d]);
                    }
                    if (best == inf) {
                        ok = false;
                        break;
                    }
                    sv += best;
                }
                if (ok) res[0] = Math.max(res[0], sv);
                if (j == 0) f[u] = res;
                else g[u] = res;
            }
        }

        long res = inf;
        for (int i = 0; i <= k; i++) res = Math.max(res, f[0][i]);
        return (int)res;
    }
}