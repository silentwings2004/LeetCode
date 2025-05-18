package LC3001_3300;

public class LC3062_WinneroftheLinkedListGame {
    /**
     * You are given the head of a linked list of even length containing integers.
     *
     * Each odd-indexed node contains an odd integer and each even-indexed node contains an even integer.
     *
     * We call each even-indexed node and its next node a pair, e.g., the nodes with indices 0 and 1 are a pair, the
     * nodes with indices 2 and 3 are a pair, and so on.
     *
     * For every pair, we compare the values of the nodes in the pair:
     *
     * If the odd-indexed node is higher, the "Odd" team gets a point.
     * If the even-indexed node is higher, the "Even" team gets a point.
     * Return the name of the team with the higher points, if the points are equal, return "Tie".
     *
     * Input: head = [2,1]
     * Output: "Even"
     *
     * Input: head = [2,5,4,7,20,5]
     * Output: "Odd"
     *
     * Input: head = [4,5,2,1]
     * Output: "Tie"
     *
     * Constraints:
     *
     * The number of nodes in the list is in the range [2, 100].
     * The number of nodes in the list is even.
     * 1 <= Node.val <= 100
     * The value of each odd-indexed node is odd.
     * The value of each even-indexed node is even.
     * @param head
     * @return
     */
    // time = O(n), space = O(1)
    public String gameResult(ListNode head) {
        int odd = 0, even = 0;
        for (ListNode p = head; p != null; p = p.next.next) {
            int a = p.val, b = p.next.val;
            if (a > b) even++;
            else odd++;
        }
        if (even > odd) return "Even";
        else if (even < odd) return "Odd";
        return "Tie";
    }
}