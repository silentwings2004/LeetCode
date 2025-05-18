package LC2700_3000;

public class LC2772_ApplyOperationstoMakeAllArrayElementsEqualtoZero {
    /**
     * You are given a 0-indexed integer array nums and a positive integer k.
     *
     * You can apply the following operation on the array any number of times:
     *
     * Choose any subarray of size k from the array and decrease all its elements by 1.
     * Return true if you can make all the array elements equal to 0, or false otherwise.
     *
     * A subarray is a contiguous non-empty part of an array.
     *
     * Input: nums = [2,2,3,1,1,0], k = 3
     * Output: true
     *
     * Input: nums = [1,3,1,1], k = 2
     * Output: false
     *
     * Constraints:
     *
     * 1 <= k <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^6
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public boolean checkArray(int[] nums, int k) {
        int n = nums.length;
        long[] b = new long[n + 1];
        long s = 0;
        for (int i = 0; i < n; i++) {
            s += b[i];
            if (i + k - 1 >= n && nums[i] != s) return false;
            if (nums[i] < s) return false;
            long t = nums[i] - s;
            s += t;
            if (i + k < n) b[i + k] -= t;
        }
        return true;
    }
}