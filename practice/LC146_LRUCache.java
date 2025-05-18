package practice;
import java.util.*;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: LRUCache
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 146. LRU Cache
 */
public class LC146_LRUCache {
    /**
     * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following
     * operations: get and put.
     *
     * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise
     * return -1.
     * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
     * it should invalidate the least recently used item before inserting a new item.
     *
     * The cache is initialized with a positive capacity.
     *
     * Follow up:
     * Could you do both operations in O(1) time complexity?
     *
     * Example:
     *
     * LRUCache cache = new LRUCache( 2 /* capacity */ /**);
     *
     *cache.put(1,1);
     *cache.put(2,2);
     *cache.get(1);       // returns 1
     *cache.put(3,3);    // evicts key 2
     *cache.get(2);       // returns -1 (not found)
     *cache.put(4,4);    // evicts key 1
     *cache.get(1);       // returns -1 (not found)
     *cache.get(3);       // returns 3
     *cache.get(4);       // returns 4
     * @param capacity
     * @param key
     * @param value
     * @return
     */
    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */
    private Map<Integer, ListNode> map;
    private int size;
    private int capacity;
    private ListNode head, tail;

    public LC146_LRUCache(int capacity) {
        map = new HashMap<>();
        this.size = 0;
        this.capacity = capacity;

        head = new ListNode(); // no constructor defined in the ListNode class, use default one
        tail = new ListNode();

        head.next = tail;
        tail.pre = head;
    }


    public int get(int key) {
        ListNode node = map.get(key);
        // corner case
        if (node == null) return -1;

        // move the accessed node to the head;
        moveNodeToHead(node);
        return node.value;
    }


    public void put(int key, int value) {
        ListNode node = map.get(key);

        if (node == null) {
            ListNode newNode = new ListNode();
            newNode.key = key;
            newNode.value = value;

            map.put(key, newNode);
            addNodeToHead(newNode);
            size++;

            if (size > capacity) {
                // pop the tail
                ListNode tail = popTail();
                map.remove(tail.key);
                size--;
            }
        } else {
            // update the value
            node.value = value;
            moveNodeToHead(node);
        }
    }


    private class ListNode {
        int key;
        int value;
        ListNode pre;
        ListNode next;
    }

    // Always add the new node right after head.
    private void addNodeToHead(ListNode addNode) {
        addNode.pre = head;
        addNode.next = head.next;
        head.next.pre = addNode;
        head.next = addNode;
    }

    // Remove an existing node from the linked list.
    private void removeNode(ListNode deleteNode) {
        ListNode preNode = deleteNode.pre;
        ListNode nextNode = deleteNode.next;

        preNode.next = nextNode;
        nextNode.pre = preNode;

        deleteNode.pre = null;
        deleteNode.next = null;
    }

    // Move certain node in between to the head.
    private void moveNodeToHead(ListNode node) {
        removeNode(node);
        addNodeToHead(node);
    }

    //Pop the current tail.
    private ListNode popTail() {
        ListNode poppedTail = tail.pre;
        removeNode(poppedTail);
        return poppedTail;
    }
}
