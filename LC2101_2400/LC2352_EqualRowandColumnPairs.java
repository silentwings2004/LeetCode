package LC2101_2400;
import java.util.*;
public class LC2352_EqualRowandColumnPairs {
    /**
     * Given a 0-indexed n x n integer matrix grid, return the number of pairs (Ri, Cj) such that row Ri and column Cj
     * are equal.
     *
     * A row and column pair is considered equal if they contain the same elements in the same order (i.e. an equal
     * array).
     *
     * Input: grid = [[3,2,1],[1,7,6],[2,7,7]]
     * Output: 1
     *
     * Input: grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
     * Output: 3
     *
     * Constraints:
     *
     * n == grid.length == grid[i].length
     * 1 <= n <= 200
     * 1 <= grid[i][j] <= 10^5
     * @param grid
     * @return
     */
    // S1: string hashing
    // time = O(m * n), space = O(m * n)
    int P = 131;
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        HashMap<Long, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            long h = 0;
            for (int j = 0; j < n; j++) {
                h = h * P + grid[i][j];
            }
            map.put(h, map.getOrDefault(h, 0) + 1);
        }

        int count = 0;
        for (int j = 0; j < n; j++) {
            long h = 0;
            for (int k = 0; k < n; k++) {
                h = h * P + grid[k][j];
            }
            count += map.getOrDefault(h, 0);
        }
        return count;
    }

    // S2: brute-force
    // time = O(n^3), space = O(1)
    public int equalPairs2(int[][] grid) {
        int n = grid.length, res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean flag = true;
                for (int k = 0; k < n; k++) {
                    if (grid[i][k] != grid[k][j]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) res++;
            }
        }
        return res;
    }
}
