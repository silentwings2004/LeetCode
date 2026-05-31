package LC3901_4200;
import java.util.*;
public class LC3939_CountNonAdjacentSubsetsinaRootedTree {
    /**
     * You are given a rooted tree with n nodes labeled from 0 to n - 1, represented by an integer array parent of
     * length n, where:
     *
     * parent[0] = -1 (node 0 is the root).
     * For each 1 <= i < n, parent[i] is the parent of node i (0 <= parent[i] < i).
     * You are also given an integer array nums of length n, where nums[i] is the value of node i, and an integer k.
     *
     * A non-empty subset of nodes is called valid if:
     *
     * The sum of the values of the selected nodes is divisible by k.
     * No two selected nodes are adjacent in the tree (no node and its direct parent are both included in the subset).
     * Return the number of valid subsets modulo 10^9 + 7.
     *
     * Input: parent = [-1,0,1], nums = [1,2,3], k = 3
     * Output: 1
     *
     * Input: parent = [-1,0,0,0], nums = [2,1,2,1], k = 3
     * Output: 2
     *
     * Constraints:
     *
     * n == parent.length == nums.length
     * 1 <= n <= 1000
     * parent[0] == -1
     * For all 1 <= i < n:
     * 0 <= parent[i] < i
     * 1 <= nums[i] <= 10^9
     * 1 <= k <= 100
     * parent describes a valid rooted tree.
     * @param parent
     * @param nums
     * @param k
     * @return
     */
    // S1: 树上独立集 + 树上背包
    // time = O(n * k^2), space = O(n * k)
    long mod = (long)(1e9 + 7);
    int[] nums;
    int k;
    List<Integer>[] adj;
    public int countValidSubsets(int[] parent, int[] nums, int k) {
        int n = nums.length;
        this.k = k;
        this.nums = nums;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) adj[parent[i]].add(i);

        long[][] f = new long[n][k]; // f[u][r]: ways in subtree of u where u is NOT selected, sum % k = r
        long[][] g = new long[n][k]; // g[u][r]: ways where u IS selected, sum % k = r
        dfs(0, f, g);

        // total valid non-empty subsets = (f[0][0] + g[0][0] - 1) % mod
        return (int)((f[0][0] + g[0][0] - 1 + mod) % mod);
    }

    private void dfs(int u, long[][] f, long[][] g) {
        for (int i = 0; i < k; i++) f[u][i] = g[u][i] = 0;
        f[u][0] = 1; // empty set inside subtree
        g[u][nums[u] % k] = 1; // only node u selected

        for (int v : adj[u]) {
            dfs(v, f, g);
            long[] nf = new long[k];
            long[] ng = new long[k];

            // u not selected: child can be selected or not
            for (int r1 = 0; r1 < k; r1++) {
                if (f[u][r1] == 0) continue;
                for (int r2 = 0; r2 < k; r2++) {
                    long cw = (f[v][r2] + g[v][r2]) % mod;
                    if (cw == 0) continue;
                    int nr = (r1 + r2) % k;
                    nf[nr] = (nf[nr] + f[u][r1] * cw) % mod;
                }
            }

            // u selected: child cannot be selected
            for (int r1 = 0; r1 < k; r1++) {
                if (g[u][r1] == 0) continue;
                for (int r2 = 0; r2 < k; r2++) {
                    long cw = f[v][r2];
                    if (cw == 0) continue;
                    int nr = (r1 + r2) % k;
                    ng[nr] = (ng[nr] + g[u][r1] * cw) % mod;
                }
            }
            f[u] = nf.clone();
            g[u] = ng.clone();
        }
    }
}