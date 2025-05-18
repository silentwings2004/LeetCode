package LC301_600;
import java.util.*;
public class LC533_LonelyPixelII {
    /**
     * Given an m x n picture consisting of black 'B' and white 'W' pixels and an integer target, return the number of
     * black lonely pixels.
     *
     * A black lonely pixel is a character 'B' that located at a specific position (r, c) where:
     *
     * Row r and column c both contain exactly target black pixels.
     * For all rows that have a black pixel at column c, they should be exactly the same as row r.
     *
     * Input: picture = [["W","B","W","B","B","W"],["W","B","W","B","B","W"],["W","B","W","B","B","W"],
     * ["W","W","B","W","B","W"]], target = 3
     * Output: 6
     *
     * Input: picture = [["W","W","B"],["W","W","B"],["W","W","B"]], target = 1
     * Output: 0
     *
     * Constraints:
     *
     * m == picture.length
     * n == picture[i].length
     * 1 <= m, n <= 200
     * picture[i][j] is 'W' or 'B'.
     * 1 <= target <= min(m, n)
     * @param picture
     * @param target
     * @return
     */
    // time = O(m * n), space = O(m + n)
    HashMap<Integer, HashSet<Long>> map;
    long[] rows;
    boolean[] cols;
    int P = 131;
    public int findBlackPixel(char[][] picture, int target) {
        int m = picture.length, n = picture[0].length;
        int[] r = new int[m], c = new int[n];
        map = new HashMap<>();
        rows = new long[m];
        cols = new boolean[n];
        for (int i = 0; i < m; i++) {
            long h = 0;
            for (int j = 0; j < n; j++) {
                h = h * P + picture[i][j];
            }
            rows[i] = h;
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 'B') {
                    r[i]++;
                    c[j]++;
                    map.putIfAbsent(j, new HashSet<>());
                    map.get(j).add(rows[i]);
                    if (map.get(j).size() > 1) cols[j] = true;
                }
            }
        }

        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 'B') {
                    if (r[i] != target || c[j] != target || cols[j]) continue;
                    res++;
                }
            }
        }
        return res;
    }
}