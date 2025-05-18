package LC3001_3300;
import java.util.*;
public class LC3207_MaximumPointsAfterEnemyBattles {
    /**
     * You are given an integer array enemyEnergies denoting the energy values of various enemies.
     *
     * You are also given an integer currentEnergy denoting the amount of energy you have initially.
     *
     * You start with 0 points, and all the enemies are unmarked initially.
     *
     * You can perform either of the following operations zero or multiple times to gain points:
     *
     * Choose an unmarked enemy, i, such that currentEnergy >= enemyEnergies[i]. By choosing this option:
     * You gain 1 point.
     * Your energy is reduced by the enemy's energy, i.e. currentEnergy = currentEnergy - enemyEnergies[i].
     * If you have at least 1 point, you can choose an unmarked enemy, i. By choosing this option:
     * Your energy increases by the enemy's energy, i.e. currentEnergy = currentEnergy + enemyEnergies[i].
     * The enemy i is marked.
     * Return an integer denoting the maximum points you can get in the end by optimally performing operations.
     *
     * Input: enemyEnergies = [3,2,2], currentEnergy = 2
     *
     * Output: 3
     *
     * Input: enemyEnergies = [2], currentEnergy = 10
     *
     * Output: 5
     *
     * Constraints:
     *
     * 1 <= enemyEnergies.length <= 10^5
     * 1 <= enemyEnergies[i] <= 10^9
     * 0 <= currentEnergy <= 10^9
     * @param enemyEnergies
     * @param currentEnergy
     * @return
     */
    // S1
    // time = O(nlogn), space = O(logn)
    public long maximumPoints(int[] enemyEnergies, int currentEnergy) {
        Arrays.sort(enemyEnergies);
        if (currentEnergy < enemyEnergies[0]) return 0;
        int n = enemyEnergies.length, i = n - 1;
        long res = 0, p = 0, t = currentEnergy;
        while (true) {
            long x = t / enemyEnergies[0];
            t %= enemyEnergies[0];
            p += x;
            res += x;
            if (i == 0) break;
            while (i > 0 && p > 0) {
                t += enemyEnergies[i--];
                p--;
            }
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public long maximumPoints2(int[] enemyEnergies, int currentEnergy) {
        int minv = enemyEnergies[0];
        long sum = 0;
        for (int x : enemyEnergies) {
            minv = Math.min(minv, x);
            sum += x;
        }
        if (currentEnergy < minv) return 0;
        return (currentEnergy + sum - minv) / minv;
    }
}