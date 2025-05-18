package LC601_900;
import java.util.*;
public class LC853_CarFleet {
    /**
     * There are n cars going to the same destination along a one-lane road. The destination is target miles away.
     *
     * You are given two integer array position and speed, both of length n, where position[i] is the position of the
     * ith car and speed[i] is the speed of the ith car (in miles per hour).
     *
     * A car can never pass another car ahead of it, but it can catch up to it and drive bumper to bumper at the same
     * speed. The faster car will slow down to match the slower car's speed. The distance between these two cars is
     * ignored (i.e., they are assumed to have the same position).
     *
     * A car fleet is some non-empty set of cars driving at the same position and same speed. Note that a single car is
     * also a car fleet.
     *
     * If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet.
     *
     * Return the number of car fleets that will arrive at the destination.
     *
     * Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
     * Output: 3
     *
     * Input: target = 10, position = [3], speed = [3]
     * Output: 1
     *
     * Input: target = 100, position = [0,2,4], speed = [4,2,1]
     * Output: 1
     *
     * Constraints:
     *
     * n == position.length == speed.length
     * 1 <= n <= 10^5
     * 0 < target <= 10^6
     * 0 <= position[i] < target
     * All the values of position are unique.
     * 0 < speed[i] <= 10^6
     * @param target
     * @param position
     * @param speed
     * @return
     */
    // time = O(nlogn), space = O(n)
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        int[][] w = new int[n][2];
        for (int i = 0; i < n; i++) w[i] = new int[]{position[i], speed[i]};
        Arrays.sort(w, (o1, o2) -> o1[0] - o2[0]);

        int res = n;
        double last = 0;
        for (int i = n - 1; i >= 0; i--) {
            double t = (target - w[i][0]) * 1.0 / w[i][1];
            if (t <= last) res--;
            else last = t;
        }
        return res;
    }
}