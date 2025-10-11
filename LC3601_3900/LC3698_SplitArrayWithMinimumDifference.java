package LC3601_3900;

public class LC3698_SplitArrayWithMinimumDifference {
    /**
     * You are given an integer array nums.
     *
     * Split the array into exactly two subarrays, left and right, such that left is strictly increasing and right is
     * strictly decreasing.
     *
     * Return the minimum possible absolute difference between the sums of left and right. If no valid split exists,
     * return -1.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * An array is said to be strictly increasing if each element is strictly greater than its previous one (if exists).
     *
     * An array is said to be strictly decreasing if each element is strictly smaller than its previous one (if exists).
     *
     * Input: nums = [1,3,2]
     * Output: 2
     *
     * Input: nums = [1,2,4,3]
     * Output: 4
     *
     * Input: nums = [3,1,2]
     * Output: -1
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public long splitArray(int[] nums) {
        final long inf = (long)1E12;
        int n = nums.length;
        boolean[] pre = new boolean[n], suf = new boolean[n];
        pre[0] = suf[n - 1] = true;
        for (int i = 1; i < n; i++) pre[i] = pre[i - 1] && (nums[i] > nums[i - 1]);
        for (int i = n - 2; i >= 0; i--) suf[i] = suf[i + 1] && (nums[i] > nums[i + 1]);
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + nums[i - 1];
        long res = inf;
        for (int i = 0; i + 1 < n; i++) {
            if (pre[i] && suf[i + 1]) {
                long ls = s[i + 1], rs = s[n] - s[i + 1];
                res = Math.min(res, Math.abs(ls - rs));
            }
        }
        return res == inf ? -1 : res;
    }

    // S2
    // time = O(n), space = O(1)
    public long splitArray2(int[] nums) {
        int n = nums.length;
        long pre = nums[0];
        int i = 1;
        while (i < n && nums[i] > nums[i - 1]) {
            pre += nums[i];
            i++;
        }

        long suf = nums[n - 1];
        int j = n - 2;
        while (j >= 0 && nums[j] > nums[j + 1]) {
            suf += nums[j];
            j--;
        }

        if (i - 1 < j) return -1;
        long d = pre - suf;
        if (i - 1 == j) return Math.abs(d);
        return Math.min(Math.abs(d + nums[i - 1]), Math.abs(d - nums[i - 1]));
    }
}