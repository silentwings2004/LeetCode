package LC1201_1500;
import java.util.*;
public class LC1261_FindElementsinaContaminatedBinaryTree {
    /**
     * Given a binary tree with the following rules:
     *
     * root.val == 0
     * If treeNode.val == x and treeNode.left != null, then treeNode.left.val == 2 * x + 1
     * If treeNode.val == x and treeNode.right != null, then treeNode.right.val == 2 * x + 2
     * Now the binary tree is contaminated, which means all treeNode.val have been changed to -1.
     *
     * Implement the FindElements class:
     *
     * FindElements(TreeNode* root) Initializes the object with a contaminated binary tree and recovers it.
     * bool find(int target) Returns true if the target value exists in the recovered binary tree.
     *
     * Input
     * ["FindElements","find","find"]
     * [[[-1,null,-1]],[1],[2]]
     * Output
     * [null,false,true]
     *
     * Input
     * ["FindElements","find","find","find"]
     * [[[-1,-1,-1,-1,-1]],[1],[3],[5]]
     * Output
     * [null,true,true,false]
     *
     * Input
     * ["FindElements","find","find","find","find"]
     * [[[-1,null,-1,-1,null,-1]],[2],[3],[4],[5]]
     * Output
     * [null,true,false,false,true]
     *
     *
     *
     * Constraints:
     *
     * TreeNode.val == -1
     * The height of the binary tree is less than or equal to 20
     * The total number of nodes is between [1, 10^4]
     * Total calls of find() is between [1, 10^4]
     * 0 <= target <= 10^6
     * @param root
     */
    // time = O(n), space = O(n)
    HashSet<Integer> set;
    public LC1261_FindElementsinaContaminatedBinaryTree(TreeNode root) {
        set = new HashSet<>();
        root.val = 0;
        set.add(0);
        dfs(root);
    }

    public boolean find(int target) {
        return set.contains(target);
    }


    private void dfs(TreeNode node) {
        if (node == null) return;
        if (node.left != null) {
            node.left.val = node.val * 2 + 1;
            set.add(node.left.val);
            dfs(node.left);
        }
        if (node.right != null) {
            node.right.val = node.val * 2 + 2;
            set.add(node.right.val);
            dfs(node.right);
        }
    }

    // S2: Bit
    class FindElements {
        // time = O(1), space = O(1)
        TreeNode root;
        public FindElements(TreeNode root) {
            this.root = root;
        }

        public boolean find(int target) {
            target++;
            TreeNode cur = root;
            for (int i = 30 - Integer.numberOfLeadingZeros(target); i >= 0; i--) {
                int x = target >> i & 1;
                if (x == 0) cur = cur.left;
                else cur = cur.right;
                if (cur == null) return false;
            }
            return true;
        }
    }
}