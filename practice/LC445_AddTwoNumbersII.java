package practice;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: AddTwoNumbersII
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 445. Add Two Numbers II
 */
public class LC445_AddTwoNumbersII {
    /**
     * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes
     * first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
     *
     * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     *
     * Follow up:
     * What if you cannot modify the input lists? In other words, reversing the lists is not allowed.
     *
     * Example:
     *
     * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
     * Output: 7 -> 8 -> 0 -> 7
     * @param l1
     * @param l2
     * @return
     */
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    // Two Stacks
    // time = O(max(m, n)), space = O(m + n)
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // corner case
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        int carry = 0;
        ListNode head = null;

        while (!s1.empty() || !s2.empty() || carry > 0) {
            // 注意当carry > 0时依然需要继续循环，比如[5],[5]这样的case --> [1,0]而不是[0]
            int sum = carry;
            sum += s1.empty() ? 0 : s1.pop();
            sum += s2.empty() ? 0 : s2.pop();
            carry = sum / 10;
            ListNode node = new ListNode(sum % 10);
            node.next = head;
            head = node;
        }
        return head;
    }
}
