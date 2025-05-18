package LC2401_2700;
import java.util.*;
public class LC2508_AddEdgestoMakeDegreesofAllNodesEven {
    /**
     * There is an undirected graph consisting of n nodes numbered from 1 to n. You are given the integer n and a 2D
     * array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi. The graph can be disconnected.
     *
     * You can add at most two additional edges (possibly none) to this graph so that there are no repeated edges and no self-loops.
     *
     * Return true if it is possible to make the degree of each node in the graph even, otherwise return false.
     *
     * The degree of a node is the number of edges connected to it.
     *
     * Input: n = 5, edges = [[1,2],[2,3],[3,4],[4,2],[1,4],[2,5]]
     * Output: true
     *
     * Input: n = 4, edges = [[1,2],[3,4]]
     * Output: true
     *
     * Input: n = 4, edges = [[1,2],[1,3],[1,4]]
     * Output: false
     *
     * Constraints:
     *
     * 3 <= n <= 10^5
     * 2 <= edges.length <= 10^5
     * edges[i].length == 2
     * 1 <= ai, bi <= n
     * ai != bi
     * There are no repeated edges.
     * @param n
     * @param edges
     * @return
     */
    // time = O(n), space = O(n)
    public boolean isPossible(int n, List<List<Integer>> edges) {
        int[] indegree = new int[n];
        HashSet<Integer>[] graph = new HashSet[n];
        for (int i = 0; i < n; i++) graph[i] = new HashSet<>();
        for (List<Integer> e : edges) {
            int a = e.get(0) - 1, b = e.get(1) - 1;
            indegree[a]++;
            indegree[b]++;
            graph[a].add(b);
            graph[b].add(a);
        }

        List<Integer> q = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] % 2 == 1) q.add(i);
        }
        if (q.size() == 0) return true;
        if (q.size() % 2 == 1 || q.size() > 4) return false;
        if (q.size() == 2) {
            int a = q.get(0), b = q.get(1);
            if (!graph[a].contains(b)) return true;
            else {
                for (int i = 0; i < n; i++) {
                    if (i == a || i == b) continue;
                    if (!graph[a].contains(i) && !graph[b].contains(i)) return true;
                }
                return false;
            }
        } else {
            int a = q.get(0), b = q.get(1), c = q.get(2), d = q.get(3);
            if (!graph[a].contains(b) && !graph[c].contains(d) || !graph[a].contains(c) && !graph[b].contains(d) ||
            !graph[a].contains(d) && !graph[b].contains(c)) return true;
            return false;
        }
    }
}
