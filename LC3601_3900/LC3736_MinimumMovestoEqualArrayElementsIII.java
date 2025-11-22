package LC3601_3900;

public class LC3736_MinimumMovestoEqualArrayElementsIII {
    /**
     * You are given an integer array nums.
     *
     * In one move, you may increase the value of any single element nums[i] by 1.
     *
     * Return the minimum total number of moves required so that all elements in nums become equal.
     *
     * Input: nums = [2,1,3]
     * Output: 3
     *
     * Input: nums = [4,4,5]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 100
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int minMoves(int[] nums) {
        int mx = nums[0], n = nums.length, s = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
            s += x;
        }
        return mx * n - s;
    }
}