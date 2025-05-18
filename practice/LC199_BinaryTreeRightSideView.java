package practice;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: BinaryTreeRightSideView
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 199. Binary Tree Right Side View
 */
public class LC199_BinaryTreeRightSideView {
    /**
     * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can
     * see ordered from top to bottom.
     *
     * Example:
     *
     * Input: [1,2,3,null,5,null,4]
     * Output: [1, 3, 4]
     * Explanation:
     *
     *    1            <---
     *  /   \
     * 2     3         <---
     *  \     \
     *   5     4       <---
     * @param root
     * @return
     */
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */
    // BFS
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode node = null;
            while (size-- > 0) {
                TreeNode cur =  queue.poll();
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right!= null) queue.offer(cur.right);
                node = cur;
            }
            res.add(node.val);
        }
        return res;
    }
}
