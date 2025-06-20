package LC3301_3600;
import java.util.*;
public class LC3575_MaximumGoodSubtreeScore {
    /**
     * You are given an undirected tree rooted at node 0 with n nodes numbered from 0 to n - 1. Each node i has an
     * integer value vals[i], and its parent is given by par[i].
     *
     * A subset of nodes within the subtree of a node is called good if every digit from 0 to 9 appears at most once in
     * the decimal representation of the values of the selected nodes.
     *
     * The score of a good subset is the sum of the values of its nodes.
     *
     * Define an array maxScore of length n, where maxScore[u] represents the maximum possible sum of values of a good
     * subset of nodes that belong to the subtree rooted at node u, including u itself and all its descendants.
     *
     * Return the sum of all values in maxScore.
     *
     * Since the answer may be large, return it modulo 10^9 + 7.
     *
     * Input: vals = [2,3], par = [-1,0]
     * Output: 8
     *
     * Input: vals = [1,5,2], par = [-1,0,0]
     * Output: 15
     *
     * Input: vals = [34,1,2], par = [-1,0,1]
     * Output: 42
     *
     * Input: vals = [3,22,5], par = [-1,0,1]
     * Output: 18
     *
     * Constraints:
     *
     * 1 <= n == vals.length <= 500
     * 1 <= vals[i] <= 10^9
     * par.length == n
     * par[0] == -1
     * 0 <= par[i] < n for i in [1, n - 1]
     * The input is generated such that the parent array par represents a valid tree.
     * @param vals
     * @param par
     * @return
     */
    // time = O(n * 3^10), space = O(n * 2^10)
    final long mod = (long)(1e9 + 7);
    List<Integer>[] adj;
    int[] vals;
    long res;
    public int goodSubtreeSum(int[] vals, int[] par) {
        this.vals = vals;
        int n = par.length;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) adj[par[i]].add(i);
        dfs(0);
        return (int)(res % mod);
    }

    private int[] dfs(int u) {
        int[] f = new int[1 << 10];
        int mask = 0;
        for (int i = vals[u]; i > 0; i /= 10) {
            int x = i % 10;
            if ((mask >> x & 1) == 1) {
                mask = 0;
                break;
            }
            mask |= 1 << x;
        }
        if (mask > 0) f[mask] = vals[u];
        for (int v : adj[u]) {
            int[] g = dfs(v);
            for (int i = 0; i < 1 << 10; i++) {
                f[i] = Math.max(f[i], g[i]);
            }
        }

        int mx = 0;
        for (int i = 3; i < 1 << 10; i++) {
            for (int j = i & (i - 1); j > 0; j = (j - 1) & i) {
                f[i] = Math.max(f[i], f[j] + f[i ^ j]);
            }
            mx = Math.max(mx, f[i]);
        }
        res += mx;
        return f;
    }
}
/**
 * 0 ~ 9 最多只能选 9 个数，因为单独的 0 不存在
 * 12 -> {1, 2}, 34 -> {3, 4}
 */