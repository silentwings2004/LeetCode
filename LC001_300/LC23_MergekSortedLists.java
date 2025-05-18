package LC001_300;
import java.util.*;
public class LC23_MergekSortedLists {
    /**
     * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
     *
     * Merge all the linked-lists into one sorted linked-list and return it.
     *
     * Input: lists = [[1,4,5],[1,3,4],[2,6]]
     * Output: [1,1,2,3,4,4,5,6]
     *
     * Constraints:
     *
     * k == lists.length
     * 0 <= k <= 10^4
     * 0 <= lists[i].length <= 500
     * -10^4 <= lists[i][j] <= 10^4
     * lists[i] is sorted in ascending order.
     * The sum of lists[i].length won't exceed 10^4.
     *
     * @param lists
     * @return
     */
    // time = O(nlogk), space = O(k)  k: the number of linked lists, n: total number of nodes
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return partition(lists, 0, lists.length - 1);
    }

    private ListNode partition(ListNode[] h, int l, int r) {
        if (l >= r) return h[r];

        int mid = l + r >> 1;
        ListNode h1 = partition(h, l, mid);
        ListNode h2 = partition(h, mid + 1, r);

        return merge(h1, h2);
    }

    private ListNode merge(ListNode h1, ListNode h2) {
        ListNode dummy = new ListNode(-1), cur = dummy;
        while (h1 != null && h2 != null) {
            if (h1.val < h2.val) {
                cur.next = h1;
                h1 = h1.next;
            } else {
                cur.next = h2;
                h2 = h2.next;
            }
            cur = cur.next;
        }
        if (h1 != null) cur.next = h1;
        if (h2 != null) cur.next = h2;
        return dummy.next;
    }

    // S2: heap
    // time = O(nlogk), space = O(k) n: total number of nodes
    public ListNode mergeKLists2(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        for (ListNode x : lists) {
            if (x != null) pq.offer(x);
        }

        ListNode dummy = new ListNode(-1), cur = dummy;
        while (!pq.isEmpty()) {
            ListNode t = pq.poll();
            cur = cur.next = t;
            if (t.next != null) pq.offer(t.next);
        }
        return dummy.next;
    }
}
/**
 * 用堆来找最小值 O(nk) -> O(nlogk)
 */