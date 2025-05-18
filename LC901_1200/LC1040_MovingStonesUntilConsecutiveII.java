package LC901_1200;
import java.util.*;
public class LC1040_MovingStonesUntilConsecutiveII {
    /**
     * There are some stones in different positions on the X-axis. You are given an integer array stones, the positions
     * of the stones.
     *
     * Call a stone an endpoint stone if it has the smallest or largest position. In one move, you pick up an endpoint
     * stone and move it to an unoccupied position so that it is no longer an endpoint stone.
     *
     * In particular, if the stones are at say, stones = [1,2,5], you cannot move the endpoint stone at position 5,
     * since moving it to any position (such as 0, or 3) will still keep that stone as an endpoint stone.
     * The game ends when you cannot make any more moves (i.e., the stones are in three consecutive positions).
     *
     * Return an integer array answer of length 2 where:
     *
     * answer[0] is the minimum number of moves you can play, and
     * answer[1] is the maximum number of moves you can play.
     *
     * Input: stones = [7,4,9]
     * Output: [1,2]
     *
     * Input: stones = [6,5,4,3,10]
     * Output: [2,3]
     *
     * Constraints:
     *
     * 3 <= stones.length <= 10^4
     * 1 <= stones[i] <= 10^9
     * All the values of stones are unique.
     * @param stones
     * @return
     */
    // time = O(nlogn), space = O(logn)
    public int[] numMovesStonesII(int[] stones) {
        int[] res = new int[2];
        int n = stones.length;
        Arrays.sort(stones);
        int m = stones[n - 1] - stones[0] + 1 - n;
        res[1] = m - Math.min(stones[1] - stones[0] - 1, stones[n - 1] - stones[n - 2] - 1);

        res[0] = n;
        for (int i = 0, j = 0; i < n; i++) {
            while (stones[i] - stones[j] + 1 > n) j++; // 保证长度为n的滑窗
            m = i - j + 1; // 这段滑窗内有m个石子
            int r;
            if (m == n - 1 && stones[i] - stones[j] == i - j) r = 2; // 石子全部互相紧邻在一起
            else r = n - m;
            res[0] = Math.min(res[0], r);
        }
        return res;
    }
}
/**
 * 最短：求下所有长度为n的段内，最多包含多少个已有石子 => 操作多少次  >= n - m
 * 1. n == m => n - m = 0
 * 2. m = n - 1 => 有空隙 n - m; 无空隙 2
 * 3. m <= n - 2  => n - m
 *
 * 最多：
 * 选左 m1
 * 选右 m2
 * 不动的位置尽可能多
 * 所有长度为n的段里，最多包含多少个石子
 */