package LC3301_3600;
import java.util.*;
public class LC3342_FindMinimumTimetoReachLastRoomII {
    /**
     * There is a dungeon with n x m rooms arranged as a grid.
     *
     * You are given a 2D array moveTime of size n x m, where moveTime[i][j] represents the minimum time in seconds
     * when you can start moving to that room. You start from the room (0, 0) at time t = 0 and can move to an adjacent
     * room. Moving between adjacent rooms takes one second for one move and two seconds for the next, alternating
     * between the two.
     *
     * Return the minimum time to reach the room (n - 1, m - 1).
     *
     * Two rooms are adjacent if they share a common wall, either horizontally or vertically.
     *
     * Input: moveTime = [[0,4],[4,4]]
     *
     * Output: 7
     *
     * Input: moveTime = [[0,0,0,0],[0,0,0,0]]
     *
     * Output: 6
     *
     * Input: moveTime = [[0,1],[1,2]]
     *
     * Output: 4
     *
     * Constraints:
     *
     * 2 <= n == moveTime.length <= 750
     * 2 <= m == moveTime[i].length <= 750
     * 0 <= moveTime[i][j] <= 10^9
     * @param moveTime
     * @return
     */
    // time = O(m * n * log(m * n)), space = O(m * n)
    public int minTimeToReach(int[][] moveTime) {
        int m = moveTime.length, n = moveTime[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
        pq.offer(new int[]{0, 0, 0, 0});
        boolean[][] st = new boolean[m][n];

        int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
        while (!pq.isEmpty()) {
            int[] t = pq.poll();
            int cur = t[0], x = t[1], y = t[2], cnt = t[3];
            if (st[x][y]) continue;
            st[x][y] = true;
            if (x == m - 1 && y == n - 1) return cur;

            for (int i = 0; i < 4; i++) {
                int a = x + dx[i], b = y + dy[i];
                if (a < 0 || a >= m || b < 0 || b >= n) continue;
                if (st[a][b]) continue;
                int next = Math.max(moveTime[a][b], cur) + ((cnt + 1) % 2 == 0 ? 2 : 1);
                pq.offer(new int[]{next, a, b, (cnt + 1) % 2});
            }
        }
        return -1;
    }
}