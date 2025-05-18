package practice;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: CountSquareSubmatriceswithAllOnes
 * Creater: Silentwings
 * Date: May, 2020
 * Description: 1277. Count Square Submatrices with All Ones
 */
public class LC1277_CountSquareSubmatriceswithAllOnes {
    /**
     * Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
     *
     *
     *
     * Example 1:
     *
     * Input: matrix =
     * [
     *   [0,1,1,1],
     *   [1,1,1,1],
     *   [0,1,1,1]
     * ]
     * Output: 15
     * Explanation:
     * There are 10 squares of side 1.
     * There are 4 squares of side 2.
     * There is  1 square of side 3.
     * Total number of squares = 10 + 4 + 1 = 15.
     *
     * Example 2:
     *
     * Input: matrix =
     * [
     *   [1,0,1],
     *   [1,1,0],
     *   [1,1,0]
     * ]
     * Output: 7
     * Explanation:
     * There are 6 squares of side 1.
     * There is 1 square of side 2.
     * Total number of squares = 6 + 1 = 7.
     *
     *
     * Constraints:
     *
     * 1 <= arr.length <= 300
     * 1 <= arr[0].length <= 300
     * 0 <= arr[i][j] <= 1
     * @param matrix
     * @return
     */
    // S1: DP
    // time = O(m * n), space = O(1)
    public int countSquares(int[][] matrix) {
        // corner case --> no due to constraints
        int res = 0;
        int row = matrix.length, col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) continue;
                else if (i == 0 || j == 0) res++;
                else {
                    matrix[i][j] = Math.min(Math.min(matrix[i - 1][j], matrix[i - 1][j - 1]), matrix[i][j - 1]) + 1;
                    res += matrix[i][j];
                }
            }
        }
        return res;
    }
}
