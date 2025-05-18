package LC601_900;
import java.util.*;
public class LC666_PathSumIV {
    /**
     * If the depth of a tree is smaller than 5, then this tree can be represented by an array of three-digit integers.
     * For each integer in this array:
     *
     * The hundreds digit represents the depth d of this node where 1 <= d <= 4.
     * The tens digit represents the position p of this node in the level it belongs to where 1 <= p <= 8. The position
     * is the same as that in a full binary tree.
     * The units digit represents the value v of this node where 0 <= v <= 9.
     * Given an array of ascending three-digit integers nums representing a binary tree with a depth smaller than 5,
     * return the sum of all paths from the root towards the leaves.
     *
     * It is guaranteed that the given array represents a valid connected binary tree.
     *
     * Input: nums = [113,215,221]
     * Output: 12
     *
     * Input: nums = [113,221]
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= nums.length <= 15
     * 110 <= nums[i] <= 489
     * nums represents a valid binary tree with depth less than 5.
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    int[][] adj;
    int res = 0;
    public int pathSum(int[] nums) {
        adj = new int[5][9];
        for (int i = 0; i < 5; i++) Arrays.fill(adj[i], -1);
        for (int x : nums) {
            int d = x / 100, p = (x - d * 100) / 10, v = x - d * 100 - 10 * p;
            adj[d][p] = v;
        }
        dfs(1, 1, 0);
        return res;
    }

    private void dfs(int u, int d, int s) {
        s += adj[d][get(u, d)];
        int l = get(u * 2, d + 1), r = get(u * 2 + 1, d + 1);
        if (d + 1 > 4 || adj[d + 1][l] == -1 && adj[d + 1][r] == -1) res += s;
        else {
            if (adj[d + 1][l] != -1) dfs(u * 2, d + 1, s);
            if (adj[d + 1][r] != -1) dfs(u * 2 + 1, d + 1, s);
        }
    }

    private int get(int u, int d) {
        int l = (int)Math.pow(2, d - 1);
        return u - l + 1;
    }
}