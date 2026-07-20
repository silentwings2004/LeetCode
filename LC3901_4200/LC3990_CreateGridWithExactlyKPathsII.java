package LC3901_4200;
import java.util.*;
public class LC3990_CreateGridWithExactlyKPathsII {
    /**
     * You are given an integer k.
     *
     * Construct any grid consisting only of the characters '.' and '#', where:
     *
     * '.' represents a free cell.
     * '#' represents an obstacle cell.
     * The grid must contain at most 25 rows and at most 25 columns.
     *
     * A valid path is a sequence of free cells that:
     *
     * Starts at the top-left cell (0, 0).
     * Ends at the bottom-right cell (m - 1, n - 1), where m and n are the dimensions of your constructed grid.
     * Moves only:
     * Right, from (i, j) to (i, j + 1), or
     * Down, from (i, j) to (i + 1, j).
     * Return any grid such that there are exactly k valid paths from the top-left cell to the bottom-right cell. If no
     * such grid exists, return an empty array.
     *
     * Input: k = 2
     * Output: ["..#","#..","#.."]
     *
     * Input: k = 3
     * Output: ["...","#..","#.."]
     *
     * Constraints:
     *
     * 1 <= k <= 1000
     * @param k
     * @return
     */
    // time = O((logk)^2), space = O((logk)^2)
    public List<String> createGrid(int k) {
        if (k == 1) {
            List<String> res = new ArrayList<>();
            res.add(".");
            return res;
        }
        String bin = Integer.toBinaryString(k);
        int L = bin.length();
        int c0 = 2;
        int n = c0 + L; // width
        int m = 2 * L - 1; // height
        char[][] g = new char[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(g[i], '#');

        for (int j = 0; j <= c0; j++) g[0][j] = '.';

        int r = 1, c = c0;
        for (int i = 1; i < L; i++) {
            int b = bin.charAt(i) - '0';
            g[r][0] = '.';
            g[r][c] = g[r][c + 1] = '.';
            g[r + 1][0] = '.';
            if (b == 1) {
                for (int j = 1; j <= c - 1; j++) {
                    g[r + 1][j] = '.';
                }
            }
            g[r + 1][c] = g[r + 1][c + 1] = '.';
            c++;
            r += 2;
        }

        List<String> res = new ArrayList<>();
        for (int i = 0; i < m; i++) res.add(new String(g[i]));
        return res;
    }
}