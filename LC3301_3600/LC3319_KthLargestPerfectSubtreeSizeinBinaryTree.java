package LC3301_3600;
import java.util.*;
public class LC3319_KthLargestPerfectSubtreeSizeinBinaryTree {
    /**
     * You are given the root of a binary tree and an integer k.
     *
     * Return an integer denoting the size of the kth largest perfect binary
     * subtree, or -1 if it doesn't exist.
     *
     * A perfect binary tree is a tree where all leaves are on the same level, and every parent has two children.
     *
     * Input: root = [5,3,6,5,2,5,7,1,8,null,null,6,8], k = 2
     * Output: 3
     *
     * Input: root = [1,2,3,4,5,6,7], k = 1
     * Output: 7
     *
     * Input: root = [1,2,3,null,4], k = 3
     * Output: -1
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 2000].
     * 1 <= Node.val <= 2000
     * 1 <= k <= 1024
     * @param root
     * @param k
     * @return
     */
    // S1: dfs + sort
    // time = O(nlogn), space = O(n)
    List<Integer> q;
    public int kthLargestPerfectSubtree(TreeNode root, int k) {
        q = new ArrayList<>();
        dfs(root);
        Collections.sort(q, (o1, o2) -> o2 - o1); // can use quick-select to get O(n)
        return q.size() < k ? -1 : q.get(k - 1);
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        int l = dfs(node.left);
        int r = dfs(node.right);
        if (l != r || l == -1) return -1;
        q.add(l + r + 1);
        return l + r + 1;
    }

    // S2: dfs + quick-select
    class Solution {
        // time = O(n), space = O(n)
        List<Integer> q;
        public int kthLargestPerfectSubtree(TreeNode root, int k) {
            q = new ArrayList<>();
            dfs(root);
            if (q.size() < k) return -1;
            return quick_select(q, 0, q.size() - 1, q.size() + 1 - k);
        }

        private int dfs(TreeNode node) {
            if (node == null) return 0;

            int l = dfs(node.left);
            int r = dfs(node.right);
            if (l != r || l == -1) return -1;
            q.add(l + r + 1);
            return l + r + 1;
        }

        private int quick_select(List<Integer> q, int l, int r, int k) {
            if (l >= r) return q.get(r);

            int x = q.get(l + r >> 1), i = l - 1, j = r + 1;
            while (i < j) {
                do i++; while (q.get(i) < x);
                do j--; while (q.get(j) > x);
                if (i < j) {
                    int t = q.get(i);
                    q.set(i, q.get(j));
                    q.set(j, t);
                }
            }
            int sl = j - l + 1;
            if (k <= sl) return quick_select(q, l, j, k);
            return quick_select(q, j + 1, r, k - sl);
        }
    }
}