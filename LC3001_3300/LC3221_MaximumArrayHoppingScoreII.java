package LC3001_3300;

public class LC3221_MaximumArrayHoppingScoreII {
    /**
     * Given an array nums, you have to get the maximum score starting from index 0 and hopping until you reach the
     * last element of the array.
     *
     * In each hop, you can jump from index i to an index j > i, and you get a score of (j - i) * nums[j].
     *
     * Return the maximum score you can get.
     *
     * Input: nums = [1,5,8]
     * Output: 16
     *
     * Input: nums = [4,5,2,8,9,1,3]
     * Output: 42
     *
     * Constraints:
     *
     * 2 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^5
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public long maxScore(int[] nums) {
        int n = nums.length, mx = nums[n - 1];
        long res = 0;
        for (int i = n - 1; i > 0; i--) {
            mx = Math.max(mx, nums[i]);
            res += mx;
        }
        return res;
    }
}