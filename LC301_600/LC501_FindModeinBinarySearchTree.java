package LC301_600;
import java.util.*;
public class LC501_FindModeinBinarySearchTree {
    /**
     * Given the root of a binary search tree (BST) with duplicates, return all the mode(s) (i.e., the most frequently
     * occurred element) in it.
     *
     * If the tree has more than one mode, return them in any order.
     *
     * Assume a BST is defined as follows:
     *
     * The left subtree of a node contains only nodes with keys less than or equal to the node's key.
     * The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
     * Both the left and right subtrees must also be binary search trees.
     *
     * Input: root = [1,null,2,2]
     * Output: [2]
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 10^4].
     * -105 <= Node.val <= 105
     *
     *
     * Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to
     * recursion does not count).
     * @param root
     * @return
     */
    // time = O(n), space = O(n)
    List<Integer> res;
    int maxc = 0, curc = 0, last;
    public int[] findMode(TreeNode root) {
        dfs(root);
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    private void dfs(TreeNode node) {
        if (node == null) return;
        dfs(node.left);
        if (curc == 0 || last == node.val) curc++;
        else curc = 1;
        last = node.val;
        if (curc > maxc) {
            res = new ArrayList<>();
            res.add(last);
            maxc = curc;
        } else if (curc == maxc) res.add(last);
        dfs(node.right);
    }
}
