package LC3301_3600;
import java.util.*;
public class LC3489_ZeroArrayTransformationIV {
    /**
     * You are given an integer array nums of length n and a 2D array queries, where queries[i] = [li, ri, vali].
     *
     * Each queries[i] represents the following action on nums:
     *
     * Select a subset of indices in the range [li, ri] from nums.
     * Decrement the value at each selected index by exactly vali.
     * A Zero Array is an array with all its elements equal to 0.
     *
     * Return the minimum possible non-negative value of k, such that after processing the first k queries in sequence,
     * nums becomes a Zero Array. If no such k exists, return -1.
     *
     * A subset of an array is a selection of elements (possibly none) of the array.
     *
     * Input: nums = [2,0,2], queries = [[0,2,1],[0,2,1],[1,1,3]]
     * Output: 2
     *
     * Input: nums = [4,3,2,1], queries = [[1,3,2],[0,2,1]]
     * Output: -1
     *
     * Input: nums = [1,2,3,2,1], queries = [[0,1,1],[1,2,1],[2,3,2],[3,4,1],[4,4,1]]
     * Output: 4
     *
     * Input: nums = [1,2,3,2,6], queries = [[0,1,1],[0,2,1],[1,4,2],[4,4,4],[3,4,1],[4,4,5]]
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10
     * 0 <= nums[i] <= 1000
     * 1 <= queries.length <= 1000
     * queries[i] = [li, ri, vali]
     * 0 <= li <= ri < nums.length
     * 1 <= vali <= 10
     * @param nums
     * @param queries
     * @return
     */
    // time = O(n * m * k), space = O(n + k)
    public int minZeroArray(int[] nums, int[][] queries) {
        int n = nums.length, m = queries.length;
        int[] w = new int[n];
        Arrays.fill(w, -1);
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            if (x == 0) {
                w[i] = 0;
                continue;
            }
            boolean[] f = new boolean[x + 1];
            f[0] = true;
            for (int j = 0; j < m; j++) {
                int l = queries[j][0], r = queries[j][1], v = queries[j][2];
                if (i >= l && i <= r) {
                    for (int k = x - v; k >= 0; k--) {
                        if (f[k]) f[k + v] = true;
                    }
                }
                if (f[x]) {
                    w[i] = j + 1;
                    break;
                }
            }
            if (w[i] == -1) return -1;
        }
        int res = 0;
        for (int x : w) res = Math.max(res, x);
        return res;
    }
}