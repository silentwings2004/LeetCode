package LC3901_4200;

public class LC3903_SmallestStableIndexI {
    /**
     * You are given an integer array nums of length n and an integer k.
     *
     * For each index i, define its instability score as max(nums[0..i]) - min(nums[i..n - 1]).
     *
     * In other words:
     *
     * max(nums[0..i]) is the largest value among the elements from index 0 to index i.
     * min(nums[i..n - 1]) is the smallest value among the elements from index i to index n - 1.
     * An index i is called stable if its instability score is less than or equal to k.
     *
     * Return the smallest stable index. If no such index exists, return -1.
     *
     * Input: nums = [5,0,1,4], k = 3
     * Output: 3
     *
     * Input: nums = [3,2,1], k = 1
     * Output: -1
     *
     * Input: nums = [0], k = 0
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 10^9
     * 0 <= k <= 10^9
     * @param nums
     * @param k
     * @return
     */
    // time = O(n^2), space = O(1)
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length, mx = nums[0];
        for (int i = 0; i < n; i++) {
            mx = Math.max(mx, nums[i]);
            int mn = nums[i];
            for (int j = i; j < n; j++) {
                mn = Math.min(mn, nums[j]);
            }
            if (mx - mn <= k) return i;
        }
        return -1;
    }
}