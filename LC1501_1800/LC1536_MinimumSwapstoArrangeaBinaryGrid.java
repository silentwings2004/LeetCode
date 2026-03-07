package LC1501_1800;
import java.util.*;
public class LC1536_MinimumSwapstoArrangeaBinaryGrid {
    /**
     * Given an n x n binary grid, in one step you can choose two adjacent rows of the grid and swap them.
     *
     * A grid is said to be valid if all the cells above the main diagonal are zeros.
     *
     * Return the minimum number of steps needed to make the grid valid, or -1 if the grid cannot be valid.
     *
     * The main diagonal of a grid is the diagonal that starts at cell (1, 1) and ends at cell (n, n).
     *
     * Input: grid = [[0,0,1],[1,1,0],[1,0,0]]
     * Output: 3
     *
     * Input: grid = [[0,1,1,0],[0,1,1,0],[0,1,1,0],[0,1,1,0]]
     * Output: -1
     *
     * Input: grid = [[1,0,0],[1,1,0],[1,1,1]]
     * Output: 0
     *
     * Constraints:
     *
     * n == grid.length == grid[i].length
     * 1 <= n <= 200
     * grid[i][j] is either 0 or 1
     * @param grid
     * @return
     */
    // time = O(n^2), space = O(1)
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] zeros = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] != 0) break;
                zeros[i]++;
            }
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            int t = n - 1 - i;
            int j = i;
            while (j < n) {
                if (zeros[j] >= t) break;
                j++;
            }
            if (j == n) return -1;
            res += j - i;
            int tmp = zeros[j];
            for (int k = j; k > i; k--) zeros[k] = zeros[k - 1];
            zeros[i] = tmp;
        }
        return res;
    }
}
/**
 * zeros[i]: how many trailing zeros in the ith row
 * zeros:  1 3 3 5 6
 * target: 5 4 3 2 1
 * zeros_new[i] >= target[i]
 *
 */