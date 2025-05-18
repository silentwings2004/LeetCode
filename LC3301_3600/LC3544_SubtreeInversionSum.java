package LC3301_3600;
import java.util.*;
public class LC3544_SubtreeInversionSum {
    /**
     * You are given an undirected tree rooted at node 0, with n nodes numbered from 0 to n - 1. The tree is represented
     * by a 2D integer array edges of length n - 1, where edges[i] = [ui, vi] indicates an edge between nodes ui and vi.
     *
     * You are also given an integer array nums of length n, where nums[i] represents the value at node i, and an integer
     * k.
     *
     * You may perform inversion operations on a subset of nodes subject to the following rules:
     *
     * Subtree Inversion Operation:
     *
     * When you invert a node, every value in the subtree rooted at that node is multiplied by -1.
     *
     * Distance Constraint on Inversions:
     *
     * You may only invert a node if it is "sufficiently far" from any other inverted node.
     *
     * Specifically, if you invert two nodes a and b such that one is an ancestor of the other (i.e., if LCA(a, b) = a
     * or LCA(a, b) = b), then the distance (the number of edges on the unique path between them) must be at least k.
     *
     * Return the maximum possible sum of the tree's node values after applying inversion operations.
     *
     * In a rooted tree, the subtree of some node v is the set of all vertices whose their path to the root contains v.
     *
     * Input: edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]], nums = [4,-8,-6,3,7,-2,5], k = 2
     * Output: 27
     *
     * Input: edges = [[0,1],[1,2],[2,3],[3,4]], nums = [-1,3,-2,4,-5], k = 2
     * Output: 9
     *
     * Input: edges = [[0,1],[0,2]], nums = [0,-1,-2], k = 3
     * Output: 3
     *
     * Constraints:
     *
     * 2 <= n <= 5 * 10^4
     * edges.length == n - 1
     * edges[i] = [ui, vi]
     * 0 <= ui, vi < n
     * nums.length == n
     * -5 * 104 <= nums[i] <= 5 * 10^4
     * 1 <= k <= 50
     * The input is generated such that edges represents a valid tree.
     * @param edges
     * @param nums
     * @param k
     * @return
     */
    // S1
    // time = O(n * k), space = O(n * k)
    List<Integer>[] adj;
    long[][] f, g;
    int[] nums;
    int k, n;
    public long subtreeInversionSum(int[][] edges, int[] nums, int k) {
        this.nums = nums;
        this.k = k;
        n = edges.length + 1;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            adj[a].add(b);
            adj[b].add(a);
        }

        f = new long[n][k + 1];
        g = new long[n][k + 1];
        dfs(0, -1);
        return f[0][0];
    }

    private void dfs(int u, int fa) {
        long[] a = new long[k + 1], b = new long[k + 1];
        for (int v : adj[u]) {
            if (v == fa) continue;
            dfs(v, u);
            for (int i = 0; i <= k; i++) {
                a[i] += f[v][i];
                b[i] += g[v][i];
            }
        }
        // not invert
        for (int i = 1; i <= k; i++) {
            f[u][i] = nums[u] + a[i - 1];
            g[u][i] = nums[u] + b[i - 1];
        }
        // invert
        f[u][0] = -nums[u] - b[k - 1];
        g[u][0] = -nums[u] - a[k - 1];
        for (int i = k - 1; i >= 0; i--) {
            f[u][i] = Math.max(f[u][i], f[u][i + 1]);
            g[u][i] = Math.min(g[u][i], g[u][i + 1]);
        }
    }

    // S2
    // time = O(n * k), space = o(n * k)
    public long subtreeInversionSum2(int[][] edges, int[] nums, int k) {
        int n = nums.length;
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }

        long[][][] f = new long[n][k][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                Arrays.fill(f[i][j], Long.MIN_VALUE);
            }
        }
        return dp(0, -1, 0, 0, g, nums, k, f);
    }

    private long dp(int u, int fa, int cd, int parity, List<Integer>[] g, int[] nums, int k, long[][][] f) {
        if (f[u][cd][parity] != Long.MIN_VALUE) return f[u][cd][parity];

        // 不反转
        long res = parity > 0 ? -nums[u] : nums[u];
        for (int v : g[u]) {
            if (v == fa) continue;
            res += dp(v, u, Math.max(cd - 1, 0), parity, g, nums, k, f);
        }

        // 反转
        if (cd == 0) {
            long s = parity > 0 ? nums[u] : -nums[u];
            for (int v : g[u]) {
                if (v == fa) continue;
                s += dp(v, u, k - 1, parity ^ 1, g, nums, k, f);
            }
            res = Math.max(res, s);
        }
        return f[u][cd][parity] = res;
    }

    // S3: 树上刷表法
    class Solution {
        // time = O(n), space = O(n)
        List<Integer>[] adj;
        List<long[]> f;
        int[] nums;
        int k;
        public long subtreeInversionSum(int[][] edges, int[] nums, int k) {
            this.nums = nums;
            this.k = k;
            int n = nums.length;
            adj = new List[n];
            for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
            for (int[] e : edges) {
                int a = e[0], b = e[1];
                adj[a].add(b);
                adj[b].add(a);
            }

            f = new ArrayList<>();
            long[] res = dfs(0, -1);
            return res[0] + res[1];
        }

        private long[] dfs(int u, int fa) {
            f.add(new long[]{0, 0});

            long s = nums[u], notInv0 = 0, notInv1 = 0;
            for (int v : adj[u]) {
                if (v == fa) continue;
                long[] t = dfs(v, u);
                s += t[0];
                notInv0 += t[1];
                notInv1 += t[2];
            }

            long[] sub = f.remove(f.size() - 1);
            long inv0 = sub[1] - s * 2;
            long inv1 = sub[0] + s * 2;
            long res0 = Math.max(notInv0, inv0);
            long res1 = Math.max(notInv1, inv1);

            if (f.size() >= k) {
                long[] anc = f.get(f.size() - k);
                anc[0] += res0;
                anc[1] += res1;
            }
            return new long[]{s, res0, res1};
        }
    }
}
/**
 * 多维 DP
 * 反转操作的距离约束相当于：一旦执行了一次反转操作，那么会有 k 秒的冷却期（CD），在冷却中不能执行反转操作。每往下走一步，CD 减一.
 * 定义 dfs(x,cd,parity) 表示当前递归到节点 x，剩余冷却时间为 cd，x 的祖先节点执行的反转操作次数的奇偶性是 parity 时，x 子树的最大点权和
 * 设 y 是 x 的儿子。用选或不选（是否反转）思考：
 * 不反转：往下递归到 dfs(y,max(cd−1,0),parity)
 * 反转（前提是 cd=0）：往下递归到 dfs(y,k−1,parity⊕1)。其中 ⊕ 表示异或运算
 * 两种情况，分别累加 dfs 的返回值，再加上 nums[x]（不反转/反转）后的值，
 * 由于会重复访问状态，需要写记忆化搜索。
 */