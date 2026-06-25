package LC3901_4200;
import java.util.*;
public class LC3967_FinishTimeofTasksII {
    /**
     * You are given an integer n representing the number of tasks in a project, numbered from 0 to n - 1. These tasks
     * are connected as an undirected tree. This is represented by a 2D integer array edges of length n - 1, where
     * edges[i] = [ui, vi] indicates an undirected connection between task ui and task vi.
     *
     * You are also given an array baseTime of length n, where baseTime[i] represents the time to complete task i.
     *
     * For any chosen task as the root, the finish time of each task is calculated as follows:
     *
     * Leaf task: The finish time is baseTime[i].
     * Non-leaf task:
     * Let earliest be the minimum finish time among its children, and latest be the maximum finish time among its
     * children.
     * Let ownDuration be (latest - earliest) + baseTime[i].
     * Finish time of task i is latest + ownDuration.
     * Choose any task as the root and compute the finish time of that root based on the rules above.
     *
     * Return the minimum possible finish time among all choices of root.
     *
     * Input: n = 3, edges = [[0,1],[1,2]], baseTime = [9,1,5]
     * Output: 14
     *
     * Input: n = 3, edges = [[0,1],[0,2]], baseTime = [4,7,6]
     * Output: 12
     *
     * Input: n = 4, edges = [[0,1],[0,2],[2,3]], baseTime = [5,8,2,1]
     * Output: 16
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * edges.length = n - 1
     * edges[i] == [ui, vi]
     * 0 <= ui, vi <= n - 1
     * ui != vi
     * The input is generated such that edges represents a valid undirected tree.
     * baseTime.length == n
     * 1 <= baseTime[i] <= 10^5
     * @param n
     * @param edges
     * @param baseTime
     * @return
     */
    // time = O(n), space = O(n)
    final long inf = (long)1E18;
    List<Integer>[] adj;
    TreeInfo[] subRes;
    long[] downTime;
    long[] ans;
    int[] base;
    long res;
    public long finishTime(int n, int[][] edges, int[] baseTime) {
        base = baseTime;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            adj[u].add(v);
            adj[v].add(u);
        }

        subRes = new TreeInfo[n];
        downTime = new long[n];
        ans = new long[n];

        dfs(0, -1);

        res = inf;
        reroot(0, -1, -1);
        return res;
    }

    private void dfs(int u, int fa) {
        subRes[u] = new TreeInfo();
        int childCnt = 0;
        for (int v : adj[u]) {
            if (v == fa) continue;
            dfs(v, u);
            subRes[u].update(downTime[v], v);
            childCnt++;
        }
        downTime[u] = childCnt == 0 ? base[u] : (2 * subRes[u].mx1 - subRes[u].mn1 + base[u]);
    }

    private void reroot(int u, int fa, long fromUp) {
        TreeInfo cur = subRes[u];
        if (fromUp != -1) cur.update(fromUp, fa);

        int neighborCnt = adj[u].size();
        ans[u] = neighborCnt == 0 ? base[u] : (2 * cur.mx1 - cur.mn1 + base[u]);
        res = Math.min(res, ans[u]);

        for (int v : adj[u]) {
            if (v == fa) continue;
            long useMx = v == cur.maxChild ? cur.mx2 : cur.mx1;
            long useMn = v == cur.minChild ? cur.mn2 : cur.mn1;

            long up;
            if (neighborCnt == 1) up = base[u];
            else up = 2 * useMx - useMn + base[u];
            reroot(v, u, up);
        }
    }

    class TreeInfo {
        long mx1 = -inf, mx2 = -inf;
        long mn1 = inf, mn2 = inf;
        int maxChild = -1, minChild = -1;
        private void update(long val, int childId) {
            if (val > mx1) {
                mx2 = mx1;
                mx1 = val;
                maxChild = childId;
            } else if (val > mx2) mx2 = val;

            if (val < mn1) {
                mn2 = mn1;
                mn1 = val;
                minChild = childId;
            } else if (val < mn2) mn2 = val;
        }
    }
}