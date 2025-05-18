package LC3001_3300;
import java.util.*;
public class LC3263_ConvertDoublyLinkedListtoArrayI {
    /**
     * You are given the head of a doubly linked list, which contains nodes that have a next pointer and a previous
     * pointer.
     *
     * Return an integer array which contains the elements of the linked list in order.
     *
     * Input: head = [1,2,3,4,3,2,1]
     * Output: [1,2,3,4,3,2,1]
     *
     * Input: head = [2,2,2,2,2]
     * Output: [2,2,2,2,2]
     *
     * Input: head = [3,2,3,2,3,2]
     * Output: [3,2,3,2,3,2]
     *
     * Constraints:
     *
     * The number of nodes in the given list is in the range [1, 50].
     * 1 <= Node.val <= 50
     * @param head
     * @return
     */
    // time = O(n), space = O(n)
    public int[] toArray(Node head) {
        List<Integer> q = new ArrayList<>();
        for (Node p = head; p != null; p = p.next) q.add(p.val);
        int[] res = new int[q.size()];
        for (int i = 0; i < q.size(); i++) res[i] = q.get(i);
        return res;
    }

    // Definition for a Node.
    class Node {
        public int val;
        public Node prev;
        public Node next;
    }
}