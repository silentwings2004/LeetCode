package LC2401_2700;
import java.util.*;
public class LC2547_MinimumCosttoSplitanArray {
    /**
     * You are given an integer array nums and an integer k.
     *
     * Split the array into some number of non-empty subarrays. The cost of a split is the sum of the importance value
     * of each subarray in the split.
     *
     * Let trimmed(subarray) be the version of the subarray where all numbers which appear only once are removed.
     *
     * For example, trimmed([3,1,2,4,3,4]) = [3,4,3,4].
     * The importance value of a subarray is k + trimmed(subarray).length.
     *
     * For example, if a subarray is [1,2,3,3,3,4,4], then trimmed([1,2,3,3,3,4,4]) = [3,3,3,4,4].The importance value
     * of this subarray will be k + 5.
     * Return the minimum possible cost of a split of nums.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,2,1,2,1,3,3], k = 2
     * Output: 8
     *
     * Input: nums = [1,2,1,2,1], k = 2
     * Output: 6
     *
     * Input: nums = [1,2,1,2,1], k = 5
     * Output: 10
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 0 <= nums[i] < nums.length
     * 1 <= k <= 10^9
     * @param nums
     * @param k
     * @return
     */
    // time = O(n^2), space = O(n)
    public int minCost(int[] nums, int k) {
        int n = nums.length;
        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 0;
        int[] cnt = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(cnt, 0);
            for (int j = i, len = 0; j > 0; j--) {
                cnt[nums[j - 1]]++;
                if (cnt[nums[j - 1]] > 1) {
                    if (cnt[nums[j - 1]] == 2) len += 2;
                    else len++;
                }
                f[i] = Math.min(f[i], f[j - 1] + len + k);
            }
        }
        return f[n];
    }
}
/**
 * dp[i]: the minimum possible cost of a split of nums[0:i]
 * x x x [x x x i]
 *        j
 * dp[i] = min{dp[j - 1] + score[j:i]}
 */