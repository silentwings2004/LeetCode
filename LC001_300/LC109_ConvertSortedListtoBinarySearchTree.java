package LC001_300;
import java.util.*;
public class LC109_ConvertSortedListtoBinarySearchTree {
    /**
     * Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height
     * balanced BST.
     *
     * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two
     * subtrees of every node never differ by more than 1.
     *
     * Input: head = [-10,-3,0,5,9]
     * Output: [0,-3,9,-10,null,5]
     *
     * Constraints:
     *
     * The number of nodes in head is in the range [0, 2 * 10^4].
     * -10^5 <= Node.val <= 10^5
     * @param head
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;

        int n = 0;
        for (ListNode p = head; p != null; p = p.next) n++;
        if (n == 1) return new TreeNode(head.val);

        ListNode cur = head;
        for (int i = 0; i < n / 2 - 1; i++) cur = cur.next; // 保持左边不空，比右边多 [(n - 1) / 2] 上取整取中点
        TreeNode root = new TreeNode(cur.next.val);
        root.right = sortedListToBST(cur.next.next);
        cur.next = null;
        root.left = sortedListToBST(head);
        return root;
    }
}
/**
 * 取中点 => O(n)
 * 开一个数组变成上一题 => 空间多O(n)
 *
 * 不用额外空间，每次遍历一遍找中点
 * O(n/2)
 * logn层 => time = O(nlogn)
 */