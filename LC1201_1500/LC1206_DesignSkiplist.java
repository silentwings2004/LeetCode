package LC1201_1500;
import java.util.*;
public class LC1206_DesignSkiplist {
    /**
     * Design a Skiplist without using any built-in libraries.
     *
     * A skiplist is a data structure that takes O(log(n)) time to add, erase and search. Comparing with treap and
     * red-black tree which has the same function and performance, the code length of Skiplist can be comparatively
     * short and the idea behind Skiplists is just simple linked lists.
     *
     * For example, we have a Skiplist containing [30,40,50,60,70,90] and we want to add 80 and 45 into it. The Skiplist
     * works this way:
     *
     * You can see there are many layers in the Skiplist. Each layer is a sorted linked list. With the help of the top
     * layers, add, erase and search can be faster than O(n). It can be proven that the average time complexity for
     * each operation is O(log(n)) and space complexity is O(n).
     *
     * See more about Skiplist: https://en.wikipedia.org/wiki/Skip_list
     *
     * Implement the Skiplist class:
     *
     * Skiplist() Initializes the object of the skiplist.
     * bool search(int target) Returns true if the integer target exists in the Skiplist or false otherwise.
     * void add(int num) Inserts the value num into the SkipList.
     * bool erase(int num) Removes the value num from the Skiplist and returns true. If num does not exist in the
     * Skiplist, do nothing and return false. If there exist multiple num values, removing any one of them is fine.
     * Note that duplicates may exist in the Skiplist, your code needs to handle this situation.
     *
     * Input
     * ["Skiplist", "add", "add", "add", "search", "add", "search", "erase", "erase", "search"]
     * [[], [1], [2], [3], [0], [4], [1], [0], [1], [1]]
     * Output
     * [null, null, null, null, false, null, true, false, true, false]
     *
     * Constraints:
     *
     * 0 <= num, target <= 2 * 10^4
     * At most 5 * 10^4 calls will be made to search, add, and erase.
     */
    // time = O(logn), space = O(n)
    final int level = 8;
    Node head;
    Random random;
    public LC1206_DesignSkiplist() {
        head = new Node(-1);
        random = new Random();
    }

    public boolean search(int target) {
        Node[] pre = new Node[level];
        find(target, pre);
        Node p = pre[0].next[0];
        return p != null && p.val == target;
    }

    public void add(int num) {
        Node[] pre = new Node[level];
        find(num, pre);
        Node p = new Node(num);
        for (int i = 0; i < level; i++) {
            p.next[i] = pre[i].next[i];
            pre[i].next[i] = p;
            if (random.nextInt() % 2 == 0) break; // 有50%的概率不会到上一层！
        }
    }

    public boolean erase(int num) {
        Node[] pre = new Node[level];
        find(num, pre);
        Node p = pre[0].next[0];
        if (p == null || p.val != num) return false;
        for (int i = 0; i < level && pre[i].next[i] == p; i++) {
            pre[i].next[i] = p.next[i];
        }
        return true;
    }

    private void find(int t, Node[] pre) {
        Node p = head;
        for (int i = level - 1; i >= 0; i--) {
            while (p.next[i] != null && p.next[i].val < t) p = p.next[i];
            pre[i] = p;
        }
    }

    private class Node {
        private int val;
        private Node[] next;
        public Node(int val) {
            this.val = val;
            this.next = new Node[level];
        }
    }
}
/**
 * 每层中找到 < T 的最大元素
 * 从上往下找
 * 每找一层，大概是将整个区间/2
 * O(logn)
 * 查找只要看最后一层
 */