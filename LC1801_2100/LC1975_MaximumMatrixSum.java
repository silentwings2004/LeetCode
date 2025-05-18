package LC1801_2100;
import java.util.*;
public class LC1975_MaximumMatrixSum {
    /**
     * You are given an n x n integer matrix. You can do the following operation any number of times:
     *
     * Choose any two adjacent elements of matrix and multiply each of them by -1.
     * Two elements are considered adjacent if and only if they share a border.
     *
     * Your goal is to maximize the summation of the matrix's elements. Return the maximum sum of the matrix's elements
     * using the operation mentioned above.
     *
     * Input: matrix = [[1,-1],[-1,1]]
     * Output: 4
     *
     * Constraints:
     *
     * n == matrix.length == matrix[i].length
     * 2 <= n <= 250
     * -10^5 <= matrix[i][j] <= 10^5
     * @param matrix
     * @return
     */
    // time = O(n^2), space = O(1)
    public long maxMatrixSum(int[][] matrix) {
        int n = matrix.length;
        long res = 0;
        int minv = Integer.MAX_VALUE;
        boolean f = true;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int x = matrix[i][j];
                if (x >= 0) res += x;
                else {
                    res -= x;
                    f = !f;
                }
                minv = Math.min(minv, Math.abs(x));
            }
        }
        return f ? res : res - minv * 2;
    }
}