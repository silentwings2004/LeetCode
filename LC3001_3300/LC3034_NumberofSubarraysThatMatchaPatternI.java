package LC3001_3300;

public class LC3034_NumberofSubarraysThatMatchaPatternI {
    /**
     * You are given a 0-indexed integer array nums of size n, and a 0-indexed integer array pattern of size m
     * consisting of integers -1, 0, and 1.
     *
     * A subarray nums[i..j] of size m + 1 is said to match the pattern if the following conditions hold for each
     * element pattern[k]:
     *
     * nums[i + k + 1] > nums[i + k] if pattern[k] == 1.
     * nums[i + k + 1] == nums[i + k] if pattern[k] == 0.
     * nums[i + k + 1] < nums[i + k] if pattern[k] == -1.
     * Return the count of subarrays in nums that match the pattern.
     *
     * Input: nums = [1,2,3,4,5,6], pattern = [1,1]
     * Output: 4
     *
     * Input: nums = [1,4,4,1,3,5,5,3], pattern = [1,0,-1]
     * Output: 2
     *
     * Constraints:
     *
     * 2 <= n == nums.length <= 100
     * 1 <= nums[i] <= 10^9
     * 1 <= m == pattern.length < n
     * -1 <= pattern[i] <= 1
     * @param nums
     * @param pattern
     * @return
     */
    // time = O(m * n), space = O(1)
    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        int n = nums.length, m = pattern.length, res = 0;
        for (int i = 0; i + m < n; i++) {
            boolean flag = true;
            for (int j = 0; j < m; j++) {
                int x = pattern[j];
                if (x == 1 && nums[i + j + 1] > nums[i + j]) continue;
                if (x == 0 && nums[i + j + 1] == nums[i + j]) continue;
                if (x == -1 && nums[i + j + 1] < nums[i + j]) continue;
                flag = false;
                break;
            }
            if (flag) res++;
        }
        return res;
    }
}