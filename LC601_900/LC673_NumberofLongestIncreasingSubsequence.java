package LC601_900;
import java.util.*;
public class LC673_NumberofLongestIncreasingSubsequence {
    /**
     * Given an unsorted array of integers, find the number of longest increasing subsequence.
     *
     * Example 1:
     * Input: [1,3,5,4,7]
     * Output: 2
     * Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1, 3, 5, 7].
     *
     * Example 2:
     * Input: [2,2,2,2,2]
     * Output: 5
     * Explanation: The length of longest continuous increasing subsequence is 1, and there are 5 subsequences' length
     * is 1, so output 5.
     *
     * Note: Length of the given array will be not exceed 2000 and the answer is guaranteed to be fit in 32-bit signed
     * int.
     *
     * @param nums
     * @return
     */
    // time = O(n^2), space = O(n)
    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] f = new int[n], g = new int[n];
        int maxl = 0, cnt = 0;

        for (int i = 0; i < n; i++) {
            f[i] = g[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (f[i] < f[j] + 1) {
                        f[i] = f[j] + 1;
                        g[i] = g[j];
                    } else if (f[i] == f[j] + 1) g[i] += g[j];
                }
            }
            if (maxl < f[i]) {
                maxl = f[i];
                cnt = g[i];
            } else if (maxl == f[i]) cnt += g[i];
        }
        return cnt;
    }
}