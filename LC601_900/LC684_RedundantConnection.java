package LC601_900;
import java.util.*;
public class LC684_RedundantConnection {
    /**
     * In this problem, a tree is an undirected graph that is connected and has no cycles.
     *
     * You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added.
     * The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph
     * is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between
     * nodes ai and bi in the graph.
     *
     * Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple
     * answers, return the answer that occurs last in the input.
     *
     * Input: edges = [[1,2],[1,3],[2,3]]
     * Output: [2,3]
     *
     * Constraints:
     *
     * n == edges.length
     * 3 <= n <= 1000
     * edges[i].length == 2
     * 1 <= ai < bi <= edges.length
     * ai != bi
     * There are no repeated edges.
     * The given graph is connected.
     * @param edges
     * @return
     */
    // time = O(nlogn), space = O(n)
    int[] p;
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        p = new int[n + 1];
        for (int i = 1; i <= n; i++) p[i] = i;
        for (int[] e : edges) {
            int a = find(e[0]), b = find(e[1]);
            if (a != b) p[a] = b;
            else return e;
        }
        return new int[0];
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}
/**
 * Union Find模板题
 * 考察每条边，怎么判断放入后这条边变成环？放入前2端点已经是连通的了。
 * 如何快速判断两点是否连通？=> Union Find
 * 1-2
 *   |
 * 3-4
 */
