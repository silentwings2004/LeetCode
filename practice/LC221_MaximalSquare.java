package practice;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: MaximalSquare
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 221. Maximal Square
 */
public class LC221_MaximalSquare {
    /**
     * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
     *
     * Example:
     *
     * Input:
     *
     * 1 0 1 0 0
     * 1 0 1 1 1
     * 1 1 1 1 1
     * 1 0 0 1 0
     *
     * Output: 4
     * @param matrix
     * @return
     */
    // S1: DP I  --> time = O(m * n), space = O(m * n)
    public int maximalSquare(char[][] matrix) {
        // corner case
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows][cols];

        dp[0][0] = Character.getNumericValue(matrix[0][0]);
        int max = dp[0][0];

        for (int i = 1; i < rows; i++) {
            dp[i][0] = Character.getNumericValue(matrix[i][0]);
            max = Math.max(max, dp[i][0]);
        }
        for (int i = 1; i < cols; i++) {
            dp[0][i] = Character.getNumericValue(matrix[0][i]);
            max = Math.max(max, dp[0][i]);
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if (Character.getNumericValue(matrix[i][j]) == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max * max;
    }

    // S2:
    public int maximalSquare2(char[][] matrix) {
        // corner case
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;

        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] dp = new int[rows + 1][cols + 1];
        int res = 0;

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res * res;
    }
}
