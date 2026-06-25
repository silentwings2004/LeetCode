package LC3901_4200;
import java.util.*;
public class LC3965_FinishTimeofTasksI {
    /**
     * You are given an integer n representing the number of tasks in a project, numbered from 0 to n - 1. These tasks
     * are connected as a tree rooted at task 0. This is represented by a 2D integer array edges of length n - 1, where
     * edges[i] = [ui, vi] indicates that task ui is the parent of task vi.
     *
     * You are also given an array baseTime of length n, where baseTime[i] represents the time to complete task i.
     *
     * The finish time of each task is calculated as follows:
     *
     * Leaf task: The finish time is baseTime[i].
     * Non-leaf task:
     * Let earliest be the minimum finish time among its children, and latest be the maximum finish time among its
     * children.
     * Let ownDuration be (latest - earliest) + baseTime[i].
     * The finish time of task i is latest + ownDuration.
     * Return the finish time of the root task 0.
     *
     * Input: n = 3, edges = [[0,1],[1,2]], baseTime = [9,5,3]
     * Output: 17
     *
     * Input: n = 3, edges = [[0,1],[0,2]], baseTime = [4,7,6]
     * Output: 12
     *
     * Input: n = 4, edges = [[0,1],[0,2],[2,3]], baseTime = [5,8,2,1]
     * Output: 18
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * edges.length = n - 1
     * edges[i] == [ui, vi]
     * 0 <= ui, vi <= n - 1
     * ui != vi
     * The input is generated such that edges represents a valid tree.
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
    int[] base;
    public long finishTime(int n, int[][] edges, int[] baseTime) {
       this.base = baseTime;
       adj = new List[n];
       for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
       for (int[] e : edges) {
           int u = e[0], v = e[1];
           adj[u].add(v);
       }
       return dfs(0);
    }

    private long dfs(int u) {
        long a = inf, b = 0;
        for (int v : adj[u]) {
            long x = dfs(v);
            a = Math.min(a, x);
            b = Math.max(b, x);
        }
        if (a == inf) return base[u];
        return (b - a) + base[u] + b;
    }
}
/**
 * 注意：计算的是儿子的最小最大完成时间，不是子树中的所有节点。
 */