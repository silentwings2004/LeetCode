package LC3301_3600;
import java.util.*;
public class LC3409_LongestSubsequenceWithDecreasingAdjacentDifference {
    /**
     * You are given an array of integers nums.
     *
     * Your task is to find the length of the longest subsequence seq of nums, such that the absolute differences
     * between consecutive elements form a non-increasing sequence of integers. In other words, for a subsequence seq0,
     * seq1, seq2, ..., seqm of nums, |seq1 - seq0| >= |seq2 - seq1| >= ... >= |seqm - seqm - 1|.
     *
     * Return the length of such a subsequence.
     *
     * A subsequence is an non-empty array that can be derived from another array by deleting some or no elements
     * without changing the order of the remaining elements.
     *
     * Input: nums = [16,6,3]
     * Output: 3
     *
     * Input: nums = [6,5,3,4,2,1]
     * Output: 4
     *
     * Input: nums = [10,20,10,19,10,20]
     * Output: 5
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^4
     * 1 <= nums[i] <= 300
     * @param nums
     * @return
     */
    // time = O(k * n), space = O(k^2)
    public int longestSubsequence(int[] nums) {
        int n = nums.length, res = 0;
        int[][] f = new int[302][302];
        int[][] g = new int[302][302];
        Arrays.fill(g[nums[0]], 1);

        for (int i = 1; i < n; i++) {
            int x = nums[i];
            for (int j = 1; j <= 300; j++) {
                int d = Math.abs(x - j);
                f[x][d] = Math.max(f[x][d], g[j][d] + 1);
                res = Math.max(res, f[x][d]);
            }

            for (int k = 300; k >= 0; k--) {
                g[x][k] = Math.max(g[x][k + 1], f[x][k]);
            }
        }
        return res;
    }
}