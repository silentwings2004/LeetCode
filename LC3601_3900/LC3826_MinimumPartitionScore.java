package LC3601_3900;
import java.util.*;
public class LC3826_MinimumPartitionScore {
    /**
     * You are given an integer array nums and an integer k.
     *
     * Your task is to partition nums into exactly k subarrays and return an integer denoting the minimum possible score
     * among all valid partitions.
     *
     * The score of a partition is the sum of the values of all its subarrays.
     *
     * The value of a subarray is defined as sumArr * (sumArr + 1) / 2, where sumArr is the sum of its elements.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [5,1,2,1], k = 2
     * Output: 25
     *
     * Input: nums = [1,2,3,4], k = 1
     * Output: 55
     *
     * Input: nums = [1,1,1], k = 3
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 10^4
     * 1 <= k <= nums.length
     * @param nums
     * @param k
     * @return
     */
    // time = O(k * nlogn), space = O(k * nlogn)
    final long inf = (long) 1E18;
    public long minPartitionScore(int[] nums, int k) {
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + nums[i - 1];
        long[][] f = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) Arrays.fill(f[i], inf);
        for (int i = 1; i <= n; i++) f[i][1] = s[i] * (s[i] + 1) / 2;
        for (int j = 2; j <= k; j++) helper(f, j, 1, n, j - 1, n, s);
        return f[n][k];
    }

    private void helper(long[][] f, int j, int l, int r, int opl, int opr, long[] s) {
        if (l > r) return;
        int mid = l + r >> 1;
        int bo = -1;
        long bv = inf;
        int st = Math.max(opl, j - 1);
        int ed = Math.min(opr, mid - 1);
        for (int i = st; i <= ed; i++) {
            long sum = s[mid] - s[i];
            long v = sum * (sum + 1) / 2;
            long cand = f[i][j - 1] + v;
            if (cand < bv) {
                bv = cand;
                bo = i;
            }
        }
        f[mid][j] = bv;
        helper(f, j, l, mid - 1, opl, bo, s);
        helper(f, j, mid + 1, r, bo, opr, s);
    }
}