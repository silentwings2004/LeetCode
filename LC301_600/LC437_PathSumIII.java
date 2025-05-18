package LC301_600;
import java.util.*;
public class LC437_PathSumIII {
    /**
     * Given the root of a binary tree and an integer targetSum, return the number of paths where the sum of the values
     * along the path equals targetSum.
     *
     * The path does not need to start or end at the root or a leaf, but it must go downwards (i.e., traveling only
     * from parent nodes to child nodes).
     *
     * Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
     * Output: 3
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [0, 1000].
     * -10^9 <= Node.val <= 10^9
     * -1000 <= targetSum <= 1000
     * @param root
     * @param targetSum
     * @return
     */
    // S1: DFS
    // time = O(n), space = O(n)
    HashMap<Long, Integer> map;
    int res = 0;
    public int pathSum(TreeNode root, int targetSum) {
        map = new HashMap<>();
        map.put(0L, 1);
        dfs(root, targetSum, 0);
        return res;
    }

    private void dfs(TreeNode node, int t, long sum) {
        if (node == null) return;

        sum += node.val;
        res += map.getOrDefault(sum - t, 0);
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        dfs(node.left, t, sum);
        dfs(node.right, t, sum);
        map.put(sum, map.get(sum) - 1);
    }
}