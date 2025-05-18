package LC001_300;
import java.util.*;
public class LC270_ClosestBinarySearchTreeValue {
    /**
     * Given the root of a binary search tree and a target value, return the value in the BST that is closest to the
     * target. If there are multiple answers, print the smallest.
     *
     * Input: root = [4,2,5,1,3], target = 3.714286
     * Output: 4
     *
     * Input: root = [1], target = 4.428571
     * Output: 1
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 10^4].
     * 0 <= Node.val <= 10^9
     * -10^9 <= target <= 10^9
     * @param root
     * @param target
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int closestValue(TreeNode root, double target) {
        if (root == null) return Integer.MIN_VALUE;

        if (root.val == target) return root.val;
        int l = closestValue(root.left, target);
        int r = closestValue(root.right, target);

        double a = Math.abs(root.val - target);
        double b = Math.abs(l - target);
        double c = Math.abs(r - target);

        if (b < c) return a < b ? root.val : l;
        return a <= c ? root.val : r;
    }

    // S2
    // time = O(logn), space = O(1)
    public int closestValue2(TreeNode root, double target) {
        int res = root.val;
        TreeNode cur = root;
        while (cur != null) {
            if (Math.abs(cur.val - target) <= Math.abs(res - target)) {
                if (Math.abs(cur.val - target) < Math.abs(res - target)) res = cur.val;
                else res = Math.min(res, cur.val);
            }
            if (cur.val < target) cur = cur.right;
            else if (cur.val > target) cur = cur.left;
            else return cur.val;
        }
        return res;
    }
}
/**
 * 利用BST的性质不断朝target搜索，直至最底层。记录一路经过的根节点，不断更新 (root->val - target)的最小结果。
 * 注意：result的初始值必须设置为root->val。
 */
