package LC3901_4200;

public class LC4084_LastVisitedCellinaDirectionalGrid {
    /**
     * You are given an m x n matrix grid where each cell contains one of the following characters:
     *
     * 'L': move one cell left.
     * 'R': move one cell right.
     * 'U': move one cell up.
     * 'D': move one cell down.
     * You start at cell (0, 0), which is considered visited. At each step, move to the cell indicated by the character
     * in your current cell.
     *
     * The process ends if the indicated move would either leave the grid or enter a cell that has already been visited.
     * In either case, the move is not made.
     *
     * Return an integer array [r, c] representing the row and column indices of the last visited cell. The indices are
     * 0-based.
     *
     * Input: grid = [["R","D"],["U","L"]]
     * Output: [1,0]
     *
     * Input: grid = [["R","R","R"]]
     * Output: [0,2]
     *
     * Constraints:
     *
     * 1 <= m == grid.length <= 10^5
     * 1 <= n == grid[i].length <= 10^5
     * m * n <= 10^5
     * grid[i][j] is one of 'L', 'R', 'U', or 'D'.
     * @param grid
     * @return
     */
    // time = O(m * n), space = O(1)
    public int[] lastVisitedCell(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int x = 0, y = 0;
        int[] res = new int[2];

        while (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] != '#') {
            res = new int[]{x, y};
            char c = grid[x][y];
            grid[x][y] = '#';
            if (c == 'U') x--;
            else if (c == 'R') y++;
            else if (c == 'D') x++;
            else y--;
        }
        return res;
    }
}