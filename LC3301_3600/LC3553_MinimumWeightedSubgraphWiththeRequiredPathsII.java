package LC3301_3600;
import java.util.*;
public class LC3553_MinimumWeightedSubgraphWiththeRequiredPathsII {
    /**
     * You are given an undirected weighted tree with n nodes, numbered from 0 to n - 1. It is represented by a 2D
     * integer array edges of length n - 1, where edges[i] = [ui, vi, wi] indicates that there is an edge between nodes
     * ui and vi with weight wi.
     *
     * Additionally, you are given a 2D integer array queries, where queries[j] = [src1j, src2j, destj].
     *
     * Return an array answer of length equal to queries.length, where answer[j] is the minimum total weight of a
     * subtree such that it is possible to reach destj from both src1j and src2j using edges in this subtree.
     *
     * A subtree here is any connected subset of nodes and edges of the original tree forming a valid tree.
     *
     * Input: edges = [[0,1,2],[1,2,3],[1,3,5],[1,4,4],[2,5,6]], queries = [[2,3,4],[0,2,5]]
     * Output: [12,11]
     *
     * Input: edges = [[1,0,8],[0,2,7]], queries = [[0,1,2]]
     * Output: [15]
     *
     * Constraints:
     *
     * 3 <= n <= 10^5
     * edges.length == n - 1
     * edges[i].length == 3
     * 0 <= ui, vi < n
     * 1 <= wi <= 10^4
     * 1 <= queries.length <= 10^5
     * queries[j].length == 3
     * 0 <= src1j, src2j, destj < n
     * src1j, src2j, and destj are pairwise distinct.
     * The input is generated such that edges represents a valid tree.
     * @param edges
     * @param queries
     * @return
     */
    // time = O(nlogn), space = O(n)
    final int inf = 0x3f3f3f3f;
    List<int[]>[] adj;
    int[][] fa;
    int[] depth;
    long[] dist;
    int lg;
    public int[] minimumWeight(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1], c = e[2];
            adj[a].add(new int[]{b, c});
            adj[b].add(new int[]{a, c});
        }

        lg = 32 - Integer.numberOfLeadingZeros(n);
        depth = new int[n];
        dist = new long[n];
        fa = new int[n][lg];
        bfs(0);

        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int a = queries[i][0], b = queries[i][1], c = queries[i][2];
            res[i] = (int)((get(a, b) + get(b, c) + get(c, a)) / 2);
        }
        return res;
    }

    private long get(int a, int b) {
        int p = lca(a, b);
        return dist[a] + dist[b] - 2 * dist[p];
    }

    private int lca(int a, int b) {
        if (depth[a] < depth[b]) {
            int t = a;
            a = b;
            b = t;
        }

        for (int k = lg - 1; k >= 0; k--) {
            if (depth[fa[a][k]] >= depth[b]) a = fa[a][k];
        }

        if (a == b) return a;
        for (int k = lg - 1; k >= 0; k--) {
            if (fa[a][k] != fa[b][k]) {
                a = fa[a][k];
                b = fa[b][k];
            }
        }
        return fa[a][0];
    }

    private void bfs(int root) {
        Arrays.fill(depth, inf);
        depth[0] = 0;
        depth[root] = 1;
        Queue<Integer> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int[] p : adj[u]) {
                int v = p[0], w = p[1];
                if (depth[v] > depth[u] + 1) {
                    depth[v] = depth[u] + 1;
                    dist[v] = dist[u] + w;
                    q.offer(v);
                    fa[v][0] = u;
                    for (int k = 1; k < lg; k++) {
                        fa[v][k] = fa[fa[v][k - 1]][k - 1];
                    }
                }
            }
        }
    }
}
/**
 * 对于每个询问，设这三个点分别为 a,b,c，我们可以计算 a 到 b 的距离，b 到 c 的距离，c 到 a 的距离
 * 这三条路径，恰好形成了一个回路（从 a 到 b 到 c 再回到 a），所以回路中的每条边都恰好出现在两条路径中。
 * 因此，把这三个距离相加，再除以 2，即为答案
 * DFN: dfs 序
 * 先序遍历：这样可以保证每条边只经过2次
 * 为什么三个点按照任意顺序求都是对的，不需要 dfs 序?
 * 三个点的路径交集只有一个点是三者路径都经过的（可以证明是深度最大的 LCA）
 */