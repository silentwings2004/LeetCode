package LC2700_3000;
import java.util.*;
public class LC2926_MaximumBalancedSubsequenceSum {
    /**
     * You are given a 0-indexed integer array nums.
     *
     * A subsequence of nums having length k and consisting of indices i0 < i1 < ... < ik-1 is balanced if the following
     * holds:
     *
     * nums[ij] - nums[ij-1] >= ij - ij-1, for every j in the range [1, k - 1].
     * A subsequence of nums having length 1 is considered balanced.
     *
     * Return an integer denoting the maximum possible sum of elements in a balanced subsequence of nums.
     *
     * A subsequence of an array is a new non-empty array that is formed from the original array by deleting some
     * (possibly none) of the elements without disturbing the relative positions of the remaining elements.
     *
     * Input: nums = [3,3,5,6]
     * Output: 14
     *
     * Input: nums = [5,-1,-3,8]
     * Output: 13
     *
     * Input: nums = [-2,-1]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * -10^9 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(n)
    long[] tr;
    int n;
    public long maxBalancedSubsequenceSum(int[] nums) {
        n = nums.length;
        tr = new long[n + 1];
        int[] p = new int[n];
        for (int i = 0; i < n; i++) p[i] = nums[i] - i;
        Arrays.sort(p);

        long res = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int pos = find(p, nums[i] - i);
            long t = Math.max(0, sum(pos + 1)) + nums[i];
            res = Math.max(res, t);
            add(pos + 1, t);
        }
        return res;
    }

    private int find(int[] p, int x) {
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (p[mid] <= x) l = mid;
            else r = mid - 1;
        }
        return p[r] <= x ? r : r - 1;
    }

    private int lowbit(int x) {
        return x & -x;
    }

    private void add(int x, long c) {
        for (int i = x; i <= n; i += lowbit(i)) tr[i] = Math.max(tr[i], c);
    }

    private long sum(int x) {
        long res = 0;
        for (int i = x; i > 0; i -= lowbit(i)) res = Math.max(res, tr[i]);
        return res;
    }
}