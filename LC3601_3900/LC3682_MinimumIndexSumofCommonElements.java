package LC3601_3900;
import java.util.*;
public class LC3682_MinimumIndexSumofCommonElements {
    /**
     * You are given two integer arrays nums1 and nums2 of equal length n.
     *
     * We define a pair of indices (i, j) as a good pair if nums1[i] == nums2[j].
     *
     * Return the minimum index sum i + j among all possible good pairs. If no such pairs exist, return -1.
     *
     * Input: nums1 = [3,2,1], nums2 = [1,3,1]
     * Output: 1
     *
     * Input: nums1 = [5,1,2], nums2 = [2,1,3]
     * Output: 2
     *
     * Input: nums1 = [6,4], nums2 = [7,8]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= nums1.length == nums2.length <= 10^5
     * -10^5 <= nums1[i], nums2[i] <= 10^5
     * @param nums1
     * @param nums2
     * @return
     */
    // time = O(n), space = O(n)
    public int minimumSum(int[] nums1, int[] nums2) {
        int n = nums1.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = nums1[i];
            if (!map.containsKey(x)) map.put(x, i);
        }
        int res = n * 2;
        for (int i = 0; i < n; i++) {
            int x = nums2[i];
            if (map.containsKey(x)) res = Math.min(res, i + map.get(x));
        }
        return res == n * 2 ? -1 : res;
    }
}