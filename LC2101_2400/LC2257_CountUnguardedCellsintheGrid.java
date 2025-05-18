package LC2101_2400;
import java.util.*;
public class LC2257_CountUnguardedCellsintheGrid {
    /**
     * You are given two integers m and n representing a 0-indexed m x n grid. You are also given two 2D integer arrays
     * guards and walls where guards[i] = [rowi, coli] and walls[j] = [rowj, colj] represent the positions of the ith
     * guard and jth wall respectively.
     *
     * A guard can see every cell in the four cardinal directions (north, east, south, or west) starting from their
     * position unless obstructed by a wall or another guard. A cell is guarded if there is at least one guard that can
     * see it.
     *
     * Return the number of unoccupied cells that are not guarded.
     *
     * Input: m = 4, n = 6, guards = [[0,0],[1,1],[2,3]], walls = [[0,1],[2,2],[1,4]]
     * Output: 7
     *
     * Input: m = 3, n = 3, guards = [[1,1]], walls = [[0,1],[1,0],[2,1],[1,2]]
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= m, n <= 10^5
     * 2 <= m * n <= 10^5
     * 1 <= guards.length, walls.length <= 5 * 10^4
     * 2 <= guards.length + walls.length <= m * n
     * guards[i].length == walls[j].length == 2
     * 0 <= rowi, rowj < m
     * 0 <= coli, colj < n
     * All the positions in guards and walls are unique.
     * @param m
     * @param n
     * @param guards
     * @param walls
     * @return
     */
    // time = O(m * n), space = O(m * n)
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        int[][] grid = new int[m][n];
        for (int[] g : guards) grid[g[0]][g[1]] = 1;
        for (int[] w : walls) grid[w[0]][w[1]] = 2;
        int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
        for (int[] g : guards) {
            int x = g[0], y = g[1];
            for (int i = 0; i < 4; i++) {
                int a = x + dx[i], b = y + dy[i];
                while (a >= 0 && a < m && b >= 0 && b < n && grid[a][b] != 1 && grid[a][b] != 2) {
                    grid[a][b] = 3;
                    a += dx[i];
                    b += dy[i];
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    res++;
                }
            }
        }
        return res;
    }
}