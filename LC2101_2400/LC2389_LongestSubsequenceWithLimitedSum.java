package LC2101_2400;
import java.util.*;
public class LC2389_LongestSubsequenceWithLimitedSum {
    /**
     * You are given an integer array nums of length n, and an integer array queries of length m.
     *
     * Return an array answer of length m where answer[i] is the maximum size of a subsequence that you can take from
     * nums such that the sum of its elements is less than or equal to queries[i].
     *
     * A subsequence is an array that can be derived from another array by deleting some or no elements without changing
     * the order of the remaining elements.
     *
     * Input: nums = [4,5,2,1], queries = [3,10,21]
     * Output: [2,3,4]
     *
     * Input: nums = [2,3,4,5], queries = [1]
     * Output: [0]
     *
     * Constraints:
     *
     * n == nums.length
     * m == queries.length
     * 1 <= n, m <= 1000
     * 1 <= nums[i], queries[i] <= 10^6
     * @param nums
     * @param queries
     * @return
     */
    // time = O(nlogn + m), space = O(1)
    public int[] answerQueries(int[] nums, int[] queries) {
        int n = nums.length, m = queries.length;
        Arrays.sort(nums);
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int k = 0, s = 0;
            while (k < n && s + nums[k] <= queries[i]) s += nums[k++];
            res[i] = k;
        }
        return res;
    }
}
