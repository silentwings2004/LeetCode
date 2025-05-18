package LC3001_3300;

public class LC3259_MaximumEnergyBoostFromTwoDrinks {
    /**
     * You are given two integer arrays energyDrinkA and energyDrinkB of the same length n by a futuristic sports
     * scientist. These arrays represent the energy boosts per hour provided by two different energy drinks, A and B,
     * respectively.
     *
     * You want to maximize your total energy boost by drinking one energy drink per hour. However, if you want to
     * switch from consuming one energy drink to the other, you need to wait for one hour to cleanse your system
     * (meaning you won't get any energy boost in that hour).
     *
     * Return the maximum total energy boost you can gain in the next n hours.
     *
     * Note that you can start consuming either of the two energy drinks.
     *
     * Input: energyDrinkA = [1,3,1], energyDrinkB = [3,1,1]
     *
     * Output: 5
     *
     * Input: energyDrinkA = [4,1,1], energyDrinkB = [1,1,3]
     *
     * Output: 7
     *
     * Constraints:
     *
     * n == energyDrinkA.length == energyDrinkB.length
     * 3 <= n <= 10^5
     * 1 <= energyDrinkA[i], energyDrinkB[i] <= 10^5
     * @param energyDrinkA
     * @param energyDrinkB
     * @return
     */
    // time = O(n), space = O(n)
    public long maxEnergyBoost(int[] energyDrinkA, int[] energyDrinkB) {
        int n = energyDrinkA.length;
        long[][] f = new long[n][2];
        f[0][0] = energyDrinkA[0];
        f[0][1] = energyDrinkB[0];
        for (int i = 1; i < n; i++) {
            f[i][0] = Math.max(f[i - 1][0] + energyDrinkA[i], f[i - 1][1]);
            f[i][1] = Math.max(f[i - 1][1] + energyDrinkB[i], f[i - 1][0]);
        }
        return Math.max(f[n - 1][0], f[n - 1][1]);
    }
}