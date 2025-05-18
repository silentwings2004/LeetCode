package LC001_300;
import java.util.*;
public class LC203_RemoveLinkedListElements {
    /**
     * Given the head of a linked list and an integer val, remove all the nodes of the linked list that has
     * Node.val == val, and return the new head.
     *
     * Input: head = [1,2,6,3,4,5,6], val = 6
     * Output: [1,2,3,4,5]
     *
     * Constraints:
     *
     * The number of nodes in the list is in the range [0, 10^4].
     * 1 <= Node.val <= 50
     * 0 <= k <= 50
     * @param head
     * @param val
     * @return
     */
    // time = O(n), space = O(1)
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return head;

        ListNode dummy = new ListNode(-1), p = dummy;
        for (ListNode q = head; q != null; q = q.next) {
            if (q.val == val) continue;
            p = p.next = q;
        }
        p.next = null;
        return dummy.next;
    }
}