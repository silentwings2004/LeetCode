package LC3601_3900;

public class LC3737_CountSubarraysWithMajorityElementI {
    /**
     * You are given an integer array nums and an integer target.
     *
     * Return the number of subarrays of nums in which target is the majority element.
     *
     * The majority element of a subarray is the element that appears strictly more than half of the times in that subarray.
     *
     * A subarray is a contiguous non-empty sequence of elements within an array.
     *
     * Input: nums = [1,2,2,3], target = 2
     * Output: 5
     *
     * Input: nums = [1,1,1,1], target = 1
     * Output: 10
     *
     * Input: nums = [1,2,3], target = 4
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 10^9
     * 1 <= target <= 10^9
     * @param nums
     * @param target
     * @return
     */
    // time = O(n^2), space = O(n)
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length, res = 0;
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + (nums[i - 1] == target ? 1 : -1);
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (s[j + 1] - s[i] > 0) res++;
            }
        }
        return res;
    }
}