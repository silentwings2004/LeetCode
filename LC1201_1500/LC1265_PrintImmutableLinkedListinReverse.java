package LC1201_1500;
import java.util.*;
public class LC1265_PrintImmutableLinkedListinReverse {
    /**
     * You are given an immutable linked list, print out all values of each node in reverse with the help of the
     * following interface:
     *
     * ImmutableListNode: An interface of immutable linked list, you are given the head of the list.
     * You need to use the following functions to access the linked list (you can't access the ImmutableListNode
     * directly):
     *
     * ImmutableListNode.printValue(): Print value of the current node.
     * ImmutableListNode.getNext(): Return the next node.
     * The input is only given to initialize the linked list internally. You must solve this problem without modifying
     * the linked list. In other words, you must operate the linked list using only the mentioned APIs.
     *
     * Input: head = [1,2,3,4]
     * Output: [4,3,2,1]
     *
     * Input: head = [0,-4,-1,3,-5]
     * Output: [-5,3,-1,-4,0]
     *
     * Input: head = [-2,0,6,4,4,-6]
     * Output: [-6,4,4,6,0,-2]
     * @param head
     */
    // S1
    // time = O(n), space = O(n)
    public void printLinkedListInReverse(ImmutableListNode head) {
        if (head == null) return;
        printLinkedListInReverse(head.getNext());
        head.printValue();
    }

    // S2
    // time = O(n), space = O(n)
    public void printLinkedListInReverse2(ImmutableListNode head) {
        List<ImmutableListNode> q = new ArrayList<>();
        while (head != null) {
            q.add(head);
            head = head.getNext();
        }

        for (int i = q.size() - 1; i >= 0; i--) q.get(i).printValue();;
    }

    // S3
    // time = O(n^2), space = O(1)
    public void printLinkedListInReverse3(ImmutableListNode head) {
        ImmutableListNode p = head, q = head;
        while (p.getNext() != null) p = p.getNext();
        p.printValue();
        while (q != p) {
            while (q.getNext() != p) q = q.getNext();
            p = q;
            q = head;
            p.printValue();
        }
    }

    interface ImmutableListNode {
        public void printValue(); // print the value of this node.
        public ImmutableListNode getNext(); // return the next node.
    }
}