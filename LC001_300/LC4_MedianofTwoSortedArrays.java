package LC001_300;
import java.util.*;
public class LC4_MedianofTwoSortedArrays {
    /**
     * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
     *
     * Input: nums1 = [1,3], nums2 = [2]
     * Output: 2.00000
     *
     * Constraints:
     *
     * nums1.length == m
     * nums2.length == n
     * 0 <= m <= 1000
     * 0 <= n <= 1000
     * 1 <= m + n <= 2000
     * -106 <= nums1[i], nums2[i] <= 10^6
     *
     *
     * Follow up: The overall run time complexity should be O(log (m+n)).
     * @param nums1
     * @param nums2
     * @return
     */
    // S1: Recursion
    // time = O(log(m + n)), space = O(logm + logn)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if ((m + n) % 2 == 1) {
            return findKth(nums1, 0, nums2, 0, (m + n) / 2 + 1);
        } else {
            return (findKth(nums1, 0, nums2, 0, (m + n) / 2) + findKth(nums1, 0, nums2, 0, (m + n) / 2 + 1)) / 2.0;
        }
    }

    private int findKth(int[] nums1, int a, int[] nums2, int b, int k) {
        int m = nums1.length, n = nums2.length;
        if (m - a > n - b) return findKth(nums2, b, nums1, a, k);
        if (m - a == 0) return nums2[b + k - 1];
        if (k == 1) return Math.min(nums1[a], nums2[b]);

        int k1 = 0, k2 = 0;
        if (a + k / 2 >= m) k1 = m - a;
        else k1 = k / 2;
        k2 = k - k1;
        if (nums1[a + k1 - 1] < nums2[b + k2 - 1]) {
            return findKth(nums1, a + k1, nums2, b, k - k1);
        }
        return findKth(nums1, a, nums2, b + k2, k - k2);
    }

    // S2: 最优解！！！
    // time = O(log(min(m, n))), space = O(1)
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int m = nums1.length, n = nums2.length;
            if (m < n) return findMedianSortedArrays(nums2, nums1);

            int l = 0, r = n * 2;
            while (l <= r) {
                int mid2 = l + r >> 1;
                int mid1 = m + n - mid2;

                double L1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[(mid1 - 1) / 2];
                double L2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[(mid2 - 1) / 2];
                double R1 = (mid1 == m * 2) ? Integer.MAX_VALUE : nums1[mid1 / 2];
                double R2 = (mid2 == n * 2) ? Integer.MAX_VALUE : nums2[mid2 / 2];

                if (L1 > R2) l = mid2 + 1;
                else if (L2 > R1) r = mid2 - 1;
                else return (Math.max(L1, L2) + Math.min(R1, R2)) / 2;
            }
            return -1;
        }
    }
}
/**
 * The kth element of n sorted list -> 外排序
 * 每次都取n个list里的第一个，然后在这n个数里面挑一个最小的，把它拿出来，然后后面的补上，以此类推...
 * time = nlog(n * total nums)
 * 用B.S. => array与List最大差别就是array可以快速定位
 *
 * The kth element of two sorted arrays
 *    k/2 th
 * [XXX X0] XXX
 * YYY Y0 YYYYYYYY
 * k / 2 估摸在这附近
 * if X0 < Y0 -> 比Y0小的数至少有k - 1个 => 第k个元素肯定不是X0
 *
 * =>
 * XXX
 * YYYY0YYYYY    k - k / 2 -> k'
 * 变成一个递归问题，把上面的数组砍掉一半
 * X X0 X
 * [Y Y0] YY
 * if X0 > Y0 -> Y0不可能是第K个
 * 本题不是typical的二分解法，一般二分通常是：
 * 1. index 索引二分，给一个数组不断二分
 * 2. 值二分，最低值和最高值，不断二分看继续向上还是向下走
 * 这里不是找一个确切的位置，这里是一个不断删除的做法，不是很好归类
 * 像这种类似递归的做法就更难想了。
 */
