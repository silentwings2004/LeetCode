package LC3301_3600;

public class LC3400_MaximumNumberofMatchingIndicesAfterRightShifts {
    /**
     * You are given two integer arrays, nums1 and nums2, of the same length.
     *
     * An index i is considered matching if nums1[i] == nums2[i].
     *
     * Return the maximum number of matching indices after performing any number of right shifts on nums1.
     *
     * A right shift is defined as shifting the element at index i to index (i + 1) % n, for all indices.
     *
     * Input: nums1 = [3,1,2,3,1,2], nums2 = [1,2,3,1,2,3]
     * Output: 6
     *
     * Input: nums1 = [1,4,2,5,3,1], nums2 = [2,3,1,2,4,6]
     * Output: 3
     *
     * Constraints:
     *
     * nums1.length == nums2.length
     * 1 <= nums1.length, nums2.length <= 3000
     * 1 <= nums1[i], nums2[i] <= 10^9
     * @param nums1
     * @param nums2
     * @return
     */
    // time = O(n^2), space = O(1)
    public int maximumMatchingIndices(int[] nums1, int[] nums2) {
        int n = nums1.length, res = 0;
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (nums1[(i + j) % n] == nums2[j]) cnt++;
            }
            res = Math.max(res, cnt);
        }
        return res;
    }
}