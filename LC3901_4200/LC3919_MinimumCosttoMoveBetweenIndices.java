package LC3901_4200;

public class LC3919_MinimumCosttoMoveBetweenIndices {
    /**
     * You are given an integer array nums where nums is strictly increasing.
     *
     * For each index x, let closest(x) be the adjacent index such that abs(nums[x] - nums[y]) is minimized. If both
     * adjacent indices exist and give the same difference, choose the smaller index.
     *
     * From any index x, you can move in two ways:
     *
     * To any index y with cost abs(nums[x] - nums[y]), or
     * To closest(x) with cost 1.
     * You are also given a 2D integer array queries, where each queries[i] = [li, ri].
     *
     * For each query, calculate the minimum total cost to move from index li to index ri.
     *
     * Return an integer array ans, where ans[i] is the answer for the ith query.
     *
     * The absolute difference between two values x and y is defined as abs(x - y).
     *
     * Input: nums = [-5,-2,3], queries = [[0,2],[2,0],[1,2]]
     * Output: [6,2,5]
     *
     * Input: nums = [0,2,3,9], queries = [[3,0],[1,2],[2,0]]
     * Output: [4,1,3]
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^5
     * -10^9 <= nums[i] <= 10^9
     * nums is strictly increasing
     * 1 <= queries.length <= 10^5
     * queries[i] = [li, ri]
     * 0 <= li, ri < nums.length
     * @param nums
     * @param queries
     * @return
     */
    // time = O(n), space = O(n)
    public int[] minCost(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] sl = new int[n], sr = new int[n];
        for (int i = 1, t = 0; i < n; i++) {
            if (i < n - 1 && nums[i] - nums[i - 1] > nums[i + 1] - nums[i]) t = nums[i] - nums[i - 1];
            else t = 1;
            sl[i] = sl[i - 1] + t;
            if (i > 1 && nums[i - 1] - nums[i - 2] <= nums[i] - nums[i - 1]) t = nums[i] - nums[i - 1];
            else t = 1;
            sr[i] = sr[i - 1] + t;
        }

        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int l = queries[i][0], r = queries[i][1];
            if (l < r) res[i] = sr[r] - sr[l];
            else res[i] = sl[l] - sl[r];
        }
        return res;
    }
}
/**
 * 计算的一个子数组的元素和 => 快速计算：前缀和
 */