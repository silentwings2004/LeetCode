package LC001_300;
import java.util.*;
public class LC199_BinaryTreeRightSideView {
    /**
     * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes
     * you can see ordered from top to bottom.
     *
     * Input: [1,2,3,null,5,null,4]
     * Output: [1, 3, 4]
     *
     * @param root
     * @return
     */
    // S1: bfs
    // time = O(n), space = O(n)
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (i == size - 1) res.add(cur.val);
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
        }
        return res;
    }

    // S2: dfs
    // time = O(n), space = O(n)
    List<Integer> res;
    public List<Integer> rightSideView2(TreeNode root) {
        res = new ArrayList<>();
        if (root == null) return res;
        dfs(root, 0);
        return res;
    }

    private void dfs(TreeNode node, int depth) {
        if (node == null) return;

        if (depth == res.size()) res.add(node.val);
        dfs(node.right, depth + 1);
        dfs(node.left, depth + 1);
    }
}