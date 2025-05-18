package LC901_1200;
import java.util.*;
public class LC1030_MatrixCellsinDistanceOrder {
    /**
     * You are given four integers row, cols, rCenter, and cCenter. There is a rows x cols matrix and you are on the
     * cell with the coordinates (rCenter, cCenter).
     *
     * Return the coordinates of all cells in the matrix, sorted by their distance from (rCenter, cCenter) from the
     * smallest distance to the largest distance. You may return the answer in any order that satisfies this condition.
     *
     * The distance between two cells (r1, c1) and (r2, c2) is |r1 - r2| + |c1 - c2|.
     *
     * Input: rows = 1, cols = 2, rCenter = 0, cCenter = 0
     * Output: [[0,0],[0,1]]
     *
     * Input: rows = 2, cols = 2, rCenter = 0, cCenter = 1
     * Output: [[0,1],[0,0],[1,1],[1,0]]
     *
     * Input: rows = 2, cols = 3, rCenter = 1, cCenter = 2
     * Output: [[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
     *
     * Constraints:
     *
     * 1 <= rows, cols <= 100
     * 0 <= rCenter < rows
     * 0 <= cCenter < cols
     * @param rows
     * @param cols
     * @param rCenter
     * @param cCenter
     * @return
     */
    // time = O(m * n), space = O(m * n)
    public int[][] allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {
        int m = rows, n = cols, r = rCenter, c = cCenter;
        int[] dx = new int[]{1,  1, -1, -1}, dy = new int[]{1, -1, -1, 1};
        List<int[]> res = new ArrayList<>();
        res.add(new int[]{r, c});

        for (int d = 1;; d++) {
            int x = r - d, y = c, cnt = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < d; j++) {
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        res.add(new int[]{x, y});
                        cnt++;
                    }
                    x += dx[i];
                    y += dy[i];
                }
            }
            if (cnt == 0) break;
        }
        return res.toArray(new int[res.size()][]);
    }
}