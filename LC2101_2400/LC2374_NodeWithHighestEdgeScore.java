package LC2101_2400;

public class LC2374_NodeWithHighestEdgeScore {
    /**
     * You are given a directed graph with n nodes labeled from 0 to n - 1, where each node has exactly one outgoing edge.
     *
     * The graph is represented by a given 0-indexed integer array edges of length n, where edges[i] indicates that
     * there is a directed edge from node i to node edges[i].
     *
     * The edge score of a node i is defined as the sum of the labels of all the nodes that have an edge pointing to i.
     *
     * Return the node with the highest edge score. If multiple nodes have the same edge score, return the node with the
     * smallest index.
     *
     * Input: edges = [1,0,0,0,0,7,7,5]
     * Output: 7
     *
     * Constraints:
     *
     * n == edges.length
     * 2 <= n <= 10^5
     * 0 <= edges[i] < n
     * edges[i] != i
     * @param edges
     * @return
     */
    // time = O(n), space = O(n)
    public int edgeScore(int[] edges) {
        int n = edges.length;
        long[] w = new long[n];
        for (int i = 0; i < n; i++) w[edges[i]] += i;

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (w[i] > w[res]) res = i;
        }
        return res;
    }
}
