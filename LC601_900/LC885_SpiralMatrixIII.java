package LC601_900;
import java.util.*;
public class LC885_SpiralMatrixIII {
    /**
     * You start at the cell (rStart, cStart) of an rows x cols grid facing east. The northwest corner is at the first
     * row and column in the grid, and the southeast corner is at the last row and column.
     *
     * You will walk in a clockwise spiral shape to visit every position in this grid. Whenever you move outside the
     * grid's boundary, we continue our walk outside the grid (but may return to the grid boundary later.). Eventually,
     * we reach all rows * cols spaces of the grid.
     *
     * Return an array of coordinates representing the positions of the grid in the order you visited them.
     *
     * Input: rows = 1, cols = 4, rStart = 0, cStart = 0
     * Output: [[0,0],[0,1],[0,2],[0,3]]
     *
     * Input: rows = 5, cols = 6, rStart = 1, cStart = 4
     * Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],
     * [4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]
     *
     * Constraints:
     *
     * 1 <= rows, cols <= 100
     * 0 <= rStart < rows
     * 0 <= cStart < cols
     * @param rows
     * @param cols
     * @param rStart
     * @param cStart
     * @return
     */
    // time = O(max(m, n)^2), space = O(m * n)
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
        int x = rStart, y = cStart;
        List<int[]> res = new ArrayList<>();
        res.add(new int[]{x, y});
        int d = 1, tot = rows * cols;
        for (int k = 1; res.size() < tot; k++) {
            for (int i = 0; i < 2 && res.size() < tot; i++) {
                for (int j = 0; j < k && res.size() < tot; j++) {
                    int a = x + dx[d], b = y + dy[d];
                    if (a >= 0 && a < rows && b >= 0 && b < cols) {
                        res.add(new int[]{a, b});
                    }
                    x = a;
                    y = b;
                }
                d = (d + 1) % 4;
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}