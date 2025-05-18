package LC601_900;

public class LC725_SplitLinkedListinParts {
    /**
     * Given the head of a singly linked list and an integer k, split the linked list into k consecutive linked list parts.
     *
     * The length of each part should be as equal as possible: no two parts should have a size differing by more than
     * one. This may lead to some parts being null.
     *
     * The parts should be in the order of occurrence in the input list, and parts occurring earlier should always have
     * a size greater than or equal to parts occurring later.
     *
     * Return an array of the k parts.
     *
     * Input: head = [1,2,3], k = 5
     * Output: [[1],[2],[3],[],[]]
     *
     * Constraints:
     *
     * The number of nodes in the list is in the range [0, 1000].
     * 0 <= Node.val <= 1000
     * 1 <= k <= 50
     * @param head
     * @param k
     * @return
     */
    // time = O(n + min(n, k)), space = O(1)
    public ListNode[] splitListToParts(ListNode head, int k) {
        int n = 0;
        for (ListNode p = head; p != null; p = p.next) n++;
        ListNode[] res = new ListNode[k];
        ListNode p = head;
        for (int i = 0; i < k; i++) {
            int len = n / k;
            if (i + 1 <= n % k) len++;
            res[i] = p;
            for (int j = 0; j < len - 1; j++) p = p.next;
            if (p != null) {
                ListNode q = p.next;
                p.next = null;
                p = q;
            }
        }
        return res;
    }
}