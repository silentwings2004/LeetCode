package LC601_900;
import java.util.*;
public class LC785_IsGraphBipartite {
    /**
     * Given an undirected graph, return true if and only if it is bipartite.
     *
     * Recall that a graph is bipartite if we can split its set of nodes into two independent subsets A and B, such
     * that every edge in the graph has one node in A and another node in B.
     *
     * The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i
     * and j exists. Each node is an integer between 0 and graph.length - 1. There are no self edges or parallel edges:
     * graph[i] does not contain i, and it doesn't contain any element twice.
     *
     * Input: graph = [[1,3],[0,2],[1,3],[0,2]]
     * Output: true
     *
     * Constraints:
     *
     * 1 <= graph.length <= 100
     * 0 <= graph[i].length < 100
     * 0 <= graph[i][j] <= graph.length - 1
     * graph[i][j] != i
     * All the values of graph[i] are unique.
     * The graph is guaranteed to be undirected.
     *
     * @param graph
     * @return
     */
    // time = O(n), space = O(n)
    public boolean isBipartite(int[][] graph) {
        // corner case
        // 注意：graph.length == 0 || graph[0].length == 0在这里不能作为corner case来直接return true / false
        // 参考：[[],[3],[],[1],[]]
        if (graph == null || graph[0] == null) return false;

        int n = graph.length;
        int[] visited = new int[n];

        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i);
                visited[i] = 1;
                while (!queue.isEmpty()) {
                    int cur = queue.poll();
                    int curColor = visited[cur];
                    int neiColor = curColor == 1 ? 2 : 1;
                    for (int nei : graph[cur]) {
                        if (visited[nei] == 0) {
                            visited[nei] = neiColor;
                            queue.offer(nei);
                        } else {
                            if (visited[nei] != neiColor) return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
