package LC3601_3900;

public class LC3872_LongestArithmeticSequenceAfterChangingAtMostOneElement {
    /**
     * You are given an integer array nums.
     *
     * A subarray is arithmetic if the difference between consecutive elements in the subarray is constant.
     *
     * You can replace at most one element in nums with any integer. Then, you select an arithmetic subarray from nums.
     *
     * Return an integer denoting the maximum length of the arithmetic subarray you can select.
     *
     * A subarray is a contiguous sequence of elements within an array.
     *
     * Input: nums = [9,7,5,10,1]
     * Output: 5
     *
     * Input: nums = [1,2,6,7]
     * Output: 3
     *
     * Constraints:
     *
     * 4 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public int longestArithmetic(int[] nums) {
        int n = nums.length;
        int[] b = new int[n - 1];
        b[0] = 1;
        for (int i = 0; i + 1 < n; i++) b[i] = nums[i + 1] - nums[i];
        int m = n - 1;
        int[] l = new int[m], r = new int[m];
        l[0] = 1;
        for (int i = 1; i < m; i++) l[i] = b[i] == b[i - 1] ? l[i - 1] + 1 : 1;
        r[m - 1] = 1;
        for (int i = m - 2; i >= 0; i--) r[i] = b[i] == b[i + 1] ? r[i + 1] + 1 : 1;

        int res = 0;
        for (int i = 0; i < m - 1; i++) res = Math.max(res, l[i] + 2);
        for (int i = m - 1; i > 0; i--) res = Math.max(res, r[i] + 2);
        for (int i = 0; i < m - 1; i++) {
            long s = b[i] + b[i + 1];
            if (s % 2 == 0) {
                long v = s / 2;
                int len = 2;
                if (i > 0 && b[i - 1] == v) len += l[i - 1];
                if (i + 2 < m && b[i + 2] == v) len += r[i + 2];
                res = Math.max(res, len + 1);
            }
        }
        return res;
    }
}