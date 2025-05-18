package LC301_600;
import LC001_300.LC106_ConstructBinaryTreefromInorderandPostorderTraversal;

import java.util.*;
public class LC450_DeleteNodeinaBST {
    /**
     * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root
     * node reference (possibly updated) of the BST.
     *
     * Basically, the deletion can be divided into two stages:
     *
     * Search for a node to remove.
     * If the node is found, delete the node.
     * Follow up: Can you solve it with time complexity O(height of tree)?
     *
     * Input: root = [5,3,6,2,4,null,7], key = 3
     * Output: [5,4,6,2,null,null,7]
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [0, 10^4].
     * -10^5 <= Node.val <= 10^5
     * Each node has a unique value.
     * root is a valid binary search tree.
     * -10^5 <= key <= 10^5
     *
     * @param root
     * @param key
     * @return
     */
    // time = O(n), space = O(n)
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return root;

        if (root.val < key) root.right = deleteNode(root.right, key);
        else if (root.val > key) root.left = deleteNode(root.left, key);
        else {
            if (root.right == null) return root.left;
            TreeNode cur = root.right;
            while (cur.left != null) cur = cur.left;
            root.val = cur.val;
            root.right = deleteNode(root.right, cur.val);
        }
        return root;
    }
}
/**
 * 找到n的后继，将后继的值覆盖到u上，然后删除其后继
 */