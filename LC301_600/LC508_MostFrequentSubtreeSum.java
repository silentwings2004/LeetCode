package LC301_600;
import java.util.*;
public class LC508_MostFrequentSubtreeSum {
    /**
     * Given the root of a binary tree, return the most frequent subtree sum. If there is a tie, return all the values
     * with the highest frequency in any order.
     *
     * The subtree sum of a node is defined as the sum of all the node values formed by the subtree rooted at that node
     * (including the node itself).
     *
     * Input: root = [5,2,-3]
     * Output: [2,-3,4]
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 104].
     * -10^5 <= Node.val <= 10^5
     * @param root
     * @return
     */
    // time = O(n), space = O(n)
    HashMap<Integer, Integer> map;
    List<Integer> res;
    int maxc = 0;
    public int[] findFrequentTreeSum(TreeNode root) {
        map = new HashMap<>();
        res = new ArrayList<>();
        dfs(root);
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) ans[i] = res.get(i);
        return ans;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        int sum = node.val + dfs(node.left) + dfs(node.right);
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        if (map.get(sum) > maxc) {
            maxc = map.get(sum);
            res = new ArrayList<>();
            res.add(sum);
        } else if (map.get(sum) == maxc) res.add(sum);
        return sum;
    }
}