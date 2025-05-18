package LC3301_3600;
import java.util.*;
public class LC3417_ZigzagGridTraversalWithSkip {
    /**
     * You are given an m x n 2D array grid of positive integers.
     *
     * Your task is to traverse grid in a zigzag pattern while skipping every alternate cell.
     *
     * Zigzag pattern traversal is defined as following the below actions:
     *
     * Start at the top-left cell (0, 0).
     * Move right within a row until the end of the row is reached.
     * Drop down to the next row, then traverse left until the beginning of the row is reached.
     * Continue alternating between right and left traversal until every row has been traversed.
     * Note that you must skip every alternate cell during the traversal.
     *
     * Return an array of integers result containing, in order, the value of the cells visited during the zigzag
     * traversal with skips.
     *
     * Input: grid = [[1,2],[3,4]]
     * Output: [1,4]
     *
     * Input: grid = [[2,1],[2,1],[2,1]]
     * Output: [2,1,2]
     *
     * Input: grid = [[1,2,3],[4,5,6],[7,8,9]]
     * Output: [1,3,5,7,9]
     *
     * Constraints:
     *
     * 2 <= n == grid.length <= 50
     * 2 <= m == grid[i].length <= 50
     * 1 <= grid[i][j] <= 2500
     * @param grid
     * @return
     */
    // time = O(m * n), space = O(1)
    public List<Integer> zigzagTraversal(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        List<Integer> res = new ArrayList<>();
        boolean f = true;
        int last = 1;
        for (int i = 0; i < m; i++) {
            if (f) {
                int start = last == 0 ? 1 : 0;
                for (int j = start; j < n; j += 2) {
                    res.add(grid[i][j]);
                    last = j;
                }
            } else {
                int start = last == n - 1 ? n - 2 : n - 1;
                for (int j = start; j >= 0; j -= 2) {
                    res.add(grid[i][j]);
                    last = j;
                }
            }
            f = !f;
        }
        return res;
    }
}