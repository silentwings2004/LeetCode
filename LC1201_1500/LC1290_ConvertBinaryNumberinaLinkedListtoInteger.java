package LC1201_1500;

public class LC1290_ConvertBinaryNumberinaLinkedListtoInteger {
    /**
     * Given head which is a reference node to a singly-linked list. The value of each node in the linked list is
     * either 0 or 1. The linked list holds the binary representation of a number.
     *
     * Return the decimal value of the number in the linked list.
     *
     * Input: head = [1,0,1]
     * Output: 5
     *
     * Constraints:
     *
     * The Linked List is not empty.
     * Number of nodes will not exceed 30.
     * Each node's value is either 0 or 1.
     * @param head
     * @return
     */
    // time = O(n), space = O(1)
    public int getDecimalValue(ListNode head) {
        int res = 0;
        for (ListNode p = head; p != null; p = p.next) {
            res = res * 2 + p.val;
        }
        return res;
    }
}