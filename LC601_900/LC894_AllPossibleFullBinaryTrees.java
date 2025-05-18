package LC601_900;
import java.util.*;
public class LC894_AllPossibleFullBinaryTrees {
    /**
     * Given an integer n, return a list of all possible full binary trees with n nodes. Each node of each tree in the
     * answer must have Node.val == 0.
     *
     * Each element of the answer is the root node of one possible tree. You may return the final list of trees in any
     * order.
     *
     * A full binary tree is a binary tree where each node has exactly 0 or 2 children.
     *
     * Input: n = 7
     * Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],
     * [0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
     *
     * Input: n = 3
     * Output: [[0,0,0]]
     *
     * Constraints:
     *
     * 1 <= n <= 20
     * @param n
     * @return
     */
    // time = O(2^n), space = O(2^n)
    public List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode> res = new ArrayList<>();
        if (n % 2 == 0) return res;
        if (n == 1) {
            res.add(new TreeNode(0));
            return res;
        }
        for (int i = 1; i + 1 < n; i++) {
            List<TreeNode> left = allPossibleFBT(i);
            List<TreeNode> right = allPossibleFBT(n - 1 - i);
            for (TreeNode l : left) {
                for (TreeNode r : right) {
                    res.add(new TreeNode(0, l, r));
                }
            }
        }
        return res;
    }
}