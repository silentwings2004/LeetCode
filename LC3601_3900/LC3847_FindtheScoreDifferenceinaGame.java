package LC3601_3900;

public class LC3847_FindtheScoreDifferenceinaGame {
    /**
     * You are given an integer array nums, where nums[i] represents the points scored in the ith game.
     *
     * There are exactly two players. Initially, the first player is active and the second player is inactive.
     *
     * The following rules apply sequentially for each game i:
     *
     * If nums[i] is odd, the active and inactive players swap roles.
     * In every 6th game (that is, game indices 5, 11, 17, ...), the active and inactive players swap roles.
     * The active player plays the ith game and gains nums[i] points.
     * Return the score difference, defined as the first player's total score minus the second player's total score.
     *
     * Input: nums = [1,2,3]
     * Output: 0
     *
     * Input: nums = [2,4,2,1,2,1]
     * Output: 4
     *
     * Input: nums = [1]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= 1000
     * @param nums
     * @return
     */
    // time = O(n), space = O(1)
    public int scoreDifference(int[] nums) {
        int n = nums.length, res = 0;
        for (int i = 0, t = 0; i < n; i++) {
            if ((i + 1) % 6 == 0) t ^= 1;
            if (nums[i] % 2 == 1) t ^= 1;
            if (t == 0) res += nums[i];
            else res -= nums[i];
        }
        return res;
    }
}