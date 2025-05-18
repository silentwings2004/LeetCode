package LC2101_2400;
import java.util.*;
public class LC2368_ReachableNodesWithRestrictions {
    /**
     * There is an undirected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
     *
     * You are given a 2D integer array edges of length n - 1 where edges[i] = [ai, bi] indicates that there is an edge
     * between nodes ai and bi in the tree. You are also given an integer array restricted which represents restricted
     * nodes.
     *
     * Return the maximum number of nodes you can reach from node 0 without visiting a restricted node.
     *
     * Note that node 0 will not be a restricted node.
     *
     * Input: n = 7, edges = [[0,1],[1,2],[3,1],[4,0],[0,5],[5,6]], restricted = [4,5]
     * Output: 4
     *
     * Input: n = 7, edges = [[0,1],[0,2],[0,5],[0,4],[3,2],[6,5]], restricted = [4,2,1]
     * Output: 3
     *
     * Constraints:
     *
     * 2 <= n <= 10^5
     * edges.length == n - 1
     * edges[i].length == 2
     * 0 <= ai, bi < n
     * ai != bi
     * edges represents a valid tree.
     * 1 <= restricted.length < n
     * 1 <= restricted[i] < n
     * All the values of restricted are unique.
     * @param n
     * @param edges
     * @param restricted
     * @return
     */
    // time = O(n), space = O(n)
    List<Integer>[] graph;
    boolean[] st;
    int res = 0;
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] x : edges) {
            int a = x[0], b = x[1];
            graph[a].add(b);
            graph[b].add(a);
        }

        st = new boolean[n];
        for (int x : restricted) st[x] = true;
        dfs(0);
        return res;
    }

    private void dfs(int u) {
        st[u] = true;
        res++;

        for (int next : graph[u]) {
            if (st[next]) continue;
            dfs(next);
        }
    }
}
