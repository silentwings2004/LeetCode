package LC301_600;

public class LC302_SmallestRectangleEnclosingBlackPixels {
    /**
     * You are given an image that is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel.
     *
     * The black pixels are connected (i.e., there is only one black region). Pixels are connected horizontally and
     * vertically.
     *
     * Given two integers x and y that represent the location of one of the black pixels, return the area of the
     * smallest (axis-aligned) rectangle that encloses all black pixels.
     *
     * Input: image = [["0","0","1","0"],["0","1","1","0"],["0","1","0","0"]], x = 0, y = 2
     * Output: 6
     *
     * Constraints:
     *
     * m == image.length
     * n == image[i].length
     * 1 <= m, n <= 100
     * image[i][j] is either '0' or '1'.
     * 1 <= x < m
     * 1 <= y < n
     * image[x][y] == '1'.
     * The black pixels in the image only form one component.
     * @param image
     * @param x
     * @param y
     * @return
     */
    // time = O(nlogm + mlogn), space = O(1)
    public int minArea(char[][] image, int x, int y) {
        int m = image.length, n = image[0].length;
        int u = up(image, 0, x, 0, n - 1);
        int d = down(image, x, m - 1, 0, n - 1);
        int l = left(image, 0, m - 1, 0, y);
        int r = right(image, 0, m - 1, y, n - 1);
        return (r - l + 1) * (d - u + 1);
    }

    private int up(char[][] image, int rmin, int rmax, int cmin, int cmax) {
        int l = rmin, r = rmax;
        while (l < r) {
            int mid = l + r >> 1;
            boolean flag = false;
            for (int i = cmin; i <= cmax; i++) {
                if (image[mid][i] == '1') {
                    flag = true;
                    break;
                }
            }
            if (!flag) l = mid + 1;
            else r = mid;
        }
        return r;
    }

    private int down(char[][] image, int rmin, int rmax, int cmin, int cmax) {
        int l = rmin, r = rmax;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            boolean flag = false;
            for (int i = cmin; i <= cmax; i++) {
                if (image[mid][i] == '1') {
                    flag = true;
                    break;
                }
            }
            if (!flag) r = mid - 1;
            else l = mid;
        }
        return r;
    }

    private int left(char[][] image, int rmin, int rmax, int cmin, int cmax) {
        int l = cmin, r = cmax;
        while (l < r) {
            int mid = l + r >> 1;
            boolean flag = false;
            for (int i = rmin; i <= rmax; i++) {
                if (image[i][mid] == '1') {
                    flag = true;
                    break;
                }
            }
            if (!flag) l = mid + 1;
            else r = mid;
        }
        return r;
    }

    private int right(char[][] image, int rmin, int rmax, int cmin, int cmax) {
        int l = cmin, r = cmax;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            boolean flag = false;
            for (int i = rmin; i <= rmax; i++) {
                if (image[i][mid] == '1') {
                    flag = true;
                    break;
                }
            }
            if (!flag) r = mid - 1;
            else l = mid;
        }
        return r;
    }
}
