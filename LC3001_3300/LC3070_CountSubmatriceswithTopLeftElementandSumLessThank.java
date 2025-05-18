package LC3001_3300;

public class LC3070_CountSubmatriceswithTopLeftElementandSumLessThank {
    /**
     * You are given a 0-indexed integer matrix grid and an integer k.
     *
     * Return the number of submatrices that contain the top-left element of the grid, and have a sum less than or equal
     * to k.
     *
     * Input: grid = [[7,6,3],[6,6,1]], k = 18
     * Output: 4
     *
     * Input: grid = [[7,2,9],[1,5,0],[2,6,6]], k = 20
     * Output: 6
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= n, m <= 1000
     * 0 <= grid[i][j] <= 1000
     * 1 <= k <= 10^9
     * @param grid
     * @param k
     * @return
     */
    // time = O(m * n), space = O(m * n)
    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[][] s = new int[m + 1][n + 1];
        int res = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                s[i][j] += s[i - 1][j] + s[i][j - 1] - s[i - 1][j - 1] + grid[i - 1][j - 1];
                if (s[i][j] <= k) res++;
            }
        }
        return res;
    }
}