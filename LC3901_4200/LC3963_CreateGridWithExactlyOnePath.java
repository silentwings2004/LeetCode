package LC3901_4200;
import java.util.*;
public class LC3963_CreateGridWithExactlyOnePath {
    /**
     * You are given two integers m and n, representing the number of rows and columns of a grid.
     *
     * Construct any m x n grid consisting only of the characters '.' and '#', where:
     *
     * '.' represents a free cell.
     * '#' represents an obstacle cell.
     * A valid path is a sequence of free cells that:
     *
     * Starts at the top-left cell (0, 0).
     * Ends at the bottom-right cell (m - 1, n - 1).
     * Moves only:
     * Right, from (i, j) to (i, j + 1), or
     * Down, from (i, j) to (i + 1, j).
     * Return any grid such that there is exactly one valid path from the top-left cell to the bottom-right cell.
     *
     * Input: m = 2, n = 3
     * Output: ["..#","#.."]
     *
     * Input: m = 3, n = 3
     * Output: ["..#","#..","##."]
     *
     * Input: m = 1, n = 4
     * Output: ["...."]
     *
     * Constraints:
     *
     * 1 <= m, n <= 25
     * @param m
     * @param n
     * @return
     */
    // S1
    // time = O(m * n), space = O(m * n)
    public String[] createGrid(int m, int n) {
        char[][] s = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0 || i == m - 1) s[i][j] = '.';
                else s[i][j] = '#';
            }
        }
        String[] res = new String[m];
        for (int i = 0; i < m; i++) res[i] = String.valueOf(s[i]);
        return res;
    }

    // S2
    // time = O(m * n), space = O(n)
    public String[] createGrid2(int m, int n) {
        String[] res = new String[m];
        StringBuilder sb = new StringBuilder();
        sb.append('.');
        for (int i = 0; i < n - 1; i++) sb.append('#');
        for (int i = 0; i < m - 1; i++) res[i] = sb.toString();
        sb.setLength(0);
        for (int i = 0; i < n; i++) sb.append('.');
        res[m - 1] = sb.toString();
        return res;
    }
}