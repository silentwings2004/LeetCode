package LC901_1200;
import java.util.*;
public class LC1008_ConstructBinarySearchTreefromPreorderTraversal {
    /**
     * Given an array of integers preorder, which represents the preorder traversal of a BST (i.e., binary search tree),
     * construct the tree and return its root.
     *
     * It is guaranteed that there is always possible to find a binary search tree with the given requirements for the
     * given test cases.
     *
     * A binary search tree is a binary tree where for every node, any descendant of Node.left has a value strictly less
     * than Node.val, and any descendant of Node.right has a value strictly greater than Node.val.
     *
     * A preorder traversal of a binary tree displays the value of the node first, then traverses Node.left, then
     * traverses Node.right.
     *
     * Input: preorder = [8,5,1,7,10,12]
     * Output: [8,5,10,1,7,null,12]
     *
     * Input: preorder = [1,3]
     * Output: [1,null,3]
     *
     * Constraints:
     *
     * 1 <= preorder.length <= 100
     * 1 <= preorder[i] <= 1000
     * All the values of preorder are unique.
     * @param preorder
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public TreeNode bstFromPreorder(int[] preorder) {
        return dfs(preorder, 0, preorder.length - 1);
    }

    private TreeNode dfs(int[] w, int l, int r) {
        if (l > r) return null;

        TreeNode node = new TreeNode(w[l]);
        int x = l + 1;
        while (x <= r && w[x] < w[l]) x++;
        node.left = dfs(w, l + 1, x - 1);
        node.right = dfs(w, x, r);
        return node;
    }

    // S2: HashMap
    // time = O(n), space = O(n)
    int[] a, b;
    HashMap<Integer, Integer> map;
    public TreeNode bstFromPreorder2(int[] preorder) {
        map = new HashMap<>();
        a = preorder;
        b = a.clone();
        Arrays.sort(b);
        int n = b.length;
        for (int i = 0; i < n; i++) map.put(b[i], i);
        return build(0, n - 1, 0, n - 1);
    }

    private TreeNode build(int x, int y, int l, int r) {
        if (x > y) return null;
        TreeNode node = new TreeNode(a[x]);
        int k = map.get(node.val);
        node.left = build(x + 1, k - l + x, l, k - 1);
        node.right = build(k - l + x + 1, y, k + 1, r);
        return node;
    }
}