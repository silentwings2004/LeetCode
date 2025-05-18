package LC1201_1500;
import java.util.*;
public class LC1434_NumberofWaystoWearDifferentHatstoEachOther {
    /**
     * There are n people and 40 types of hats labeled from 1 to 40.
     *
     * Given a 2D integer array hats, where hats[i] is a list of all hats preferred by the ith person.
     *
     * Return the number of ways that the n people wear different hats to each other.
     *
     * Since the answer may be too large, return it modulo 10^9 + 7.
     *
     * Input: hats = [[3,4],[4,5],[5]]
     * Output: 1
     *
     * Input: hats = [[3,5,1],[3,5]]
     * Output: 4
     *
     * Input: hats = [[1,2,3,4],[1,2,3,4],[1,2,3,4],[1,2,3,4]]
     * Output: 24
     *
     * Constraints:
     *
     * n == hats.length
     * 1 <= n <= 10
     * 1 <= hats[i].length <= 40
     * 1 <= hats[i][j] <= 40
     * hats[i] contains a list of unique integers.
     * @param hats
     * @return
     */
    // time = O(n * 2^n), space = O(2^n)
    public int numberWays(List<List<Integer>> hats) {
        int n = hats.size(), mod = (int)1e9 + 7;
        long[] f = new long[1 << n];
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int x : hats.get(i)) {
                map.putIfAbsent(x - 1, new ArrayList<>());
                map.get(x - 1).add(i);
            }
        }

        f[0] = 1;
        for (int i = 0; i < 40; i++) {
            long[] g = f.clone();
            for (int state = 0; state < (1 << n); state++) {
                for (int j : map.getOrDefault(i, new ArrayList<>())) {
                    if ((state >> j & 1) == 1) continue;
                    f[state | 1 << j] = (f[state | 1 << j] + g[state]) % mod;
                }
            }
        }
        return (int)f[(1 << n) - 1];
    }
}
/**
 * 背包+状态压缩dp
 * ref: LC1125
 * 背包问题，最外层就是物品种类，内层是状态(容量)
 * state: 00000000000 ith-bit represents if the i-th hat has been taken
 * dp[state]: the number of ways for this state
 * 物品是人
 * for (int p = 0; p < n; p++) {
 *     for (int state = 000...000; state <= 111...111; state++) {
 *          for (int hat: hatsForThisPerson[p]) {
 *              if (hat has been taken in state) continue;
 *              dp_new[state+hat] += dp[state];
 *          }
 *     }
 * }
 * ans = sum(dp[state]) for those states contain n bits 1
 *
 * => state: 00000000000 ith-bit represents if the i-th person has taken a hat
 * for (int hat = 1; hat <= 40; hat++) {
 *     for (int state = 000...000; state <= 111...111; state++) {
 *         for (int person : PersonsForThisHat) {
 *             if (person has taken hat in this state) continue;
 *             dp_new[state+person] += dp[state]
 *         }
 *     }
 * }
 * ans = dp[111...111]
 */