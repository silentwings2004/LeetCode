package LC2700_3000;
import java.util.*;
public class LC2768_NumberofBlackBlocks {
    /**
     * You are given two integers m and n representing the dimensions of a 0-indexed m x n grid.
     *
     * You are also given a 0-indexed 2D integer matrix coordinates, where coordinates[i] = [x, y] indicates that the
     * cell with coordinates [x, y] is colored black. All cells in the grid that do not appear in coordinates are white.
     *
     * A block is defined as a 2 x 2 submatrix of the grid. More formally, a block with cell [x, y] as its top-left
     * corner where 0 <= x < m - 1 and 0 <= y < n - 1 contains the coordinates [x, y], [x + 1, y], [x, y + 1], and
     * [x + 1, y + 1].
     *
     * Return a 0-indexed integer array arr of size 5 such that arr[i] is the number of blocks that contains exactly i
     * black cells.
     *
     * Input: m = 3, n = 3, coordinates = [[0,0]]
     * Output: [3,1,0,0,0]
     *
     * Input: m = 3, n = 3, coordinates = [[0,0],[1,1],[0,2]]
     * Output: [0,2,2,0,0]
     *
     * Constraints:
     *
     * 2 <= m <= 10^5
     * 2 <= n <= 10^5
     * 0 <= coordinates.length <= 10^4
     * coordinates[i].length == 2
     * 0 <= coordinates[i][0] < m
     * 0 <= coordinates[i][1] < n
     * It is guaranteed that coordinates contains pairwise distinct coordinates.
     * @param m
     * @param n
     * @param coordinates
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    int[] dx = new int[]{-1, -1, 1, 1}, dy = new int[]{1, -1, 1, -1};
    public long[] countBlackBlocks(int m, int n, int[][] coordinates) {
        long[] res = new long[5];
        res[0] = (long)(m - 1) * (n - 1);
        HashSet<Long> set = new HashSet<>();
        for (int[] c : coordinates) set.add((long)c[0] * n + c[1]);
        for (int[] c : coordinates) {
            int x = c[0], y = c[1];
            for (int i = 0; i < 4; i++) {
                int cnt = 1;
                long a = x + dx[i], b = y + dy[i];
                if (a < 0 || a >= m || b < 0 || b >= n) continue;
                if (set.contains(a * n + y)) cnt++;
                if (set.contains(x * n + b)) cnt++;
                if (set.contains(a * n + b)) cnt++;
                res[cnt]++;
            }
        }

        res[2] /= 2;
        res[3] /= 3;
        res[4] /= 4;
        res[0] -= res[1] + res[2] + res[3] + res[4];
        return res;
    }

    // S2
    // time = O(n), space = O(n)
    int n;
    public long[] countBlackBlocks2(int m, int n, int[][] coordinates) {
        this.n = n;
        HashMap<Long, Integer> map = new HashMap<>();
        for (int[] c : coordinates) {
            int x = c[0], y = c[1];
            for (int i = x - 1; i <= x; i++) {
                for (int j = y - 1; j <= y; j++) {
                    if (i >= 0 && i < m - 1 && j >= 0 && j < n - 1) {
                        long key = encode(i, j);
                        map.put(key, map.getOrDefault(key, 0) + 1);
                    }
                }
            }
        }
        long[] res = new long[5];
        for (int v : map.values()) res[v]++;
        res[0] = (long)(m - 1) * (n - 1) - res[1] - res[2] - res[3] - res[4];
        return res;
    }

    private long encode(long x, long y) {
        return x * n + y;
    }
}