package LC3001_3300;
import java.util.*;
public class LC3249_CounttheNumberofGoodNodes {
    /**
     * There is an undirected tree with n nodes labeled from 0 to n - 1, and rooted at node 0. You are given a 2D
     * integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai
     * and bi in the tree.
     *
     * A node is good if all the subtrees rooted at its children have the same size.
     *
     * Return the number of good nodes in the given tree.
     *
     * A subtree of treeName is a tree consisting of a node in treeName and all of its descendants.
     *
     * Input: edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]]
     * Output: 7
     *
     * Input: edges = [[0,1],[1,2],[2,3],[3,4],[0,5],[1,6],[2,7],[3,8]]
     * Output: 6
     *
     * Input: edges = [[0,1],[1,2],[1,3],[1,4],[0,5],[5,6],[6,7],[7,8],[0,9],[9,10],[9,12],[10,11]]
     * Output: 12
     *
     * Constraints:
     *
     * 2 <= n <= 10^5
     * edges.length == n - 1
     * edges[i].length == 2
     * 0 <= ai, bi < n
     * The input is generated such that edges represents a valid tree.
     * @param edges
     * @return
     */
    // time = O(n), space = O(n)
    List<Integer>[] adj;
    int res;
    public int countGoodNodes(int[][] edges) {
        int n = edges.length + 1;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            adj[a].add(b);
            adj[b].add(a);
        }
        dfs(0, -1);
        return res;
    }

    private int dfs(int u, int fa) {
        HashSet<Integer> set = new HashSet<>();
        int cnt = 1;
        for (int v : adj[u]) {
            if (v == fa) continue;
            int t = dfs(v, u);
            set.add(t);
            cnt += t;
        }
        if (set.size() <= 1) res++;
        return cnt;
    }
}