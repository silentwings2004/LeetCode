package LC001_300;
import java.util.*;
public class LC107_BinaryTreeLevelOrderTraversalII {
    /**
     * Given the root of a binary tree, return the bottom-up level order traversal of its nodes' values. (i.e., from
     * left to right, level by level from leaf to root).
     *
     * Input: root = [3,9,20,null,null,15,7]
     * Output: [[15,7],[9,20],[3]]
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [0, 2000].
     * -1000 <= Node.val <= 1000
     *
     * @param root
     * @return
     */
    // time = O(n), space = O(n)
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> path = new ArrayList<>();
            while (size-- > 0) {
                TreeNode t = q.poll();
                path.add(t.val);
                if (t.left != null) q.offer(t.left);
                if (t.right != null) q.offer(t.right);
            }
            res.add(path);
        }
        Collections.reverse(res);
        return res;
    }
}