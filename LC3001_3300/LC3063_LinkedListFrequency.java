package LC3001_3300;
import java.util.*;
public class LC3063_LinkedListFrequency {
    /**
     * Given the head of a linked list containing k distinct elements, return the head to a linked list of length k
     * containing the frequency of each distinct element in the given linked list in any order.
     *
     * Input: head = [1,1,1,2,2,3]
     * Output: [3,2,1]
     *
     * Input: head = [1,1,2,2,2]
     * Output: [2,3]
     *
     * Input: head = [6,5,4,3,2,1]
     * Output: [1,1,1,1,1,1]
     *
     * Constraints:
     *
     * The number of nodes in the list is in the range [1, 10^5].
     * 1 <= Node.val <= 10^5
     * @param head
     * @return
     */
    // time = O(n + k), space = O(1)
    public ListNode frequenciesOfElements(ListNode head) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (ListNode p = head; p != null; p = p.next) map.put(p.val, map.getOrDefault(p.val, 0) + 1);
        ListNode dummy = new ListNode(0), p = dummy;
        for (int v : map.values()) {
            ListNode q = new ListNode(v);
            p = p.next = q;
        }
        return dummy.next;
    }
}