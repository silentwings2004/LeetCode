package LC3301_3600;
import java.util.*;
public class LC3372_MaximizetheNumberofTargetNodesAfterConnectingTreesI {
    /**
     * There exist two undirected trees with n and m nodes, with distinct labels in ranges [0, n - 1] and [0, m - 1],
     * respectively.
     *
     * You are given two 2D integer arrays edges1 and edges2 of lengths n - 1 and m - 1, respectively, where
     * edges1[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the first tree and
     * edges2[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the second tree. You are also
     * given an integer k.
     *
     * Node u is target to node v if the number of edges on the path from u to v is less than or equal to k. Note that
     * a node is always target to itself.
     *
     * Return an array of n integers answer, where answer[i] is the maximum possible number of nodes target to node i
     * of the first tree if you have to connect one node from the first tree to another node in the second tree.
     *
     * Note that queries are independent from each other. That is, for every query you will remove the added edge before
     * proceeding to the next query.
     *
     * Input: edges1 = [[0,1],[0,2],[2,3],[2,4]], edges2 = [[0,1],[0,2],[0,3],[2,7],[1,4],[4,5],[4,6]], k = 2
     * Output: [9,7,9,8,8]
     *
     * Input: edges1 = [[0,1],[0,2],[0,3],[0,4]], edges2 = [[0,1],[1,2],[2,3]], k = 1
     * Output: [6,3,3,3,3]
     *
     * Constraints:
     *
     * 2 <= n, m <= 1000
     * edges1.length == n - 1
     * edges2.length == m - 1
     * edges1[i].length == edges2[i].length == 2
     * edges1[i] = [ai, bi]
     * 0 <= ai, bi < n
     * edges2[i] = [ui, vi]
     * 0 <= ui, vi < m
     * The input is generated such that edges1 and edges2 represent valid trees.
     * 0 <= k <= 1000
     * @param edges1
     * @param edges2
     * @param k
     * @return
     */
    // time = O(n^2 + m^2), space = O(n + m)
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        int n = edges1.length + 1, m = edges2.length + 1;
        List<Integer>[] adj1 = build(edges1);
        List<Integer>[] adj2 = build(edges2);

        int[] d1 = cal(adj1, k);
        int[] d2 = cal(adj2, k - 1);

        int mx = 0;
        for (int x : d2) mx = Math.max(mx, x);

        int[] res = new int[n];
        for (int i = 0; i < n; i++) res[i] = d1[i] + mx;
        return res;
    }

    private int bfs(List<Integer>[] adj, int start, int k) {
        int n = adj.length;
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        boolean[] st = new boolean[n];
        st[start] = true;

        int step = 0, cnt = 0;
        if (step > k) return cnt; // when k = 0, k - 1 = -1
        while (!q.isEmpty()) {
            int sz = q.size();
            while (sz-- > 0) {
                int u = q.poll();
                cnt++;
                for (int v : adj[u]) {
                    if (st[v]) continue;
                    st[v] = true;
                    q.offer(v);
                }
            }
            step++;
            if (step > k) break;
        }
        return cnt;
    }

    private int[] cal(List<Integer>[] adj, int k) {
        int n = adj.length;
        int[] d = new int[n];
        Arrays.fill(d, -1);
        for (int i = 0; i < n; i++) {
            d[i] = bfs(adj, i, k);
        }
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
/**
 * 第一棵树和第二棵树的目标节点个数可以分开计算
 * ans[i] = i 到第一棵树的目标节点个数 (距离 <= k) + 第二棵树中的距离某个结点 <= k - 1 的结点个数的最大值
 * 第二棵树中的距离某个结点 <= k - 1 的节点个数的最大值和 i 无关，可以独立的计算出来
 */