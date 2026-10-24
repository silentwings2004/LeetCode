package LC3901_4200;

public class LC4083_MinimumDeletionstoMakeArraySharp {
    /**
     * You are given an integer array nums.
     *
     * You may delete any number of elements from nums. The remaining elements keep their original order.
     *
     * The resulting array a is sharp if the bitwise XOR of every pair of adjacent elements is non-zero. That is, a[i]
     * XOR a[i + 1] != 0 for every index i such that 0 <= i < a.length - 1. An array with at most one element is sharp.
     *
     * Return the minimum number of deletions needed to make nums sharp.
     *
     * Bitwise XOR compares the corresponding bits of two integers. A bit in the result is 1 if the corresponding bits
     * differ, and 0 otherwise.
     *
     * Input: nums = [1,1,2,2,2,3]
     * Output: 3
     *
     * Input: nums = [4,4,9,4]
     * Output: 1
     *
     * Constraints:
     *
     * 2 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int minDeletions(int[] nums) {
        int n = nums.length, res = 0;
        for (int i = 1, j = 0; i < n; i++) {
            int x = nums[i], y = nums[j];
            if ((x ^ y) == 0) res++;
            else j = i;
        }
        return res;
    }
}