package LC601_900;

public class LC876_MiddleoftheLinkedList {
    /**
     * Given the head of a singly linked list, return the middle node of the linked list.
     *
     * If there are two middle nodes, return the second middle node.
     *
     * Input: head = [1,2,3,4,5]
     * Output: [3,4,5]
     *
     * Constraints:
     *
     * The number of nodes in the list is in the range [1, 100].
     * 1 <= Node.val <= 100
     * @param head
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) return head;
        int n = 0;
        for (ListNode p = head; p != null; p = p.next) n++;
        n /= 2;
        ListNode p = head;
        for (int i = 1; i <= n; i++) p = p.next;
        return p;
    }

    // S2
    // time = O(n), space = O(1)
    public ListNode middleNode2(ListNode head) {
        ListNode p = head, q = head;
        while (q != null && q.next != null) {
            p = p.next;
            q = q.next.next;
        }
        return p;
    }
}
