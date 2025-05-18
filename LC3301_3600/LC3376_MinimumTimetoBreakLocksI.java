package LC3301_3600;
import java.util.*;
public class LC3376_MinimumTimetoBreakLocksI {
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
     * After breaking a lock, the energy of the sword resets to 0, and the factor X increases by a given value K.
     * Your task is to determine the minimum time in minutes required for Bob to break all n locks and escape the dungeon.
     *
     * Return the minimum time required for Bob to break all n locks.
     *
     * Input: strength = [3,4,1], K = 1
     * Output: 4
     *
     * Input: strength = [2,5,4], K = 2
     * Output: 5
     *
     * Constraints:
     *
     * n == strength.length
     * 1 <= n <= 8
     * 1 <= K <= 10
     * 1 <= strength[i] <= 106
     * @param strength
     * @param K
     * @return
     */
    // time = O(n * 2^n), space = O(2^n)
    int res = 0x3f3f3f3f;
    int n, k;
    public int findMinimumTime(List<Integer> strength, int K) {
        n = strength.size();
        k = K;
        dfs(strength, 0, 1, 0);
        return res;
    }

    private void dfs(List<Integer> q, int t, int x, int state) {
        if (state == (1 << n) - 1) {
            res = Math.min(res, t);
            return;
        }
        if (t > res) return;

        for (int i = 0; i < n; i++) {
            if ((state >> i & 1) == 1) continue;
            state |= 1 << i;
            dfs(q, t + (q.get(i) + x - 1) / x, x + k, state);
            state ^= 1 << i;
        }
    }
}