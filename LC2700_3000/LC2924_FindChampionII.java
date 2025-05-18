package LC2700_3000;

public class LC2924_FindChampionII {
    /**
     * There are n teams numbered from 0 to n - 1 in a tournament; each team is also a node in a DAG.
     *
     * You are given the integer n and a 0-indexed 2D integer array edges of length m representing the DAG, where
     * edges[i] = [ui, vi] indicates that there is a directed edge from team ui to team vi in the graph.
     *
     * A directed edge from a to b in the graph means that team a is stronger than team b and team b is weaker than team
     * a.
     *
     * Team a will be the champion of the tournament if there is no team b that is stronger than team a.
     *
     * Return the team that will be the champion of the tournament if there is a unique champion, otherwise, return -1.
     *
     * Notes
     *
     * A cycle is a series of nodes a1, a2, ..., an, an+1 such that node a1 is the same node as node an+1, the nodes
     * a1, a2, ..., an are distinct, and there is a directed edge from the node ai to node ai+1 for every i in the range
     * [1, n].
     * A DAG is a directed graph that does not have any cycle.
     *
     * Input: n = 3, edges = [[0,1],[1,2]]
     * Output: 0
     *
     * Input: n = 4, edges = [[0,2],[1,3],[1,2]]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= n <= 100
     * m == edges.length
     * 0 <= m <= n * (n - 1) / 2
     * edges[i].length == 2
     * 0 <= edge[i][j] <= n - 1
     * edges[i][0] != edges[i][1]
     * The input is generated such that if team a is stronger than team b, team b is not stronger than team a.
     * The input is generated such that if team a is stronger than team b and team b is stronger than team c, then team
     * a is stronger than team c.
     * @param n
     * @param edges
     * @return
     */
    // time = O(n^2), space = O(n)
    public int findChampion(int n, int[][] edges) {
        int[] d = new int[n];
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            d[b]++;
        }
        int res = -1, cnt = 0;
        for (int i = 0; i < n; i++) {
            if (d[i] == 0) {
                if (cnt == 0) res = i;
                cnt++;
                if (cnt > 1) return -1;
            }
        }
        return res;
    }
}