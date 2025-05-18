package LC2101_2400;
import java.util.*;
public class LC2378_ChooseEdgestoMaximizeScoreinaTree {
    /**
     * You are given a weighted tree consisting of n nodes numbered from 0 to n - 1.
     *
     * The tree is rooted at node 0 and represented with a 2D array edges of size n where edges[i] = [pari, weighti]
     * indicates that node pari is the parent of node i, and the edge between them has a weight equal to weighti. Since
     * the root does not have a parent, you have edges[0] = [-1, -1].
     *
     * Choose some edges from the tree such that no two chosen edges are adjacent and the sum of the weights of the
     * chosen edges is maximized.
     *
     * Return the maximum sum of the chosen edges.
     *
     * Note:
     *
     * You are allowed to not choose any edges in the tree, the sum of weights in this case will be 0.
     * Two edges Edge1 and Edge2 in the tree are adjacent if they have a common node.
     * In other words, they are adjacent if Edge1 connects nodes a and b and Edge2 connects nodes b and c.
     *
     * Input: edges = [[-1,-1],[0,5],[0,10],[2,6],[2,4]]
     * Output: 11
     *
     * Input: edges = [[-1,-1],[0,5],[0,-6],[0,7]]
     * Output: 7
     *
     * Constraints:
     *
     * n == edges.length
     * 1 <= n <= 10^5
     * edges[i].length == 2
     * par0 == weight0 == -1
     * 0 <= pari <= n - 1 for all i >= 1.
     * pari != i
     * -106 <= weighti <= 106 for all i >= 1.
     * edges represents a valid tree.
     * @param edges
     * @return
     */
    // time = O(n), space = O(n)
    List<int[]>[] graph;
    public long maxScore(int[][] edges) {
        int n = edges.length;
        graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            graph[edges[i][0]].add(new int[]{i, edges[i][1]});
        }

        long[] res = dfs(0);
        return Math.max(res[0], res[1]);
    }

    private long[] dfs(int u) {
        long[] res = new long[2];
        for (int[] x : graph[u]) {
            int next = x[0], w = x[1];
            long[] y = dfs(next);
            res[0] += y[1]; // skip current edge,but choose all edges from children to next
            res[1] = Math.max(res[1], w + y[0] - y[1]);
        }
        // pick current edge to this child, w + no children to next picked + all children to their next is picked
        // except current node
        res[1] += res[0];
        return res;
    }
}
