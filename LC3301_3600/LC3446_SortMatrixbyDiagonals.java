package LC3301_3600;
import java.util.*;
public class LC3446_SortMatrixbyDiagonals {
    /**
     * You are given an n x n square matrix of integers grid. Return the matrix such that:
     *
     * The diagonals in the bottom-left triangle (including the middle diagonal) are sorted in non-increasing order.
     * The diagonals in the top-right triangle are sorted in non-decreasing order.
     *
     * Input: grid = [[1,7,3],[9,8,2],[4,5,6]]
     * Output: [[8,2,3],[9,6,7],[4,5,1]]
     *
     * Input: grid = [[0,1],[1,2]]
     * Output: [[2,1],[1,0]]
     *
     * Input: grid = [[1]]
     * Output: [[1]]
     *
     * Constraints:
     *
     * grid.length == grid[i].length == n
     * 1 <= n <= 10
     * -105 <= grid[i][j] <= 10^5
     * @param grid
     * @return
     */
    // time = O(n * nlogn), space = O(logn)
    public int[][] sortMatrix(int[][] grid) {
        int n = grid.length;
        for (int i = n - 1; i >= 0; i--) helper(grid, i, 0, false);
        for (int i = 1; i < n; i++) helper(grid, 0, i, true);
        return grid;
    }

    private void helper(int[][] g, int r, int c, boolean f) {
        int n = g.length;
        List<Integer> q = new ArrayList<>();
        int i = r, j = c;
        while (i < n && j < n) q.add(g[i++][j++]);
        if (f) Collections.sort(q);
        else Collections.sort(q, (o1, o2) -> o2 - o1);
        i = r;
        j = c;
        int idx = 0;
        while (i < n && j < n) g[i++][j++] = q.get(idx++);
    }
}