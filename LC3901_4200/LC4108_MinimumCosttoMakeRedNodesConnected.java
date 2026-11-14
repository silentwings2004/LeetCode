package LC3901_4200;
import java.util.*;
public class LC4108_MinimumCosttoMakeRedNodesConnected {
    /**
     * You are given an integer n representing a tree with n nodes, where the nodes are numbered from 0 to n - 1 and the
     * tree is rooted at node 0. The tree is represented by a 2D array edges of length n - 1, where edges[i] = [ui, vi]
     * indicates an undirected edge between nodes ui and vi.
     * 
     * You are also given a string colors of length n, where colors[i] represents the color of node i: 'R' if it is Red,
     * or 'B' if it is Blue.
     * 
     * You may flip the color of any node: from 'R' to 'B', or from 'B' to 'R'. Flipping node u incurs a cost equal to
     * the size of the subtree of node u.
     * 
     * Your goal is to perform flips so the Red nodes form a single connected component in the tree. Blue nodes may be
     * disconnected. A configuration with no Red nodes is also considered valid.
     * 
     * Return the minimum total cost required to achieve this.
     * 
     * In a rooted tree, the subtree of a node v is the set of all nodes whose path to the root contains v.
     * 
     * Input: n = 3, colors = "RBR", edges = [[0,1],[1,2]]
     * Output: 1
     * 
     * Input: n = 3, colors = "RBR", edges = [[0,1],[0,2]]
     * Output: 0
     * 
     * Input: n = 4, colors = "BRRR", edges = [[0,1],[0,2],[0,3]]
     * Output: 2
     * 
     * Input: n = 3, colors = "BBB", edges = [[0,1],[1,2]]
     * Output: 0
     * 
     * Constraints:
     * 
     * 1 <= n <= 10^5
     * n == colors.length
     * colors[i] is either 'R' or 'B'.
     * edges.length == n - 1
     * edges[i] = [ui, vi]
     * 0 <= ui, vi <= n - 1
     * The input is generated such that edges represents a valid tree.
     *
     * @param n
     * @param colors
     * @param edges
     * @return
     */
    // time = O(n), space = O(n)
    public long minCost(int n, String colors, int[][] edges) {
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
        int cnt = 0;
        order[cnt++] = 0;

        for (int i = 0; i < cnt; i++) {
            int u = order[i];
            for (int v : adj[u]) {
                if (v == fa[u]) continue;
                fa[v] = u;
                order[cnt++] = v;
            }
        }

        int[] sz = new int[n];
        Arrays.fill(sz, 1);
        for (int i = n - 1; i > 0; i--) {
            int u = order[i];
            sz[fa[u]] += sz[u];
        }

        long[] f = new long[n];
        long base = 0;
        for (int i = 0; i < n; i++) {
            if (colors.charAt(i) == 'R') {
                f[i] = sz[i];
                base += sz[i];
            } else f[i] = -sz[i];
        }

        long best = 0;
        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];
            best = Math.max(best, f[u]);
            if (u != 0 && f[u] > 0) f[fa[u]] += f[u];
        }
        return base - best;
    }
}
/**
 * 枚举连通块最上面的点 => 在连通块外面的点变蓝色
 * 一次 DFS
 * 所有节点都变蓝色 base，在这个基础上，把一部分点变成红色的，最大可以减少多少，不需要考虑外部的内容了
 * 1.撤销恢复成红色 => base - size[x]
 * 2.原来是蓝色，现在变成红色 => base + size[x]
 * 我在这个树里，找到一个点权之和最大的连通块 => 树形 DP
 * f[x] 是在一定选 x 的情况下，从子树 x 中选出的最大点权和连通块
 * 枚举 x 的子树 y
 * f[x] = a[x] + sum(max(f[y], 0))
 * B - max(f)
 */