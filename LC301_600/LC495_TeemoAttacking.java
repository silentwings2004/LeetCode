package LC301_600;

public class LC495_TeemoAttacking {
    /**
     * Our hero Teemo is attacking an enemy Ashe with poison attacks! When Teemo attacks Ashe, Ashe gets poisoned for
     * a exactly duration seconds. More formally, an attack at second t will mean Ashe is poisoned during the inclusive
     * time interval [t, t + duration - 1]. If Teemo attacks again before the poison effect ends, the timer for it is
     * reset, and the poison effect will end duration seconds after the new attack.
     *
     * You are given a non-decreasing integer array timeSeries, where timeSeries[i] denotes that Teemo attacks Ashe at
     * second timeSeries[i], and an integer duration.
     *
     * Return the total number of seconds that Ashe is poisoned.
     *
     * Input: timeSeries = [1,4], duration = 2
     * Output: 4
     *
     * Input: timeSeries = [1,2], duration = 2
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= timeSeries.length <= 10^4
     * 0 <= timeSeries[i], duration <= 10^7
     * timeSeries is sorted in non-decreasing order.
     * @param timeSeries
     * @param duration
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int n = timeSeries.length, res = 0;
        if (n == 1) return duration;
        for (int i = 1; i < n; i++) {
            int diff = timeSeries[i] - timeSeries[i - 1];
            res += diff < duration ? diff : duration;
        }
        return res + duration;
    }

    // S1.2
    // time = O(n), space = O(1)
    public int findPoisonedDuration2(int[] timeSeries, int duration) {
        int n = timeSeries.length, res = 0;
        for (int i = 1; i < n; i++) {
            res += Math.min(timeSeries[i] - timeSeries[i - 1], duration);
        }
        return res + duration;
    }
}
