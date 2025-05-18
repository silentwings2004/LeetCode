package LC601_900;

public class LC832_FlippinganImage {
    /**
     * Given an n x n binary matrix image, flip the image horizontally, then invert it, and return the resulting image.
     *
     * To flip an image horizontally means that each row of the image is reversed.
     *
     * For example, flipping [1,1,0] horizontally results in [0,1,1].
     * To invert an image means that each 0 is replaced by 1, and each 1 is replaced by 0.
     *
     * For example, inverting [0,1,1] results in [1,0,0].
     *
     * Input: image = [[1,1,0],[1,0,1],[0,0,0]]
     * Output: [[1,0,0],[0,1,0],[1,1,1]]
     *
     * Input: image = [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
     * Output: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
     *
     * Constraints:
     *
     * n == image.length
     * n == image[i].length
     * 1 <= n <= 20
     * images[i][j] is either 0 or 1.
     * @param image
     * @return
     */
    // time = O(m * n), space = O(1)
    public int[][] flipAndInvertImage(int[][] image) {
         int m = image.length, n = image[0].length;
         for (int i = 0; i < m; i++) {
             for (int j = 0, k = n - 1; j < k; j++, k--) {
                 int t = image[i][j];
                 image[i][j] = image[i][k];
                 image[i][k] = t;
             }
             for (int j = 0; j < n; j++) image[i][j] ^= 1;
         }
         return image;
    }
}