package LC1201_1500;
import java.util.*;
public class LC1469_FindAllTheLonelyNodes {
    /**
     * In a binary tree, a lonely node is a node that is the only child of its parent node. The root of the tree is not
     * lonely because it does not have a parent node.
     *
     * Given the root of a binary tree, return an array containing the values of all lonely nodes in the tree. Return
     * the list in any order.
     *
     * Input: root = [1,2,3,null,4]
     * Output: [4]
     *
     * Input: root = [11,99,88,77,null,null,66,55,null,null,44,33,null,null,22]
     * Output: [77,55,33,66,44,22]
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 1000].
     * 1 <= Node.val <= 10^6
     * @param root
     * @return
     */
    List<Integer> res;
    public List<Integer> getLonelyNodes(TreeNode root) {
        res = new ArrayList<>();
        dfs(root);
        return res;
    }

    private void dfs(TreeNode node) {
        if (node == null) return;

        if ((node.left == null) ^ (node.right == null)) res.add(node.left == null ? node.right.val : node.left.val);
        dfs(node.left);
        dfs(node.right);
    }
}