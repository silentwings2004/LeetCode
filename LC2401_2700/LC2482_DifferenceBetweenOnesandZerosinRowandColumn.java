package LC2401_2700;

public class LC2482_DifferenceBetweenOnesandZerosinRowandColumn {
    /**
     * You are given a 0-indexed m x n binary matrix grid.
     *
     * A 0-indexed m x n difference matrix diff is created with the following procedure:
     *
     * Let the number of ones in the ith row be onesRowi.
     * Let the number of ones in the jth column be onesColj.
     * Let the number of zeros in the ith row be zerosRowi.
     * Let the number of zeros in the jth column be zerosColj.
     * diff[i][j] = onesRowi + onesColj - zerosRowi - zerosColj
     * Return the difference matrix diff.
     *
     * Input: grid = [[0,1,1],[1,0,1],[0,0,1]]
     * Output: [[0,0,4],[0,0,4],[-2,-2,2]]
     *
     * Input: grid = [[1,1,1],[1,1,1]]
     * Output: [[5,5,5],[5,5,5]]
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 10^5
     * 1 <= m * n <= 10^5
     * grid[i][j] is either 0 or 1.
     * @param grid
     * @return
     */
    // time = O(m * n), space = O(m * n)
    public int[][] onesMinusZeros(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] a = new int[m], b = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    a[i]++;
                    b[j]++;
                }
            }
        }

        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = a[i] + b[j] - (n - a[i]) - (m - b[j]);
            }
        }
        return res;
    }
}