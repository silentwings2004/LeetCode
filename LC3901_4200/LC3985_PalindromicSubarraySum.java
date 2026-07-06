package LC3901_4200;

public class LC3985_PalindromicSubarraySum {
    /**
     * You are given an integer array nums.
     *
     * Your task is to find the maximum sum of a subarray of nums that is a palindrome.
     *
     * Return the maximum sum of such a subarray.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * A subarray is a palindrome if it reads the same forward and backward.
     *
     * Input: nums = [10,10]
     * Output: 20
     *
     * Input: nums = [1,2,3,2,1,5,6]
     * Output: 9
     *
     * Input: nums = [7,1,2,1,7,3,4,3,4]
     * Output: 18
     *
     * Input: nums = [1,2,3,4,5]
     * Output: 5
     *
     * Input: nums = [1000]
     * Output: 1000
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     * @param nums
     * @return
     */
    // time = O(n), space = O(n)
    public long getSum(int[] nums) {
        int n = nums.length;
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + nums[i - 1];

        int[] d1 = new int[n]; // odd palin
        int l = 0, r = -1;
        for (int i = 0; i < n; i++) {
            int k = i > r ? 1 : Math.min(d1[l + r - i], r - i + 1);
            while (i - k >= 0 && i + k < n && nums[i - k] == nums[i + k]) k++;
            d1[i] = k;
            if (i + k - 1 > r) {
                l = i - k + 1;
                r = i + k - 1;
            }
        }

        int[] d2 = new int[n]; // even palin
        l = 0;
        r = -1;
        for (int i = 0; i < n; i++) {
            int k = i > r ? 0 : Math.min(d2[l + r - i + 1], r - i + 1);
            while (i - k - 1 >= 0 && i + k < n && nums[i - k - 1] == nums[i + k]) k++;
            d2[i] = k;
            if (i + k - 1 > r) {
                l = i - k;
                r = i + k - 1;
            }
        }

        long res = 0;
        for (int i = 0; i < n; i++) {
            int left = i - d1[i] + 1, right = i + d1[i] - 1;
            res = Math.max(res, s[right + 1] - s[left]);
        }
        for (int i = 0; i < n; i++) {
            int left = i - d2[i], right = i + d2[i] - 1;
            res = Math.max(res, s[right + 1] - s[left]);
        }
        return res;
    }
}