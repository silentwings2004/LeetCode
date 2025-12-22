package LC3601_3900;
import java.util.*;
public class LC3776_MinimumMovestoBalanceCircularArray {
    /**
     * You are given a circular array balance of length n, where balance[i] is the net balance of person i.
     *
     * In one move, a person can transfer exactly 1 unit of balance to either their left or right neighbor.
     *
     * Return the minimum number of moves required so that every person has a non-negative balance. If it is impossible,
     * return -1.
     *
     * Note: You are guaranteed that at most 1 index has a negative balance initially.
     *
     * Input: balance = [5,1,-4]
     * Output: 4
     *
     * Input: balance = [1,2,-5,2]
     * Output: 6
     *
     * Input: balance = [-3,2]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= n == balance.length <= 10^5
     * -109 <= balance[i] <= 10^9
     * There is at most one negative value in balance initially.
     * @param balance
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public long minMoves(int[] balance) {
        int n = balance.length, idx = -1;
        long tot = 0;
        for (int i = 0; i < n; i++) {
            if (balance[i] < 0) idx = i;
            tot += balance[i];
        }
        if (idx == -1) return 0;
        if (tot < 0) return -1;

        long need = -balance[idx];
        List<int[]> q = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i == idx) continue;
            int d1 = (i - idx + n) % n;
            int d2 = (idx - i + n) % n;
            int d = Math.min(d1, d2);
            q.add(new int[]{d, balance[i]});
        }
        Collections.sort(q, (o1, o2) -> o1[0] - o2[0]);
        long res = 0;
        for (int[] x : q) {
            long v = Math.min(need, x[1]);
            res += v * x[0];
            need -= v;
            if (need == 0) break;
        }
        return need == 0 ? res : -1;
    }

    // S2
    // time = O(n), space = O(1)
    public long minMoves2(int[] balance) {
        int n = balance.length, idx = -1;
        long tot = 0;
        for (int i = 0; i < n; i++) {
            if (balance[i] < 0) idx = i;
            tot += balance[i];
        }
        if (idx == -1) return 0;
        if (tot < 0) return -1;

        long need = -balance[idx], res = 0;
        for (int d = 1; ; d++){
            int s = balance[(idx - d + n) % n] + balance[(idx + d) % n];
            if (s >= need) {
                res += need * d;
                break;
            }
            res += 1L * s * d;
            need -= s;
        }
        return res;
    }
}