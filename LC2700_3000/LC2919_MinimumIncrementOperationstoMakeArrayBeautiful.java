package LC2700_3000;
import java.util.*;
public class LC2919_MinimumIncrementOperationstoMakeArrayBeautiful {
    /**
     * You are given a 0-indexed integer array nums having length n, and an integer k.
     *
     * You can perform the following increment operation any number of times (including zero):
     *
     * Choose an index i in the range [0, n - 1], and increase nums[i] by 1.
     * An array is considered beautiful if, for any subarray with a size of 3 or more, its maximum element is greater
     * than or equal to k.
     *
     * Return an integer denoting the minimum number of increment operations needed to make nums beautiful.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [2,3,0,0,2], k = 4
     * Output: 3
     *
     * Input: nums = [0,1,3,3], k = 5
     * Output: 2
     *
     * Input: nums = [1,1,2], k = 1
     * Output: 0
     *
     * Constraints:
     *
     * 3 <= n == nums.length <= 10^5
     * 0 <= nums[i] <= 10^9
     * 0 <= k <= 10^9
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public long minIncrementOperations(int[] nums, int k) {
        int n = nums.length;
        long INF = (long)1e14;
        long[] f = new long[n + 1];
        Arrays.fill(f, INF);
        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            int t = Math.max(0, k - nums[i - 1]);
            if (i >= 1) f[i] = Math.min(f[i], f[i - 1] + t);
            if (i >= 2) f[i] = Math.min(f[i], f[i - 2] + t);
            if (i >= 3) f[i] = Math.min(f[i], f[i - 3] + t);
        }
        return Math.min(f[n], Math.min(f[n - 1], f[n - 2]));
    }
}