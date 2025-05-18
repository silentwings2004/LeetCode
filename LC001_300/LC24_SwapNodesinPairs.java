package LC001_300;
import java.util.*;
public class LC24_SwapNodesinPairs {
    /**
     * Given a linked list, swap every two adjacent nodes and return its head.
     *
     * You may not modify the values in the list's nodes. Only nodes itself may be changed.
     *
     * Input: head = [1,2,3,4]
     * Output: [2,1,4,3]
     *
     * 1 -> 2 -> 3 -> 4 -> null
     * 2 -> 1 -> 4 -> 3
     *
     * @param head
     * @return
     */
    // S1: recursion
    // time = O(n), space = O(n)
    public ListNode swapPairs(ListNode head) {
        // corner case
        if (head == null || head.next == null) return head;

        ListNode subHead = swapPairs(head.next.next);
        ListNode newHead = head.next;
        head.next.next = head;
        head.next = subHead;
        return newHead;
    }

    // S2: iteration --> 最优解！！！
    // time = O(n), space = O(1)
    public ListNode swapPairs2(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        for (ListNode p = dummy; p.next != null && p.next.next != null;) {
            ListNode a = p.next, b = p.next.next;
            p.next = b;
            a.next = b.next;
            b.next = a;
            p = a;
        }
        return dummy.next;
    }
}
/**
 *  dummy -> 1 -><- 2     3 -> 4 -> null
 *   prev  cur       next
 *   dummy -> 2 -> 1 ->  3 - > 4 -> null
 *                 prev cur
 */

// 最优解：用dummy node来实现成对分批次swap的操作，借用一个prev和cur指针来操作，其中用来swap的两个node分别是cur与cur.next。
// 在swap过程中，由于新的cur要接到老的cur的头部，所以需要获取cur之前的一个node，这里让prev去指向，同时原来的cur去指向新的cur之前
// 后面的一个node，完成后再断开新cur的next指向，转而指向老的cur，最后prev和cur集体一起向前移动到下一对node。
