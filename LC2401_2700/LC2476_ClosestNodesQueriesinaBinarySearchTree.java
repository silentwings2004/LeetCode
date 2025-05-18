package LC2401_2700;
import java.util.*;
public class LC2476_ClosestNodesQueriesinaBinarySearchTree {
    /**
     * You are given the root of a binary search tree and an array queries of size n consisting of positive integers.
     *
     * Find a 2D array answer of size n where answer[i] = [mini, maxi]:
     *
     * mini is the largest value in the tree that is smaller than or equal to queries[i]. If a such value does not
     * exist, add -1 instead.
     * maxi is the smallest value in the tree that is greater than or equal to queries[i]. If a such value does not
     * exist, add -1 instead.
     * Return the array answer.
     *
     * Input: root = [6,2,13,1,4,9,15,null,null,null,null,null,null,14], queries = [2,5,16]
     * Output: [[2,2],[4,6],[15,-1]]
     *
     * Input: root = [4,null,9], queries = [3]
     * Output: [[-1,4]]
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [2, 105].
     * 1 <= Node.val <= 10^6
     * n == queries.length
     * 1 <= n <= 10^5
     * 1 <= queries[i] <= 10^6
     * @param root
     * @param queries
     * @return
     */
    // time = O(nlogn), space = O(n)
    TreeSet<Integer> set;
    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<List<Integer>> res = new ArrayList<>();
        set = new TreeSet<>();

        dfs(root);
        for (int x : queries) {
            int a = set.floor(x) == null ? -1 : set.floor(x);
            int b = set.ceiling(x) == null ? -1 : set.ceiling(x);
            res.add(Arrays.asList(a, b));
        }
        return res;
    }

    private void dfs(TreeNode node) {
        if (node == null) return;

        dfs(node.left);
        set.add(node.val);
        dfs(node.right);
    }
}
