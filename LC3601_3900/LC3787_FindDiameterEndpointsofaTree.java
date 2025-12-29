package LC3601_3900;
import java.util.*;
public class LC3787_FindDiameterEndpointsofaTree {
    /**
     * You are given an undirected tree with n nodes, numbered from 0 to n - 1. It is represented by a 2D integer array
     * edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge
     * between nodes ai and bi in the tree.
     *
     * A node is called special if it is an endpoint of any diameter path of the tree.
     *
     * Return a binary string s of length n, where s[i] = '1' if node i is special, and s[i] = '0' otherwise.
     *
     * A diameter path of a tree is the longest simple path between any two nodes. A tree may have multiple diameter
     * paths.
     *
     * An endpoint of a path is the first or last node on that path.
     *
     * Input: n = 3, edges = [[0,1],[1,2]]
     * Output: "101"
     *
     * Input: n = 7, edges = [[0,1],[1,2],[2,3],[3,4],[3,5],[1,6]]
     * Output: "1000111"
     *
     * Input: n = 2, edges = [[0,1]]
     * Output: "11"
     *
     * Constraints:
     *
     * 2 <= n <= 10^5
     * edges.length == n - 1
     * edges[i] = [ai, bi]
     * 0 <= ai, bi < n
     * The input is generated such that edges represents a valid tree.
     * @param n
     * @param edges
     * @return
     */
    // time = O(n), space = O(n)
    public String findSpecialNodes(int n, int[][] edges) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            adj[a].add(b);
            adj[b].add(a);
        }

        int[] d0 = bfs(adj, 0);
        int a = 0;
        for (int i = 1; i < n; i++) {
            if (d0[i] > d0[a]) a = i;
        }
        int[] d1 = bfs(adj, a);
        int b = 0;
        for (int i = 1; i < n; i++) {
            if (d1[i] > d1[b]) b = i;
        }
        int d = d1[b];
        int[] d2 = bfs(adj, b);
        char[] res = new char[n];
        for (int i = 0; i < n; i++) {
            res[i] = d1[i] == d || d2[i] == d ? '1' : '0';
        }
        return new String(res);
    }

    private int[] bfs(List<Integer>[] adj, int st) {
        int n = adj.length;
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        Queue<Integer> q = new LinkedList<>();
        q.offer(st);
        dist[st] = 0;

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj[u]) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    q.offer(v);
                }
            }
        }
        return dist;
    }
}
