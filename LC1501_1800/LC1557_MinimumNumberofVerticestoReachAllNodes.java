package LC1501_1800;
import java.util.*;
public class LC1557_MinimumNumberofVerticestoReachAllNodes {
    /**
     * Given a directed acyclic graph, with n vertices numbered from 0 to n-1, and an array edges where edges[i] =
     * [fromi, toi] represents a directed edge from node fromi to node toi.
     *
     * Find the smallest set of vertices from which all nodes in the graph are reachable. It's guaranteed that a unique
     * solution exists.
     *
     * Notice that you can return the vertices in any order.
     *
     * Input: n = 6, edges = [[0,1],[0,2],[2,5],[3,4],[4,2]]
     * Output: [0,3]
     *
     * Input: n = 5, edges = [[0,1],[2,1],[3,1],[1,4],[2,4]]
     * Output: [0,2,3]
     *
     * Constraints:
     *
     * 2 <= n <= 10^5
     * 1 <= edges.length <= min(10^5, n * (n - 1) / 2)
     * edges[i].length == 2
     * 0 <= fromi, toi < n
     * All pairs (fromi, toi) are distinct.
     * @param n
     * @param edges
     * @return
     */
    // time = O(n), space = O(n)
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        int[] d = new int[n];
        for (List<Integer> e : edges) d[e.get(1)]++;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (d[i] == 0) res.add(i);
        }
        return res;
    }
}
/**
 * 图里没有环，因此入度非0的点必然能被其他点所遍历到，找到能走到该点的任何一点
 * 如果这个点的入度依然不为0， 那就继续往前推
 * 这个操作过程最多只能操作n次，如果超过n次的话，那么就表示路径上有n+1个点，那就必然有点会重复，表示有环
 * 矛盾 => 任何一个入度不为0的点必然能被入度为0的点走到，而入度=0的点必须要选 => 只选入度为0的点
 */