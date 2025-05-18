package LC3001_3300;

public class LC3127_MakeaSquarewiththeSameColor {
    /**
     * You are given a 2D matrix grid of size 3 x 3 consisting only of characters 'B' and 'W'. Character 'W' represents
     * the white color, and character 'B' represents the black color.
     *
     * Your task is to change the color of at most one cell so that the matrix has a 2 x 2 square where all cells are of
     * the same color.
     *
     * Return true if it is possible to create a 2 x 2 square of the same color, otherwise, return false.
     *
     * Input: grid = [["B","W","B"],["B","W","W"],["B","W","B"]]
     * Output: true
     *
     * Input: grid = [["B","W","B"],["W","B","W"],["B","W","B"]]
     * Output: false
     *
     * Input: grid = [["B","W","B"],["B","W","W"],["B","W","W"]]
     * Output: true
     *
     * Constraints:
     *
     * grid.length == 3
     * grid[i].length == 3
     * grid[i][j] is either 'W' or 'B'.
     * @param grid
     * @return
     */
    // time = O(m * n), space = O(1)
    public boolean canMakeSquare(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                int w = 0, b = 0;
                for (int x = 0; x < 2; x++) {
                    for (int y = 0; y < 2; y++) {
                        if (grid[i + x][j + y] == 'W') w++;
                        else b++;
                    }
                }
                if (w >= 3 || b >= 3) return true;
            }
        }
        return false;
    }
}