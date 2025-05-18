package LC3001_3300;
import java.util.*;
public class LC3147_TakingMaximumEnergyFromtheMysticDungeon {
    /**
     * In a mystic dungeon, n magicians are standing in a line. Each magician has an attribute that gives you energy.
     * Some magicians can give you negative energy, which means taking energy from you.
     *
     * You have been cursed in such a way that after absorbing energy from magician i, you will be instantly
     * transported to magician (i + k). This process will be repeated until you reach the magician where (i + k) does
     * not exist.
     *
     * In other words, you will choose a starting point and then teleport with k jumps until you reach the end of the
     * magicians' sequence, absorbing all the energy during the journey.
     *
     * You are given an array energy and an integer k. Return the maximum possible energy you can gain.
     *
     * Input: energy = [5,2,-10,-5,1], k = 3
     *
     * Output: 3
     *
     * Input: energy = [-2,-3,-1], k = 2
     *
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= energy.length <= 10^5
     * -1000 <= energy[i] <= 1000
     * 1 <= k <= energy.length - 1
     * @param energy
     * @param k
     * @return
     */
    // S1: dp
    // time = O(n), space = O(n)
    public int maximumEnergy(int[] energy, int k) {
        final int inf = 0x3f3f3f3f;
        int n = energy.length, res = -inf;
        int[] f = new int[n];
        Arrays.fill(f, -inf);
        for (int i = n - 1; i >= 0; i--) {
            if (f[i] != -inf) continue;
            int t = 0, j = i;
            while (j < n) {
                if (f[j] != -inf) {
                    t += f[j];
                    break;
                }
                t += energy[j];
                j += k;
            }
            f[i] = t;
            res = Math.max(res, t);
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public int maximumEnergy2(int[] energy, int k) {
        int n = energy.length, res = Integer.MIN_VALUE;
        for (int i = n - k; i < n; i++) {
            int s = 0;
            for (int j = i; j >= 0; j -= k) {
                s += energy[j];
                res = Math.max(res, s);
            }
        }
        return res;
    }
}
/**
 * 起点任意，结束位置必须在数组的末尾
 * => 后缀和
 */