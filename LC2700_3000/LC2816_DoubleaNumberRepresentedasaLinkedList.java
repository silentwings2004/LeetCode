package LC2700_3000;

public class LC2816_DoubleaNumberRepresentedasaLinkedList {
    /**
     * You are given the head of a non-empty linked list representing a non-negative integer without leading zeroes.
     *
     * Return the head of the linked list after doubling it.
     *
     * Input: head = [1,8,9]
     * Output: [3,7,8]
     *
     * Input: head = [9,9,9]
     * Output: [1,9,9,8]
     *
     * Constraints:
     *
     * The number of nodes in the list is in the range [1, 104]
     * 0 <= Node.val <= 9
     * The input is generated such that the list represents a number that does not have leading zeros, except the
     * number 0 itself.
     * @param head
     * @return
     */
    // time = O(n), space = O(1)
    public ListNode doubleIt(ListNode head) {
        ListNode h = reverse(head);
        ListNode p;
        int t = 0;
        for (p = h; p.next != null; p = p.next) {
            t += p.val * 2;
            p.val = t % 10;
            t /= 10;
        }
        t += p.val * 2;
        p.val = t % 10;
        t /= 10;
        if (t > 0) p.next = new ListNode(t);
        return reverse(h);
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
}