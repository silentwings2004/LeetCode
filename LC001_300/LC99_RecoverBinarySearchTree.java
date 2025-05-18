package LC001_300;
import java.util.*;
public class LC99_RecoverBinarySearchTree {
    /**
     * You are given the root of a binary search tree (BST), where exactly two nodes of the tree were swapped by
     * mistake. Recover the tree without changing its structure.
     *
     * Follow up: A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
     *
     * Input: root = [1,3,null,null,2]
     * Output: [3,1,null,null,2]
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [2, 1000].
     * -2^31 <= Node.val <= 2^31 - 1
     * @param root
     */
    // S1
    // time = O(n), space = O(n)
    TreeNode[] res;
    TreeNode prev = null;
    public void recoverTree(TreeNode root) {
        res = new TreeNode[2];
        if (root == null) return;

        dfs(root);
        int t = res[0].val;
        res[0].val = res[1].val;
        res[1].val = t;
    }

    private void dfs(TreeNode node) {
        if (node == null) return;

        dfs(node.left);
        if (prev != null && prev.val > node.val) {
            if (res[1] == null) res[0] = prev;
            res[1] = node;
        }
        prev = node;
        dfs(node.right);
    }

    // S2: Morris Traversal
    // time = O(n), space = O(1)
    public void recoverTree2(TreeNode root) {
        TreeNode[] res = new TreeNode[2];
        TreeNode prev = null;

        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                if (prev != null && prev.val > cur.val) {
                    if (res[1] == null) res[0] = prev;
                    res[1] = cur;
                }
                prev = cur;
                cur = cur.right;
            } else {
                TreeNode p = cur.left;
                while (p.right != null && p.right != cur) p = p.right;
                if (p.right == null) {
                    p.right = cur;
                    cur = cur.left;
                } else {
                    p.right = null;
                    if (prev != null && prev.val > cur.val) {
                        if (res[1] == null) res[0] = prev;
                        res[1] = cur;
                    }
                    prev = cur;
                    cur = cur.right;
                }
            }
        }

        int t = res[0].val;
        res[0].val = res[1].val;
        res[1].val = t;
    }
}
/**
 * 中序遍历得到一个递增的序列
 * 交换2个node -> 不和谐 -> 递增顺序没有了
 * 扫一遍定位peak和valley
 * 如果被交换的2个是相邻的 => 1处不和谐
 * 把所有元素抽出来转化成数组
 * 中序遍历，一边走一边看，看相邻2个是否是递增的
 *
 * Morris 遍历 space = O(1)
 * 找逆序对，交换的是相邻2个数
 * 如果交换的不是相邻2个数 => 也是找逆序对
 * 1. 没有左子树,则直接遍历该点，然后走到右儿子
 * 2. 如果有左子树，则找前驱节点p,将左子树的最后一个点的右指针指向当前点，从而可以继续回溯
 * 若 p.right == null, 则p.right = root, root = root.left;
 * 否则 p.right = null; 遍历root, root = root.right
 * 还得判断是从上面下来的，还是从下面上来的，如果是把这个指针清空
 */