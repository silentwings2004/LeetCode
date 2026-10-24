package LC3901_4200;

public class LC4085_FindRootofaColoredTree {
    /**
     * You are given an undirected tree of n nodes labeled from 0 to n - 1, represented by a 2D integer array edges of
     * length n - 1, where edges[i] = [ui, vi] indicates that there is an undirected edge between nodes ui and vi.
     *
     * You are also given a string colors of length n, where colors[i] is one of 'R', 'B', or 'W', and denotes the color
     * of node i.
     *
     * A node chosen as root must satisfy both of the following conditions once the tree is oriented away from it:
     *
     * Binary-tree property: Every node has at most two children.
     * Layered color pattern: All nodes at the same depth have the same color. Starting at depth 0, these colors follow
     * one of the three repeating sequences:
     *
     * R → B → W → R → B → W → ...
     * B → W → R → B → W → R → ...
     * W → R → B → W → R → B → ...
     *
     * Return the index of any root that satisfies both conditions, or -1 if no such root exists.
     *
     * A tree is a connected undirected graph with no cycles.
     *
     * The depth of a node is the number of edges on the path from the root to that node. The root has depth 0. A child
     * of a node is an adjacent node whose depth is one greater.
     *
     * The degree of a node is the number of edges incident to it.
     *
     * Input: edges = [[3,1],[3,2],[1,4],[2,0]], colors = "BRRWB"
     * Output: 3
     *
     * Input: edges = [[0,1],[1,2],[2,3]], colors = "RBRB"
     * Output: -1
     *
     * Constraints:
     *
     * 2 <= n <= 10^5
     * edges.length == n - 1
     * edges[i] = [ui, vi]
     * 0 <= ui, vi < n
     * colors.length == n
     * colors[i] is one of 'R', 'B', 'W'.
     * The input graph is a tree and every node has degree at most three.
     * @param edges
     * @param colors
     * @return
     */
    // time = O(n), space = O(n)
    public int findColoredRoot(int[][] edges, String colors) {
        int n = colors.length();
        int[] color = new int[n];
        for (int i = 0; i < n; i++) {
            char c = colors.charAt(i);
            if (c == 'R') continue;
            color[i] = c == 'B' ? 1 : 2;
        }

        int[] ind = new int[n], deg = new int[n];
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            deg[a]++;
            deg[b]++;
            int x = color[a], y = color[b];
            if (x == y) return -1;
            if ((x + 1) % 3 == y) ind[b]++;
            else ind[a]++;
        }

        int root = -1, cnt = 0;
        for (int i = 0; i < n; i++) {
            if (ind[i] == 0) {
                root = i;
                cnt++;
            }
        }
        if (cnt != 1 || deg[root] > 2) return -1;
        return root;
    }
}
/**
 * 由于必须按照 白 - 红 - 蓝的顺序，对于一个固定的节点，它的邻居节点中，哪些是父节点哪些是儿子节点，是一开始就决定的
 *
 * 什么时候无解？
 * 1. 存在邻居个数 > 3 的节点
 * 2. 对于节点 x，x 的邻居的颜色等于 x 的颜色，那么无解
 * 3. 对于节点 x，x 的邻居的颜色中，有 > 1 个 x 的父节点的颜色，那么无解
 *
 * 什么样的店能作为根节点？
 * 1. 至多两个邻居
 * 2. 邻居结点的颜色均为儿子的颜色
 *
 * 如果有多个可以作为根的点，会发生什么？
 * 无解(第 4 种无解情况)。一个点作为根的时候，另一个点必须有父亲，所以其中一个儿子变成了父亲，颜色不符合要求
 */