package LC2401_2700;
import java.util.*;
public class LC2415_ReverseOddLevelsofBinaryTree {
    /**
     * Given the root of a perfect binary tree, reverse the node values at each odd level of the tree.
     *
     * For example, suppose the node values at level 3 are [2,1,3,4,7,11,29,18], then it should become
     * [18,29,11,7,4,3,1,2].
     * Return the root of the reversed tree.
     *
     * A binary tree is perfect if all parent nodes have two children and all leaves are on the same level.
     *
     * The level of a node is the number of edges along the path between it and the root node.
     *
     * Input: root = [2,3,5,8,13,21,34]
     * Output: [2,5,3,8,13,21,34]
     *
     * Input: root = [7,13,11]
     * Output: [7,11,13]
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 2^14].
     * 0 <= Node.val <= 10^5
     * root is a perfect binary tree.
     * @param root
     * @return
     */
    // S1: BFS
    // time = O(n), space = O(n)
    public TreeNode reverseOddLevels(TreeNode root) {
        if (root == null) return root;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<TreeNode> list = new ArrayList<>();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                if (step % 2 == 1) list.add(cur);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            if (step % 2 == 1) reverse(list);
            step++;
        }
        return root;
    }

    private void reverse(List<TreeNode> list) {
        int i = 0, j = list.size() - 1;
        while (i < j) {
            int t = list.get(i).val;
            list.get(i++).val = list.get(j).val;
            list.get(j--).val = t;
        }
    }

    // S2: DFS
    // time = O(n), space = O(n)
    public TreeNode reverseOddLevels2(TreeNode root) {
        if (root == null) return root;
        dfs(root.left, root.right, 1);
        return root;
    }

    private void dfs(TreeNode a, TreeNode b, int d) {
        if (a == null) return;
        if (d % 2 == 1) {
            int t = a.val;
            a.val = b.val;
            b.val = t;
        }
        dfs(a.left, b.right, d + 1);
        dfs(a.right, b.left, d + 1);
    }
}