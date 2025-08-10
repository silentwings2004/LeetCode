package LC3601_3900;

public class LC3641_LongestSemiRepeatingSubarray {
    /**
     * You are given an integer array nums of length n and an integer k.
     *
     * A semi‑repeating subarray is a contiguous subarray in which at most k elements repeat (i.e., appear more than once).
     *
     * Return the length of the longest semi‑repeating subarray in nums.
     *
     * Input: nums = [1,2,3,1,2,3,4], k = 2
     * Output: 6
     *
     * Input: nums = [1,1,1,1,1], k = 4
     * Output: 5
     *
     * Input: nums = [1,1,1,1,1], k = 0
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * 0 <= k <= nums.length
     * @param nums
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public int longestSubarray(int[] nums, int k) {
        int n = nums.length, res = 0;
        int[] cnt = new int[100010];
        for (int i = 0, j = 0, t = 0; i < n; i++) {
            cnt[nums[i]]++;
            if (cnt[nums[i]] == 2) t++;
            while (t > k) {
                cnt[nums[j]]--;
                if (cnt[nums[j]] == 1) t--;
                j++;
            }
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}