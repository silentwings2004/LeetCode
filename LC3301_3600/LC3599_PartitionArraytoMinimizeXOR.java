package LC3301_3600;
import java.util.*;
public class LC3599_PartitionArraytoMinimizeXOR {
    /**
     * You are given an integer array nums and an integer k.
     *
     * Your task is to partition nums into k non-empty subarrays. For each subarray, compute the bitwise XOR of all
     * its elements.
     *
     * Return the minimum possible value of the maximum XOR among these k subarrays.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,2,3], k = 2
     * Output: 1
     *
     * Input: nums = [2,3,3,2], k = 3
     * Output: 2
     *
     * Input: nums = [1,1,2,3,1], k = 2
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 250
     * 1 <= nums[i] <= 10^9
     * 1 <= k <= n
     * @param nums
     * @param k
     * @return
     */
    // time = O(n^2 * k), space = O(n * k)
    public int minXor(int[] nums, int k) {
        final long inf = (long)1E18;
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] ^ nums[i - 1];
        long[][] f = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) Arrays.fill(f[i], inf);
        f[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                for (int u = j - 1; u < i; u++) {
                    long x = s[i] ^ s[u];
                    long t = Math.max(f[u][j - 1], x);
                    f[i][j] = Math.min(f[i][j], t);
                }
            }
        }
        return (int)f[n][k];
    }
}