package LC1201_1500;
import java.util.*;
public class LC1273_DeleteTreeNodes {
    /**
     * A tree rooted at node 0 is given as follows:
     *
     * The number of nodes is nodes;
     * The value of the ith node is value[i];
     * The parent of the ith node is parent[i].
     * Remove every subtree whose sum of values of nodes is zero.
     *
     * Return the number of the remaining nodes in the tree.
     *
     * Input: nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-1]
     * Output: 2
     *
     * Input: nodes = 7, parent = [-1,0,0,1,2,2,2], value = [1,-2,4,0,-2,-1,-2]
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= nodes <= 10^4
     * parent.length == nodes
     * 0 <= parent[i] <= nodes - 1
     * parent[0] == -1 which indicates that 0 is the root.
     * value.length == nodes
     * -10^5 <= value[i] <= 10^5
     * The given input is guaranteed to represent a valid tree.
     * @param nodes
     * @param parent
     * @param value
     * @return
     */
    // time = O(n), space = O(n)
    int res;
    int[] v;
    List<Integer>[] g;
    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        res = nodes;
        v = value;
        g = new List[nodes];
        for (int i = 0; i < nodes; i++) g[i] = new ArrayList<>();
        for (int i = 0; i < nodes; i++) {
            if (parent[i] != -1) g[parent[i]].add(i);
        }
        dfs(0);
        return res;
    }

    private int[] dfs(int u) {
        int cnt = 1, s = v[u];
        for (int j : g[u]) {
            int[] t = dfs(j);
            s += t[0];
            cnt += t[1];
        }
        if (s == 0) {
            res -= cnt;
            return new int[]{0, 0};
        }
        return new int[]{s, cnt};
    }
}