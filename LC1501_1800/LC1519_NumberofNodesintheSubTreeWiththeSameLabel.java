package LC1501_1800;
import java.util.*;
public class LC1519_NumberofNodesintheSubTreeWiththeSameLabel {
    /**
     * You are given a tree (i.e. a connected, undirected graph that has no cycles) consisting of n nodes numbered from
     * 0 to n - 1 and exactly n - 1 edges. The root of the tree is the node 0, and each node of the tree has a label
     * which is a lower-case character given in the string labels (i.e. The node with the number i has the label
     * labels[i]).
     *
     * The edges array is given on the form edges[i] = [ai, bi], which means there is an edge between nodes ai and bi
     * in the tree.
     *
     * Return an array of size n where ans[i] is the number of nodes in the subtree of the ith node which have the same
     * label as node i.
     *
     * A subtree of a tree T is the tree consisting of a node in T and all of its descendant nodes.
     *
     * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], labels = "abaedcd"
     * Output: [2,1,1,1,1,1,1]
     *
     * Input: n = 4, edges = [[0,1],[1,2],[0,3]], labels = "bbbb"
     * Output: [4,2,1,1]
     *
     * Input: n = 5, edges = [[0,1],[0,2],[1,3],[0,4]], labels = "aabab"
     * Output: [3,2,1,1,1]
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * edges.length == n - 1
     * edges[i].length == 2
     * 0 <= ai, bi < n
     * ai != bi
     * labels.length == n
     * labels is consisting of only of lowercase English letters.
     * @param n
     * @param edges
     * @param labels
     * @return
     */
    // time = O(n), space = O(n)
    List<Integer>[] graph;
    int[][] cnt;
    String s;
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] x : edges) {
            int a = x[0], b = x[1];
            graph[a].add(b);
            graph[b].add(a);
        }

        cnt = new int[n][26];
        s = labels;
        dfs(0, -1);

        int[] res = new int[n];
        for (int i = 0; i < n; i++) res[i] = cnt[i][s.charAt(i) - 'a'];
        return res;
    }

    private void dfs(int u, int fa) {
        cnt[u][s.charAt(u) - 'a']++;

        for (int next : graph[u]) {
            if (next == fa) continue;
            dfs(next, u);
            for (int i = 0; i < 26; i++) cnt[u][i] += cnt[next][i];
        }
    }
}