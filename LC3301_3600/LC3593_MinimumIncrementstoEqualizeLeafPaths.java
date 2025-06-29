package LC3301_3600;
import java.util.*;
public class LC3593_MinimumIncrementstoEqualizeLeafPaths {
    /**
     * You are given an integer n and an undirected tree rooted at node 0 with n nodes numbered from 0 to n - 1. This
     * is represented by a 2D array edges of length n - 1, where edges[i] = [ui, vi] indicates an edge from node ui to
     * vi .
     *
     * Each node i has an associated cost given by cost[i], representing the cost to traverse that node.
     *
     * The score of a path is defined as the sum of the costs of all nodes along the path.
     *
     * Your goal is to make the scores of all root-to-leaf paths equal by increasing the cost of any number of nodes by
     * any non-negative amount.
     *
     * Return the minimum number of nodes whose cost must be increased to make all root-to-leaf path scores equal.
     *
     * Input: n = 3, edges = [[0,1],[0,2]], cost = [2,1,3]
     * Output: 1
     *
     * Input: n = 3, edges = [[0,1],[1,2]], cost = [5,1,4]
     * Output: 0
     *
     * Input: n = 5, edges = [[0,4],[0,1],[1,2],[1,3]], cost = [3,4,1,1,7]
     * Output: 1
     *
     * Constraints:
     *
     * 2 <= n <= 10^5
     * edges.length == n - 1
     * edges[i] == [ui, vi]
     * 0 <= ui, vi < n
     * cost.length == n
     * 1 <= cost[i] <= 10^9
     * The input is generated such that edges represents a valid tree.
     * @param n
     * @param edges
     * @param cost
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public int minIncrease(int n, int[][] edges, int[] cost) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            adj[a].add(b);
            adj[b].add(a);
        }

        int[] p = new int[n];
        Arrays.fill(p, -1);
        p[0] = 0;
        List<Integer> order = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);

        while (!q.isEmpty()) {
            int u = q.poll();
            order.add(u);
            for (int v : adj[u]) {
                if (p[v] == -1) {
                    p[v] = u;
                    q.offer(v);
                }
            }
        }

        long[] w = new long[n];
        int[] son = new int[n];
        Arrays.fill(son, -1);
        for (int i = n - 1; i >= 0; i--) {
            int u = order.get(i);
            long maxv = 0;
            int sid = -1;
            for (int v : adj[u]) {
                if (p[u] == v) continue;
                if (w[v] > maxv) {
                    maxv = w[v];
                    sid = v;
                }
            }
            w[u] = cost[u] + maxv;
            son[u] = sid;
        }

        List<Integer>[] cs = new List[n];
        for (int i = 0; i < n; i++) cs[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) cs[p[i]].add(i);

        Stack<Integer> stk = new Stack<>();
        stk.push(0);
        int res = 0;
        while (!stk.isEmpty()) {
            int u = stk.pop();
            int h = son[u];
            long bd = h == -1 ? 0 : w[h];
            for (int v : cs[u]) {
                long x = bd - w[v];
                if (x > 0) res++;
                stk.push(v);
            }
        }
        return res;
    }

    // S2
    // time = O(n), space = O(n)
    List<Integer>[] adj;
    int[] cost;
    int res;
    public int minIncrease2(int n, int[][] edges, int[] cost) {
        this.cost = cost;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            adj[a].add(b);
            adj[b].add(a);
        }
        adj[0].add(-1); // 避免误把根节点当作叶子
        dfs(0, -1, 0);
        return res;
    }

    private long dfs(int u, int fa, long sum) {
        sum += cost[u];
        if (adj[u].size() == 1) return sum;

        // 在根到叶子的 sum 中，有 cnt 个 sum 等于 maxv
        long maxv = 0;
        int cnt = 0;
        for (int v : adj[u]) {
            if (v == fa) continue;
            long mx = dfs(v, u, sum);
            if (mx > maxv) {
                maxv = mx;
                cnt = 1;
            } else if (mx == maxv) cnt++;
        }
        // 其余小于 maxv 的 sum，可以通过增大 cost[v] 的值，改成 maxv
        res += adj[u].size() - 1 - cnt;
        return maxv;
    }
}