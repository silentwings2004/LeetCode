package LC001_300;
import java.util.*;
public class LC148_SortList {
    /**
     * Given the head of a linked list, return the list after sorting it in ascending order.
     *
     * Follow up: Can you sort the linked list in O(n logn) time and O(1) memory (i.e. constant space)?
     *
     * Input: head = [4,2,1,3]
     * Output: [1,2,3,4]
     *
     * Constraints:
     *
     * The number of nodes in the list is in the range [0, 5 * 10^4].
     * -10^5 <= Node.val <= 10^5
     * @param head
     * @return
     */
    // S1
    // time = O(nlogn), space = O(1)
    public ListNode sortList(ListNode head) {
        int n = 0;
        for (ListNode p = head; p != null; p = p.next) n++;

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        for (int i = 1; i < n; i *= 2) {
            ListNode cur = dummy;
            for (int j = 1; j + i <= n; j += i * 2) {
                ListNode p = cur.next, q = p;
                for (int k = 0; k < i; k++) q = q.next;

                int l = 0, r = 0;
                while (l < i && r < i && p != null && q != null) {
                    if (l < i && p.val <= q.val) {
                        cur = cur.next = p;
                        p = p.next;
                        l++;
                    } else {
                        cur = cur.next = q;
                        q = q.next;
                        r++;
                    }
                }

                while (l < i && p != null) {
                    cur = cur.next = p;
                    p = p.next;
                    l++;
                }
                while (r < i && q != null) {
                    cur = cur.next = q;
                    q = q.next;
                    r++;
                }
                cur.next = q;
            }
        }
        return dummy.next;
    }

    // S2
    // time = O(nlogn), space = O(nlogn)
    public ListNode sortList2(ListNode head) {
        // corner case
        if (head == null || head.next == null) return head;

        ListNode mid = getMid(head);
        ListNode next = mid.next;
        mid.next = null;
        return merge(sortList(head), sortList(next));
    }

    private ListNode getMid(ListNode head) {
        ListNode slow = head, fast = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        return prev;
    }

    private ListNode merge(ListNode h1, ListNode h2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (h1 != null && h2 != null) {
            if (h1.val <= h2.val) {
                cur.next = h1;
                h1 = h1.next;
            } else {
                cur.next = h2;
                h2 = h2.next;
            }
            cur = cur.next;
        }
        if (h1 != null) cur.next = h1;
        if (h2 != null) cur.next = h2;
        return dummy.next;
    }
}
