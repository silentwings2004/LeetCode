package LC3901_4200;
import java.util.*;
public class LC4045_CountRobotGroups {
    /**
     * You are given a strictly increasing integer array position, where position[i] is the initial position of the ith
     * robot at time t = 0.
     *
     * You are also given an integer array speed, where speed[i] is the constant speed of the ith robot in units per
     * second, and an integer distance.
     *
     * Time is continuous and measured in seconds. A robot or group with speed v moves v * t units to the right over any
     * interval of t seconds.
     *
     * Whenever the distance between two robots or groups becomes at most distance, they merge into a single group.
     *
     * If multiple robots or groups satisfy the merging condition at the same time, all merges happen simultaneously. In
     * particular, every connected collection of robots or groups whose consecutive positions differ by at most distance
     * merges into one group.
     *
     * After a merge, the resulting group takes the current position and speed of the rightmost robot in that group.
     * Once merged, robots never separate.
     *
     * Return the number of groups remaining after all possible merges have occurred.
     *
     * An array is strictly increasing if each element is strictly greater than its previous element, if one exists.
     *
     * Input: position = [1,5,6,20], speed = [4,3,2,3], distance = 1
     * Output: 2
     *
     * Input: position = [1,5,9], speed = [3,2,2], distance = 2
     * Output: 2
     *
     * Input: position = [9], speed = [8], distance = 5
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= position.length == speed.length <= 10^5
     * 1 <= position[i], speed[i], distance <= 10^9
     * position is strictly increasing.
     * @param position
     * @param speed
     * @param distance
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int countGroups(int[] position, int[] speed, int distance) {
        int n = position.length;
        int[] stk = new int[n + 1];
        int tt = 0;
        for (int i = 0; i < n; i++) {
            if (i == n - 1 || position[i + 1] - position[i] > distance) {
                int v = speed[i];
                while (tt > 0 && stk[tt] > v) tt--;
                stk[++tt] = v;
            }
        }
        return tt;
    }

    // S2
    // time = O(n), space = O(1)
    public int countGroups2(int[] position, int[] speed, int distance) {
        int n = speed.length, mn = speed[n - 1], res = 1;
        for (int i = n - 2; i >= 0; i--) {
            if (speed[i] <= mn && position[i + 1] - position[i] > distance) {
                mn = speed[i];
                res++;
            }
        }
        return res;
    }
}