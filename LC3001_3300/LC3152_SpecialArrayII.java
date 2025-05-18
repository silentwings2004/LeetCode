package LC3001_3300;

public class LC3152_SpecialArrayII {
    /**
     * An array is considered special if every pair of its adjacent elements contains two numbers with different parity.
     *
     * You are given an array of integer nums and a 2D integer matrix queries, where for queries[i] = [fromi, toi] your
     * task is to check that subarray nums[fromi..toi] is special or not.
     *
     * Return an array of booleans answer such that answer[i] is true if nums[fromi..toi] is special.
     *
     * Input: nums = [3,4,1,2,6], queries = [[0,4]]
     * Output: [false]
     *
     * Input: nums = [4,3,1,6], queries = [[0,2],[2,3]]
     * Output: [false,true]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * 1 <= queries.length <= 10^5
     * queries[i].length == 2
     * 0 <= queries[i][0] <= queries[i][1] <= nums.length - 1
     * @param nums
     * @param queries
     * @return
     */
    // time = O(n), space = O(n)
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n = nums.length;
        for (int i = 0; i < n; i++) nums[i] %= 2;
        int[] s = new int[n + 1];
        for (int i = 1; i + 1 <= n; i++) {
            s[i] = s[i - 1] + (nums[i - 1] ^ nums[i]);
        }

        int m = queries.length;
        boolean[] res = new boolean[m];
        for (int i = 0; i < m; i++) {
            int l = queries[i][0], r = queries[i][1];
            int t = s[r] - s[l];
            if (t == r - l) res[i] = true;
        }
        return res;
    }
}
/**
 * a[i] = 0 表示 nums[i] 和 nums[i + 1] 奇偶性不同
 * a[i] = 1 表示 nums[i] 和 nums[i + 1] 奇偶性相同
 * 长为 n-1 的数组 a
 * 问：a[from] 到 a[to-1] 中是否包含 1， 如果不包含 1，那么 true, 否则就是 false
 * => 求子数组前缀和问题，子数组元素和 = 0 => true
 */