package LC3301_3600;
import java.util.*;
public class LC3385_MinimumTimetoBreakLocksII {
    /**
     * Bob is stuck in a dungeon and must break n locks, each requiring some amount of energy to break. The required
     * energy for each lock is stored in an array called strength where strength[i] indicates the energy needed to break
     * the ith lock.
     *
     * To break a lock, Bob uses a sword with the following characteristics:
     *
     * The initial energy of the sword is 0.
     * The initial factor X by which the energy of the sword increases is 1.
     * Every minute, the energy of the sword increases by the current factor X.
     * To break the ith lock, the energy of the sword must reach at least strength[i].
     * After breaking a lock, the energy of the sword resets to 0, and the factor X increases by 1.
     * Your task is to determine the minimum time in minutes required for Bob to break all n locks and escape the dungeon.
     *
     * Return the minimum time required for Bob to break all n locks.
     *
     * Input: strength = [3,4,1]
     * Output: 4
     *
     * Input: strength = [2,5,4]
     * Output: 6
     *
     * Constraints:
     *
     * n == strength.length
     * 1 <= n <= 80
     * 1 <= strength[i] <= 10^6
     * n == strength.length
     * @param strength
     * @return
     */
    // time = O(n^3), space = O(n^2)
    public int findMinimumTime(int[] strength) {
        int n = strength.length;
        int[][] w = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                w[i][j] = (int)Math.ceil(1.0 * strength[j] / (i + 1));
            }
        }

        int[] ys = new int[n];
        int[] yl = new int[n];
        int[] ms = new int[n];
        int[] ml = new int[n];
        Arrays.fill(ms, -1);
        Arrays.fill(ml, -1);

        for (int i = 0; i < n; i++) { // sword
            int[] mins = new int[n];
            int[] so = new int[n]; // slack owner
            boolean[] vs = new boolean[n];
            boolean[] vl = new boolean[n];
            Arrays.fill(mins, Integer.MAX_VALUE);

            int cs = i, cl = -1;

            while (true) {
                vs[cs] = true;
                int delta = Integer.MAX_VALUE, nl = -1;
                for (int j = 0; j < n; j++) {
                    if (!vl[j]) {
                        int cost = w[cs][j] - ys[cs] - yl[j];
                        if (cost < mins[j]) {
                            mins[j] = cost;
                            so[j] = cs;
                        }
                        if (mins[j] < delta) {
                            delta = mins[j];
                            nl = j;
                        }
                    }
                }
                for (int j = 0; j < n; j++) {
                    if (vs[j]) ys[j] += delta;
                    if (vl[j]) yl[j] -= delta;
                    else mins[j] -= delta;
                }

                cl = nl;
                vl[cl] = true;
                int swordOwner = so[cl];
                if (ms[cl] == -1) {
                    while (cl != -1) {
                        int pl = ml[swordOwner];
                        ms[cl] = swordOwner;
                        ml[swordOwner] = cl;
                        cl = pl;
                        swordOwner = cl != -1 ? so[cl] : -1;
                    }
                    break;
                }
                cs = ms[cl];
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) res += w[i][ml[i]];
        return res;
    }
}