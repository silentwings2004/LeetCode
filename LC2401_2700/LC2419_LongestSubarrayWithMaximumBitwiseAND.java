package LC2401_2700;

public class LC2419_LongestSubarrayWithMaximumBitwiseAND {
    /**
     * You are given an integer array nums of size n.
     *
     * Consider a non-empty subarray from nums that has the maximum possible bitwise AND.
     *
     * In other words, let k be the maximum value of the bitwise AND of any subarray of nums. Then, only subarrays with
     * a bitwise AND equal to k should be considered.
     * Return the length of the longest such subarray.
     *
     * The bitwise AND of an array is the bitwise AND of all the numbers in it.
     *
     * A subarray is a contiguous sequence of elements within an array.
     *
     * Input: nums = [1,2,3,3,2,2]
     * Output: 2
     *
     * Input: nums = [1,2,3,4]
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^6
     * @param nums
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int longestSubarray(int[] nums) {
        int n = nums.length, res = 0, max = 0;
        for (int i = 0; i < n; i++) {
            int j = i;
            while (j < n && nums[j] == nums[i]) j++;
            if (nums[i] > max) {
                max = nums[i];
                res = j - i;
            } else if (nums[i] == max) res = Math.max(res, j - i);
            i = j - 1;
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int longestSubarray2(int[] nums) {
        int n = nums.length, max = nums[0], res = 0;
        for (int i = 0; i < n; i++) {
            int j = i + 1, sum = nums[i];
            while (j < n && (sum & nums[j]) >= Math.max(sum, nums[j])) sum &= nums[j++];
            if (sum > max) {
                max = sum;
                res = j - i;
            } else if (sum == max) res = Math.max(res, j - i);
            i = j - 1;
        }
        return res;
    }
}
