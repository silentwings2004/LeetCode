package LC3001_3300;
import java.util.*;
public class LC3242_DesignNeighborSumService {
    /**
     * You are given a n x n 2D array grid containing distinct elements in the range [0, n2 - 1].
     *
     * Implement the neighborSum class:
     *
     * neighborSum(int [][]grid) initializes the object.
     * int adjacentSum(int value) returns the sum of elements which are adjacent neighbors of value, that is either to
     * the top, left, right, or bottom of value in grid.
     * int diagonalSum(int value) returns the sum of elements which are diagonal neighbors of value, that is either to
     * the top-left, top-right, bottom-left, or bottom-right of value in grid.
     *
     * Input:
     * ["neighborSum", "adjacentSum", "adjacentSum", "diagonalSum", "diagonalSum"]
     * [[[[0, 1, 2], [3, 4, 5], [6, 7, 8]]], [1], [4], [4], [8]]
     * Output: [null, 6, 16, 16, 4]
     *
     * Input:
     * ["neighborSum", "adjacentSum", "diagonalSum"]
     * [[[[1, 2, 0, 3], [4, 7, 15, 6], [8, 9, 10, 11], [12, 13, 14, 5]]], [15], [9]]
     * Output: [null, 23, 45]
     *
     * Constraints:
     *
     * 3 <= n == grid.length == grid[0].length <= 10
     * 0 <= grid[i][j] <= n^2 - 1
     * All grid[i][j] are distinct.
     * value in adjacentSum and diagonalSum will be in the range [0, n^2 - 1].
     * At most 2 * n^2 calls will be made to adjacentSum and diagonalSum.
     * @param grid
     */
    // S1
    int[] dx = new int[]{-1, 0, 1, 0, -1, -1, 1, 1}, dy = new int[]{0, 1, 0, -1, -1, 1, 1, -1};
    HashMap<Integer, int[]> map;
    int m, n;
    int[][] grid;
    // time = O(n), space = O(n)
    public LC3242_DesignNeighborSumService(int[][] grid) {
        this.grid = grid;
        map = new HashMap<>();
        m = grid.length;
        n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map.put(grid[i][j], new int[]{i, j});
            }
        }
    }
    // time = O(1), space = O(n)
    public int adjacentSum(int value) {
        int[] c = map.get(value);
        int x = c[0], y = c[1];
        int res = 0;
        for (int i = 0; i < 4; i++) {
            int a = x + dx[i], b = y + dy[i];
            if (a < 0 || a >= m || b < 0 || b >= n) continue;
            res += grid[a][b];
        }
        return res;
    }
    // time = O(1), space = O(n)
    public int diagonalSum(int value) {
        int[] c = map.get(value);
        int x = c[0], y = c[1];
        int res = 0;
        for (int i = 4; i < 8; i++) {
            int a = x + dx[i], b = y + dy[i];
            if (a < 0 || a >= m || b < 0 || b >= n) continue;
            res += grid[a][b];
        }
        return res;
    }

    // S2
    // time = O(n^2), space = O(n^2)
    class neighborSum {
        int[] dx = new int[]{-1,0, 1, 0, -1, -1, 1, 1}, dy = new int[]{0, 1, 0, -1, -1, 1, 1, -1};
        int[][] s;
        public neighborSum(int[][] grid) {
            int n = grid.length;
            s = new int[n * n][2];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    int v = grid[i][j];
                    for (int k = 0; k < 8; k++) {
                        int a = i + dx[k], b = j + dy[k];
                        if (a < 0 || a >= n || b < 0 || b >= n) continue;
                        s[v][k / 4] += grid[a][b];
                    }
                }
            }
        }

        public int adjacentSum(int value) {
            return s[value][0];
        }

        public int diagonalSum(int value) {
            return s[value][1];
        }
    }
}