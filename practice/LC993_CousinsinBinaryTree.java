package practice;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: CousinsinBinaryTree
 * Creater: Silentwings
 * Date: May, 2020
 * Description: 993. Cousins in Binary Tree
 */
public class LC993_CousinsinBinaryTree {
    /**
     * In a binary tree, the root node is at depth 0, and children of each depth k node are at depth k+1.
     *
     * Two nodes of a binary tree are cousins if they have the same depth, but have different parents.
     *
     * We are given the root of a binary tree with unique values, and the values x and y of two different nodes in the
     * tree.
     *
     * Return true if and only if the nodes corresponding to the values x and y are cousins.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: root = [1,2,3,4], x = 4, y = 3
     * Output: false
     *
     * Example 2:
     *
     *
     * Input: root = [1,2,3,null,4,null,5], x = 5, y = 4
     * Output: true
     *
     * Example 3:
     *
     *
     *
     * Input: root = [1,2,3,null,4], x = 2, y = 3
     * Output: false
     *
     *
     * Note:
     *
     * The number of nodes in the tree will be between 2 and 100.
     * Each node has a unique integer value from 1 to 100.
     * @param root
     * @param x
     * @param y
     * @return
     */
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */
    // S: DFS
    // time = O(n), space = O(n) n是给定树中节点的数量
    public boolean isCousins(TreeNode root, int x, int y) {
        // corner case
        if (root == null) return false;
        if (root.val == x || root.val == y) return false;

        HashMap<Integer, Integer> depthMap = new HashMap<>();
        HashMap<Integer, TreeNode> parentMap = new HashMap<>();
        dfs(root, null, depthMap, parentMap);
        return depthMap.get(x) == depthMap.get(y) && parentMap.get(x) != parentMap.get(y);
    }

    private void dfs(TreeNode root, TreeNode parent, HashMap<Integer, Integer> depthMap,
                     HashMap<Integer, TreeNode> parentMap) {
        if (root != null) {
            int depth = parent != null ? 1 + depthMap.get(parent.val) : 0;
            depthMap.put(root.val, depth);
            parentMap.put(root.val, parent);
            dfs(root.left, root, depthMap, parentMap);
            dfs(root.right, root, depthMap, parentMap);
        }
    }
}
