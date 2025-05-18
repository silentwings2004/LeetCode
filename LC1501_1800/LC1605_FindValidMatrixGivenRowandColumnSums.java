package LC1501_1800;

public class LC1605_FindValidMatrixGivenRowandColumnSums {
    /**
     * You are given two arrays rowSum and colSum of non-negative integers where rowSum[i] is the sum of the elements
     * in the ith row and colSum[j] is the sum of the elements of the jth column of a 2D matrix. In other words, you do
     * not know the elements of the matrix, but you do know the sums of each row and column.
     *
     * Find any matrix of non-negative integers of size rowSum.length x colSum.length that satisfies the rowSum and
     * colSum requirements.
     *
     * Return a 2D array representing any matrix that fulfills the requirements. It's guaranteed that at least one
     * matrix that fulfills the requirements exists.
     *
     * Input: rowSum = [3,8], colSum = [4,7]
     * Output: [[3,0],
     *          [1,7]]
     *
     * Input: rowSum = [5,7,10], colSum = [8,6,8]
     * Output: [[0,5,0],
     *          [6,1,0],
     *          [2,0,8]]
     *
     * Constraints:
     *
     * 1 <= rowSum.length, colSum.length <= 500
     * 0 <= rowSum[i], colSum[i] <= 10^8
     * sum(rows) == sum(columns)
     * @param rowSum
     * @param colSum
     * @return
     */
    // time = O(m * n), space = O(1)
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int m = rowSum.length, n = colSum.length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int t = Math.min(rowSum[i], colSum[j]);
                res[i][j] = t;
                rowSum[i] -= t;
                colSum[j] -= t;
            }
        }
        return res;
    }
}