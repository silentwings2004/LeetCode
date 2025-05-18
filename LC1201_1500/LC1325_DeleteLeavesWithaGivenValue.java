package LC1201_1500;

public class LC1325_DeleteLeavesWithaGivenValue {
    /**
     * Given a binary tree root and an integer target, delete all the leaf nodes with value target.
     *
     * Note that once you delete a leaf node with value target, if its parent node becomes a leaf node and has the
     * value target, it should also be deleted (you need to continue doing that until you cannot).
     *
     * Input: root = [1,2,3,2,null,2,4], target = 2
     * Output: [1,null,3,null,4]
     *
     * Input: root = [1,3,3,3,2], target = 3
     * Output: [1,3,null,null,2]
     *
     * Input: root = [1,2,null,2,null,2], target = 2
     * Output: [1]
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 3000].
     * 1 <= Node.val, target <= 1000
     * @param root
     * @param target
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) return null;
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);
        if (root.left == null && root.right == null && root.val == target) return null;
        return root;
    }

    // S2
    // time = O(n), space = O(n)
    public TreeNode removeLeafNodes2(TreeNode root, int target) {
        dfs(root, null, target);
        if (root.left == null && root.right == null && root.val == target) return null;
        return root;
    }

    private void dfs(TreeNode node, TreeNode p, int t) {
        if (node == null) return;

        dfs(node.left, node, t);
        dfs(node.right, node, t);

        if (node.left == null && node.right == null && node.val == t) {
            if (p != null) {
                if (p.left == node) p.left = null;
                else p.right = null;
            }
        }
    }
}