package LC1501_1800;
import java.util.*;
public class LC1786_NumberofRestrictedPathsFromFirsttoLastNode {
    /**
     * There is an undirected weighted connected graph. You are given a positive integer n which denotes that the graph
     * has n nodes labeled from 1 to n, and an array edges where each edges[i] = [ui, vi, weighti] denotes that there is
     * an edge between nodes ui and vi with weight equal to weighti.
     *
     * A path from node start to node end is a sequence of nodes [z0, z1, z2, ..., zk] such that z0 = start and zk = end
     * and there is an edge between zi and zi+1 where 0 <= i <= k-1.
     *
     * The distance of a path is the sum of the weights on the edges of the path. Let distanceToLastNode(x) denote the
     * shortest distance of a path between node n and node x. A restricted path is a path that also satisfies that
     * distanceToLastNode(zi) > distanceToLastNode(zi+1) where 0 <= i <= k-1.
     *
     * Return the number of restricted paths from node 1 to node n. Since that number may be too large, return it
     * modulo 10^9 + 7.
     *
     * Input: n = 5, edges = [[1,2,3],[1,3,3],[2,3,1],[1,4,2],[5,2,2],[3,5,1],[5,4,10]]
     * Output: 3
     *
     * Input: n = 7, edges = [[1,3,1],[4,1,2],[7,3,4],[2,5,3],[5,6,1],[6,7,2],[7,5,3],[2,6,4]]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= n <= 2 * 10^4
     * n - 1 <= edges.length <= 4 * 10^4
     * edges[i].length == 3
     * 1 <= ui, vi <= n
     * ui != vi
     * 1 <= weighti <= 10^5
     * There is at most one edge between any two nodes.
     * There is at least one path between any two nodes.
     *
     * @param n
     * @param edges
     * @return
     */
    // S1: Dijkstra + dfs
    // time = O(ElogE) = O(mlogm), space = O(n)
    public int countRestrictedPaths(int n, int[][] edges) {
        List<int[]>[] graph = new List[n];
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] edge : edges) {
            int a = edge[0] - 1, b = edge[1] - 1;
            graph[a].add(new int[]{b, edge[2]});
            graph[b].add(new int[]{a, edge[2]});
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        pq.offer(new int[]{0, n - 1}); // dist: distance from node x to end, so we should start from end, then the dist = 0

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int d = cur[0], node = cur[1];
            if (dist[node] != -1) continue;
            dist[node] = d;

            for (int[] x : graph[node]) {
                int next = x[0], weight = x[1];
                if (dist[next] != -1) continue;
                pq.offer(new int[]{weight + dist[node], next});
            }
        }

        long[] pathNum = new long[n];
        Arrays.fill(pathNum, -1);

        long res = dfs(0, n, pathNum, graph, dist);
        return (int) res;
    }

    private long dfs(int cur, int n, long[] pathNum, List<int[]>[] graph, int[] dist) {
        // base case
        if (cur == n - 1) return 1;
        if (pathNum[cur] != -1) return pathNum[cur];

        long sum = 0;
        long M = (long)(1e9 + 7);
        for (int[] x : graph[cur]) {
            int next = x[0], weight = x[1];
            if (dist[next] >= dist[cur]) continue;
            sum += dfs(next, n, pathNum, graph, dist);
            sum %= M;
        }
        pathNum[cur] = sum;
        return sum;
    }

    // S2: Dijkstra + DP
    // time = O(nlogn), space = O(n)
    final int N = 20010, M = 80010, INF = Integer.MAX_VALUE, MOD = (int) 1e9 + 7;
    int n, idx;
    int[] h, e, ne, w;
    int[] dist, f;
    boolean[] st;
    public int countRestrictedPaths2(int n, int[][] edges) {
        h = new int[N];
        e = new int[M];
        ne = new int[M];
        w = new int[M];
        dist = new int[N];
        f = new int[N];
        st = new boolean[N];

        this.n = n;
        Arrays.fill(h, -1);
        idx = 0;

        for (int[] e : edges) {
            int a = e[0], b = e[1], c = e[2];
            add(a, b, c);
            add(b, a, c);
        }

        dijkstra(n);
        int[][] vc = new int[n][2];
        for (int i = 1; i <= n; i++) vc[i - 1] = new int[]{dist[i], i};
        Arrays.sort(vc, ((o1, o2) -> o1[0] - o2[0]));

        f[n] = 1;
        for (int[] v : vc) {
            int u = v[1];
            for (int i = h[u]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] < dist[u]) f[u] = (f[u] + f[j]) % MOD;
            }
        }
        return f[1];
    }

    private void dijkstra(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        pq.offer(new int[]{0, start});
        Arrays.fill(dist, INF);
        dist[start] = 0;

        while (!pq.isEmpty()) {
            int[] t = pq.poll();
            int u = t[1];
            if (st[u]) continue;
            st[u] = true;

            for (int i = h[u]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] > dist[u] + w[i]) {
                    dist[j] = dist[u] + w[i];
                    if (!st[j]) pq.offer(new int[]{dist[j], j});
                }
            }
        }
    }

    private void add(int a, int b, int c) {
        e[idx] = b;
        w[idx] = c;
        ne[idx] = h[a];
        h[a] = idx++;
    }
}
/**
 * Dijkstra: single source, non-negative weight -> 单源，到起点的最短距离
 * Elog(E)
 * node n是海平面，其他点到n的距离就是到海平面的海拔差，我经过的每个点，其海拔是依次递减
 * 实质：topdown: DFS + memo
 * 只选海拔更低的地方去，一路去更低的地方 -> 一旦走到海平面就成功了
 * memo: pathNum(x1) = pathNum(x2) + pathNum(x3) + ...
 * pathNum(x2) = pathNum(x3) + ...
 * => Dijkstra + DFS
 * 不需要用visited去重 -> 因为递归的过程决定永远只会往海拔更低的地方走，所以不可能会访问到之前访问过的路径，不会走回头路
 */
