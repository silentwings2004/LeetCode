package LC601_900;
import java.util.*;
public class LC874_WalkingRobotSimulation {
    /**
     * A robot on an infinite XY-plane starts at point (0, 0) facing north. The robot can receive a sequence of these
     * three possible types of commands:
     *
     * -2: Turn left 90 degrees.
     * -1: Turn right 90 degrees.
     * 1 <= k <= 9: Move forward k units, one unit at a time.
     * Some of the grid squares are obstacles. The ith obstacle is at grid point obstacles[i] = (xi, yi). If the robot
     * runs into an obstacle, then it will instead stay in its current location and move on to the next command.
     *
     * Return the maximum Euclidean distance that the robot ever gets from the origin squared (i.e. if the distance is 5,
     * return 25).
     *
     * Note:
     *
     * North means +Y direction.
     * East means +X direction.
     * South means -Y direction.
     * West means -X direction.
     *
     * Input: commands = [4,-1,3], obstacles = []
     * Output: 25
     *
     * Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
     * Output: 65
     *
     * Constraints:
     *
     * 1 <= commands.length <= 10^4
     * commands[i] is either -2, -1, or an integer in the range [1, 9].
     * 0 <= obstacles.length <= 10^4
     * -3 * 104 <= xi, yi <= 3 * 10^4
     * The answer is guaranteed to be less than 2^31.
     * @param commands
     * @param obstacles
     * @return
     */
    // time = O(m + n), space = O(m)
    public int robotSim(int[] commands, int[][] obstacles) {
        HashSet<String> set = new HashSet<>();
        for (int[] x : obstacles) set.add(x[0] + "#" + x[1]);
        int x = 0, y = 0, d = 0, res = 0;
        int[] dx = new int[]{0, 1, 0, -1}, dy = new int[]{1, 0, -1, 0};
        for (int c : commands) {
            if (c == -2) d = (d + 3) % 4;
            else if (c == -1) d = (d + 1) % 4;
            else {
                for (int i = 0; i < c; i++) {
                    int a = x + dx[d], b = y + dy[d];
                    if (set.contains(a + "#" + b)) break;
                    x = a;
                    y = b;
                    res = Math.max(res, x * x + y * y);
                }
            }
        }
        return res;
    }
}