package LC3601_3900;
import java.util.*;
public class LC3607_PowerGridMaintenance {
    /**
     * You are given an integer c representing c power stations, each with a unique identifier id from 1 to c
     * (1‑based indexing).
     *
     * These stations are interconnected via n bidirectional cables, represented by a 2D array connections, where each
     * element connections[i] = [ui, vi] indicates a connection between station ui and station vi. Stations that are
     * directly or indirectly connected form a power grid.
     *
     * Initially, all stations are online (operational).
     *
     * You are also given a 2D array queries, where each query is one of the following two types:
     *
     * [1, x]: A maintenance check is requested for station x. If station x is online, it resolves the check by itself.
     * If station x is offline, the check is resolved by the operational station with the smallest id in the same power
     * grid as x. If no operational station exists in that grid, return -1.
     *
     * [2, x]: Station x goes offline (i.e., it becomes non-operational).
     *
     * Return an array of integers representing the results of each query of type [1, x] in the order they appear.
     *
     * Note: The power grid preserves its structure; an offline (non‑operational) node remains part of its grid and
     * taking it offline does not alter connectivity.
     *
     * Input: c = 5, connections = [[1,2],[2,3],[3,4],[4,5]], queries = [[1,3],[2,1],[1,1],[2,2],[1,2]]
     * Output: [3,2,3]
     *
     * Input: c = 3, connections = [], queries = [[1,1],[2,1],[1,1]]
     * Output: [1,-1]
     *
     * Constraints:
     *
     * 1 <= c <= 10^5
     * 0 <= n == connections.length <= min(105, c * (c - 1) / 2)
     * connections[i].length == 2
     * 1 <= ui, vi <= c
     * ui != vi
     * 1 <= queries.length <= 2 * 10^5
     * queries[i].length == 2
     * queries[i][0] is either 1 or 2.
     * 1 <= queries[i][1] <= c
     * @param c
     * @param connections
     * @param queries
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int[] processQueries(int c, int[][] connections, int[][] queries) {
        UnionFind uf = new UnionFind(c);
        for (int[] x : connections) {
            int u = x[0] - 1, v = x[1] - 1;
            uf.merge(u, v);
        }

        TreeSet<Integer>[] sets = new TreeSet[c];
        boolean[] st = new boolean[c];
        Arrays.fill(st, true);
        for (int i = 0; i < c; i++) {
            int p = uf.find(i);
            if (sets[p] == null) sets[p] = new TreeSet<>();
            sets[p].add(i);
        }
        List<Integer> ans = new ArrayList<>();
        for (int[] q : queries) {
            int o = q[0], x = q[1] - 1;
            if (o == 1) {
                if (st[x]) ans.add(x + 1);
                else {
                    int p = uf.find(x);
                    TreeSet<Integer> ts = sets[p];
                    if (ts == null || ts.size() == 0) ans.add(-1);
                    else ans.add(ts.first() + 1);
                }
            } else {
                if (st[x]) {
                    st[x] = false;
                    int p = uf.find(x);
                    TreeSet<Integer> ts = sets[p];
                    if (ts != null) ts.remove(x);
                }
            }
        }
        int[] res = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) res[i] = ans.get(i);
        return res;
    }

    class UnionFind {
        private final int[] fa;
        private final int[] size;
        public int cc;

        UnionFind(int n) {
            fa = new int[n];
            for (int i = 0; i < n; i++) {
                fa[i] = i;
            }
            size = new int[n];
            Arrays.fill(size, 1);
            cc = n;
        }

        public int find(int x) {
            if (fa[x] != x) {
                fa[x] = find(fa[x]);
            }
            return fa[x];
        }

        public boolean isSame(int x, int y) {
            return find(x) == find(y);
        }

        public boolean merge(int from, int to) {
            int x = find(from);
            int y = find(to);
            if (x == y) {
                return false;
            }
            fa[x] = y;
            size[y] += size[x];
            cc--;
            return true;
        }

        public int getSize(int x) {
            return size[find(x)];
        }
    }

    // S2: 懒删除堆
    // time = O(clogc + n + qlogc), space = O(c + n)
    public int[] processQueries2(int c, int[][] connections, int[][] queries) {
        List<Integer>[] adj = new List[c + 1];
        for (int i = 0; i <= c; i++) adj[i] = new ArrayList<>();
        for (int[] e : connections) {
            int u = e[0], v = e[1];
            adj[u].add(v);
            adj[v].add(u);
        }

        int[] belong = new int[c + 1];
        Arrays.fill(belong, -1);
        List<PriorityQueue<Integer>> heaps = new ArrayList<>();
        for (int i = 1; i <= c; i++) {
            if (belong[i] >= 0) continue;
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            dfs(i, adj, belong, heaps.size(), pq);
            heaps.add(pq);
        }

        int m = 0;
        for (int[] q : queries) {
            if (q[0] == 1) m++;
        }
        int[] res = new int[m];
        int idx = 0;
        boolean[] offline = new boolean[c + 1];
        for (int[] q : queries) {
            int x = q[1];
            if (q[0] == 2) {
                offline[x] = true;
                continue;
            }
            if (!offline[x]) {
                res[idx++] = x;
                continue;
            }
            PriorityQueue<Integer> pq = heaps.get(belong[x]);
            while (!pq.isEmpty() && offline[pq.peek()]) pq.poll();
            res[idx++] = pq.isEmpty() ? -1 : pq.peek();
        }
        return res;
    }

    private void dfs(int u, List<Integer>[] adj, int[] belong, int id, PriorityQueue<Integer> pq) {
        belong[u] = id;
        pq.offer(u);
        for (int v : adj[u]) {
            if (belong[v] < 0) dfs(v, adj, belong, id, pq);
        }
    }

    // S3: 倒序处理 + 维护最小值
    // time = O(c + n + q), space = O(c + n)
    public int[] processQueries3(int c, int[][] connections, int[][] queries) {
        List<Integer>[] adj = new List[c + 1];
        for (int i = 0; i <= c; i++) adj[i] = new ArrayList<>();
        for (int[] e : connections) {
            int u = e[0], v = e[1];
            adj[u].add(v);
            adj[v].add(u);
        }

        int[] belong = new int[c + 1];
        Arrays.fill(belong, -1);
        int cc = 0;
        for (int i = 1; i <= c; i++) {
            if (belong[i] < 0) {
                dfs2(i, adj, belong, cc);
                cc++;
            }
        }

        int[] offlineTime = new int[c + 1];
        Arrays.fill(offlineTime, Integer.MAX_VALUE);
        int q1 = 0, m = queries.length;
        for (int i = m - 1; i >= 0; i--) {
            int[] q = queries[i];
            if (q[0] == 2) offlineTime[q[1]] = i;
            else q1++;
        }

        int[] mn = new int[cc];
        Arrays.fill(mn, Integer.MAX_VALUE);
        for (int i = 1; i <= c; i++) {
            if (offlineTime[i] == Integer.MAX_VALUE) {
                int j = belong[i];
                mn[j] = Math.min(mn[j], i);
            }
        }

        int[] res = new int[q1];
        for (int i = m - 1; i >= 0; i--) {
            int[] q = queries[i];
            int x = q[1], j = belong[x];
            if (q[0] == 2) {
                if (offlineTime[x] == i) mn[j] = Math.min(mn[j], x);
            } else {
                q1--;
                if (i < offlineTime[x]) res[q1] = x;
                else if (mn[j] != Integer.MAX_VALUE) res[q1] = mn[j];
                else res[q1] = -1;
            }
        }
        return res;
    }

    private void dfs2(int u, List<Integer>[] adj, int[] belong, int id) {
        belong[u] = id;
        for (int v : adj[u]) {
            if (belong[v] < 0) dfs2(v, adj, belong, id);
        }
    }
}
/**
 * 1. 正着想
 * 数据结构：初始化，删除，查询最小操作 => 有序集合(set), 懒删除堆
 * # 离线算法：按照你自己规定的一种顺序去回答询问
 * # 在线算法：按照从左到右的顺序回答询问 设计题 变成函数调用 (强制在线)
 */
