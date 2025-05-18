package LC2700_3000;

public class LC2771_LongestNondecreasingSubarrayFromTwoArrays {
    /**
     * You are given two 0-indexed integer arrays nums1 and nums2 of length n.
     *
     * Let's define another 0-indexed integer array, nums3, of length n. For each index i in the range [0, n - 1], you
     * can assign either nums1[i] or nums2[i] to nums3[i].
     *
     * Your task is to maximize the length of the longest non-decreasing subarray in nums3 by choosing its values
     * optimally.
     *
     * Return an integer representing the length of the longest non-decreasing subarray in nums3.
     *
     * Note: A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums1 = [2,3,1], nums2 = [1,2,1]
     * Output: 2
     *
     * Input: nums1 = [1,3,2,1], nums2 = [2,2,3,4]
     * Output: 4
     *
     * Input: nums1 = [1,1], nums2 = [2,2]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums1.length == nums2.length == n <= 10……5
     * 1 <= nums1[i], nums2[i] <= 10……9
     * @param nums1
     * @param nums2
     * @return
     */
    // time = O(n), space = O(n)
    public int maxNonDecreasingLength(int[] nums1, int[] nums2) {
        int n = nums1.length, res = 1;
        int[][] f = new int[n][2];
        f[0][0] = f[0][1] = 1;
        for (int i = 1; i < n; i++) {
            if (nums1[i] >= nums1[i - 1]) f[i][0] = f[i - 1][0] + 1;
            else f[i][0] = 1;
            if (nums1[i] >= nums2[i - 1]) f[i][0] = Math.max(f[i][0], f[i - 1][1] + 1);
            else f[i][0] = Math.max(f[i][0], 1);

            if (nums2[i] >= nums1[i - 1]) f[i][1] = f[i - 1][0] + 1;
            else f[i][1] = 1;
            if (nums2[i] >= nums2[i - 1]) f[i][1] = Math.max(f[i][1], f[i - 1][1] + 1);
            else f[i][1] = Math.max(f[i][1], 1);
            res = Math.max(res, Math.max(f[i][0], f[i][1]));
        }
        return res;
    }
}