package LC601_900;
import java.util.*;
public class LC818_RaceCar {
    /**
     * Your car starts at position 0 and speed +1 on an infinite number line. Your car can go into negative positions.
     * Your car drives automatically according to a sequence of instructions 'A' (accelerate) and 'R' (reverse):
     *
     * When you get an instruction 'A', your car does the following:
     * position += speed
     * speed *= 2
     * When you get an instruction 'R', your car does the following:
     * If your speed is positive then speed = -1
     * otherwise speed = 1
     * Your position stays the same.
     * For example, after commands "AAR", your car goes to positions 0 --> 1 --> 3 --> 3, and your speed goes to
     * 1 --> 2 --> 4 --> -1.
     *
     * Given a target position target, return the length of the shortest sequence of instructions to get there.
     *
     * Input: target = 3
     * Output: 2
     *
     * Input: target = 6
     * Output: 5
     *
     *
     Constraints:

     1 <= target <= 10^4
     * @param target
     * @return
     */
    // time = O(tlogt), space = O(t)   t: 表示barrier的数量级
    public int racecar(int target) {
        int[][][] dist = new int[20010][15][2];
        for (int i = 0; i < 20010; i++) {
            for (int j = 0; j < 15; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }

        Queue<int[]> q = new LinkedList<>();
        dist[0][0][1] = 0;
        q.offer(new int[]{0, 0, 1});

        while (!q.isEmpty()) {
            int[] t = q.poll();

            int distance = dist[t[0]][t[1]][t[2]];
            int x = t[0] + (1 << t[1]) * (t[2] * 2 - 1);
            if (x >= 0 && x <= target * 2) {
                int k = t[1] + 1, d = t[2];
                if (dist[x][k][d] > distance + 1) {
                    dist[x][k][d] = distance + 1;
                    if (x == target) return distance + 1;
                    q.offer(new int[]{x, k, d});
                }
            }

            x = t[0];
            int k = 0, d = t[2] ^ 1;
            if (dist[x][k][d] > distance + 1) {
                dist[x][k][d] = distance + 1;
                q.offer(new int[]{x, k, d});
            }
        }
        return -1;
    }
}
/**
 * 1. x += speed, speed x *= 2;
 * 2. 反向 speed > 0  speed = -1
 *        speed < 0  speed = 1
 * (x, speed)
 * (0,1)
 * 宽搜
 * 1. (x + speed, speed * 2)
 * 2. (x, +/- 1)
 *
 * 1. 最后一步一定是加速
 * 2. 第一步一定可以为加速
 * ARARAR...A
 * 宽搜的范围：显然，进入负的位置是没有意义的，因为总可以找到一个更快得不进入负位置的答案。
 * 进入位置大于 2 * target 也是没有意义的，因为我们倒车不可能超过之前一步走的距离。
 * 所以搜索的范围被限制在了 [0, 2 * target] 中。
 */