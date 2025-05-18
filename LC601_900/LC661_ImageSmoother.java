package LC601_900;

public class LC661_ImageSmoother {
    /**
     * An image smoother is a filter of the size 3 x 3 that can be applied to each cell of an image by rounding down the
     * average of the cell and the eight surrounding cells (i.e., the average of the nine cells in the blue smoother).
     * If one or more of the surrounding cells of a cell is not present, we do not consider it in the average
     * (i.e., the average of the four cells in the red smoother).
     *
     * Given an m x n integer matrix img representing the grayscale of an image, return the image after applying the
     * smoother on each cell of it.
     *
     * Input: img = [[1,1,1],[1,0,1],[1,1,1]]
     * Output: [[0,0,0],[0,0,0],[0,0,0]]
     *
     * Constraints:
     *
     * m == img.length
     * n == img[i].length
     * 1 <= m, n <= 200
     * 0 <= img[i][j] <= 255
     * @param img
     * @return
     */
    // time = O(m * n), space = O(1)
    public int[][] imageSmoother(int[][] img) {
        int m = img.length, n = img[0].length;
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int s = 0, c = 0;
                for (int x = i - 1; x <= i + 1; x++) {
                    for (int y = j - 1; y <= j + 1; y++) {
                        if (x < 0 || x >= m || y < 0 || y >= n) continue;
                        s += img[x][y];
                        c++;
                    }
                }
                res[i][j] = s / c;
            }
        }
        return res;
    }
}