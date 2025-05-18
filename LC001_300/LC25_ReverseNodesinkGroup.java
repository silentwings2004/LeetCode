package LC001_300;
import java.util.*;
public class LC25_ReverseNodesinkGroup {
    /**
     * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
     *
     * k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is
     * not a multiple of k then left-out nodes, in the end, should remain as it is.
     *
     * Follow up:
     *
     * Could you solve the problem in O(1) extra memory space?
     * You may not alter the values in the list's nodes, only nodes itself may be changed.
     *
     * Input: head = [1,2,3,4,5], k = 2
     * Output: [2,1,4,3,5]
     *
     * Constraints:
     *
     * The number of nodes in the list is in the range sz.
     * 1 <= sz <= 5000
     * 0 <= Node.val <= 1000
     * 1 <= k <= sz
     * @param head
     * @param k
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public ListNode reverseKGroup(ListNode head, int k) {
        // corner case
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy; // 每次要翻转的链表的头结点的上一个结点
        ListNode end = dummy; // 每次要翻转的链表的尾结点

        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) end = end.next;
            if (end == null) break; // if end == null, 要翻转的链表结点数 < k => 不执行翻转，直接返回

            ListNode next = end.next; // 先记录下end.next，方便后面连接链表
            end.next = null; // 断开end之后，即链表断开
            ListNode start = pre.next; // 记录下要翻转的链表的头结点
            pre.next = reverse(start);
            start.next = next; // 原来要翻转的头结点变成尾结点，连接上下一个要翻转链表的头结点，即next
            pre = start; // 当前链表翻转结束，start与end统统指向下一个要翻转链表的头结点的前一个结点，即start
            end = start;
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode head) {
        // corner case
        if (head == null || head.next == null) return head;

        ListNode prev = null, cur = head, next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    // S2
    // time = O(n), space = O(1)
    public ListNode reverseKGroup2(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        for (ListNode p = dummy;;) {
            ListNode q = p;
            for (int i = 0; i < k && q != null; i++) q = q.next;
            if (q == null) break;

            ListNode a = p.next, b = a.next;
            for (int i = 0; i < k - 1; i++) {
                ListNode c = b.next;
                b.next = a;
                a = b;
                b = c;
            }

            ListNode c = p.next;
            p.next = a;
            c.next = b;
            p = c;
        }
        return dummy.next;
    }
}
