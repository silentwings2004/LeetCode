package LC2700_3000;

public class LC2908_MinimumSumofMountainTripletsI {
    /**
     * You are given a 0-indexed array nums of integers.
     *
     * A triplet of indices (i, j, k) is a mountain if:
     *
     * i < j < k
     * nums[i] < nums[j] and nums[k] < nums[j]
     * Return the minimum possible sum of a mountain triplet of nums. If no such triplet exists, return -1.
     *
     * Input: nums = [8,6,1,5,3]
     * Output: 9
     *
     * Input: nums = [5,4,8,7,10,2]
     * Output: 13
     *
     * Input: nums = [6,5,4,3,4,5]
     * Output: -1
     *
     * Constraints:
     *
     * 3 <= nums.length <= 50
     * 1 <= nums[i] <= 50
     * @param nums
     * @return
     */
    // time = O(n^3), space = O(1)
    public int minimumSum(int[] nums) {
        int n = nums.length, res = 2510;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] < nums[j] && nums[j] > nums[k]) {
                        res = Math.min(res, nums[i] + nums[j] + nums[k]);
                    }
                }
            }
        }
        return res == 2510 ? -1 : res;
    }
}