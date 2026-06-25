package LC3901_4200;
import java.util.*;
public class LC3961_MaximizeSumofDeviceRatings {
    /**
     * You are given a 2D integer array units of size m × n where units[i][j] represents the capacity of the jth unit
     * in the ith device. Each device contains exactly n units.
     *
     * The rating of a device is the minimum capacity among all its units.
     *
     * You may perform the following operation any number of times (including zero):
     *
     * Choose a device i that has not been used as a source before.
     * Remove exactly one unit from
     * device i and add it to any different device.
     * Then mark the device i as used, so it cannot be chosen again as a source.
     * Return the maximum possible sum of the ratings of all devices after any number of such operations.
     *
     * Note:
     *
     * Devices can receive units from multiple devices, regardless of whether they have been selected.
     * The rating of an empty device is 0.
     *
     * Input: units = [[1,3],[2,2]]
     * Output: 4
     *
     * Input: units = [[1,2,3],[4,5,6]]
     * Output: 6
     *
     * Input: units = [[5,5,5],[1,1,1]]
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= m == units.length <= 10^5
     * 1 <= n == units[i].length <= 10^5
     * m * n <= 2 * 10^5
     * 1 <= units[i][j] <= 10^5
     * @param units
     * @return
     */
    // time = O(m * n + mlogm), sapce = O(m)
    public long maxRatings(int[][] units) {
        int m = units.length, n = units[0].length;
        int inf = 0x3f3f3f3f;
        int[][] w = new int[m][3];
        long tot = 0;
        for (int i = 0; i < m; i++) {
            int mv = inf, mv2 = inf;
            for (int j = 0; j < n; j++) {
                int v = units[i][j];
                if (v < mv) {
                    mv2 = mv;
                    mv = v;
                } else if (v < mv2) mv2 = v;
            }
            if (n == 1) mv2 = 0;
            w[i] = new int[]{mv, mv2, mv2 - mv};
            tot += mv;
        }
        if (m == 1) return tot;

        Arrays.sort(w, (o1, o2) -> o2[0] - o1[0]);
        long res = 0;
        int last = w[m - 1][0];
        for (int i = 0; i < m - 1; i++) res += Math.max(0, w[i][2]); // all to the last (m-1)th device
        if (w[m - 1][2] > 0) { // 最后一个设备也要升级
            int bl = inf;
            for (int i = 0; i < m - 1; i++) {
                int cr = w[i][2] > 0 ? w[i][1] : w[i][0];
                int nr = Math.min(cr, last);
                int loss = cr - nr;
                bl = Math.min(bl, loss);
            }
            if (w[m - 1][2] > bl) res += w[m - 1][2] - bl;
        }
        return tot + res;
    }

    // S2
    // time = O(m * n), space = O(1)
    public long maxRatings2(int[][] units) {
        long res = 0;
        if (units[0].length == 1) {
            for (int[] x : units) res += x[0];
            return res;
        }

        int mn = Integer.MAX_VALUE, mn2 = Integer.MAX_VALUE;
        for (int[] unit : units) {
            int a = Integer.MAX_VALUE, b = Integer.MAX_VALUE;
            for (int x : unit) {
                if (x < a) {
                    b = a;
                    a = x;
                } else if (x < b) b = x;
            }
            res += b;
            mn2 = Math.min(mn2, b);
            mn = Math.min(mn, a);
        }
        res += mn - mn2;
        return res;
    }
}
/**
 * 把所有行的最小值集中到一起
 * 把每一排次小值加起来，然后把次小值最小的那排来安置最小值
 */