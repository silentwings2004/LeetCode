package LC1201_1500;
import java.util.*;
public class LC1367_LinkedListinBinaryTree {
    /**
     * Given a binary tree root and a linked list with head as the first node.
     *
     * Return True if all the elements in the linked list starting from the head correspond to some downward path
     * connected in the binary tree otherwise return False.
     *
     * In this context downward path means a path that starts at some node and goes downwards.
     *
     * Input: head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
     * Output: true
     *
     * Input: head = [1,4,2,6], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
     * Output: true
     *
     * Input: head = [1,4,2,6,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
     * Output: false
     *
     * Constraints:
     *
     * The number of nodes in the tree will be in the range [1, 2500].
     * The number of nodes in the list will be in the range [1, 100].
     * 1 <= Node.val <= 100 for each node in the linked list and binary tree.
     * @param head
     * @param root
     * @return
     */
    // time = O(n), space = O(n)
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (dfs(head, root)) return true;
        if (root == null) return false;
        return isSubPath(head, root.left) || isSubPath(head, root.right);
    }

    private boolean dfs(ListNode p, TreeNode node) {
        if (p == null) return true;
        if (node == null) return false;

        if (node.val != p.val) return false;
        return dfs(p.next, node.left) || dfs(p.next, node.right);
    }
}