package LC3001_3300;
import java.util.*;
public class LC3203_FindMinimumDiameterAfterMergingTwoTrees {
    /**
     * There exist two undirected trees with n and m nodes, numbered from 0 to n - 1 and from 0 to m - 1, respectively.
     * You are given two 2D integer arrays edges1 and edges2 of lengths n - 1 and m - 1, respectively, where
     * edges1[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the first tree and
     * edges2[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the second tree.
     *
     * You must connect one node from the first tree with another node from the second tree with an edge.
     *
     * Return the minimum possible diameter of the resulting tree.
     *
     * The diameter of a tree is the length of the longest path between any two nodes in the tree.
     *
     * Input: edges1 = [[0,1],[0,2],[0,3]], edges2 = [[0,1]]
     * Output: 3
     *
     * Input: edges1 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]], edges2 = [[0,1],[0,2],[0,3],[2,4],[2,5],[3,6],[2,7]]
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= n, m <= 10^5
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
    // S1
    // time = O(n + m), space = O(n + m)
    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        int d1 = treeDiameter(edges1);
        int d2 = treeDiameter(edges2);
        return Math.max(d1, Math.max(d2, (d1 + 1) / 2 + (d2 + 1) / 2 + 1));
    }

    private int treeDiameter(int[][] edges) {
        int n = edges.length + 1;
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            adj[a].add(b);
            adj[b].add(a);
        }

        int[] x = bfs(adj, n, 0);
        int[] y = bfs(adj, n, x[0]);
        return y[1];
    }

    private int[] bfs(List<Integer>[] adj, int n, int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        int[] dist = new int[n];
        Arrays.fill(dist, 0x3f3f3f3f);
        dist[start] = 0;

        int[] res = new int[2];
        while (!q.isEmpty()) {
            int t = q.poll();
            for (int j : adj[t]) {
                if (dist[j] > dist[t] + 1) {
                    dist[j] = dist[t] + 1;
                    if (dist[j] > res[1]) {
                        res[0] = j;
                        res[1] = dist[j];
                    }
                    q.offer(j);
                }
            }
        }
        return res;
    }

    // S2
    // time = O(n + m), space = O(n + m)
    int res;
    List<Integer>[] adj;
    public int minimumDiameterAfterMerge2(int[][] edges1, int[][] edges2) {
        int d1 = helper(edges1), d2 = helper(edges2);
        return Math.max(d1, Math.max(d2, (d1 + 1) / 2 + (d2 + 1) / 2 + 1));
    }

    private int helper(int[][] edges) {
        int n = edges.length + 1;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            adj[a].add(b);
            adj[b].add(a);
        }

        res = 0;
        dfs(0, -1);
        return res;
    }

    private int dfs(int u, int fa) {
        int maxLen = 0;
        for (int v : adj[u]) {
            if (v == fa) continue;
            int t = dfs(v, u) + 1;
            res = Math.max(res, maxLen + t);
            maxLen = Math.max(maxLen, t);
        }
        return maxLen;
    }
}
/**
 * 选的点在直径上
 * 反证法
 * 选的点不再任何直径上
 *
 * A 到树上最远点的距离
 * 最远点，一定是直径的端点
 *
 * Q: 为什么不考虑重心？重心和直径终点的区别?
 */