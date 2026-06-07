package LC3901_4200;
import java.util.*;
public class LC3951_MinimumEnergytoMaintainBrightness {
    /**
     * You are given an integer n, representing n light bulbs arranged in a line and indexed from 0 to n - 1.
     *
     * You are also given an integer brightness and a 2D integer array intervals, where intervals[i] = [starti, endi]
     * represents an inclusive time interval during which the lighting requirement must be satisfied.
     *
     * At each time unit, every bulb can independently be either on or off. A bulb that is on illuminates its own
     * position and its adjacent positions, if they exist.
     *
     * The total illumination at a time unit is the number of illuminated positions. Each position is counted at most
     * once.
     *
     * For every integer time unit covered by at least one interval in intervals, the total illumination must be at
     * least brightness. At time units not covered by any interval, all bulbs may remain off. Each bulb that is on
     * consumes 1 unit of energy for that time unit.
     *
     * Return an integer denoting the minimum total energy required.
     *
     * Input: n = 5, brightness = 5, intervals = [[6,12]]
     * Output: 14
     *
     * Input: n = 2, brightness = 1, intervals = [[0,0],[2,2]]
     * Output: 2
     *
     * Input: n = 4, brightness = 2, intervals = [[1,3],[2,4]]
     * Output: 4
     *
     * Constraints:
     *
     * 1 <= n <= 10^6
     * 1 <= brightness <= n
     * 1 <= intervals.length <= 10^5
     * intervals[i] == [starti, endi]
     * 0 <= starti <= endi <= 10^9
     * @param n
     * @param brightness
     * @param intervals
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public long minEnergy(int n, int brightness, int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        int st = -1, ed = -1;
        long res = 0;
        for (int[] x : intervals) {
            if (ed < x[0]) {
                if (st != -1) res += (ed - st + 1L) * ((brightness + 2) / 3);
                st = x[0];
                ed = x[1];
            } else ed = Math.max(ed, x[1]);
        }
        if (st != -1) res += (ed - st + 1L) * ((brightness + 2) / 3);
        return res;
    }
}