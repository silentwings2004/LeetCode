package LC601_900;

public class LC641_DesignCircularDeque {
    /**
     * Design your implementation of the circular double-ended queue (deque).
     *
     * Implement the MyCircularDeque class:
     *
     * MyCircularDeque(int k) Initializes the deque with a maximum size of k.
     * boolean insertFront() Adds an item at the front of Deque. Returns true if the operation is successful, or false
     * otherwise.
     * boolean insertLast() Adds an item at the rear of Deque. Returns true if the operation is successful, or false
     * otherwise.
     * boolean deleteFront() Deletes an item from the front of Deque. Returns true if the operation is successful,
     * or false otherwise.
     * boolean deleteLast() Deletes an item from the rear of Deque. Returns true if the operation is successful, or
     * false otherwise.
     * int getFront() Returns the front item from the Deque. Returns -1 if the deque is empty.
     * int getRear() Returns the last item from Deque. Returns -1 if the deque is empty.
     * boolean isEmpty() Returns true if the deque is empty, or false otherwise.
     * boolean isFull() Returns true if the deque is full, or false otherwise.
     *
     * Input
     * ["MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull", "deleteLast",
     * "insertFront", "getFront"]
     * [[3], [1], [2], [3], [4], [], [], [], [4], []]
     * Output
     * [null, true, true, true, false, 2, true, true, true, 4]
     *
     * Constraints:
     *
     * 1 <= k <= 1000
     * 0 <= value <= 1000
     * At most 2000 calls will be made to insertFront, insertLast, deleteFront, deleteLast, getFront, getRear, isEmpty,
     * isFull.
     */
    /** Initialize your data structure here. Set the size of the deque to be k. */
    // time = O(1), space = O(1)
    private ListNode head, tail;
    private int size;
    private int k;
    /** Initialize your data structure here. Set the size of the deque to be k. */
    public LC641_DesignCircularDeque(int k) {
        head = new ListNode(-1);
        tail = new ListNode(-1);
        head.prev = tail;
        tail.next = head;
        this.size = 0;
        this.k = k;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (size == k) return false;

        ListNode node = new ListNode(value);
        node.next = head;
        node.prev = head.prev;
        head.prev.next = node;
        head.prev = node;
        size++;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (size == k) return false;

        ListNode node = new ListNode(value);
        node.prev = tail;
        node.next = tail.next;
        tail.next.prev = node;
        tail.next = node;
        size++;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (size == 0) return false;

        head.prev.prev.next = head;
        head.prev = head.prev.prev;
        size--;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (size == 0) return false;

        tail.next.next.prev = tail;
        tail.next = tail.next.next;
        size--;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        return head.prev.val;
    }

    /** Get the last item from the deque. */
    public int getRear() {
        return tail.next.val;
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return size == k;
    }

    private class ListNode {
        private ListNode prev, next;
        private int val;
        public ListNode(int val) {
            this.val = val;
        }
    }

    // S2
    class MyCircularDeque {
        // time = O(1), space = O(k)
        int[] q;
        int hh, tt;
        public MyCircularDeque(int k) {
            q = new int[k + 1];
            hh = 0;
            tt = 0;
        }

        public boolean insertFront(int value) {
            if (isFull()) return false;
            hh = get(hh - 1);
            q[hh] = value;
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) return false;
            q[tt++] = value;
            tt = get(tt);
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) return false;
            hh = get(hh + 1);
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) return false;
            tt = get(tt - 1);
            return true;
        }

        public int getFront() {
            if (isEmpty()) return -1;
            return q[hh];
        }

        public int getRear() {
            if (isEmpty()) return -1;
            return q[get(tt - 1)];
        }

        public boolean isEmpty() {
            return hh == tt;
        }

        public boolean isFull() {
            return get(hh - 1) == tt;
        }

        private int get(int x) {
            return (x + q.length) % q.length;
        }
    }
}
/**
 * [h, t]
 * 1. 队列为空 => h == t
 * 2. 队列已满 => t == h - 1
 * 在取模意义下相等
 * 3. [h, t)
 */