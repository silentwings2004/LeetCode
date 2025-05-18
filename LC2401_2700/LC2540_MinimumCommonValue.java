package LC2401_2700;

public class LC2540_MinimumCommonValue {
    /**
     * Given two integer arrays nums1 and nums2, sorted in non-decreasing order, return the minimum integer common to
     * both arrays. If there is no common integer amongst nums1 and nums2, return -1.
     *
     * Note that an integer is said to be common to nums1 and nums2 if both arrays have at least one occurrence of that
     * integer.
     *
     * Input: nums1 = [1,2,3], nums2 = [2,4]
     * Output: 2
     *
     * Input: nums1 = [1,2,3,6], nums2 = [2,3,4,5]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums1.length, nums2.length <= 10^5
     * 1 <= nums1[i], nums2[j] <= 10^9
     * Both nums1 and nums2 are sorted in non-decreasing order.
     * @param nums1
     * @param nums2
     * @return
     */
    // time = O(min(m, n)), space = O(1)
    public int getCommon(int[] nums1, int[] nums2) {
        int res = -1;
        for (int i = 0, j = 0; i < nums1.length && j < nums2.length;) {
            if (nums1[i] == nums2[j]) {
                res = nums1[i];
                break;
            } else if (nums1[i] < nums2[j]) i++;
            else j++;
        }
        return res;
    }
}