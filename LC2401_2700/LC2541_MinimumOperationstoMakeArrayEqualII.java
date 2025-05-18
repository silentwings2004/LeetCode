package LC2401_2700;
import java.util.*;
public class LC2541_MinimumOperationstoMakeArrayEqualII {
    /**
     * You are given two integer arrays nums1 and nums2 of equal length n and an integer k. You can perform the
     * following operation on nums1:
     *
     * Choose two indexes i and j and increment nums1[i] by k and decrement nums1[j] by k. In other words,
     * nums1[i] = nums1[i] + k and nums1[j] = nums1[j] - k.
     * nums1 is said to be equal to nums2 if for all indices i such that 0 <= i < n, nums1[i] == nums2[i].
     *
     * Return the minimum number of operations required to make nums1 equal to nums2. If it is impossible to make
     * them equal, return -1.
     *
     * Input: nums1 = [4,3,1,4], nums2 = [1,3,7,1], k = 3
     * Output: 2
     *
     * Input: nums1 = [3,8,5,2], nums2 = [2,4,1,6], k = 1
     * Output: -1
     *
     * Constraints:
     *
     * n == nums1.length == nums2.length
     * 2 <= n <= 10^5
     * 0 <= nums1[i], nums2[j] <= 10^9
     * 0 <= k <= 10^5
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    // time = O(n), space = O(1)
    public long minOperations(int[] nums1, int[] nums2, int k) {
        if (k == 0) return Arrays.equals(nums1, nums2) ? 0 : -1;
        int n = nums1.length;
        long plus = 0, minus = 0;
        for (int i = 0; i < n; i++) {
            int x = nums1[i] - nums2[i];
            if (x % k != 0) return -1;
            if (x > 0) plus += x;
            else minus += x;
        }
        if (plus + minus != 0) return -1;
        return plus / k;
    }
}