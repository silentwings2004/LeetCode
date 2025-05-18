package LC2401_2700;

public class LC2536_IncrementSubmatricesbyOne {
    /**
     * You are given a positive integer n, indicating that we initially have an n x n 0-indexed integer matrix mat
     * filled with zeroes.
     *
     * You are also given a 2D integer array query. For each query[i] = [row1i, col1i, row2i, col2i], you should do the
     * following operation:
     *
     * Add 1 to every element in the submatrix with the top left corner (row1i, col1i) and the bottom right corner
     * (row2i, col2i). That is, add 1 to mat[x][y] for for all row1i <= x <= row2i and col1i <= y <= col2i.
     * Return the matrix mat after performing every query.
     *
     * Input: n = 3, queries = [[1,1,2,2],[0,0,1,1]]
     * Output: [[1,1,0],[1,2,1],[0,1,1]]
     *
     * Input: n = 2, queries = [[0,0,1,1]]
     * Output: [[1,1],[1,1]]
     *
     * Constraints:
     *
     * 1 <= n <= 500
     * 1 <= queries.length <= 10^4
     * 0 <= row1i <= row2i < n
     * 0 <= col1i <= col2i < n
     * @param n
     * @param queries
     * @return
     */
    // time = O(m + n^2), space = O(n^2)
    final int N = 510;
    int[][] b;
    public int[][] rangeAddQueries(int n, int[][] queries) {
       int[][] a = new int[n + 1][n + 1], res = new int[n][n];
       b = new int[N][N];

       for (int[] q : queries) {
           int x1 = q[0] + 1, y1 = q[1] + 1, x2 = q[2] + 1, y2 = q[3] + 1;
           b[x1][y1]++;
           b[x1][y2 + 1]--;
           b[x2 + 1][y1]--;
           b[x2 + 1][y2 + 1]++;
       }

       for (int i = 1; i <= n; i++) {
           for (int j = 1; j <= n; j++) {
               a[i][j] = a[i - 1][j] + a[i][j - 1] - a[i - 1][j - 1] + b[i][j];
               res[i - 1][j - 1] = a[i][j];
           }
       }
       return res;
    }
}