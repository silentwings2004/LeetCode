package LC001_300;
import java.util.*;
public class LC143_ReorderList {
    /**
     * You are given the head of a singly linked-list. The list can be represented as:
     *
     * L0 → L1 → … → Ln - 1 → Ln
     * Reorder the list to be on the following form:
     *
     * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
     * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
     *
     * Input: head = [1,2,3,4]
     * Output: [1,4,2,3]
     *
     * Constraints:
     *
     * The number of nodes in the list is in the range [1, 5 * 10^4].
     * 1 <= Node.val <= 1000
     * @param head
     */
    // time = O(n), space = O(1)
    public void reorderList(ListNode head) {
        int n = 0;
        for (ListNode p = head; p != null; p = p.next) n++;
        ListNode a = head;
        for (int i = 0; i < (n + 1) / 2 - 1; i++) a = a.next;
        ListNode b = a.next;
        a.next = null;
        ListNode h1 = head, h2 = reverse(b);
        while (h1 != null && h2 != null) {
            ListNode p = h1.next, q = h2.next;
            h2.next = p;
            h1.next = h2;
            h1 = p;
            h2 = q;
        }
    }

    private ListNode reverse(ListNode h) {
        if (h == null || h.next == null) return h;

        ListNode cur = h, prev = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    // S2
    // time = O(n), space = O(1)
    public void reorderList2(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        int count = 0;
        ListNode p = dummy;
        while (p.next != null) {
            count++;
            p = p.next;
        }

        ListNode q = dummy;
        for (int i = 0; i < (count + 1) / 2; i++) {
            q = q.next;
        }
        ListNode head2 = q.next;
        q.next = null;

        head2 = reverseLinkedList(head2);

        p = head;
        q = head2;
        ListNode h = dummy;
        while (p != null || q != null) {
            if (p != null) {
                h.next = p;
                p = p.next;
                h = h.next;
            }
            if (q != null) {
                h.next = q;
                q = q.next;
                h = h.next;
            }
        }
    }

    private ListNode reverseLinkedList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode cur = head, prev = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}