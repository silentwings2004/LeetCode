package LC3901_4200;
import java.util.*;
public class LC3988_CreateGridWithExactlyKPathsI {
    /**
     * You are given three integers m, n, and k.
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
     * Return any grid such that there are exactly k valid paths from the top-left cell to the bottom-right cell. If no
     * such grid exists, return an empty array.
     *
     * Input: m = 2, n = 3, k = 2
     * Output: ["...","#.."]
     *
     * Input: m = 3, n = 3, k = 4
     * Output: ["..#","...","#.."]
     *
     * Input: m = 1, n = 4, k = 2
     * Output: []
     *
     * Constraints:
     *
     * 1 <= m, n <= 10
     * 1 <= k <= 4
     * @param m
     * @param n
     * @param k
     * @return
     */
    // time = O(m * n), space = O(m * n)
    public String[] createGrid(int m, int n, int k) {
        if (k == 4 && m == 3 && n == 3) return new String[]{"..#","...","#.."};
        if (m == 1 || n == 1) { // 一行或一列，只能有一种方案
            if (k > 1) return new String[0];
            String[] res = new String[m];
            for (int i = 0; i < m; i++) Arrays.fill(res, ".".repeat(n)); // 全为 '.'
            return res;
        }

        if (m < k && n < k) return new String[0]; // 至少要有 k 行或 k 列（特殊情况上面已判断）

        char[][] g = new char[m][n];
        for (char[] r : g) {
            Arrays.fill(r, '#'); // 初始全为 '#'
            r[0] = '.'; // 第一列全为 '.'
        }
        Arrays.fill(g[m - 1], '.'); // 最后一行全为 '.'
        if (n >= k) { // 至少有 k 列
            for (int j = 1; j < k; j++) g[m - 2][j] = '.'; // 倒数第二行开头 k 个 '.'
        } else { // 至少有 k 行
            for (int i = m - k; i < m - 1; i++) g[i][1] = '.'; // 第二列末尾 k 个 '.'
        }

        String[] res = new String[m];
        for (int i = 0; i < m; i++) res[i] = new String(g[i]);
        return res;
    }
}
/**
 * m, n = 100
 * k <= 10^9
 */