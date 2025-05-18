package LC901_1200;

public class LC998_MaximumBinaryTreeII {
    /**
     * A maximum tree is a tree where every node has a value greater than any other value in its subtree.
     *
     * You are given the root of a maximum binary tree and an integer val.
     *
     * Just as in the previous problem, the given tree was constructed from a list a (root = Construct(a)) recursively
     * with the following Construct(a) routine:
     *
     * If a is empty, return null.
     * Otherwise, let a[i] be the largest element of a. Create a root node with the value a[i].
     * The left child of root will be Construct([a[0], a[1], ..., a[i - 1]]).
     * The right child of root will be Construct([a[i + 1], a[i + 2], ..., a[a.length - 1]]).
     * Return root.
     * Note that we were not given a directly, only a root node root = Construct(a).
     *
     * Suppose b is a copy of a with the value val appended to it. It is guaranteed that b has unique values.
     *
     * Return Construct(b).
     *
     * Input: root = [4,1,3,null,null,2], val = 5
     * Output: [5,4,null,1,3,null,null,2]
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 100].
     * 1 <= Node.val <= 100
     * All the values of the tree are unique.
     * 1 <= val <= 100
     * @param root
     * @param val
     * @return
     */
    // time = O(n), space = O(n)
    public TreeNode insertIntoMaxTree(TreeNode root, int val) {
        if (root == null || root.val < val) {
            TreeNode p = new TreeNode(val);
            p.left = root;
            return p;
        } else {
            root.right = insertIntoMaxTree(root.right, val);
            return root;
        }
    }
}
/**
 * 中序遍历即为原数组
 * 1. 如果新节点比根节点大，创建一个新根节点，原来的接在它的左孩子上
 * 2. 如果当前数比根节点小的话，插入的数不是整个数组的最大值，那么插到最后的位置，即递归到右子树里
 * 找到第一个比当前数小的最大值，断开接在当前数的左子树上
 */