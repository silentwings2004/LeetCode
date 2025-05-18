package LC2401_2700;

public class LC2605_FormSmallestNumberFromTwoDigitArrays {
    /**
     * Given two arrays of unique digits nums1 and nums2, return the smallest number that contains at least one digit
     * from each array.
     *
     * Input: nums1 = [4,1,3], nums2 = [5,7]
     * Output: 15
     *
     * Input: nums1 = [3,5,2,6], nums2 = [3,1,7]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums1.length, nums2.length <= 9
     * 1 <= nums1[i], nums2[i] <= 9
     * All digits in each array are unique.
     * @param nums1
     * @param nums2
     * @return
     */
    // time = O(m * n), space = O(1)
    public int minNumber(int[] nums1, int[] nums2) {
        int res = 99;
        for (int x : nums1) {
            for (int y : nums2) {
                if (x == y) res = Math.min(res, x);
                else res = Math.min(res, Math.min(x * 10 + y, y * 10 + x));
            }
        }
        return res;
    }
}