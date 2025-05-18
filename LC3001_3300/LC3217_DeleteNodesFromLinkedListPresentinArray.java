package LC3001_3300;
import java.util.*;
public class LC3217_DeleteNodesFromLinkedListPresentinArray {
    /**
     * You are given an array of integers nums and the head of a linked list. Return the head of the modified linked
     * list after removing all nodes from the linked list that have a value that exists in nums.
     *
     * Input: nums = [1,2,3], head = [1,2,3,4,5]
     * Output: [4,5]
     *
     * Input: nums = [1], head = [1,2,1,2,1,2]
     * Output: [2,2,2]
     *
     * Input: nums = [5], head = [1,2,3,4]
     * Output: [1,2,3,4]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * All elements in nums are unique.
     * The number of nodes in the given list is in the range [1, 10^5].
     * 1 <= Node.val <= 10^5
     * The input is generated such that there is at least one node in the linked list that has a value not present in
     * nums.
     * @param nums
     * @param head
     * @return
     */
    // time = O(n), space = O(1)
    public ListNode modifiedList(int[] nums, ListNode head) {
        HashSet<Integer> set = new HashSet<>();
        for (int x : nums) set.add(x);
        ListNode dummy = new ListNode(0), cur = dummy;
        for (ListNode p = head; p != null; p = p.next) {
            if (set.contains(p.val)) continue;
            cur = cur.next = p;
        }
        cur.next = null;
        return dummy.next;
    }
}