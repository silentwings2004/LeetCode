package LC901_1200;
import java.util.*;
public class LC1110_DeleteNodesAndReturnForest {
    /**
     * Given the root of a binary tree, each node in the tree has a distinct value.
     *
     * After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
     *
     * Return the roots of the trees in the remaining forest. You may return the result in any order.
     *
     * Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
     * Output: [[1,2,null,4],[6],[7]]
     *
     * Input: root = [1,2,4,null,3], to_delete = [3]
     * Output: [[1,2,4]]
     *
     * Constraints:
     *
     * The number of nodes in the given tree is at most 1000.
     * Each node has a distinct value between 1 and 1000.
     * to_delete.length <= 1000
     * to_delete contains distinct values between 1 and 1000.
     * @param root
     * @param to_delete
     * @return
     */
    // time = O(n), space = O(n)
    List<TreeNode> res;
    HashSet<Integer> set;
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        res = new ArrayList<>();
        set = new HashSet<>();
        for (int x : to_delete) set.add(x);
        dfs(root, false);
        return res;
    }

    private TreeNode dfs(TreeNode node, boolean has_father) {
        if (node == null) return null;
        if (set.contains(node.val)) {
            if (node.left != null) dfs(node.left, false);
            if (node.right != null) dfs(node.right, false);
            node = null;
        } else {
            if (!has_father) res.add(node);
            node.left = dfs(node.left, true);
            node.right = dfs(node.right, true);
        }
        return node;
    }
}
/**
 * 删点
 * 找树根：没有父节点的点
 */