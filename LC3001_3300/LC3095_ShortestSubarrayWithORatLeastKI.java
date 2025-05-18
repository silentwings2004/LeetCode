package LC3001_3300;

public class LC3095_ShortestSubarrayWithORatLeastKI {
    /**
     * You are given an array nums of non-negative integers and an integer k.
     *
     * An array is called special if the bitwise OR of all of its elements is at least k.
     *
     * Return the length of the shortest special non-empty subarray of nums, or return -1 if no special subarray exists.
     *
     * Input: nums = [1,2,3], k = 2
     * Output: 1
     *
     * Input: nums = [2,1,8], k = 10
     * Output: 3
     *
     * Input: nums = [1,2], k = 0
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 50
     * 0 <= nums[i] <= 50
     * 0 <= k < 64
     * @param nums
     * @param k
     * @return
     */
    // time = O(n^2), space = O(1)
    public int minimumSubarrayLength(int[] nums, int k) {
        int n = nums.length, res = n + 1;
        for (int i = 0; i < n; i++) {
            int t = 0;
            for (int j = i; j < n; j++) {
                t |= nums[j];
                if (t >= k) {
                    res = Math.min(res, j - i + 1);
                    break;
                }
            }
        }
        return res == n + 1 ? -1 : res;
    }
}