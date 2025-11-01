package LC3601_3900;

public class LC3724_MinimumOperationstoTransformArray {
    /**
     * You are given two integer arrays nums1 of length n and nums2 of length n + 1.
     *
     * You want to transform nums1 into nums2 using the minimum number of operations.
     *
     * You may perform the following operations any number of times, each time choosing an index i:
     *
     * Increase nums1[i] by 1.
     * Decrease nums1[i] by 1.
     * Append nums1[i] to the end of the array.
     * Return the minimum number of operations required to transform nums1 into nums2.
     *
     * Input: nums1 = [2,8], nums2 = [1,7,3]
     * Output: 4
     *
     * Input: nums1 = [1,3,6], nums2 = [2,4,5,3]
     * Output: 4
     *
     * Input: nums1 = [2], nums2 = [3,4]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= n == nums1.length <= 10^5
     * nums2.length == n + 1
     * 1 <= nums1[i], nums2[i] <= 10^5
     * @param nums1
     * @param nums2
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public long minOperations(int[] nums1, int[] nums2) {
        int n = nums1.length, mn = 0x3f3f3f3f;
        boolean f = false;
        long res = 0;
        for (int i = 0; i < n; i++) {
            int t = Math.abs(nums1[i] - nums2[i]);
            res += t;
            if (!f && check(nums1[i], nums2[i], nums2[n])) f = true;
            mn = Math.min(mn, Math.min(Math.abs(nums1[i] - nums2[n]), Math.abs(nums2[i] - nums2[n])));
        }
        return res + 1 + (f ? 0 : mn);
    }

    private boolean check(int a, int b, int t) {
        if (a <= b && a <= t && t <= b) return true;
        if (a >= b && a >= t && t >= b) return true;
        return false;
    }

    // S2
    // time = O(n), space = O(1)
    public long minOperations2(int[] nums1, int[] nums2) {
        int n = nums1.length, t = nums2[n];
        long res = 1;
        int mn = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int x = Math.min(nums1[i], nums2[i]);
            int y = Math.max(nums1[i], nums2[i]);
            res += y - x;
            mn = Math.min(mn, Math.max(x - t, t - y));
        }
        return res + Math.max(mn, 0);
    }
}