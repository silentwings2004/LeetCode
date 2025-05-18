package LC2401_2700;
import java.util.*;
public class LC2445_NumberofNodesWithValueOne {
    /**
     * There is an undirected connected tree with n nodes labeled from 1 to n and n - 1 edges. You are given the integer
     * n. The parent node of a node with a label v is the node with the label floor (v / 2). The root of the tree is
     * the node with the label 1.
     *
     * For example, if n = 7, then the node with the label 3 has the node with the label floor(3 / 2) = 1 as its parent,
     * and the node with the label 7 has the node with the label floor(7 / 2) = 3 as its parent.
     * You are also given an integer array queries. Initially, every node has a value 0 on it. For each query queries[i],
     * you should flip all values in the subtree of the node with the label queries[i].
     *
     * Return the total number of nodes with the value 1 after processing all the queries.
     *
     * Note that:
     *
     * Flipping the value of a node means that the node with the value 0 becomes 1 and vice versa.
     * floor(x) is equivalent to rounding x down to the nearest integer.
     *
     * Input: n = 5 , queries = [1,2,5]
     * Output: 3
     *
     * Input: n = 3, queries = [2,3,3]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * 1 <= queries.length <= 10^5
     * 1 <= queries[i] <= n
     * @param n
     * @param queries
     * @return
     */
    // time = O(n), space = O(n)
    int[] w;
    int n, res = 0;
    public int numberOfNodes(int n, int[] queries) {
        w = new int[n + 1];
        this.n = n;
        for (int x : queries) w[x] = (w[x] + 1) % 2;

        dfs(1);
        return res;
    }

    private void dfs(int u) {
        if (w[u] == 1) res++;

        if (u * 2 <= n) {
            w[u * 2] = (w[u] + w[u * 2]) % 2;
            dfs(u * 2);
        }
        if (u * 2 + 1 <= n) {
            w[u * 2 + 1] = (w[u] + w[u * 2 + 1]) % 2;
            dfs(u * 2 + 1);
        }
    }
}
