package LC2401_2700;
import java.util.*;
public class LC2641_CousinsinBinaryTreeII {
    /**
     * Given the root of a binary tree, replace the value of each node in the tree with the sum of all its cousins'
     * values.
     *
     * Two nodes of a binary tree are cousins if they have the same depth with different parents.
     *
     * Return the root of the modified tree.
     *
     * Note that the depth of a node is the number of edges in the path from the root node to it.
     *
     * Input: root = [5,4,9,1,10,null,7]
     * Output: [0,0,0,7,7,null,11]
     *
     * Input: root = [3,1,2]
     * Output: [0,0,0]
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 105].
     * 1 <= Node.val <= 10^4
     * @param root
     * @return
     */
    // time = O(n), space = O(n)
    HashMap<Integer, Integer> map;
    HashMap<TreeNode, Integer> sub;
    public TreeNode replaceValueInTree(TreeNode root) {
        map = new HashMap<>();
        sub = new HashMap<>();
        dfs(root, null, 0);
        dfs2(root, null, 0);
        return root;
    }

    private void dfs(TreeNode node, TreeNode fa, int depth) {
        if (node == null) return;
        map.put(depth, map.getOrDefault(depth, 0) + node.val);
        if (fa != null) sub.put(fa, sub.getOrDefault(fa, 0) + node.val);
        dfs(node.left, node, depth + 1);
        dfs(node.right, node, depth + 1);
    }

    private void dfs2(TreeNode node, TreeNode fa, int depth) {
        if (node == null) return;
        if (fa == null) node.val = 0;
        else {
            int tot = map.get(depth), sum = sub.get(fa);
            node.val = tot - sum;
        }
        dfs2(node.left, node, depth + 1);
        dfs2(node.right, node, depth + 1);
    }
}