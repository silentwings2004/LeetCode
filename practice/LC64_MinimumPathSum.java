package practice;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: MinimumPathSum
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 64. Minimum Path Sum
 */
public class LC64_MinimumPathSum {
    /**
     * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes
     * the sum of all numbers along its path.
     *
     * Note: You can only move either down or right at any point in time.
     *
     * Example:
     *
     * Input:
     * [
     *   [1,3,1],
     *   [1,5,1],
     *   [4,2,1]
     * ]
     * Output: 7
     * Explanation: Because the path 1→3→1→1→1 minimizes the sum.
     * @param grid
     * @return
     */
    // DP
    // time = O(m * n), space = O(m * n)
    public int minPathSum(int[][] grid) {
        // corner case
        if (grid == null || grid.length == 0|| grid[0] == null || grid[0].length == 0) return -1;

        int row = grid.length, col = grid[0].length;
        int[][] dp = new int[row + 1][col + 1]; //多开一维
        for (int i = 0; i <= row; i++) dp[i][0] = Integer.MAX_VALUE;
        for (int i = 0; i <= col; i++) dp[0][i] = Integer.MAX_VALUE;


        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (i == 1 && j == 1) dp[i][j] = grid[i - 1][j - 1];
                else dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
            }
        }
        return dp[row][col];
    }
}
