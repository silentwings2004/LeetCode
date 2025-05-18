package LC001_300;
import java.util.*;
public class LC138_CopyListwithRandomPointer {
    /**
     * A linked list of length n is given such that each node contains an additional random pointer, which could point
     * to any node in the list, or null.
     *
     * Construct a deep copy of the list.
     *
     * Input: head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
     * Output: [[7,null],[13,0],[11,4],[10,2],[1,0]]
     *
     * Constraints:
     *
     * 0 <= n <= 1000
     * -10000 <= Node.val <= 10000
     * Node.random is null or is pointing to some node in the linked list.
     *
     * @param head
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public Node copyRandomList(Node head) {
        if (head == null) return head;

        HashMap<Node, Node> map = new HashMap<>();
        Node dummy = new Node(-1), cur = dummy;

        for (Node p = head; p != null; p = p.next) {
            map.putIfAbsent(p, new Node(p.val));
            cur.next = map.get(p);
            if (p.random != null) {
                map.putIfAbsent(p.random, new Node(p.random.val));
                cur.next.random = map.get(p.random);
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    // S2
    // time = O(n), space = O(n)
    public Node copyRandomList2(Node head) {
        for (Node p = head; p != null; p = p.next.next) {
            Node q = new Node(p.val);
            q.next = p.next;
            p.next = q;
        }

        for (Node p = head; p != null; p = p.next.next) {
            if (p.random != null) p.next.random = p.random.next;
        }

        Node dummy = new Node(-1), cur = dummy;
        for (Node p = head; p != null; p = p.next) {
            Node q = p.next;
            cur = cur.next = q;
            p.next = q.next;
        }
        return dummy.next;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}
/**
 * 每个点的clone点都放在它后面
 * 这样同样可以起到哈希表的作用, .next可以找到克隆点
 * 先复制random边: p.next.random = q.next
 */