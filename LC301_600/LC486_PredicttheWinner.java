package LC301_600;

public class LC486_PredicttheWinner {
    /**
     * You are given an integer array nums. Two players are playing a game with this array: player 1 and player 2.
     *
     * Player 1 and player 2 take turns, with player 1 starting first. Both players start the game with a score of 0.
     * At each turn, the player takes one of the numbers from either end of the array (i.e., nums[0] or
     * nums[nums.length - 1]) which reduces the size of the array by 1. The player adds the chosen number to their
     * score. The game ends when there are no more elements in the array.
     *
     * Return true if Player 1 can win the game. If the scores of both players are equal, then player 1 is still the
     * winner, and you should also return true. You may assume that both players are playing optimally.
     *
     * Input: nums = [1,5,2]
     * Output: false
     *
     * Input: nums = [1,5,233,7]
     * Output: true
     *
     * Constraints:
     *
     * 1 <= nums.length <= 20
     * 0 <= nums[i] <= 10^7
     * @param nums
     * @return
     */
    // time = O(n^2), space = O(n^2)
    public boolean PredictTheWinner(int[] nums) {
        int n = nums.length;
        int[][] f = new int[n][n];
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (len == 1) f[i][j] = nums[i];
                else {
                    f[i][j] = Math.max(nums[i] - f[i + 1][j], nums[j] - f[i][j - 1]);
                }
            }
        }
        return f[0][n - 1] >= 0;
    }
}
/**
 * 博弈论，经典dp
 * 区间dp的问题
 * f(0,n-1)
 * 无论什么时候，剩余的都是中间一段
 * 最多可以获得多少分数
 * f(i,j)
 * 1. 拿ai => -f(i+1,j) + ai
 * 2. 拿aj => -f(i,j-1) + aj
 * 取max
 */