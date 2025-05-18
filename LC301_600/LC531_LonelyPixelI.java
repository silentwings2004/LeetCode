package LC301_600;

public class LC531_LonelyPixelI {
    /**
     * Given an m x n picture consisting of black 'B' and white 'W' pixels, return the number of black lonely pixels.
     *
     * A black lonely pixel is a character 'B' that located at a specific position where the same row and same column
     * don't have any other black pixels.
     *
     * Input: picture = [["W","W","B"],["W","B","W"],["B","W","W"]]
     * Output: 3
     *
     * Input: picture = [["B","B","B"],["B","B","W"],["B","B","B"]]
     * Output: 0
     *
     * Constraints:
     *
     * m == picture.length
     * n == picture[i].length
     * 1 <= m, n <= 500
     * picture[i][j] is 'W' or 'B'.
     * @param picture
     * @return
     */
    // time = O(m * n), space = O(m + n)
    public int findLonelyPixel(char[][] picture) {
        int m = picture.length, n = picture[0].length;
        int[] r = new int[m], c = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 'B') {
                    r[i]++;
                    c[j]++;
                }
            }
        }

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 'B') {
                    if (r[i] == 1 && c[j] == 1) res++;
                }
            }
        }
        return res;
    }
}