package LC901_1200;
import java.util.*;
public class LC1019_NextGreaterNodeInLinkedList {
    /**
     * You are given the head of a linked list with n nodes.
     *
     * For each node in the list, find the value of the next greater node. That is, for each node, find the value of the
     * first node that is next to it and has a strictly larger value than it.
     *
     * Return an integer array answer where answer[i] is the value of the next greater node of the ith node (1-indexed).
     * If the ith node does not have a next greater node, set answer[i] = 0.
     *
     * Input: head = [2,1,5]
     * Output: [5,5,0]
     *
     * Constraints:
     *
     * The number of nodes in the list is n.
     * 1 <= n <= 10^4
     * 1 <= Node.val <= 10^9
     * @param head
     * @return
     */
    // time = O(n), space = O(n)
    final int N = 10010;
    public int[] nextLargerNodes(ListNode head) {
        int[] stk = new int[N];
        int tt = 0;
        List<Integer> q = new ArrayList<>();
        for (ListNode p = head; p != null; p = p.next) q.add(p.val);
        for (int i = q.size() - 1; i >= 0; i--) {
            int x = q.get(i);
            while (tt > 0 && stk[tt] <= x) tt--;
            if (tt > 0) q.set(i, stk[tt]);
            else q.set(i, 0);
            stk[++tt] = x;
        }
        int[] res = new int[q.size()];
        for (int i = 0; i < q.size(); i++) res[i] = q.get(i);
        return res;
    }
}
/**
 * 最基本的单调栈问题
 * next greater / next smaller / prev greater / prev smaller
 * 维护一个单调递减的栈
 * 可能需要试一试来决定是递增还是递减
 * 5 4 3 2 1 -> no next greater element
 * 5 4 [3 2 1]   4   -> [5 4 4] 6
 */