package LC1501_1800;

public class LC1791_FindCenterofStarGraph {
    /**
     * There is an undirected star graph consisting of n nodes labeled from 1 to n. A star graph is a graph where there
     * is one center node and exactly n - 1 edges that connect the center node with every other node.
     *
     * You are given a 2D integer array edges where each edges[i] = [ui, vi] indicates that there is an edge between
     * the nodes ui and vi. Return the center of the given star graph.
     *
     * Input: edges = [[1,2],[2,3],[4,2]]
     * Output: 2
     *
     * Input: edges = [[1,2],[5,1],[1,3],[1,4]]
     * Output: 1
     *
     * Constraints:
     *
     * 3 <= n <= 10^5
     * edges.length == n - 1
     * edges[i].length == 2
     * 1 <= ui, vi <= n
     * ui != vi
     * The given edges represent a valid star graph.
     * @param edges
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int findCenter(int[][] edges) {
        int n = edges.length + 1, res = 0;
        int[] d = new int[n + 1];
        for (int[] e : edges) {
            d[e[0]]++;
            d[e[1]]++;
            if (d[e[0]] > 1 || d[e[1]] > 1) {
                res = d[e[0]] > 1 ? e[0] : e[1];
                break;
            }
        }
        return res;
    }

    // S2
    // time = O(n), space = O(n)
    public int findCenter2(int[][] edges) {
        int[] a = edges[0], b = edges[1];
        if (a[0] == b[0] || a[0] == b[1]) return a[0];
        return a[1];
    }
}