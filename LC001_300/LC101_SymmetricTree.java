package LC001_300;
import java.util.*;
public class LC101_SymmetricTree {
    /**
     * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
     *
     * Input: root = [1,2,2,3,4,4,3]
     * Output: true
     *
     *
     Constraints:

     The number of nodes in the tree is in the range [1, 1000].
     -100 <= Node.val <= 100

     * @param root
     * @return
     */
    // S1: dfs
    // time = O(n), space = O(n)
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return dfs(root.left, root.right);
    }

    private boolean dfs(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null || p.val != q.val) return false;
        return dfs(p.left, q.right) && dfs(p.right, q.left);
    }

    // S2: bfs
    // time = O(n), space = O(n)
    public boolean isSymmetric2(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> nums = new ArrayList<>();
            while (size-- > 0) {
                TreeNode cur = queue.poll();

                if (cur.left != null) {
                    queue.offer(cur.left);
                    nums.add(cur.left.val);
                } else nums.add(Integer.MIN_VALUE);

                if (cur.right != null) {
                    queue.offer(cur.right);
                    nums.add(cur.right.val);
                } else nums.add(Integer.MIN_VALUE);
            }

            int n = nums.size();
            int i = 0, j = n - 1;
            while (i < j) {
                if (!nums.get(i).equals(nums.get(j))) return false;
                i++;
                j--;
            }
        }
        return true;
    }
}