package LC2700_3000;

public class LC2923_FindChampionI {
    /**
     * There are n teams numbered from 0 to n - 1 in a tournament.
     *
     * Given a 0-indexed 2D boolean matrix grid of size n * n. For all i, j that 0 <= i, j <= n - 1 and i != j team i is
     * stronger than team j if grid[i][j] == 1, otherwise, team j is stronger than team i.
     *
     * Team a will be the champion of the tournament if there is no team b that is stronger than team a.
     *
     * Return the team that will be the champion of the tournament.
     *
     * Input: grid = [[0,1],[0,0]]
     * Output: 0
     *
     * Input: grid = [[0,0,1],[1,0,1],[0,0,0]]
     * Output: 1
     *
     * Constraints:
     *
     * n == grid.length
     * n == grid[i].length
     * 2 <= n <= 100
     * grid[i][j] is either 0 or 1.
     * For all i, j that i != j, grid[i][j] != grid[j][i].
     * The input is generated such that if team a is stronger than team b and team b is stronger than team c, then team
     * a is stronger than team c.
     * @param grid
     * @return
     */
    // time = O(n^2), space = O(n)
    public int findChampion(int[][] grid) {
        int n = grid.length;
        boolean[] st = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (grid[i][j] == 1) st[j] = true;
                else st[i] = true;
            }
        }
        int res = -1;
        for (int i = 0; i < n; i++) {
            if (!st[i]) {
                res = i;
                break;
            }
        }
        return res;
    }
}