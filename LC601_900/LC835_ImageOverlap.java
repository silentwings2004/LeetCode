package LC601_900;

public class LC835_ImageOverlap {
    /**
     * You are given two images, img1 and img2, represented as binary, square matrices of size n x n. A binary matrix
     * has only 0s and 1s as values.
     *
     * We translate one image however we choose by sliding all the 1 bits left, right, up, and/or down any number of
     * units. We then place it on top of the other image. We can then calculate the overlap by counting the number of
     * positions that have a 1 in both images.
     *
     * Note also that a translation does not include any kind of rotation. Any 1 bits that are translated outside of
     * the matrix borders are erased.
     *
     * Return the largest possible overlap.
     *
     * Input: img1 = [[1,1,0],[0,1,0],[0,1,0]], img2 = [[0,0,0],[0,1,1],[0,0,1]]
     * Output: 3
     *
     * Input: img1 = [[1]], img2 = [[1]]
     * Output: 1
     *
     * Input: img1 = [[0]], img2 = [[0]]
     * Output: 0
     *
     * Constraints:
     *
     * n == img1.length == img1[i].length
     * n == img2.length == img2[i].length
     * 1 <= n <= 30
     * img1[i][j] is either 0 or 1.
     * img2[i][j] is either 0 or 1.
     * @param img1
     * @param img2
     * @return
     */
    // time = O(n^4), space = O(1)
    public int largestOverlap(int[][] img1, int[][] img2) {
        int res = 0;
        int n = img1.length;
        for (int i = -n; i < n; i++) {
            for (int j = -n; j < n; j++) {
                int cnt = 0;
                for (int x = Math.max(0, -i); x < Math.min(n, n - i); x++) {
                    for (int y = Math.max(0, -j); y < Math.min(n, n - j); y++) {
                        if (img1[i + x][j + y] == 1 && img2[x][y] == 1) cnt++;
                    }
                }
                res = Math.max(res, cnt);
            }
        }
        return res;
    }
}