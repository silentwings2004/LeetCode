package LC2401_2700;
import java.util.*;
public class LC2463_MinimumTotalDistanceTraveled {
    /**
     * There are some robots and factories on the X-axis. You are given an integer array robot where robot[i] is the
     * position of the ith robot. You are also given a 2D integer array factory where factory[j] = [positionj, limitj]
     * indicates that positionj is the position of the jth factory and that the jth factory can repair at most limitj
     * robots.
     *
     * The positions of each robot are unique. The positions of each factory are also unique. Note that a robot can be
     * in the same position as a factory initially.
     *
     * All the robots are initially broken; they keep moving in one direction. The direction could be the negative or
     * the positive direction of the X-axis. When a robot reaches a factory that did not reach its limit, the factory
     * repairs the robot, and it stops moving.
     *
     * At any moment, you can set the initial direction of moving for some robot. Your target is to minimize the total
     * distance traveled by all the robots.
     *
     * Return the minimum total distance traveled by all the robots. The test cases are generated such that all the
     * robots can be repaired.
     *
     * Note that
     *
     * All robots move at the same speed.
     * If two robots move in the same direction, they will never collide.
     * If two robots move in opposite directions and they meet at some point, they do not collide. They cross each other.
     * If a robot passes by a factory that reached its limits, it crosses it as if it does not exist.
     * If the robot moved from a position x to a position y, the distance it moved is |y - x|.
     *
     * Input: robot = [0,4,6], factory = [[2,2],[6,2]]
     * Output: 4
     *
     * Input: robot = [1,-1], factory = [[-2,1],[2,1]]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= robot.length, factory.length <= 100
     * factory[j].length == 2
     * -10^9 <= robot[i], positionj <= 10^9
     * 0 <= limitj <= robot.length
     * The input will be generated such that it is always possible to repair every robot.
     * @param robot
     * @param factory
     * @return
     */
    // time = O(m * n * k), space = O(m * n)
    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        Collections.sort(robot);
        Arrays.sort(factory, (o1, o2) -> o1[0] - o2[0]);
        int n = robot.size(), m = factory.length;
        long[][] f = new long[m + 1][n + 1];
        for (int i = 0; i <= m; i++) Arrays.fill(f[i], Long.MAX_VALUE / 2);
        for (int i = 0; i <= m; i++) f[i][0] = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                long dist = 0;
                f[i][j] = f[i - 1][j]; // k == 0
                for (int k = 1; k <= Math.min(factory[i - 1][1], j); k++) {
                    dist += Math.abs(robot.get(j - k) - factory[i - 1][0]);
                    f[i][j] = Math.min(f[i][j], f[i - 1][j - k] + dist);
                }
            }
        }
        return f[m][n];
    }
}
/**
 * 关键点：最左边机器人一定和最左边工厂对应起来
 * 而第二个机器人可能和左边，也可能和右边工厂对应起来
 * robot i < j
 * factory x < y
 * 不可能有错位的情况
 * x | x x x | x x | x x x
 *               j'      j
 * dp[i][j]: the minimum cost so that the first i factories covers j robots
 * dp[i][j]:
 * for (int i = ...)
 *   for (int j = ...)
 *      for (int k = 0; k <= limit[i]; k++) {
 *          dp[i][j] = min{dp[i-1][j-k] + dist(j - k + 1, j, i)};
 *      }
 *    }
 * }
 */