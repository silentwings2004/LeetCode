package LC301_600;
import java.util.*;
public class LC445_AddTwoNumbersII {
    /**
     * You are given two non-empty linked lists representing two non-negative integers. The most significant digit
     * comes first and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked
     * list.
     *
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     *
     * Input: l1 = [7,2,4,3], l2 = [5,6,4]
     * Output: [7,8,0,7]
     *
     * Constraints:
     *
     * The number of nodes in each linked list is in the range [1, 100].
     * 0 <= Node.val <= 9
     * It is guaranteed that the list represents a number that does not have leading zeros.
     *
     *
     * Follow up: Could you solve it without reversing the input lists?
     * @param l1
     * @param l2
     * @return
     */
    // S1: reverse
    // time = O(n), space = O(1)
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);
        int t = 0;
        ListNode dummy = new ListNode(0);

        while (l1 != null || l2 != null || t != 0) {
            if (l1 != null) {
                t += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                t += l2.val;
                l2 = l2.next;
            }
            ListNode cur = new ListNode(t % 10);
            t /= 10;
            cur.next = dummy.next;
            dummy.next = cur;
        }
        return dummy.next;
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode cur = head, prev = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    // S2: stack
    // time = O(n), space = O(n)
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        Stack<Integer> stk1 = new Stack<>();
        Stack<Integer> stk2 = new Stack<>();

        while (l1 != null) {
            stk1.push(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            stk2.push(l2.val);
            l2 = l2.next;
        }

        int t = 0;
        ListNode dummy = new ListNode(0);
        while (!stk1.isEmpty() || !stk2.isEmpty()) {
            if (!stk1.isEmpty()) t += stk1.pop();
            if (!stk2.isEmpty()) t += stk2.pop();
            ListNode node = new ListNode(t % 10);
            t /= 10;
            node.next = dummy.next;
            dummy.next = node;
        }
        return dummy.next;
    }
}
