package practice;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: MergeTwoSortedLists
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 21. Merge Two Sorted Lists
 */
public class LC21_MergeTwoSortedLists {
    /**
     * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the
     * nodes of the first two lists.
     *
     * Example:
     *
     * Input: 1->2->4, 1->3->4
     * Output: 1->1->2->3->4->4
     * @param l1
     * @param l2
     * @return
     */
    // S1: dummy node
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // corner case
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) cur.next = l1;
        if (l2 != null) cur.next = l2;
        return dummy.next;
    }

    // S2: Recursion
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        // corner case
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
