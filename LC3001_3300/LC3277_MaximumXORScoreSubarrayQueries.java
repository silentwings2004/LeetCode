package LC3001_3300;

public class LC3277_MaximumXORScoreSubarrayQueries {
    /**
     * You are given an array nums of n integers, and a 2D integer array queries of size q, where queries[i] = [li, ri].
     *
     * For each query, you must find the maximum XOR score of any subarray of nums[li..ri].
     *
     * The XOR score of an array a is found by repeatedly applying the following operations on a so that only one
     * element remains, that is the score:
     *
     * Simultaneously replace a[i] with a[i] XOR a[i + 1] for all indices i except the last one.
     * Remove the last element of a.
     * Return an array answer of size q where answer[i] is the answer to query i.
     *
     * Input: nums = [2,8,4,32,16,1], queries = [[0,2],[1,4],[0,5]]
     * Output: [12,60,60]
     *
     * Input: nums = [0,7,3,2,8,5,1], queries = [[0,3],[1,5],[2,4],[2,6],[5,6]]
     * Output: [7,14,11,14,5]
     *
     * Constraints:
     *
     * 1 <= n == nums.length <= 2000
     * 0 <= nums[i] <= 2^31 - 1
     * 1 <= q == queries.length <= 10^5
     * queries[i].length == 2
     * queries[i] = [li, ri]
     * 0 <= li <= ri <= n - 1
     * @param nums
     * @param queries
     * @return
     */
    // time = O(n^2), space = O(n^2)
    public int[] maximumSubarrayXor(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        int[][] f = new int[n][n], g = new int[n][n];
        for (int i = 0; i < n; i++) f[i][i] = g[i][i] = nums[i];
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                f[i][j] = f[i][j - 1] ^ f[i + 1][j];
                g[i][j] = Math.max(f[i][j], Math.max(g[i][j - 1], g[i + 1][j]));
            }
        }

        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int l = queries[i][0], r = queries[i][1];
            res[i] = g[l][r];
        }
        return res;
    }
}