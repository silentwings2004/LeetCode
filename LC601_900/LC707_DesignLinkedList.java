package LC601_900;

public class LC707_DesignLinkedList {
    /**
     * Design your implementation of the linked list. You can choose to use a singly or doubly linked list.
     * A node in a singly linked list should have two attributes: val and next. val is the value of the current node,
     * and next is a pointer/reference to the next node.
     * If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node
     * in the linked list. Assume all nodes in the linked list are 0-indexed.
     *
     * Implement the MyLinkedList class:
     *
     * MyLinkedList() Initializes the MyLinkedList object.
     * int get(int index) Get the value of the indexth node in the linked list. If the index is invalid, return -1.
     * void addAtHead(int val) Add a node of value val before the first element of the linked list. After the insertion,
     * the new node will be the first node of the linked list.
     * void addAtTail(int val) Append a node of value val as the last element of the linked list.
     * void addAtIndex(int index, int val) Add a node of value val before the indexth node in the linked list. If index
     * equals the length of the linked list, the node will be appended to the end of the linked list. If index is
     * greater than the length, the node will not be inserted.
     * void deleteAtIndex(int index) Delete the indexth node in the linked list, if the index is valid.
     *
     * Input
     * ["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
     * [[], [1], [3], [1, 2], [1], [1], [1]]
     * Output
     * [null, null, null, null, 2, null, 3]
     *
     *
     Constraints:

     0 <= index, val <= 1000
     Please do not use the built-in LinkedList library.
     At most 2000 calls will be made to get, addAtHead, addAtTail, addAtIndex and deleteAtIndex.
     */
    /** Initialize your data structure here. */
    Node head;
    public LC707_DesignLinkedList() {
        head = null;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    // time = O(n), space = O(1)
    public int get(int index) {
        Node p = head;
        for (int i = 0; i < index && p != null; i++) p = p.next;
        return p == null ? -1 : p.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be
     *  the first node of the linked list. */
    // time = O(1), space = O(1)
    public void addAtHead(int val) {
        Node cur = new Node(val);
        cur.next = head;
        head = cur;
    }

    /** Append a node of value val to the last element of the linked list. */
    // time = O(1), space = O(1)
    public void addAtTail(int val) {
        if (head == null) head = new Node(val);
        else {
            Node p = head;
            while (p.next != null) p = p.next;
            p.next = new Node(val);
        }
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    // time = O(n), space = O(1)
    public void addAtIndex(int index, int val) {
        if (index == 0) addAtHead(val);
        else {
            int len = 0;
            for (Node p = head; p != null; p = p.next) len++;
            if (index == len) addAtTail(val);
            else if (index < len) {
                Node p = head;
                for (int i = 0; i < index - 1; i++) p = p.next;
                Node cur = new Node(val);
                cur.next = p.next;
                p.next = cur;
            }
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    // time = O(n), space = O(1)
    public void deleteAtIndex(int index) {
        int len = 0;
        for (Node p = head; p != null; p = p.next) len++;
        if (index >= 0 && index < len) {
            if (index == 0) head = head.next;
            else {
                Node p = head;
                for (int i = 0; i < index - 1; i++) p = p.next;
                p.next = p.next.next;
            }
        }
    }

    private class Node {
        private int val;
        private Node next;
        public Node(int val) {
            this.val = val;
        }
    }
}
