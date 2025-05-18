package LC001_300;
import java.util.*;
public class LC83_RemoveDuplicatesfromList {
    /**
     * Given the head of a sorted linked list, delete all duplicates such that each element appears only once.
     * Return the linked list sorted as well.
     *
     * Input: head = [1,1,2]
     * Output: [1,2]
     *
     * Constraints:
     *
     * The number of nodes in the list is in the range [0, 300].
     * -100 <= Node.val <= 100
     * The list is guaranteed to be sorted in ascending order.
     * @param head
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode p = head;
        while (p != null) {
            ListNode q = p.next;
            while (q != null && q.val == p.val) q = q.next;
            p.next = q;
            p = q;
        }
        return head;
    }

    // S2
    // time = O(n), space = O(1)
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode cur = head;
        for (ListNode p = head.next; p != null; p = p.next) {
            if (p.val != cur.val) {
                cur = cur.next = p;
            }
        }
        cur.next = null;
        return head;
    }
}