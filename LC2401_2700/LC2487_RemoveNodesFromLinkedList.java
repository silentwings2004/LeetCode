package LC2401_2700;

public class LC2487_RemoveNodesFromLinkedList {
    /**
     * You are given the head of a linked list.
     *
     * Remove every node which has a node with a strictly greater value anywhere to the right side of it.
     *
     * Return the head of the modified linked list.
     *
     * Input: head = [5,2,13,3,8]
     * Output: [13,8]
     *
     * Input: head = [1,1,1,1]
     * Output: [1,1,1,1]
     *
     * Constraints:
     *
     * The number of the nodes in the given list is in the range [1, 10^5].
     * 1 <= Node.val <= 10^5
     * @param head
     * @return
     */
    // time = O(n), space = O(n)
    final int N = 100010;
    public ListNode removeNodes(ListNode head) {
        ListNode[] stk = new ListNode[N];
        int tt = 0;
        for (ListNode p = head; p != null; p = p.next) {
            while (tt > 0 && stk[tt].val < p.val) tt--;
            stk[++tt] = p;
        }

        ListNode dummy = new ListNode(0), p = dummy;
        for (int i = 1; i <= tt; i++) p = p.next = stk[i];
        return dummy.next;
    }
}