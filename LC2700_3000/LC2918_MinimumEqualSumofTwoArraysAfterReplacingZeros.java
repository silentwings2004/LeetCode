package LC2700_3000;

public class LC2918_MinimumEqualSumofTwoArraysAfterReplacingZeros {
    /**
     * You are given two arrays nums1 and nums2 consisting of positive integers.
     *
     * You have to replace all the 0's in both arrays with strictly positive integers such that the sum of elements of
     * both arrays becomes equal.
     *
     * Return the minimum equal sum you can obtain, or -1 if it is impossible.
     *
     * Input: nums1 = [3,2,0,1,0], nums2 = [6,5,0]
     * Output: 12
     *
     * Input: nums1 = [2,0,2,0], nums2 = [1,4]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= nums1.length, nums2.length <= 105
     * 0 <= nums1[i], nums2[i] <= 106
     * @param nums1
     * @param nums2
     * @return
     */
    // time = O(m + n), space = O(1)
    public long minSum(int[] nums1, int[] nums2) {
        int a = 0, b = 0;
        long s1 = 0, s2 = 0;
        for (int x : nums1) {
            if (x == 0) {
                a++;
                s1++;
            } else s1 += x;
        }
        for (int x : nums2) {
            if (x == 0) {
                b++;
                s2++;
            } else s2 += x;
        }
        long res = 0;
        if (s1 == s2) res = s1;
        else if (s1 > s2) {
            if (b == 0) return -1;
            else res = s1;
        } else {
            if (a == 0) return -1;
            else res = s2;
        }
        return res;
    }
}