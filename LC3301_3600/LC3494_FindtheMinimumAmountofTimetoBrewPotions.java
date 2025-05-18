package LC3301_3600;

public class LC3494_FindtheMinimumAmountofTimetoBrewPotions {
    /**
     * You are given two integer arrays, skill and mana, of length n and m, respectively.
     *
     * In a laboratory, n wizards must brew m potions in order. Each potion has a mana capacity mana[j] and must pass
     * through all the wizards sequentially to be brewed properly. The time taken by the ith wizard on the jth potion is
     * timeij = skill[i] * mana[j].
     *
     * Since the brewing process is delicate, a potion must be passed to the next wizard immediately after the current
     * wizard completes their work. This means the timing must be synchronized so that each wizard begins working on a
     * potion exactly when it arrives.
     *
     * Return the minimum amount of time required for the potions to be brewed properly.
     *
     * Input: skill = [1,5,2,4], mana = [5,1,4,2]
     * Output: 110
     *
     * Input: skill = [1,1,1], mana = [1,1,1]
     * Output: 5
     *
     * Input: skill = [1,2,3,4], mana = [1,2]
     * Output: 21
     *
     * Constraints:
     *
     * n == skill.length
     * m == mana.length
     * 1 <= n, m <= 5000
     * 1 <= mana[i], skill[i] <= 5000
     * @param skill
     * @param mana
     * @return
     */
    // time = O(m * n), space = O(m)
    public long minTime(int[] skill, int[] mana) {
        int m = skill.length, n = mana.length;
        long[] t = new long[m + 1];
        for (int i = 1; i <= m; i++) t[i] = t[i - 1] + 1L * skill[i - 1] * mana[0];
        for (int j = 1; j < n; j++) {
            t[0] = t[m];
            for (int i = m - 1; i > 0; i--) {
                t[0] = Math.max(t[i], t[0] - 1L * skill[i - 1] * mana[j]);
            }
            for (int i = 1; i <= m; i++) t[i] = t[i - 1] + 1L * skill[i - 1] * mana[j];
        }
        return t[m];
    }
}