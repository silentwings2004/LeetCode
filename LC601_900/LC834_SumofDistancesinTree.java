package LC601_900;
import java.util.*;
public class LC834_SumofDistancesinTree {
    /**
     * There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
     *
     * You are given the integer n and the array edges where edges[i] = [ai, bi] indicates that there is an edge
     * between nodes ai and bi in the tree.
     *
     * Return an array answer of length n where answer[i] is the sum of the distances between the ith node in the tree
     * and all other nodes.
     *
     * Input: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
     * Output: [8,12,6,10,10,10]
     *
     * Constraints:
     *
     * 1 <= n <= 3 * 10^4
     * edges.length == n - 1
     * edges[i].length == 2
     * 0 <= ai, bi < n
     * ai != bi
     * The given input represents a valid tree.
     * @param n
     * @param edges
     * @return
     */
    // S1: ArrayList + dp
    // time = O(n), space = O(n)
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        int[] dp = new int[n];
        int[] sz = new int[n];
        int[] res = new int[n];

        // build graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        dfs(dp, sz, graph, 0, -1); // 撸一遍统计获得了在 u 为根的树中，每个节点为根的子树的答案 dp
        dfs2(dp, sz, graph, 0, -1, res); // 换根，在以u为根节点得到的值的基础上作更改即可获得新的根所产生的值
        return res;
    }

    private void dfs(int[] dp, int[] sz, List<List<Integer>> graph, int cur, int parent) {
        sz[cur] = 1;
        dp[cur] = 0;

        for (int next : graph.get(cur)) {
            if (next == parent) continue;
            dfs(dp, sz, graph, next, cur);
            dp[cur] += dp[next] + sz[next];
            sz[cur] += sz[next];
        }
    }

    private void dfs2(int[] dp, int[] sz, List<List<Integer>> graph, int cur, int parent, int[] res) {
        res[cur] = dp[cur];

        for (int next : graph.get(cur)) {
            if (next == parent) continue;
            int pu = dp[cur], pv = dp[next];
            int su = sz[cur], sv = sz[next];

            dp[cur] -= dp[next] + sz[next];
            sz[cur] -= sz[next];
            dp[next] += dp[cur] + sz[cur];
            sz[next] += sz[cur];

            dfs2(dp, sz, graph, next, cur, res);

            // setback
            dp[cur] = pu;
            dp[next] = pv;
            sz[cur] = su;
            sz[next] = sv;
        }
    }

    // S1.2: HashMap + dp
    // time = O(n), space = O(n)
    public int[] sumOfDistancesInTree2(int n, int[][] edges) {
        int[] dp = new int[n];
        int[] sz = new int[n];
        int[] res = new int[n];

        // build graph
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            map.putIfAbsent(edge[0], new ArrayList<>());
            map.putIfAbsent(edge[1], new ArrayList<>());
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        dfs(dp, sz, map, 0, -1);
        dfs2(dp, sz, map, 0, -1, res);
        return res;
    }

    private void dfs(int[] dp, int[] sz, HashMap<Integer, List<Integer>> map, int cur, int parent) {
        dp[cur] = 0;
        sz[cur] = 1;

        if (map.containsKey(cur)) {
            for (int next : map.get(cur)) {
                if (next == parent) continue;
                dfs(dp, sz, map, next, cur);
                dp[cur] += dp[next] + sz[next];
                sz[cur] += sz[next];
            }
        }
    }

    private void dfs2(int[] dp, int[] sz, HashMap<Integer, List<Integer>> map, int cur, int parent, int[] res) {
        res[cur] = dp[cur];

        if (map.containsKey(cur)) {
            for (int next : map.get(cur)) {
                if (next == parent) continue;

                int pu = dp[cur], su = sz[cur];
                int pv = dp[next], sv = sz[next];

                dp[cur] -= dp[next] + sz[next];
                sz[cur] -= sz[next];
                dp[next] += dp[cur] + sz[cur];
                sz[next] += sz[cur];

                dfs2(dp, sz, map, next, cur, res);

                // setback
                dp[cur] = pu;
                sz[cur] = su;
                dp[next] = pv;
                sz[next] = sv;
            }
        }
    }

    // S3: two pass
    // time = O(n), space = O(n)
    final int N = 30010, M = N * 2;
    int[] h, e, ne;
    int[] sum, cnt, up;
    int n, idx;
    public int[] sumOfDistancesInTree3(int n, int[][] edges) {
        h = new int[N];
        e = new int[M];
        ne = new int[M];
        sum = new int[N];
        cnt = new int[N];
        up = new int[N];
        Arrays.fill(h, -1);

        this.n = n;
        idx = 0;

        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            add(a, b);
            add(b, a);
        }

        dfs3(0, -1);
        dfs4(0, -1);

        int[] res = new int[n];
        for (int i = 0; i < n; i++) res[i] = sum[i] + up[i];
        return res;
    }

    private void dfs3(int u, int p) {
        sum[u] = 0;
        cnt[u] = 1;

        for (int i = h[u]; i != -1; i = ne[i]) {
            int j = e[i];
            if (j == p) continue;
            dfs3(j, u);
            sum[u] += sum[j] + cnt[j];
            cnt[u] += cnt[j];
        }
    }

    private void dfs4(int u, int p) {
        for (int i = h[u]; i != -1; i = ne[i]) {
            int j = e[i];
            if (j == p) continue;
            up[j] = up[u] + sum[u] - (sum[j] + cnt[j]) + n - cnt[j];
            dfs4(j, u);
        }
    }

    private void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    // S4: dfs
    class Solution {
        // time = O(n), space = O(n)
        int[] subtree, res;
        List<Integer>[] graph;
        boolean[] st;
        int n;
        public int[] sumOfDistancesInTree(int n, int[][] edges) {
            this.n = n;
            subtree = new int[n];
            res = new int[n];
            st = new boolean[n];
            graph = new List[n];
            for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

            for (int[] edge : edges) {
                int a = edge[0], b = edge[1];
                graph[a].add(b);
                graph[b].add(a);
            }

            st[0] = true;
            dfs(0);

            Arrays.fill(st, false);
            st[0] = true;
            res[0] = dfs2(0);

            Arrays.fill(st, false);
            st[0] = true;
            dfs3(0);
            return res;
        }

        private int dfs(int u) {
            int sum = 1;
            for (int next : graph[u]) {
                if (st[next]) continue;
                st[next] = true;
                sum += dfs(next);
            }
            subtree[u] = sum;
            return sum;
        }

        private int dfs2(int u) {
            int sum = 0;
            for (int next : graph[u]) {
                if (st[next]) continue;
                st[next] = true;
                sum += dfs2(next);
            }
            sum += subtree[u] - 1;
            return sum;
        }

        private void dfs3(int u) {
            for (int next : graph[u]) {
                if (st[next]) continue;
                st[next] = true;
                int b = subtree[next];
                int a = n - b;
                res[next] = res[u] + a - b;
                dfs3(next);
            }
        }
    }
}
/**
 * 树型dp
 * dp[u] 表示以 u 为根的子树，它的所有子节点到它的距离之和
 * sz[u] 表示以 u 为根的子树的节点数量
 *
 * down:
 * sum[u] += sum[j] + cnt[j]
 * cnt[u] += cnt[j]
 *
 * up:
 * u 往下走的总和 = sum[u] - (sum[j] + cnt[j])
 * up[j] = S + C = (up[u] + sum[u] - (sum[j] + cnt[j])) + (n - cnt[j])
 *
 * Re-root 移根
 * f(child) -> f(parent) + a - b = f(parent) + n - 2 * subtree_size(child)
 * b = subtree_size(child)
 * a = n - b
 *
 * 0 pick a root
 * 1 subtree_size of every node
 * 2 distance sum towards root => f(root)
 * 3 f(node)
 */
