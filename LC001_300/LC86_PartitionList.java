package LC001_300;
import java.util.*;
public class LC86_PartitionList {
    /**
     * Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes
     * greater than or equal to x.
     *
     * You should preserve the original relative order of the nodes in each of the two partitions.
     *
     * Input: head = [1,4,3,2,5,2], x = 3
     * Output: [1,2,2,4,3,5]
     *
     * Constraints:
     *
     * The number of nodes in the list is in the range [0, 200].
     * -100 <= Node.val <= 100
     * -200 <= x <= 200
     * @param head
     * @param x
     * @return
     */
    // time = O(n), space = O(1)
    public ListNode partition(ListNode head, int x) {
        ListNode h1 = new ListNode(-1), p1 = h1;
        ListNode h2 = new ListNode(-1), p2 = h2;

        for (ListNode p = head; p != null; p = p.next) {
            if (p.val < x) p1 = p1.next = p;
            else p2 = p2.next = p;
        }
        p1.next = h2.next;
        p2.next = null;
        return h1.next;
    }
}