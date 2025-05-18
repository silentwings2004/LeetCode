package LC3001_3300;
import java.util.*;
public class LC3273_MinimumAmountofDamageDealttoBob {
    /**
     * You are given an integer power and two integer arrays damage and health, both having length n.
     *
     * Bob has n enemies, where enemy i will deal Bob damage[i] points of damage per second while they are alive (i.e.
     * health[i] > 0).
     *
     * Every second, after the enemies deal damage to Bob, he chooses one of the enemies that is still alive and deals
     * power points of damage to them.
     *
     * Determine the minimum total amount of damage points that will be dealt to Bob before all n enemies are dead.
     *
     * Input: power = 4, damage = [1,2,3,4], health = [4,5,6,8]
     * Output: 39
     *
     * Input: power = 1, damage = [1,1,1,1], health = [1,2,3,4]
     * Output: 20
     *
     * Input: power = 8, damage = [40], health = [59]
     * Output: 320
     *
     * Constraints:
     *
     * 1 <= power <= 10^4
     * 1 <= n == damage.length == health.length <= 10^5
     * 1 <= damage[i], health[i] <= 10^4
     * @param power
     * @param damage
     * @param health
     * @return
     */
    // S1
    // time = O(nlogn), space = O(n)
    public long minDamage(int power, int[] damage, int[] health) {
        int n = damage.length;
        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> {
            long t1 = (o1[1] + power - 1) / power;
            long t2 = (o2[1] + power - 1) / power;
            return Long.compare(o2[0] * t1, o1[0] * t2);
        });
        for (int i = 0; i < n; i++) pq.offer(new long[]{damage[i], health[i]});

        long ct = 0, res = 0;
        while (!pq.isEmpty()) {
            long[] t = pq.poll();
            ct += (t[1] + power - 1) / power;
            res += t[0] * ct;
        }
        return res;
    }

    // S2
    // time = O(nlogn), space = O(n)
    public long minDamage2(int power, int[] damage, int[] health) {
        int n = health.length;
        int[][] nums = new int[n][2];
        for (int i = 0; i < n; i++) {
            nums[i] = new int[]{(health[i] + power - 1) / power, damage[i]};
        }
        Arrays.sort(nums, (o1, o2) -> o1[0] * o2[1] - o2[0] * o1[1]);
        long res = 0, s = 0;
        for (int[] x : nums) {
            s += x[0];
            res += s * x[1];
        }
        return res;
    }
}
/**
 * A -> B: ka * da + (ka + kb) * db
 * B -> A: kb * db + (ka + kb) * da
 * if A -> B 更小: ka * db < kb * da
 */