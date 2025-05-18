package LC901_1200;
import java.util.*;
public class LC1171_RemoveZeroSumConsecutiveNodesfromLinkedList {
    /**
     * Given the head of a linked list, we repeatedly delete consecutive sequences of nodes that sum to 0 until there
     * are no such sequences.
     *
     * After doing so, return the head of the final linked list.  You may return any such answer.
     *
     * (Note that in the examples below, all sequences are serializations of ListNode objects.)
     *
     * Input: head = [1,2,-3,3,1]
     * Output: [3,1]
     *
     * Input: head = [1,2,3,-3,4]
     * Output: [1,2,4]
     *
     * Input: head = [1,2,3,-3,-2]
     * Output: [1]
     *
     * Constraints:
     *
     * The given linked list will contain between 1 and 1000 nodes.
     * Each node in the linked list has -1000 <= node.val <= 1000.
     * @param head
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public ListNode removeZeroSumSublists(ListNode head) {
        HashMap<Integer, ListNode> map = new HashMap<>();
        ListNode dummy = new ListNode(0, head);
        map.put(0, dummy);
        int sum = 0;
        for (ListNode p = head; p != null; p = p.next) {
            sum += p.val;
            if (map.containsKey(sum)) {
                int cur = sum;
                for (ListNode q = map.get(sum).next; q != p; q = q.next) {
                    cur += q.val;
                    map.remove(cur);
                }
                map.get(sum).next = p.next;
            } else map.put(sum, p);
        }
        return dummy.next;
    }

    // S2
    // time = O(n), space = O(n)
    public ListNode removeZeroSumSublists2(ListNode head) {
        HashMap<Integer, ListNode> map = new HashMap<>();
        ListNode dummy = new ListNode(0, head);
        map.put(0, dummy);
        int sum = 0;
        for (ListNode p = head; p != null; p = p.next) {
            sum += p.val;
            map.put(sum, p);
        }
        sum = 0;
        for (ListNode p = dummy; p != null; p = p.next) {
            sum += p.val;
            p.next = map.get(sum).next;
        }
        return dummy.next;
    }
}
/**
 * 枚举区间的右端点
 */