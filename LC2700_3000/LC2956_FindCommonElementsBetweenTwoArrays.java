package LC2700_3000;
import java.util.*;
public class LC2956_FindCommonElementsBetweenTwoArrays {
    /**
     * You are given two 0-indexed integer arrays nums1 and nums2 of sizes n and m, respectively.
     *
     * Consider calculating the following values:
     *
     * The number of indices i such that 0 <= i < n and nums1[i] occurs at least once in nums2.
     * The number of indices i such that 0 <= i < m and nums2[i] occurs at least once in nums1.
     * Return an integer array answer of size 2 containing the two values in the above order.
     *
     * Input: nums1 = [4,3,2,3,1], nums2 = [2,2,5,2,3,6]
     * Output: [3,4]
     *
     * Input: nums1 = [3,4,2,3], nums2 = [1,5]
     * Output: [0,0]
     *
     * Constraints:
     *
     * n == nums1.length
     * m == nums2.length
     * 1 <= n, m <= 100
     * 1 <= nums1[i], nums2[i] <= 100
     * @param nums1
     * @param nums2
     * @return
     */
    // time = O(m + n), space = O(m + n)
    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        int[] res = new int[2];
        HashSet<Integer> s1 = new HashSet<>();
        HashSet<Integer> s2 = new HashSet<>();
        for (int x : nums1) s1.add(x);
        for (int x : nums2) s2.add(x);
        for (int x : nums1) {
            if (s2.contains(x)) res[0]++;
        }
        for (int x : nums2) {
            if (s1.contains(x)) res[1]++;
        }
        return res;
    }
}