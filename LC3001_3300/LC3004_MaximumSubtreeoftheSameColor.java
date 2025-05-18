package LC3001_3300;
import java.util.*;
public class LC3004_MaximumSubtreeoftheSameColor {
    /**
     * You are given a 2D integer array edges representing a tree with n nodes, numbered from 0 to n - 1, rooted at node
     * 0, where edges[i] = [ui, vi] means there is an edge between the nodes vi and ui.
     *
     * You are also given a 0-indexed integer array colors of size n, where colors[i] is the color assigned to node i.
     *
     * We want to find a node v such that every node in the
     * subtree
     *  of v has the same color.
     *
     * Return the size of such subtree with the maximum number of nodes possible.
     *
     * Input: edges = [[0,1],[0,2],[0,3]], colors = [1,1,2,3]
     * Output: 1
     *
     * Input: edges = [[0,1],[0,2],[0,3]], colors = [1,1,1,1]
     * Output: 4
     *
     * Input: edges = [[0,1],[0,2],[2,3],[2,4]], colors = [1,2,3,3,3]
     * Output: 3
     *
     * Constraints:
     *
     * n == edges.length + 1
     * 1 <= n <= 5 * 10^4
     * edges[i] == [ui, vi]
     * 0 <= ui, vi < n
     * colors.length == n
     * 1 <= colors[i] <= 10^5
     * The input is generated such that the graph represented by edges is a tree.
     * @param edges
     * @param colors
     * @return
     */
    // time = O(n), space = O(n)
    int[] h, e, ne, w;
    int idx;
    public int maximumSubtreeSize(int[][] edges, int[] colors) {
        int n = edges.length + 1;
        h = new int[n];
        e = new int[n];
        ne = new int[n];
        w = colors;
        Arrays.fill(h, -1);
        idx = 0;

        for (int[] e : edges) {
            int a = e[0], b = e[1];
            add(a, b);
        }

        return dfs(0, 0)[0];
    }

    private int[] dfs(int u, int flag) {
        int t = 1, s = 1, f = 0;
        for (int i = h[u]; i != -1; i = ne[i]) {
            int j = e[i];
            int[] v = dfs(j, flag);
            s += v[0];
            t = Math.max(t, v[0]);
            if (w[u] != w[j] || v[1] == 1) f = 1;
        }
        flag |= f;
        if (flag == 0) return new int[]{s, 0};
        else return new int[]{t, 1};
    }

    private void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }
}