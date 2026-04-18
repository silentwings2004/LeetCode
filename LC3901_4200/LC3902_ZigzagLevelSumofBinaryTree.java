package LC3901_4200;
import java.util.*;
public class LC3902_ZigzagLevelSumofBinaryTree {
    /**
     * You are given the root of a binary tree.
     *
     * Traverse the tree level by level using a zigzag pattern:
     *
     * At odd-numbered levels (1-indexed), traverse nodes from left to right.
     * At even-numbered levels, traverse nodes from right to left.
     * While traversing a level in the specified direction, process nodes in order and stop immediately before the first
     * node that violates the condition:
     *
     * At odd levels: the node does not have a left child.
     * At even levels: the node does not have a right child.
     * Only the nodes processed before this stopping condition contribute to the level sum.
     *
     * Return an integer array ans where ans[i] is the sum of the node values that are processed at level i + 1.
     *
     * Input: root = [5,2,8,1,null,9,6]
     * Output: [5,8,0]
     *
     * Input: root = [1,2,3,4,5,null,7]
     * Output: [1,5,0]
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 10^5].
     * -10^5 <= Node.val <= 10^5
     * @param root
     * @return
     */
    // time = O(n), space = O(n)
    public List<Long> zigzagLevelSum(TreeNode root) {
        List<Long> res = new ArrayList<>();
        Deque<TreeNode> dq = new LinkedList<>();
        dq.offerLast(root);
        boolean f = false;

        while (!dq.isEmpty()) {
            int sz = dq.size();
            long s = 0;
            boolean g = true;
            if (f) {
                while (sz-- > 0) {
                    TreeNode node = dq.pollLast();
                    if (node.right != null) dq.offerFirst(node.right);
                    else g = false;
                    if (node.left != null) dq.offerFirst(node.left);
                    if (g) s += node.val;
                }
            } else {
                while (sz-- > 0) {
                    TreeNode node = dq.pollFirst();
                    if (node.left != null) dq.offerLast(node.left);
                    else g = false;
                    if (node.right != null) dq.offerLast(node.right);
                    if (g) s += node.val;
                }
            }
            res.add(s);
            f = !f;
        }
        return res;
    }
}
