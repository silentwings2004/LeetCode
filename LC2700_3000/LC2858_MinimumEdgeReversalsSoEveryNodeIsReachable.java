package LC2700_3000;
import java.util.*;
public class LC2858_MinimumEdgeReversalsSoEveryNodeIsReachable {
    /**
     * There is a simple directed graph with n nodes labeled from 0 to n - 1. The graph would form a tree if its edges
     * were bi-directional.
     *
     * You are given an integer n and a 2D integer array edges, where edges[i] = [ui, vi] represents a directed edge
     * going from node ui to node vi.
     *
     * An edge reversal changes the direction of an edge, i.e., a directed edge going from node ui to node vi becomes a
     * directed edge going from node vi to node ui.
     *
     * For every node i in the range [0, n - 1], your task is to independently calculate the minimum number of edge
     * reversals required so it is possible to reach any other node starting from node i through a sequence of directed
     * edges.
     *
     * Return an integer array answer, where answer[i] is the minimum number of edge reversals required so it is
     * possible to reach any other node starting from node i through a sequence of directed edges.
     *
     * Input: n = 4, edges = [[2,0],[2,1],[1,3]]
     * Output: [1,1,0,2]
     *
     * Input: n = 3, edges = [[1,2],[2,0]]
     * Output: [2,0,1]
     *
     * Constraints:
     *
     * 2 <= n <= 10^5
     * edges.length == n - 1
     * edges[i].length == 2
     * 0 <= ui == edges[i][0] < n
     * 0 <= vi == edges[i][1] < n
     * ui != vi
     * The input is generated such that if the edges were bi-directional, the graph would be a tree.
     * @param n
     * @param edges
     * @return
     */
    // time = O(n), space = O(n)
    List<Integer>[] adj, rev;
    int[] f, res;
    boolean[] st;
    public int[] minEdgeReversals(int n, int[][] edges) {
        f = new int[n];
        res = new int[n];
        adj = new List[n];
        rev = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
            rev[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            adj[a].add(b);
            rev[b].add(a);
        }

        st = new boolean[n];
        dfs1(0);
        res[0] = f[0];
        dfs2(0);
        return res;
    }

    private void dfs1(int u) {
        st[u] = true;
        for (int j : adj[u]) {
            if (st[j]) continue;
            dfs1(j);
            f[u] += f[j];
        }
        for (int j : rev[u]) {
            if (st[j]) continue;
            dfs1(j);
            f[u] += f[j] + 1;
        }
        st[u] = false;
    }

    private void dfs2(int u) {
        st[u] = true;
        for (int j : adj[u]) {
            if (st[j]) continue;
            res[j] = res[u] + 1;
            dfs2(j);
        }
        for (int j : rev[u]) {
            if (st[j]) continue;
            res[j] = res[u] - 1;
            dfs2(j);
        }
    }
}
/**
 * 换根dp => LC834
 */