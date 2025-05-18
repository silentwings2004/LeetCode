package LC3301_3600;
import java.sql.Array;
import java.util.*;
public class LC3331_FindSubtreeSizesAfterChanges {
    /**
     * You are given a tree rooted at node 0 that consists of n nodes numbered from 0 to n - 1. The tree is represented
     * by an array parent of size n, where parent[i] is the parent of node i. Since node 0 is the root, parent[0] == -1.
     *
     * You are also given a string s of length n, where s[i] is the character assigned to node i.
     *
     * We make the following changes on the tree one time simultaneously for all nodes x from 1 to n - 1:
     *
     * Find the closest node y to node x such that y is an ancestor of x, and s[x] == s[y].
     * If node y does not exist, do nothing.
     * Otherwise, remove the edge between x and its current parent and make node y the new parent of x by adding an
     * between them.
     * Return an array answer of size n where answer[i] is the size of the subtree rooted at node i in the final tree.
     *
     * A subtree of treeName is a tree consisting of a node in treeName and all of its descendants.
     *
     * Input: parent = [-1,0,0,1,1,1], s = "abaabc"
     * Output: [6,3,1,1,1,1]
     *
     * Input: parent = [-1,0,4,0,1], s = "abbba"
     * Output: [5,2,1,1,1]
     *
     * Constraints:
     *
     * n == parent.length == s.length
     * 1 <= n <= 10^5
     * 0 <= parent[i] <= n - 1 for all i >= 1.
     * parent[0] == -1
     * parent represents a valid tree.
     * s consists only of lowercase English letters.
     * @param parent
     * @param s
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    List<Integer>[] adj;
    int[] res;
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        int[] fa = parent.clone();
        for (int i = 1; i < n; i++) {
            int j = parent[i];
            while (j != -1 && s.charAt(j) != s.charAt(i)) j = parent[j];
            if (j != -1) fa[i] = j;
        }

        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) adj[fa[i]].add(i);

        res = new int[n];
        dfs(0);
        return res;
    }

    private int dfs(int u) {
        res[u] = 1;
        for (int v : adj[u]) res[u] += dfs(v);
        return res[u];
    }

    // S2
    // time = O(n + 26), space = O(n)
    public int[] findSubtreeSizes2(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int i = 1; i < n; i++) adj[parent[i]].add(i);

        int[] sz = new int[n], fa = new int[26];
        Arrays.fill(fa, -1);
        dfs(adj, 0, s, fa, sz);
        return sz;
    }

    private void dfs(List<Integer>[] adj, int u, String s, int[] fa, int[] sz) {
        sz[u] = 1;
        int x = s.charAt(u) - 'a';
        int old = fa[x];
        fa[x] = u;
        for (int v : adj[u]) {
            dfs(adj, v, s, fa, sz);
            int anc = fa[s.charAt(v) - 'a'];
            sz[anc < 0 ? u : anc] += sz[v];
        }
        fa[x] = old;
    }
}