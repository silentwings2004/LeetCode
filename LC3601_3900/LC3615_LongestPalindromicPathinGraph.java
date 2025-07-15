package LC3601_3900;
import java.util.*;
public class LC3615_LongestPalindromicPathinGraph {
    /**
     * You are given an integer n and an undirected graph with n nodes labeled from 0 to n - 1 and a 2D array edges,
     * where edges[i] = [ui, vi] indicates an edge between nodes ui and vi.
     *
     * You are also given a string label of length n, where label[i] is the character associated with node i.
     *
     * You may start at any node and move to any adjacent node, visiting each node at most once.
     *
     * Return the maximum possible length of a palindrome that can be formed by visiting a set of unique nodes along a
     * valid path.
     *
     * A palindrome is a string that reads the same forward and backward.
     *
     * Input: n = 3, edges = [[0,1],[1,2]], label = "aba"
     * Output: 3
     *
     * Input: n = 3, edges = [[0,1],[0,2]], label = "abc"
     * Output: 1
     *
     * Input: n = 4, edges = [[0,2],[0,3],[3,1]], label = "bbac"
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= n <= 14
     * n - 1 <= edges.length <= n * (n - 1) / 2
     * edges[i] == [ui, vi]
     * 0 <= ui, vi <= n - 1
     * ui != vi
     * label.length == n
     * label consists of lowercase English letters.
     * There are no duplicate edges.
     * @param n
     * @param edges
     * @param label
     * @return
     */
    // time = O(n * n * 2^n), space = O(n * n * 2^n)
    List<Integer>[] adj;
    String s;
    int[][][] f;
    public int maxLen(int n, int[][] edges, String label) {
        adj = new List[n];
        s = label;
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            adj[u].add(v);
            adj[v].add(u);
        }
        f = new int[n][n][1 << n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(f[i][j], -1);
            }
        }

        int res = 0;
        for (int u = 0; u < n; u++) {
            res = Math.max(res, dfs(u, u, 1 << u) + 1);
            for (int v : adj[u]) {
                if (u < v && s.charAt(u) == s.charAt(v)) {
                    res = Math.max(res, dfs(u, v, 1 << u | 1 << v) + 2);
                }
            }
        }
        return res;
    }

    private int dfs(int u, int v, int mask) {
        if (f[u][v][mask] != -1) return f[u][v][mask];

        int res = 0;
        for (int a : adj[u]) {
            for (int b : adj[v]) {
                if (s.charAt(a) == s.charAt(b)) {
                    if ((mask >> a & 1) == 0 && (mask >> b & 1) == 0 && a != b) {
                        int x = Math.min(a, b);
                        int y = Math.max(a, b);
                        int ns = mask | (1 << a) | (1 << b);
                        int t = dfs(x, y, ns);
                        res = Math.max(res, t + 2);
                    }
                }
            }
        }
        return f[u][v][mask] = res;
    }
}
/**
 * 1. n = 14
 * 2. 本质是节点的排列 <-> 路径
 * 3. 为了优化算法，我要找到重复的东西
 * 4. 如果从回文串的第一个字母开始找，我需要知道路径信息，以确保得到的是回文串
 *    路径结点顺序是必须知道的，规模就是 n!，无法接受
 * 5. 最长回文串告诉我们，可以用中心扩展法去寻找回文串
 * 6. 用中心扩展法，可以发现有重复计算的情况(具体看构造图)
 * 7. 有重复计算 -> DP -> 结合题目规模，用状压 DP 解决
 *
 * 当前向左向右扩展的左右节点分别为 x 和 y，赢访问过的点是 vis
 * dfs(x, y, vis)
 */