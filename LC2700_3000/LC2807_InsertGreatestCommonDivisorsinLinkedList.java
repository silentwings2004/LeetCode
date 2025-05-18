package LC2700_3000;

public class LC2807_InsertGreatestCommonDivisorsinLinkedList {
    /**
     * Given the head of a linked list head, in which each node contains an integer value.
     *
     * Between every pair of adjacent nodes, insert a new node with a value equal to the greatest common divisor of them.
     *
     * Return the linked list after insertion.
     *
     * The greatest common divisor of two numbers is the largest positive integer that evenly divides both numbers.
     *
     * Input: head = [18,6,10,3]
     * Output: [18,6,6,2,10,1,3]
     *
     * Input: head = [7]
     * Output: [7]
     *
     * Constraints:
     *
     * The number of nodes in the list is in the range [1, 5000].
     * 1 <= Node.val <= 1000
     * @param head
     * @return
     */
    // time = O(n), space = O(1)
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        if (head == null || head.next == null) return head;
        for (ListNode p = head; p.next != null; p = p.next) {
            int a = p.val, b = p.next.val;
            ListNode x = new ListNode(gcd(a, b));
            x.next = p.next;
            p = p.next = x;
        }
        return head;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
