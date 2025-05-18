package LC2101_2400;
import java.util.*;
public class LC2360_LongestCycleinaGraph {
    /**
     * You are given a directed graph of n nodes numbered from 0 to n - 1, where each node has at most one outgoing edge.
     *
     * The graph is represented with a given 0-indexed array edges of size n, indicating that there is a directed edge
     * from node i to node edges[i]. If there is no outgoing edge from node i, then edges[i] == -1.
     *
     * Return the length of the longest cycle in the graph. If no cycle exists, return -1.
     *
     * A cycle is a path that starts and ends at the same node.
     *
     * Input: edges = [3,3,4,2,3]
     * Output: 3
     *
     * Input: edges = [2,-1,3,1]
     * Output: -1
     *
     * n == edges.length
     * 2 <= n <= 10^5
     * -1 <= edges[i] < n
     * edges[i] != i
     */
    // S1
    // time = O(n), space = O(n)
    List<Integer>[] graph;
    public int longestCycle(int[] edges) {
        int n = edges.length;
        graph = new List[n];
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (edges[i] != -1) {
                graph[i].add(edges[i]);
                indegree[edges[i]]++;
            }
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) set.add(i);
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            set.remove(cur);
            for (int next : graph[cur]) {
                indegree[next]--;
                if (indegree[next] == 0) queue.offer(next);
            }
        }
        if (set.size() == 0) return -1;

        int res = 0, sum = 0;
        HashSet<Integer> st = new HashSet<>();
        for (int x : set) {
            if (st.contains(x)) continue;
            st.add(x);
            dfs(x, st);
            res = Math.max(res, st.size() - sum);
            sum = st.size();
        }
        return res;
    }

    private void dfs(int u, HashSet<Integer> st) {
        for (int next : graph[u]) {
            if (st.contains(next)) continue;
            st.add(next);
            dfs(next, st);
        }
    }

    // S2: dfs (optimal solution)
    // time = O(n), space = O(n)
    int[] p, in_stk;
    boolean[] st;
    int res;
    public int longestCycle2(int[] edges) {
        this.p = edges;
        int n = edges.length;
        st = new boolean[n];
        in_stk = new int[n];
        res = -1;

        for (int i = 0; i < n; i++) {
            if (!st[i]) dfs(i, 1);
        }
        return res;
    }

    private void dfs(int u, int depth) {
        st[u] = true;
        in_stk[u] = depth;

        int j = p[u];
        if (j != -1) {
            if (!st[j]) dfs(j, depth + 1); // 这点没有被搜过 -> 搜
            else if (in_stk[j] > 0) res = Math.max(res, depth + 1 - in_stk[j]); // 被搜过并且在栈里，深度可以更新!
        }

        in_stk[u] = 0; // 从栈中被弹出去
    }

    // S3: Topological sort
    // time = O(n), space = O(n)
    public int longestCycle3(int[] edges) {
        int n = edges.length;
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) {
            if (edges[i] != -1) indegree[edges[i]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                visited[i] = true;
            }
        }

        while (!queue.isEmpty()) {
            int x = queue.poll();
            int y = edges[x];
            if (y == -1) continue;
            indegree[y]--;
            if (indegree[y] == 0) {
                queue.offer(y);
                visited[y] = true;
            }
        }

        int res = -1;
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            int j = i, count = 1;
            visited[j] = true;
            while (edges[j] != -1 && !visited[edges[j]]) {
                j = edges[j];
                visited[j] = true;
                count++;
            }
            res = Math.max(res, count);
        }
        return res;
    }

    // S4
    // time = O(n), space = O(n)
    public int longestCycle4(int[] edges) {
        int n = edges.length, res = -1, cur = 1;
        int[] t = new int[n];
        for (int i = 0; i < n; i++) {
            int x = i, st = cur;
            while (x != -1 && t[x] == 0) {
                t[x] = cur++;
                x = edges[x];
            }
            if (x != -1 && t[x] >= st) res = Math.max(res, cur - t[x]);
        }
        return res;
    }
}
/**
 * 用Tarjan算法
 * 手写找环 -> 基环树dp
 * 从任意一个点往下搜
 * st[i]
 * in_stk[i]
 * 搜的时候存下每个点在栈里的深度
 * d2 - d1
 *
 * 保证只有一个出度 =>
 * 1. 单链
 * 2. 环
 * 3. 一个单链指向一个环
 */