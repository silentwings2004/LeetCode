package LC1801_2100;
import java.util.*;
public class LC1992_FindAllGroupsofFarmland {
    /**
     * You are given a 0-indexed m x n binary matrix land where a 0 represents a hectare of forested land and a 1
     * represents a hectare of farmland.
     *
     * To keep the land organized, there are designated rectangular areas of hectares that consist entirely of farmland.
     * These rectangular areas are called groups. No two groups are adjacent, meaning farmland in one group is not
     * four-directionally adjacent to another farmland in a different group.
     *
     * land can be represented by a coordinate system where the top left corner of land is (0, 0) and the bottom right
     * corner of land is (m-1, n-1). Find the coordinates of the top left and bottom right corner of each group of
     * farmland. A group of farmland with a top left corner at (r1, c1) and a bottom right corner at (r2, c2) is
     * represented by the 4-length array [r1, c1, r2, c2].
     *
     * Return a 2D array containing the 4-length arrays described above for each group of farmland in land.
     * If there are no groups of farmland, return an empty array. You may return the answer in any order.
     *
     * Input: land = [[1,0,0],[0,1,1],[0,1,1]]
     * Output: [[0,0,0,0],[1,1,2,2]]
     *
     * Constraints:
     *
     * m == land.length
     * n == land[i].length
     * 1 <= m, n <= 300
     * land consists of only 0's and 1's.
     * Groups of farmland are rectangular in shape.
     * @param land
     * @return
     */
    // S1
    // time = O(m * n), space = O(m * n)
    int[][] g;
    int m, n;
    int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
    List<int[]> res;
    public int[][] findFarmland(int[][] land) {
        g = land;
        m = g.length;
        n = g[0].length;
        res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 1) {
                    int[] t = new int[]{i, j, i, j};
                    dfs(i, j, t);
                    res.add(t);
                }
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    private void dfs(int x, int y, int[] t) {
        g[x][y] = 0;
        for (int i = 0; i < 4; i++) {
            int a = x + dx[i], b = y + dy[i];
            if (a < 0 || a >= m || b < 0 || b >= n) continue;
            if (g[a][b] == 0) continue;
            t[2] = Math.max(t[2], a);
            t[3] = Math.max(t[3], b);
            dfs(a, b, t);
        }
    }

    // S2
    // time = O(m * n), space = O(m * n)
    public int[][] findFarmland2(int[][] land) {
        int m = land.length, n = land[0].length;
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (land[i][j] == 1) {
                    int x = i, y = j;
                    for (x = i; x < m && land[x][j] == 1; x++) {
                        for (y = j; y < n && land[x][y] == 1; y++) {
                            land[x][y] = 0;
                        }
                    }
                    int[] t = new int[]{i, j, x - 1, y - 1};
                    res.add(t);
                }
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}