package LC001_300;
import java.util.*;
public class LC21_MergeTwoSortedLists {
    /**
     * Merge two sorted linked lists and return it as a sorted list. The list should be made by splicing together
     * the nodes of the first two lists.
     *
     * Input: list1 = null, list2 = 0->3->3->null
     * Output: 0->3->3->null
     *
     * Input:  list1 =  1->3->8->11->15->null, list2 = 2->null
     * Output: 1->2->3->8->11->15->null
     *
     * Constraints:
     *
     * The number of nodes in both lists is in the range [0, 50].
     * -100 <= Node.val <= 100
     * Both l1 and l2 are sorted in non-decreasing order.
     *
     * @param list1
     * @param list2
     * @return
     */
    // time = O(n), space = O(1)
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1), cur = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        if (list1 != null) cur.next = list1;
        if (list2 != null) cur.next = list2;
        return dummy.next;
    }
}
/**
 * 经典的二路归并思想
 * 凡是需要特判头结点或者需要改变头结点的 => 建立一个虚拟头结点
 */