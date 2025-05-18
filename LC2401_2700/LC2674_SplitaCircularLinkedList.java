package LC2401_2700;

public class LC2674_SplitaCircularLinkedList {
    /**
     * Given a circular linked list list of positive integers, your task is to split it into 2 circular linked lists so
     * that the first one contains the first half of the nodes in list (exactly ceil(list.length / 2) nodes) in the same
     * order they appeared in list, and the second one contains the rest of the nodes in list in the same order they
     * appeared in list.
     *
     * Return an array answer of length 2 in which the first element is a circular linked list representing the first
     * half and the second element is a circular linked list representing the second half.
     *
     * A circular linked list is a normal linked list with the only difference being that the last node's next node, is
     * the first node.
     *
     * Input: nums = [1,5,7]
     * Output: [[1,5],[7]]
     *
     * Input: nums = [2,6,1,5]
     * Output: [[2,6],[1,5]]
     *
     * Constraints:
     *
     * The number of nodes in list is in the range [2, 10^5]
     * 0 <= Node.val <= 10^9
     * LastNode.next = FirstNode where LastNode is the last node of the list and FirstNode is the first one
     * @param list
     * @return
     */
    // time = O(n), space = O(1)
    public ListNode[] splitCircularLinkedList(ListNode list) {
        ListNode[] res = new ListNode[2];
        res[0] = list;
        int n = 1;
        ListNode p;
        for (p = list; p.next != list; p = p.next) n++;
        p.next = null;
        p = list;
        for (int i = 0; i < n - 1; i++) {
            if (i == (n - 1) / 2) {
                res[1] = p.next;
                p.next = res[0];
                p = res[1];
            } else p = p.next;
        }
        p.next = res[1];
        return res;
    }
}