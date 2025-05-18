package LC2401_2700;

public class LC2425_BitwiseXORofAllPairings {
    /**
     * You are given two 0-indexed arrays, nums1 and nums2, consisting of non-negative integers. There exists another
     * array, nums3, which contains the bitwise XOR of all pairings of integers between nums1 and nums2 (every integer
     * in nums1 is paired with every integer in nums2 exactly once).
     *
     * Return the bitwise XOR of all integers in nums3.
     *
     * Input: nums1 = [2,1,3], nums2 = [10,2,5,0]
     * Output: 13
     *
     * Input: nums1 = [1,2], nums2 = [3,4]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums1.length, nums2.length <= 10^5
     * 0 <= nums1[i], nums2[j] <= 10^9
     * @param nums1
     * @param nums2
     * @return
     */
    // time = O(m + n), space = O(1)
    public int xorAllNums(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length, res = 0;
        if (n % 2 == 1) {
            for (int x : nums1) res ^= x;
        }
        if (m % 2 == 1) {
            for (int x : nums2) res ^= x;
        }
        return res;
    }
}