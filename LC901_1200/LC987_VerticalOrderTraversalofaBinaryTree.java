package LC901_1200;
import java.util.*;
public class LC987_VerticalOrderTraversalofaBinaryTree {
    /**
     * Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
     *
     * For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and
     * (row + 1, col + 1) respectively. The root of the tree is at (0, 0).
     *
     * The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index
     * starting from the leftmost column and ending on the rightmost column. There may be multiple nodes in the same
     * row and same column. In such a case, sort these nodes by their values.
     *
     * Return the vertical order traversal of the binary tree.
     *
     * Input: root = [3,9,20,null,null,15,7]
     * Output: [[9],[3,15],[20],[7]]
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 1000].
     * 0 <= Node.val <= 1000
     * @param root
     * @return
     */
    // time = O(n), space = O(n)
    final int INF = 0x3f3f3f3f;
    int minc = INF, maxc = -INF;
    HashMap<Integer, List<int[]>> map;
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        map = new HashMap<>();
        dfs(root, 0, 0);
        for (int i = minc; i <= maxc; i++) {
            List<int[]> q = map.get(i);
            Collections.sort(q, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] - o2[1]);
            List<Integer> t = new ArrayList<>();
            for (int[] x : q) t.add(x[1]);
            res.add(t);
        }
        return res;
    }

    private void dfs(TreeNode node, int r, int c) {
        if (node == null) return;
        map.putIfAbsent(c, new ArrayList<>());
        map.get(c).add(new int[]{r, node.val});
        minc = Math.min(minc, c);
        maxc = Math.max(maxc, c);

        dfs(node.left, r + 1, c - 1);
        dfs(node.right, r + 1, c + 1);
    }
}
