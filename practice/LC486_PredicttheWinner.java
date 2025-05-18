package practice;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: PredicttheWinner
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 486. Predict the Winner
 */
public class LC486_PredicttheWinner {
    /**
     * Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end of the
     * array followed by the player 2 and then player 1 and so on. Each time a player picks a number, that number will
     * not be available for the next player. This continues until all the scores have been chosen. The player with the
     * maximum score wins.
     *
     * Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize
     * his score.
     *
     * Example 1:
     * Input: [1, 5, 2]
     * Output: False
     * Explanation: Initially, player 1 can choose between 1 and 2.
     * If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will
     * be left with 1 (or 2).
     * So, final score of player 1 is 1 + 2 = 3, and player 2 is 5.
     * Hence, player 1 will never be the winner and you need to return False.
     *
     * Example 2:
     * Input: [1, 5, 233, 7]
     * Output: True
     * Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. No matter which number
     * player 2 choose, player 1 can choose 233.
     * Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can
     * win.
     *
     * Note:
     * 1 <= length of the array <= 20.
     * Any scores in the given array are non-negative integers and will not exceed 10,000,000.
     * If the scores of both players are equal, then player 1 is still the winner.
     *
     * @param nums
     * @return
     */
    // DP
    // time = O(n^2), space = O(n^2)
    public boolean PredictTheWinner(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) return false;

        int len = nums.length;
        int[][] dp = new int[len][len];
        int sum = 0;
        for (int n : nums) sum += n;

        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                int val1 = i + 2 > j ? 0 : dp[i + 2][j];
                int val2 = i + 1 > j - 1 ? 0 : dp[i + 1][j - 1];
                int val3 = i > j - 2 ? 0 : dp[i][j - 2];
                dp[i][j] = Math.max(nums[i] + Math.min(val1, val2), nums[j] + Math.min(val2, val3));
            }
        }
        return dp[0][len - 1] >= sum - dp[0][len - 1];
    }
}
