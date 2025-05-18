package LC3001_3300;

public class LC3128_RightTriangles {
    /**
     * You are given a 2D boolean matrix grid.
     *
     * Return an integer that is the number of right triangles that can be made with the 3 elements of grid such that
     * all of them have a value of 1.
     *
     * Note:
     *
     * A collection of 3 elements of grid is a right triangle if one of its elements is in the same row with another
     * element and in the same column with the third element. The 3 elements do not have to be next to each other.
     *
     * Input: grid = [[0,1,0],[0,1,1],[0,1,0]]
     * Output: 2
     *
     * Input: grid = [[1,0,0,0],[0,1,0,1],[1,0,0,0]]
     * Output: 0
     *
     * Input: grid = [[1,0,1],[1,0,0],[1,0,0]]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= grid.length <= 1000
     * 1 <= grid[i].length <= 1000
     * 0 <= grid[i][j] <= 1
     * @param grid
     * @return
     */
    // time = O(m * n), space = O(m + n)
    public long numberOfRightTriangles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] r = new int[m], c = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                r[i] += grid[i][j];
                c[j] += grid[i][j];
            }
        }

        long res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    res += (r[i] - 1) * (c[j] - 1);
                }
            }
        }
        return res;
    }
}
/**
 * 多个数，枚举中间
 */
