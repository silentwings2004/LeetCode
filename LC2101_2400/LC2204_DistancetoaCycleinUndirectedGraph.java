package LC2101_2400;
import java.util.*;
public class LC2204_DistancetoaCycleinUndirectedGraph {
    /**
     * You are given a positive integer n representing the number of nodes in a connected undirected graph containing
     * exactly one cycle. The nodes are numbered from 0 to n - 1 (inclusive).
     *
     * You are also given a 2D integer array edges, where edges[i] = [node1i, node2i] denotes that there is a
     * bidirectional edge connecting node1i and node2i in the graph.
     *
     * The distance between two nodes a and b is defined to be the minimum number of edges that are needed to go from a
     * to b.
     *
     * Return an integer array answer of size n, where answer[i] is the minimum distance between the ith node and any
     * node in the cycle.
     *
     * Input: n = 7, edges = [[1,2],[2,3],[3,4],[4,1],[0,1],[5,2],[6,5]]
     * Output: [1,0,0,0,0,1,2]
     *
     * Constraints:
     *
     * 3 <= n <= 105
     * edges.length == n
     * edges[i].length == 2
     * 0 <= node1i, node2i <= n - 1
     * node1i != node2i
     * The graph is connected.
     * The graph has exactly one cycle.
     * There is at most one edge between any pair of vertices.
     * @param n
     * @param edges
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int[] distanceToCycle(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        int[] status = new int[n]; // 0: unvisited; 1: visiting; 2: visited
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            graph[a].add(b);
            graph[b].add(a);
        }

        // Topological Sort -> find cycle
        List<Integer> path = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            path.clear();
            if (containsCycle(graph, status, -1, i, path)) break;
        }

        int m = path.size();
        int start = path.get(m - 1);
        HashSet<Integer> set = new HashSet<>();
        int i = 0;
        while (i < m - 1 && path.get(i) != path.get(m - 1)) i++;
        for (int j = i; j < m - 1; j++) set.add(path.get(j));

        // bfs
        int[] res = new int[n];
        Queue<Integer> queue = new LinkedList<>();
        for (int x : set) queue.offer(x);

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();

                for (int next : graph[cur]) {
                    if (!set.add(next)) continue;
                    res[next] = step + 1;
                    queue.offer(next);
                }
            }
            if (set.size() == n) break;
            step++;
        }
        return res;
    }

    private boolean containsCycle(List<Integer>[] graph, int[] status, int prev, int cur, List<Integer> path) {
        if (status[cur] == 2) return false; // visited
        if (status[cur] == 1) {  // visiting, find cycle
            path.add(cur);
            return true;
        }

        status[cur] = 1;
        path.add(cur);
        for (int next : graph[cur]) {
            if (next == prev) continue;
            if (containsCycle(graph, status, cur, next, path)) return true;
        }
        path.remove(path.size() - 1);
        status[cur] = 2;
        return false;
    }

    // S2: Topological Sort
    // time = O(n), space = O(n)
    public int[] distanceToCycle2(int n, int[][] edges) {
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        int[] degree = new int[n];
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            graph[a].add(b);
            graph[b].add(a);
            degree[a]++;
            degree[b]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i< n; i++) {
            if (degree[i] == 1) queue.offer(i);
        }

        int[] res = new int[n];
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            res[cur] = -1;

            for (int next : graph[cur]) {
                degree[next]--;
                if (degree[next] == 1) queue.offer(next);
            }
        }

        queue.clear();
        for (int i = 0; i < n; i++) {
            if (res[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : graph[cur]) {
                if (res[next] != -1) continue;
                res[next] = res[cur] + 1;
                queue.offer(next);
            }
        }
        return res;
    }
}
/**
 * 无向图
 * 没有入度和出度之分
 * 度为1
 * 我们首先将所有度为1的点放入队列，按照层级遍历的顺序BFS：
 * 每弹出一个节点A，就考察其邻接节点并将度减去1，如果邻接节点度降为了1，就将其加入队列。
 * 持续BFS直至队列为空。
 * 此时所有未曾访问的点都一定是在环上。
 * 我们再将这些点作为初始点放入队列中，重新开始BFS，就可以得到所有非环节点距离环的最短距离。
 */