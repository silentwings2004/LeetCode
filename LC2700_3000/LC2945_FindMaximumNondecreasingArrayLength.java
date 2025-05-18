package LC2700_3000;

public class LC2945_FindMaximumNondecreasingArrayLength {
    /**
     * You are given a 0-indexed integer array nums.
     *
     * You can perform any number of operations, where each operation involves selecting a subarray of the array and
     * replacing it with the sum of its elements. For example, if the given array is [1,3,5,6] and you select subarray
     * [3,5] the array will convert to [1,8,6].
     *
     * Return the maximum length of a non-decreasing array that can be made after applying operations.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [5,2,2]
     * Output: 1
     *
     * Input: nums = [1,2,3,4]
     * Output: 4
     *
     * Input: nums = [4,3,2,6]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int findMaximumLength(int[] nums) {
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + nums[i - 1];

        int[] prevSub = new int[n + 2];
        long[] f = new long[n + 1];
        int lastPos = 0;
        for (int i = 1; i <= n; i++) {
            lastPos = Math.max(lastPos, prevSub[i]);
            f[i] = f[lastPos] + 1;
            int p = lowerBound(s, 2 * s[i] - s[lastPos]);
            prevSub[p] = i;
        }
        return (int)f[n];
    }

    private int lowerBound(long[] s, long t) {
        int l = 0, r = s.length - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (s[mid] >= t) r = mid;
            else l = mid + 1;
        }
        return s[r] >= t ? r : r + 1;
    }
}