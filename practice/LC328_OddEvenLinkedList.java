package practice;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: OddEvenLinkedList
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 328. Odd Even Linked List
 */
public class LC328_OddEvenLinkedList {
    /**
     * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are
     * talking about the node number and not the value in the nodes.
     *
     * You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.
     *
     * Example 1:
     *
     * Input: 1->2->3->4->5->NULL
     * Output: 1->3->5->2->4->NULL
     *
     * Example 2:
     *
     * Input: 2->1->3->5->6->4->7->NULL
     * Output: 2->3->6->7->1->5->4->NULL
     * Note:
     *
     * The relative order inside both the even and odd groups should remain as it was in the input.
     * The first node is considered odd, the second node even and so on ...
     * @param head
     * @return
     */
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    // time = O(n), space = O(1)
    public ListNode oddEvenList(ListNode head) {
        // corner case
        if (head == null || head.next == null) return head;

        ListNode h1 = head, h2 = head.next;
        ListNode cur1 = h1, cur2 = h2;
        while (cur1 != null && cur1.next != null && cur2 != null && cur2.next != null) {
            cur1.next = cur1.next.next;
            cur2.next = cur2.next.next;
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        if (cur1 == null) cur1 = h2;
        else cur1.next = h2;
        if (cur2 != null) cur2.next = null;
        return h1;
    }
}
