package LC001_300;
import java.util.*;
public class LC257_BinaryTreePaths {
    /**
     * Given the root of a binary tree, return all root-to-leaf paths in any order.
     *
     * A leaf is a node with no children.
     *
     * Input: root = [1,2,3,null,5]
     * Output: ["1->2->5","1->3"]
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 100].
     * -100 <= Node.val <= 100
     * @param root
     * @return
     */
    // time = O(n^2), space = O(n^2)
    List<String> res;
    StringBuilder sb;
    public List<String> binaryTreePaths(TreeNode root) {
        res = new ArrayList<>();
        sb = new StringBuilder();
        if (root == null) return res;
        sb.append(root.val);
        dfs(root);
        return res;
    }

    private void dfs(TreeNode node) {
        if (node.left == null && node.right == null) {
            res.add(sb.toString());
            return;
        }

        int len = sb.length();
        if (node.left != null) {
            sb.append("->").append(node.left.val);
            dfs(node.left);
            sb.setLength(len);
        }
        if (node.right != null) {
            sb.append("->").append(node.right.val);
            dfs(node.right);
            sb.setLength(len);
        }
    }
}
/**
 * 如果有n/2个叶子节点 => 存下来(n^2 / 4) => O(n^2)
 */