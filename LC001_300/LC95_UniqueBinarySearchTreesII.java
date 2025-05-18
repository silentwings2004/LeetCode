package LC001_300;
import java.util.*;
public class LC95_UniqueBinarySearchTreesII {
    /**
     * Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of
     * unique values from 1 to n. Return the answer in any order.
     *
     * Input: n = 3
     * Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
     *
     * Input: n = 1
     * Output: [[1]]
     *
     * Constraints:
     *
     * 1 <= n <= 8
     * @param n
     * @return
     */
    // time = O(n * G(n)), space = O(nG(n))  G(n): Catalan number = 4^n / n^(1/2)
    public List<TreeNode> generateTrees(int n) {
        return dfs(1, n);
    }

    private List<TreeNode> dfs(int l, int r) {
        List<TreeNode> res = new ArrayList<>();
        if (l > r) {
            res.add(null);
            return res;
        }

        for (int i = l; i <= r; i++) {
            List<TreeNode> ls = dfs(l, i - 1);
            List<TreeNode> rs = dfs(i + 1, r);
            for (TreeNode a : ls) {
                for (TreeNode b : rs) {
                    TreeNode root = new TreeNode(i);
                    root.left = a;
                    root.right = b;
                    res.add(root);
                }
            }
        }
        return res;
    }
}