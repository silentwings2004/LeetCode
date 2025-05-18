package LC2700_3000;
import java.util.*;
public class LC2711_DifferenceofNumberofDistinctValuesonDiagonals {
    /**
     * Given a 0-indexed 2D grid of size m x n, you should find the matrix answer of size m x n.
     *
     * The value of each cell (r, c) of the matrix answer is calculated in the following way:
     *
     * Let topLeft[r][c] be the number of distinct values in the top-left diagonal of the cell (r, c) in the matrix grid.
     * Let bottomRight[r][c] be the number of distinct values in the bottom-right diagonal of the cell (r, c) in the
     * matrix grid.
     * Then answer[r][c] = |topLeft[r][c] - bottomRight[r][c]|.
     *
     * Return the matrix answer.
     *
     * A matrix diagonal is a diagonal line of cells starting from some cell in either the topmost row or leftmost
     * column and going in the bottom-right direction until reaching the matrix's end.
     *
     * A cell (r1, c1) belongs to the top-left diagonal of the cell (r, c), if both belong to the same diagonal and
     * r1 < r. Similarly is defined bottom-right diagonal.
     *
     * Input: grid = [[1,2,3],[3,1,5],[3,2,1]]
     * Output: [[1,1,0],[1,0,1],[0,1,1]]
     *
     * Input: grid = [[1]]
     * Output: [[0]]
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n, grid[i][j] <= 50
     * @param grid
     * @return
     */
    // time = O(m * n * min(m, n)), space = O(min(m, n))
    public int[][] differenceOfDistinctValues(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                HashSet<Integer> set = new HashSet<>();
                int x = i - 1, y = j - 1;
                while (x >= 0 && y >= 0) {
                    set.add(grid[x][y]);
                    x--;
                    y--;
                }
                int a = set.size();
                set.clear();
                x = i + 1;
                y = j + 1;
                while (x < m && y < n) {
                    set.add(grid[x][y]);
                    x++;
                    y++;
                }
                int b = set.size();
                res[i][j] = Math.abs(a - b);
            }
        }
        return res;
    }
}