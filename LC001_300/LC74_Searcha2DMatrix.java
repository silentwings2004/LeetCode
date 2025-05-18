package LC001_300;
import java.util.*;
public class LC74_Searcha2DMatrix {
    /**
     * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has
     * the following properties:
     *
     * Integers in each row are sorted from left to right.
     * The first integer of each row is greater than the last integer of the previous row.
     *
     * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
     * Output: true
     *
     * Constraints:
     *
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 100
     * -104 <= matrix[i][j], target <= 104
     *
     * @param matrix
     * @param target
     * @return
     */
    // S1: 二分
    // time = O(log(m * n)), space = O(1)
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int l = 0, r = m * n - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (matrix[mid / n][mid % n] < target) l = mid + 1;
            else r = mid;
        }
        return matrix[r / n][r % n] == target;
    }

    // S2
    // time = O(m + n), space = O(1)
    public boolean searchMatrix2(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int i = m - 1, j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] == target) return true;
            if (matrix[i][j] > target) i--;
            else j++;
        }
        return false;
    }
}