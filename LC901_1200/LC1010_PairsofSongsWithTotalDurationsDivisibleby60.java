package LC901_1200;
public class LC1010_PairsofSongsWithTotalDurationsDivisibleby60 {
    /**
     * You are given a list of songs where the ith song has a duration of time[i] seconds.
     *
     * Return the number of pairs of songs for which their total duration in seconds is divisible by 60. Formally, we
     * want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.
     *
     * Input: time = [30,20,150,100,40]
     * Output: 3
     *
     * Input: time = [60,60,60]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= time.length <= 6 * 10^4
     * 1 <= time[i] <= 500
     * @param time
     * @return
     */
    // time = O(n), space = O(1)
    public int numPairsDivisibleBy60(int[] time) {
        int[] s = new int[60];
        int res = 0;
        for (int x : time) {
            res += s[(60 - x % 60) % 60];
            s[x % 60]++;
        }
        return res;
    }
}