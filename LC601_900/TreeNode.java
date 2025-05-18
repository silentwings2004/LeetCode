package LC601_900;

public class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) { val = x; }
    TreeNode(int x, TreeNode l, TreeNode r) {
        val = x;
        left = l;
        right = r;
    }
}