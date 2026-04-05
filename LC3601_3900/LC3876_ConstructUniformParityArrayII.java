package LC3601_3900;
import java.util.*;
public class LC3876_ConstructUniformParityArrayII {
    /**
     * You are given an array nums1 of n distinct integers.
     *
     * You want to construct another array nums2 of length n such that the elements in nums2 are either all odd or all
     * even.
     *
     * For each index i, you must choose exactly one of the following (in any order):
     *
     * nums2[i] = nums1[i]
     * nums2[i] = nums1[i] - nums1[j], for an index j != i, such that nums1[i] - nums1[j] >= 1
     * Return true if it is possible to construct such an array, otherwise return false.
     *
     * Input: nums1 = [1,4,7]
     * Output: true
     *
     * Input: nums1 = [2,3]
     * Output: false
     *
     * Input: nums1 = [4,6]
     * Output: true
     *
     * Constraints:
     *
     * 1 <= n == nums1.length <= 10^5
     * 1 <= nums1[i] <= 10^9
     * nums1 consists of distinct integers.
     * @param nums1
     * @return
     */
    // time = O(n), space = O(1)
    public boolean uniformArray(int[] nums1) {
        int odd = Integer.MAX_VALUE;
        for (int x : nums1) {
            if (x % 2 == 1) odd = Math.min(odd, x);
        }
        if (odd == Integer.MAX_VALUE) return true;
        for (int x : nums1) {
            if (x % 2 == 0 && x - odd < 1) return false;
        }
        return true;
    }
}