package LC2401_2700;
import java.util.*;
public class LC2403_MinimumTimetoKillAllMonsters {
    /**
     * You are given an integer array power where power[i] is the power of the ith monster.
     *
     * You start with 0 mana points, and each day you increase your mana points by gain where gain initially is equal
     * to 1.
     *
     * Each day, after gaining gain mana, you can defeat a monster if your mana points are greater than or equal to the
     * power of that monster. When you defeat a monster:
     *
     * your mana points will be reset to 0, and
     * the value of gain increases by 1.
     * Return the minimum number of days needed to defeat all the monsters.
     *
     * Input: power = [3,1,4]
     * Output: 4
     *
     * Input: power = [1,1,4]
     * Output: 4
     *
     * Input: power = [1,2,4,9]
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= power.length <= 17
     * 1 <= power[i] <= 10^9
     * @param power
     * @return
     */
    // time = O(n * 2^n), space = O(2^n)
    public long minimumTime(int[] power) {
        int n = power.length;
        long[] f = new long[1 << n];
        Arrays.fill(f, Long.MAX_VALUE / 2);
        f[0] = 0;
        for (int i = 0; i < n; i++) f[1 << i] = power[i];

        for (int state = 1; state < 1 << n; state++) {
            for (int i = 0; i < n; i++) {
                if ((state >> i & 1) == 1) {
                    int subset = state - (1 << i);
                    f[state] = Math.min(f[state], f[subset] + helper(power, state, subset));
                }
            }
        }
        return f[(1 << n) - 1];
    }

    private long helper(int[] power, int s, int t) {
        int n = power.length;
        int x = s ^ t, k = Integer.bitCount(t);
        int p = 1 + k, i = 0;
        for (i = 0; i < n; i++) {
            if ((x >> i & 1) == 1) break;
        }
        return power[i] % p == 0 ? power[i] / p : power[i] / p + 1;
    }
}
