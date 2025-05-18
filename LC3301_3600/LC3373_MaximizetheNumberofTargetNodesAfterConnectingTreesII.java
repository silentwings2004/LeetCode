package LC3301_3600;
import java.util.*;
public class LC3373_MaximizetheNumberofTargetNodesAfterConnectingTreesII {
    /**
     * There exist two undirected trees with n and m nodes, labeled from [0, n - 1] and [0, m - 1], respectively.
     *
     * You are given two 2D integer arrays edges1 and edges2 of lengths n - 1 and m - 1, respectively, where
     * edges1[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the first tree and
     * edges2[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the second tree.
     *
     * Node u is target to node v if the number of edges on the path from u to v is even. Note that a node is always
     * target to itself.
     *
     * Return an array of n integers answer, where answer[i] is the maximum possible number of nodes that are target to
     * node i of the first tree if you had to connect one node from the first tree to another node in the second tree.
     *
     * Note that queries are independent from each other. That is, for every query you will remove the added edge before
     * proceeding to the next query.
     *
     * Input: edges1 = [[0,1],[0,2],[2,3],[2,4]], edges2 = [[0,1],[0,2],[0,3],[2,7],[1,4],[4,5],[4,6]]
     * Output: [8,7,7,8,8]
     *
     * Input: edges1 = [[0,1],[0,2],[0,3],[0,4]], edges2 = [[0,1],[1,2],[2,3]]
     * Output: [3,6,6,6,6]
     *
     * Constraints:
     *
     * 2 <= n, m <= 10^5
     * edges1.length == n - 1
     * edges2.length == m - 1
     * edges1[i].length == edges2[i].length == 2
     * edges1[i] = [ai, bi]
     * 0 <= ai, bi < n
     * edges2[i] = [ui, vi]
     * 0 <= ui, vi < m
     * The input is generated such that edges1 and edges2 represent valid trees.
     * @param edges1
     * @param edges2
     * @return
     */
    // time = O(n + m), space = O(n + m)
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2) {
        int n = edges1.length + 1, m = edges2.length + 1;
        List<Integer>[] adj1 = build(edges1);
        List<Integer>[] adj2 = build(edges2);

        int[] d1 = cal(adj1);
        int[] d2 = cal(adj2);

        int odd1 = 0, even1 = 0, odd2 = 0, even2 = 0;
        for (int x : d1) {
            if (x % 2 == 1) odd1++;
            else even1++;
        }
        for (int x : d2) {
            if (x % 2 == 1) odd2++;
            else even2++;
        }
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            if (d1[i] % 2 == 1) res[i] = odd1 + Math.max(odd2, even2);
            else res[i] = even1 + Math.max(odd2, even2);
        }
        return res;
    }

    private void dfs(List<Integer>[] adj, int u, int depth, int[] d) {
        d[u] = depth % 2;
        for (int v : adj[u]) {
            if (d[v] == -1) dfs(adj, v, depth + 1, d);
        }
    }

    private int[] cal(List<Integer>[] adj) {
        int n = adj.length;
        int[] d = new int[n];
        Arrays.fill(d, -1);
        dfs(adj, 0, 0, d);
        return d;
    }

    private List<Integer>[] build(int[][] edges) {
        int n = edges.length + 1;
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            adj[a].add(b);
            adj[b].add(a);
        }
        return adj;
    }
}