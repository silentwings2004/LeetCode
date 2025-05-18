package LC3001_3300;
import java.util.*;
public class LC3018_MaximumNumberofRemovalQueriesThatCanBeProcessedI {
    /**
     * You are given a 0-indexed array nums and a 0-indexed array queries.
     *
     * You can do the following operation at the beginning at most once:
     *
     * Replace nums with a
     * subarray
     *  of nums.
     * We start processing queries in the given order; for each query, we do the following:
     *
     * If the first and the last element of nums is less than queries[i], the processing of queries ends.
     * Otherwise, we choose either the first or the last element of nums if it is greater than or equal to queries[i],
     * and we remove the chosen element from nums.
     * Return the maximum number of queries that can be processed by doing the operation optimally.
     *
     * Input: nums = [1,2,3,4,5], queries = [1,2,3,4,6]
     * Output: 4
     *
     * Input: nums = [2,3,2], queries = [2,2,3]
     * Output: 3
     *
     * Input: nums = [3,4,3], queries = [4,3,2]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= queries.length <= 1000
     * 1 <= nums[i], queries[i] <= 10^9
     * @param nums
     * @param queries
     * @return
     */
    // time = O(n^2), space = O(n^2)
    public int maximumProcessableQueries(int[] nums, int[] queries) {
        int n = nums.length, m = queries.length;
        int[][] f = new int[n + 1][n + 1];
        f[0][n - 1] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= i; j--) {
                if (i > 0) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j]);
                    if (f[i - 1][j] < m && nums[i - 1] >= queries[f[i - 1][j]]) {
                        f[i][j] = Math.max(f[i][j], f[i - 1][j] + 1);
                    }
                }
                if (j + 1 < n) {
                    f[i][j] = Math.max(f[i][j], f[i][j + 1]);
                    if (f[i][j + 1] < m && nums[j + 1] >= queries[f[i][j + 1]]) {
                        f[i][j] = Math.max(f[i][j], f[i][j + 1] + 1);
                    }
                }
                if (f[i][j] == m) return m;
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) res = Math.max(res, f[i][i] + (nums[i] >= queries[f[i][i]] ? 1 : 0));
        return res;
    }
}
/**
 * f[i][j]: the maximum queries processed wihout removal of nums[i : j], so f[0][n - 1] = 0 as default.
 * f[i][j] can either come from f[i - 1][j] or f[i][j + 1] without any removal, or remove i - 1 or j + 1.
 */