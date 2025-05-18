package LC601_900;

public class LC733_FloodFill {
    /**
     * An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image.
     *
     * You are also given three integers sr, sc, and color. You should perform a flood fill on the image starting from
     * the pixel image[sr][sc].
     *
     * To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally to the starting
     * pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also
     * with the same color), and so on. Replace the color of all of the aforementioned pixels with color.
     *
     * Return the modified image after performing the flood fill.
     *
     * Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
     * Output: [[2,2,2],[2,2,0],[2,0,1]]
     *
     * Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0
     * Output: [[0,0,0],[0,0,0]]
     *
     * Constraints:
     *
     * m == image.length
     * n == image[i].length
     * 1 <= m, n <= 50
     * 0 <= image[i][j], color < 216
     * 0 <= sr < m
     * 0 <= sc < n
     * @param image
     * @param sr
     * @param sc
     * @param color
     * @return
     */
    // time = O(m * n), space = O(m * n)
    int[][] g;
    int m, n;
    int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        g = image;
        m = g.length;
        n = g[0].length;
        int oc = g[sr][sc];
        if (oc == color) return g;
        dfs(sr, sc, oc, color);
        return g;
    }

    private void dfs(int x, int y, int oc, int nc) {
        g[x][y] = nc;
        for (int i = 0; i < 4; i++) {
            int a = x + dx[i], b = y + dy[i];
            if (a < 0 || a >= m || b < 0 || b >= n) continue;
            if (g[a][b] != oc) continue;
            dfs(a, b, oc, nc);
        }
    }
}