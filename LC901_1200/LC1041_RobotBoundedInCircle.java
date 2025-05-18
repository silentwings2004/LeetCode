package LC901_1200;
import java.util.*;
public class LC1041_RobotBoundedInCircle {
    /**
     * On an infinite plane, a robot initially stands at (0, 0) and faces north. The robot can receive one of three
     * instructions:
     *
     * "G": go straight 1 unit;
     * "L": turn 90 degrees to the left;
     * "R": turn 90 degrees to the right.
     * The robot performs the instructions given in order, and repeats them forever.
     *
     * Return true if and only if there exists a circle in the plane such that the robot never leaves the circle.
     *
     * Input: instructions = "GGLLGG"
     * Output: true
     *
     * Constraints:
     *
     * 1 <= instructions.length <= 100
     * instructions[i] is 'G', 'L' or, 'R'.
     * @param instructions
     * @return
     */
    // time = O(n), space = O(1)
    public boolean isRobotBounded(String instructions) {
        int x = 0, y = 0, d = 0, n = instructions.length();
        int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
        for (int i = 0; i < n; i++) {
            char c = instructions.charAt(i);
            if (c == 'G') {
                x += dx[d];
                y += dy[d];
            } else if (c == 'L') d = (d + 3) % 4;
            else d = (d + 1) % 4;
        }
        return x == 0 && y == 0 || d != 0;
    }
}
/**
 * 1.回到原点 true
 * 2.未回到起点  方向有四大类：
 * 1. 同样方向：一直沿着直线走 false
 * 2. 90度方向：true
 * 3. 相反方向：true
 */