package LC001_300;
import java.util.*;
public class LC54_SpiralMatrix {
    /**
     * Given an m x n matrix, return all elements of the matrix in spiral order.
     *
     * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
     * Output: [1,2,3,6,9,8,7,4,5]
     *
     * Constraints:
     *
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 10
     * -100 <= matrix[i][j] <= 100
     * @param matrix
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        // corner case
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return res;

        int rowBegin = 0, rowEnd = matrix.length - 1;
        int colBegin = 0, colEnd = matrix[0].length - 1;

        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            for (int i = colBegin; i <= colEnd; i++) res.add(matrix[rowBegin][i]);
            rowBegin++;

            for (int i = rowBegin; i <= rowEnd; i++) res.add(matrix[i][colEnd]);
            colEnd--;

            if (rowBegin <= rowEnd) {  // 注意：这里要加上判断，否则不用继续下去！！！
                for (int i = colEnd; i >= colBegin; i--) res.add(matrix[rowEnd][i]);
            }
            rowEnd--;

            if (colBegin <= colEnd) { // 注意：这里要加上判断，否则不用继续下去！！！
                for (int i = rowEnd; i >= rowBegin; i--) res.add(matrix[i][colBegin]);
            }
            colBegin++;
        }
        return res;
    }

    // S2
    // time = O(m * n), space = O(1)
    public List<Integer> spiralOrder2(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int n = matrix.length, m = matrix[0].length;

        int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};

        for (int i = 0, x = 0, y = 0, d = 1; i < n * m; i++) { // 注意：d要从1开始，先走右边！
            res.add(matrix[x][y]);
            matrix[x][y] = Integer.MAX_VALUE;

            int a = x + dx[d], b = y + dy[d];
            if (a < 0 || a >= n || b < 0 || b >= m || matrix[a][b] == Integer.MAX_VALUE) {
                d = (d + 1) % 4;
                a = x + dx[d];
                b = y + dy[d];
            }
            x = a;
            y = b;
        }
        return res;
    }
}
