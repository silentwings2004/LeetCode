package LC901_1200;
import java.util.*;
public class LC971_FlipBinaryTreeToMatchPreorderTraversal {
    /**
     * You are given the root of a binary tree with n nodes, where each node is uniquely assigned a value from 1 to n.
     * You are also given a sequence of n values voyage, which is the desired pre-order traversal of the binary tree.
     *
     * Any node in the binary tree can be flipped by swapping its left and right subtrees. For example, flipping node 1
     * will have the following effect:
     *
     * Flip the smallest number of nodes so that the pre-order traversal of the tree matches voyage.
     *
     * Return a list of the values of all flipped nodes. You may return the answer in any order. If it is impossible to
     * flip the nodes in the tree to make the pre-order traversal match voyage, return the list [-1].
     *
     * Input: root = [1,2], voyage = [2,1]
     * Output: [-1]
     *
     * Constraints:
     *
     * The number of nodes in the tree is n.
     * n == voyage.length
     * 1 <= n <= 100
     * 1 <= Node.val, voyage[i] <= n
     * All the values in the tree are unique.
     * All the values in voyage are unique.
     * @param root
     * @param voyage
     * @return
     */
    // time = O(n), space = O(n)
    List<Integer> res;
    int k = 0;
    public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
        res = new ArrayList<>();
        if (dfs(root, voyage)) return res;
        return Arrays.asList(-1);
    }

    private boolean dfs(TreeNode node, int[] w) {
        if (node == null) return true;
        if (node.val != w[k]) return false;
        k++;
        if (node.left != null && node.left.val != w[k]) {
            res.add(node.val);
            return dfs(node.right, w) && dfs(node.left, w);
        } else return dfs(node.left, w) && dfs(node.right, w);
    }
}