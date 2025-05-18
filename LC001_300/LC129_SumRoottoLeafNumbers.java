package LC001_300;
import java.util.*;
public class LC129_SumRoottoLeafNumbers {
    /**
     * You are given the root of a binary tree containing digits from 0 to 9 only.
     *
     * Each root-to-leaf path in the tree represents a number.
     *
     * For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
     * Return the total sum of all root-to-leaf numbers.
     *
     * A leaf node is a node with no children.
     *
     * Input: root = [1,2,3]
     * Output: 25
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 1000].
     * 0 <= Node.val <= 9
     * The depth of the tree will not exceed 10.
     * @param root
     * @return
     */
    // time = O(n), space = O(n)
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int sum) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return sum * 10 + node.val;
        return dfs(node.left, sum * 10 + node.val) + dfs(node.right, sum * 10 + node.val);
    }
}