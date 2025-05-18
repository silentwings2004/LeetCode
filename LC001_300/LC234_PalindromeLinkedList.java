package LC001_300;
import java.util.*;
public class LC234_PalindromeLinkedList {
    /**
     * Given the head of a singly linked list, return true if it is a palindrome.
     *
     * Input: head = [1,2,2,1]
     * Output: true
     *
     * Constraints:
     *
     * The number of nodes in the list is in the range [1, 10^5].
     * 0 <= Node.val <= 9
     *
     *
     * Follow up: Could you do it in O(n) time and O(1) space?
     * @param head
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public boolean isPalindrome(ListNode head) {
        // corner case
        if (head == null || head.next == null) return true;

        ListNode mid = findMid(head);
        mid.next = reverse(mid.next);

        for (ListNode p = head, q = mid.next; p != null && q != null; p = p.next, q = q.next) {
            if (p.val != q.val) return false;
        }
        return true;
    }

    private ListNode findMid(ListNode head) {
        ListNode s = head, f = head.next;
        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
        }
        return s;
    }

    private ListNode reverse(ListNode head) {
        // corner case
        if (head == null || head.next == null) return head;

        ListNode cur = head, prev = null, next = null;
        while (cur != null) { // 1 2 2 1
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    // S2
    // time = O(n), space = O(1)
    public boolean isPalindrome2(ListNode head) {
        int n = 0;
        for (ListNode p = head; p != null; p = p.next) n++;
        if (n <= 1) return true;

        int half = n / 2;
        ListNode a = head;
        for (int i = 0; i < n - half; i++) a = a.next;
        ListNode b = a.next;
        for (int i = 0; i < half - 1; i++) {
            ListNode c = b.next;
            b.next = a;
            a = b;
            b = c;
        }

        ListNode p = head, q = a;
        boolean success = true;
        for (int i = 0; i < half; i++) {
            if (p.val != q.val) {
                success = false;
                break;
            }
            p = p.next;
            q = q.next;
        }
        // 将链表恢复原状
        ListNode tail = a;
        b = a.next;
        for (int i = 0; i < half - 1; i++) {
            ListNode c = b.next;
            b.next = a;
            a = b;
            b = c;
        }
        tail.next = null;
        return success;
    }
}
/**
 * 把后面一半翻转下即可，判断下是否前一半后一半相等
 */