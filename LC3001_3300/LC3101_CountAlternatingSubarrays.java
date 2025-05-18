package LC3001_3300;

public class LC3101_CountAlternatingSubarrays {
    /**
     * You are given a binary array nums.
     *
     * We call a subarray alternating if no two adjacent elements in the subarray have the same value.
     *
     * Return the number of alternating subarrays in nums.
     *
     * Input: nums = [0,1,1,1]
     * Output: 5
     *
     * Input: nums = [1,0,1,0]
     * Output: 10
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * nums[i] is either 0 or 1.
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public long countAlternatingSubarrays(int[] nums) {
        int n = nums.length;
        long res = 1;
        for (int i = 1, j = 0; i < n; i++) {
            if (nums[i] != nums[i - 1]) {
                res += i - j + 1;
            } else {
                j = i;
                res++;
            }
        }
        return res;
    }
}
/**
 * 计算以 i 为右端点的子数组有多少个？
 */