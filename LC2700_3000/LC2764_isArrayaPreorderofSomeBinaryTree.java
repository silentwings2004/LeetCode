package LC2700_3000;
import java.util.*;
public class LC2764_isArrayaPreorderofSomeBinaryTree {
    /**
     * Given a 0-indexed integer 2D array nodes, your task is to determine if the given array represents the preorder
     * traversal of some binary tree.
     *
     * For each index i, nodes[i] = [id, parentId], where id is the id of the node at the index i and parentId is the id
     * of its parent in the tree (if the node has no parent, then parentId = -1).
     *
     * Return true if the given array represents the preorder traversal of some tree, and false otherwise.
     *
     * Note: the preorder traversal of a tree is a recursive way to traverse a tree in which we first visit the current
     * node, then we do the preorder traversal for the left child, and finally, we do it for the right child.
     *
     * Input: nodes = [[0,-1],[1,0],[2,0],[3,2],[4,2]]
     * Output: true
     *
     * Input: nodes = [[0,-1],[1,0],[2,0],[3,1],[4,1]]
     * Output: false
     *
     *
     Constraints:

     1 <= nodes.length <= 10^5
     nodes[i].length == 2
     0 <= nodes[i][0] <= 10^5
     -1 <= nodes[i][1] <= 10^5
     The input is generated such that nodes make a binary tree.
     * @param nodes
     * @return
     */
    // time = O(n), space = O(n)
    int[][] tr;
    int u = 0;
    public boolean isPreorder(List<List<Integer>> nodes) {
        int n = nodes.size();
        tr = new int[n + 1][2];
        for (int i = 0; i < n + 1; i++) tr[i] = new int[]{-1, -1};
        int root = 0;
        for (List<Integer> node : nodes) {
            int a = node.get(0), b = node.get(1);
            if (b == -1) {
                root = a;
                continue;
            }
            if (tr[b][0] == -1) tr[b][0] = a;
            else tr[b][1] = a;
        }

        return dfs(root, nodes);
    }

    private boolean dfs(int node, List<List<Integer>> nodes) {
        if (u == nodes.size()) return true;
        if (node == -1) return true;

        if (node != nodes.get(u).get(0)) return false;
        u++;
        if (!dfs(tr[node][0], nodes)) return false;
        return dfs(tr[node][1], nodes);
    }

    // S2: stack
    // time = O(n), space = O(n)
    public boolean isPreorder2(List<List<Integer>> nodes) {
        int n = nodes.size(), tt = 0;
        int[] stk = new int[n + 1];
        stk[++tt] = nodes.get(0).get(0);
        for (int i = 1; i < n; i++) {
            int a = nodes.get(i).get(0);
            int b = nodes.get(i).get(1);
            while (tt > 0 && stk[tt] != b) tt--;
            if (tt == 0) return false;
            stk[++tt] = a;
        }
        return true;
    }
}