package LC901_1200;
import java.util.*;
public class LC1091_ShortestPathinBinaryMatrix {
    /**
     * In an N by N square grid, each cell is either empty (0) or blocked (1).
     *
     * A clear path from top-left to bottom-right has length k if and only if it is composed of cells
     * C_1, C_2, ..., C_k such that:
     *
     * Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
     * C_1 is at location (0, 0) (ie. has value grid[0][0])
     * C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
     * If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
     * Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist,
     * return -1.
     *
     * Input: [[0,0,0],[1,1,0],[1,1,0]]
     * Output: 4
     *
     * Note:
     *
     * 1 <= grid.length == grid[0].length <= 100
     * grid[r][c] is 0 or 1
     * @param grid
     * @return
     */
    // time = O(n^2), space = O(n^2)
    private int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        grid[0][0] = 1;

        int step = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] t = q.poll();
                int x = t[0], y = t[1];
                if (x == n - 1 && y == n - 1) return step;
                for (int[] dir : directions) {
                    int a = x + dir[0], b = y + dir[1];
                    if (a < 0 || a >= n || b < 0 || b >= n) continue;
                    if (grid[a][b] != 0) continue;
                    q.offer(new int[]{a, b});
                    grid[a][b] = 1;
                }
            }
            step++;
        }
        return -1;
    }
}