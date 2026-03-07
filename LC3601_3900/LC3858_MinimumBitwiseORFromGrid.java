package LC3601_3900;

public class LC3858_MinimumBitwiseORFromGrid {
    /**
     * You are given a 2D integer array grid of size m x n.
     *
     * You must select exactly one integer from each row of the grid.
     *
     * Return an integer denoting the minimum possible bitwise OR of the selected integers from each row.
     *
     * Input: grid = [[1,5],[2,4]]
     * Output: 3
     *
     * Input: grid = [[3,5],[6,4]]
     * Output: 5
     *
     * Input: grid = [[7,9,8]]
     * Output: 7
     *
     * Constraints:
     *
     * 1 <= m == grid.length <= 10^5
     * 1 <= n == grid[i].length <= 10^5
     * m * n <= 105
     * 1 <= grid[i][j] <= 10^5
     * @param grid
     * @return
     */
    // S1: binary search
    // time = O(m * n * logk), space = O(1)
    public int minimumOR(int[][] grid) {
        int l = 0, r = (1 << 21) - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (check(grid, mid)) r = mid;
            else l = mid + 1;
        }
        return r;
    }

    private boolean check(int[][] grid, int mid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            boolean f = false;
            for (int j = 0; j < n; j++) {
                if ((grid[i][j] | mid) == mid) {
                    f = true;
                    break;
                }
            }
            if (!f) return false;
        }
        return true;
    }

    // S2: 试填法
    // time = O(m * n * logk), space = O(1)
    public int minimumOR2(int[][] grid) {
        int mx = 0;
        for (int[] r : grid) {
            for (int x : r) {
                mx = Math.max(mx, x);
            }
        }

        int res = 0, b = 32 - Integer.numberOfLeadingZeros(mx);
        for (int i = b - 1; i >= 0; i--) {
            int mask = res | (1 << i) - 1;
            for (int[] r : grid) {
                boolean f = false;
                for (int x : r) {
                    if ((x | mask) == mask) {
                        f = true;
                        break;
                    }
                }
                if (!f) {
                    res |= 1 << i;
                    break;
                }
            }
        }
        return res;
    }
}
/**
 * 从高到低去思考
 */