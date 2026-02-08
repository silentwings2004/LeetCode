package LC3601_3900;

import java.util.Arrays;

public class LC3825_LongestStrictlyIncreasingSubsequenceWithNonZeroBitwiseAND {
    /**
     * You are given an integer array nums.
     *
     * Return the length of the longest strictly increasing subsequence in nums whose bitwise AND is non-zero. If no
     * such subsequence exists, return 0.
     *
     * A subsequence is a non-empty array that can be derived from another array by deleting some or no elements without
     * changing the order of the remaining elements.
     *
     * Input: nums = [5,4,7]
     * Output: 2
     *
     * Input: nums = [2,3,6]
     * Output: 3
     *
     * Input: nums = [0,1]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public int longestSubsequence(int[] nums) {
        int n = nums.length, res = 0;
        for (int i = 0; i < 30; i++) {
            int[] f = new int[n + 1];
            int len = 0;
            for (int j = 0; j < n; j++) {
                int x = nums[j];
                if ((x >> i & 1) == 0) continue;
                int l = 0, r = len;
                while (l < r) {
                    int mid = l + r >> 1;
                    if (f[mid] < x) l = mid + 1;
                    else r = mid;
                }
                f[r] = x;
                if (r == len) len++;
            }
            res = Math.max(res, len);
        }
        return res;
    }

    // S2
    // time = O(nlogn * logU), space = O(n)  U: max(nums)
    public int longestSubsequence2(int[] nums) {
        int mx = nums[0];
        for (int x : nums) mx = Math.max(mx, x);
        int w = 32 - Integer.numberOfLeadingZeros(mx);

        int n = nums.length, res = 0;
        int[] f = new int[n];
        for (int i = 0; i < w; i++) {
            int len = 0;
            for (int x : nums) {
                if ((x >> i & 1) == 0) continue;
                int j = Arrays.binarySearch(f, 0, len, x);
                if (j < 0) j = ~j;
                f[j] = x;
                if (j == len) len++;
            }
            res = Math.max(res, len);
        }
        return res;
    }
}