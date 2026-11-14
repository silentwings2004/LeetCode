package LC3901_4200;

public class LC4096_SimulateBombExplosionsandFallingBoxes {
    /**
     * You are given a 2D character array grid of size m x n, where each cell contains one of the following symbols:
     *
     * '#': a box.
     * '-': an empty cell.
     * '*': a bomb.
     * Each second, the following events occur in order:
     *
     * Explosions: A bomb explodes if it is orthogonally adjacent to at least one box (shares a side with it). An
     * exploding bomb destroys all boxes in its 8 neighboring cells, including diagonally adjacent cells, turning them
     * into empty cells. All explosions happen simultaneously, based on the grid at the beginning of that second. Empty
     * cells and bombs remain unchanged.
     * Gravity: After the explosions, all non-static boxes simultaneously fall down by exactly one row. A box is static
     * if it is in the bottommost row, immediately above a bomb, or immediately above another static box.
     * Bombs never move or disappear, and the same bomb may explode multiple times. Boxes above non-static boxes can
     * fall together with them.
     *
     * Return the final grid after 10^100 seconds.
     *
     * Input: grid = [["#","#","#"],["-","-","*"],["-","-","-"]]
     * Output: [["-","-","-"],["-","-","*"],["#","-","-"]]
     *
     * Input: grid = [["-","*"],["#","-"]]
     * Output: [["-","*"],["#","-"]]
     *
     * Input: grid = [["-","*","#"],["#","-","-"]]
     * Output: [["-","*","-"],["-","-","-"]]
     *
     * Input: grid = [["#","*","-","#"],["-","-","*","-"]]
     * Output: [["-","*","-","-"],["-","-","*","-"]]
     *
     * 1 <= m == grid.length <= 100
     * 1 <= n == grid[i].length <= 100
     * grid[i][j] is '#', '-', or '*'.
     * @param grid
     * @return
     */
    // time = O(m * n * (m + n)), space = O(m * n)
    public char[][] finalGrid(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        while (true) {
            boolean changed = false;
            boolean[][] explode = new boolean[m][n];
            boolean[][] destroy = new boolean[m][n];

            // mark bombs that will explode
            int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] != '*') continue;
                    for (int k = 0; k < 4; k++) {
                        int x = i + dx[k], y = j + dy[k];
                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '#') {
                            explode[i][j] = true;
                            break;
                        }
                    }
                }
            }

            // mark box destroyed by an exploding bomb
            int[] dr = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
            int[] dc = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (!explode[i][j]) continue;
                    for (int k = 0; k < 8; k++) {
                        int x = i + dr[k], y = j + dc[k];
                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == '#') {
                            destroy[x][y] = true;
                        }
                    }
                }
            }

            // Apply all explosions simultaneously
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (destroy[i][j]) {
                        grid[i][j] = '-';
                        changed = true;
                    }
                }
            }

            // Compute static boxes after the explosions
            boolean[][] stat = new boolean[m][n];
            for (int j = 0; j < n; j++) {
                for (int i = m - 1; i >= 0; i--) {
                    if (grid[i][j] != '#') continue;
                    stat[i][j] = i == m - 1 || grid[i + 1][j] == '*' || grid[i + 1][j] == '#' && stat[i + 1][j];
                }
            }

            // save with boxes move
            boolean[][] moving = new boolean[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '#' && !stat[i][j]) moving[i][j] = true;
                }
            }

            // clear all moving boxes
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (moving[i][j]) {
                        grid[i][j] = '-';
                        changed = true;
                    }
                }
            }

            // move them down by exactly one row simultaneously
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (moving[i][j]) grid[i + 1][j] = '#';
                }
            }

            if (!changed) break;
        }
        return grid;
    }
}