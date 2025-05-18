package LC301_600;

public class LC328_OddEvenLinkedList {
    /**
     * Given the head of a singly linked list, group all the nodes with odd indices together followed by the nodes with
     * even indices, and return the reordered list.
     *
     * The first node is considered odd, and the second node is even, and so on.
     *
     * Note that the relative order inside both the even and odd groups should remain as it was in the input.
     *
     * You must solve the problem in O(1) extra space complexity and O(n) time complexity.
     *
     * Input: head = [1,2,3,4,5]
     * Output: [1,3,5,2,4]
     *
     * Constraints:
     *
     * n == number of nodes in the linked list
     * 0 <= n <= 10^4
     * -10^6 <= Node.val <= 10^6
     * @param head
     * @return
     */
    // time = O(n), space = O(1)
    public ListNode oddEvenList(ListNode head) {
        // corner case
        if (head == null || head.next == null) return head;

        ListNode oh = head, ot = oh;
        ListNode eh = head.next, et = eh;
        for (ListNode p = head.next.next; p != null;) {
            ot.next = p;
            ot = ot.next;
            p = p.next;
            if (p != null) {
                et.next = p;
                et = et.next;
                p = p.next;
            }
        }
        ot.next = eh;
        et.next = null;
        return oh;
    }
}
