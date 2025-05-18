package LC3001_3300;
import java.util.*;
public class LC3294_ConvertDoublyLinkedListtoArrayII {
    /**
     * You are given an arbitrary node from a doubly linked list, which contains nodes that have a next pointer and a
     * previous pointer.
     *
     * Return an integer array which contains the elements of the linked list in order.
     *
     * Input: head = [1,2,3,4,5], node = 5
     * Output: [1,2,3,4,5]
     *
     * Input: head = [4,5,6,7,8], node = 8
     * Output: [4,5,6,7,8]
     *
     * Constraints:
     *
     * The number of nodes in the given list is in the range [1, 500].
     * 1 <= Node.val <= 1000
     * All nodes have unique Node.val.
     * @param node
     * @return
     */
    // time = O(n), space = O(n)
    public int[] toArray(Node node) {
        List<Integer> q = new LinkedList<>();
        Node p = node;
        while (p != null) {
            q.add(0, p.val);
            p = p.prev;
        }
        p = node.next;
        while (p != null) {
            q.add(p.val);
            p = p.next;
        }
        int[] res = new int[q.size()];
        for (int i = 0; i < q.size(); i++) res[i] = q.get(i);
        return res;
    }

    class Node {
        public int val;
        public Node prev;
        public Node next;
    }
}
