package LC3601_3900;
import java.util.*;
public class LC3868_MinimumCosttoEqualizeArraysUsingSwaps {
    /**
     * You are given two integer arrays nums1 and nums2 of size n.
     *
     * You can perform the following two operations any number of times on these two arrays:
     *
     * Swap within the same array: Choose two indices i and j. Then, choose either to swap nums1[i] and nums1[j], or
     * nums2[i] and nums2[j]. This operation is free of charge.
     * Swap between two arrays: Choose an index i. Then, swap nums1[i] and nums2[i]. This operation incurs a cost of 1.
     * Return an integer denoting the minimum cost to make nums1 and nums2 identical. If this is not possible, return -1.
     *
     * Input: nums1 = [10,20], nums2 = [20,10]
     * Output: 0
     *
     * Input: nums1 = [10,10], nums2 = [20,20]
     * Output: 1
     *
     * Input: nums1 = [10,20], nums2 = [30,40]
     * Output: -1
     *
     * Constraints:
     *
     * 2 <= n == nums1.length == nums2.length <= 8 * 10^4
     * 1 <= nums1[i], nums2[i] <= 8 * 10^4
     * @param nums1
     * @param nums2
     * @return
     */
    // time = O(n), space = O(n)
    public int minCost(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int x : nums1) map.put(x, map.getOrDefault(x, 0) + 1);
        for (int x : nums2) {
            if (map.containsKey(x)) map.put(x, map.get(x) - 1);
            else map.put(x, map.getOrDefault(x, 0) - 1);
        }
        int s = 0;
        for (int v : map.values()) {
            if (v % 2 != 0) return -1;
            if (v > 0) s += v;
        }
        return s / 2;
    }
}