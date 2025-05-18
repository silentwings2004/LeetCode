package LC601_900;

public class LC799_ChampagneTower {
    /**
     * We stack glasses in a pyramid, where the first row has 1 glass, the second row has 2 glasses, and so on until the
     * 100th row.  Each glass holds one cup of champagne.
     *
     * Then, some champagne is poured into the first glass at the top.  When the topmost glass is full, any excess
     * liquid poured will fall equally to the glass immediately to the left and right of it.  When those glasses become
     * full, any excess champagne will fall equally to the left and right of those glasses, and so on.  (A glass at the
     * bottom row has its excess champagne fall on the floor.)
     *
     * For example, after one cup of champagne is poured, the top most glass is full.  After two cups of champagne are
     * poured, the two glasses on the second row are half full.  After three cups of champagne are poured, those two
     * cups become full - there are 3 full glasses total now.  After four cups of champagne are poured, the third row
     * has the middle glass half full, and the two outside glasses are a quarter full, as pictured below.
     *
     * Now after pouring some non-negative integer cups of champagne, return how full the jth glass in the ith row is
     * (both i and j are 0-indexed.)
     *
     * Input: poured = 1, query_row = 1, query_glass = 1
     * Output: 0.00000
     *
     * Constraints:
     *
     * 0 <= poured <= 10^9
     * 0 <= query_glass <= query_row < 100
     * @param poured
     * @param query_row
     * @param query_glass
     * @return
     */
    // time = O(n^2), space = O(n^2)
    public double champagneTower(int poured, int query_row, int query_glass) {
        int m = query_row, n = query_glass;
        double[][] f = new double[m + 1][m + 1];
        f[0][0] = poured;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j <= i; j++) {
                if (f[i][j] > 1) {
                    double x = (f[i][j] - 1) / 2;
                    f[i + 1][j] += x;
                    f[i + 1][j + 1] += x;
                }
            }
        }
        return Math.min(1.0, f[m][n]);
    }
}
/**
 * if f(i,j) > 1 => x = (f(i,j) - 1) / 2
 */
