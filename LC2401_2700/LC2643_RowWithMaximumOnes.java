package LC2401_2700;

public class LC2643_RowWithMaximumOnes {
    /**
     * Given a m x n binary matrix mat, find the 0-indexed position of the row that contains the maximum count of ones,
     * and the number of ones in that row.
     *
     * In case there are multiple rows that have the maximum count of ones, the row with the smallest row number should
     * be selected.
     *
     * Return an array containing the index of the row, and the number of ones in it.
     *
     * Input: mat = [[0,1],[1,0]]
     * Output: [0,1]
     *
     * Input: mat = [[0,0,0],[0,1,1]]
     * Output: [1,2]
     *
     * Input: mat = [[0,0],[1,1],[0,0]]
     * Output: [1,2]
     *
     * Constraints:
     *
     * m == mat.length
     * n == mat[i].length
     * 1 <= m, n <= 100
     * mat[i][j] is either 0 or 1.
     * @param mat
     * @return
     */
    // time = O(m * n), space = O(1)
    public int[] rowAndMaximumOnes(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] res = new int[2];
        for (int i = 0; i < m; i++) {
            int s = 0;
            for (int j = 0; j < n; j++) {
                s += mat[i][j];
            }
            if (s > res[1]) {
                res[1] = s;
                res[0] = i;
            }
        }
        return res;
    }
}