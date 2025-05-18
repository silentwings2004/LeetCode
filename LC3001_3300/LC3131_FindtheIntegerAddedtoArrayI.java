package LC3001_3300;
import java.util.*;
public class LC3131_FindtheIntegerAddedtoArrayI {
    /**
     * You are given two arrays of equal length, nums1 and nums2.
     *
     * Each element in nums1 has been increased (or decreased in the case of negative) by an integer, represented by
     * the variable x.
     *
     * As a result, nums1 becomes equal to nums2. Two arrays are considered equal when they contain the same integers
     * with the same frequencies.
     *
     * Return the integer x.
     *
     * Input: nums1 = [2,6,4], nums2 = [9,7,5]
     * Output: 3
     *
     * Input: nums1 = [10], nums2 = [5]
     * Output: -5
     *
     * Input: nums1 = [1,1,1,1], nums2 = [1,1,1,1]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums1.length == nums2.length <= 100
     * 0 <= nums1[i], nums2[i] <= 1000
     * The test cases are generated in a way that there is an integer x such that nums1 can become equal to nums2 by
     * adding x to each element of nums1.
     * @param nums1
     * @param nums2
     * @return
     */
    // time = O(n), space = O(1)
    public int addedInteger(int[] nums1, int[] nums2) {
        int a = nums1[0], b = nums2[0];
        for (int x : nums1) a = Math.min(a, x);
        for (int x : nums2) b = Math.min(b, x);
        return b - a;
    }
}