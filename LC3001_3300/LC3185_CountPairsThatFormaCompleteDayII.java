package LC3001_3300;

public class LC3185_CountPairsThatFormaCompleteDayII {
    /**
     * Given an integer array hours representing times in hours, return an integer denoting the number of pairs i, j
     * where i < j and hours[i] + hours[j] forms a complete day.
     *
     * A complete day is defined as a time duration that is an exact multiple of 24 hours.
     *
     * For example, 1 day is 24 hours, 2 days is 48 hours, 3 days is 72 hours, and so on.
     *
     * Input: hours = [12,12,30,24,24]
     * Output: 2
     *
     * Input: hours = [72,48,24,3]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= hours.length <= 5 * 10^5
     * 1 <= hours[i] <= 10^9
     * @param hours
     * @return
     */
    // time = O(n), space = O(1)
    public long countCompleteDayPairs(int[] hours) {
        long res = 0;
        int[] cnt = new int[24];
        for (int x : hours) {
            int r = x % 24;
            res += cnt[(24 - r) % 24];
            cnt[r]++;
        }
        return res;
    }
}