package LC2700_3000;
import java.util.*;
public class LC2814_MinimumTimeTakestoReachDestinationWithoutDrowning {
    /**
     * You are given an n * m 0-indexed grid of string land. Right now, you are standing at the cell that contains "S",
     * and you want to get to the cell containing "D". There are three other types of cells in this land:
     *
     * ".": These cells are empty.
     * "X": These cells are stone.
     * "*": These cells are flooded.
     * At each second, you can move to a cell that shares a side with your current cell (if it exists). Also, at each
     * second, every empty cell that shares a side with a flooded cell becomes flooded as well.
     * There are two problems ahead of your journey:
     *
     * You can't step on stone cells.
     * You can't step on flooded cells since you will drown (also, you can't step on a cell that will be flooded at the
     * same time as you step on it).
     * Return the minimum time it takes you to reach the destination in seconds, or -1 if it is impossible.
     *
     * Note that the destination will never be flooded.
     *
     * Input: land = [["D",".","*"],[".",".","."],[".","S","."]]
     * Output: 3
     *
     * Input: land = [["D","X","*"],[".",".","."],[".",".","S"]]
     * Output: -1
     *
     * Input: land = [["D",".",".",".","*","."],[".","X",".","X",".","."],[".",".",".",".","S","."]]
     * Output: 6
     *
     * Constraints:
     *
     * 2 <= n, m <= 100
     * land consists only of "S", "D", ".", "*" and "X".
     * Exactly one of the cells is equal to "S".
     * Exactly one of the cells is equal to "D".
     * @param land
     * @return
     */
    // time = O(m * n), space = O(m * n)
    final int INF = 0x3f3f3f3f;
    public int minimumSeconds(List<List<String>> land) {
        int m = land.size(), n = land.get(0).size();
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(dist[i], INF);
        Queue<int[]> q = new LinkedList<>();
        int[] start = new int[2], end = new int[2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                String s = land.get(i).get(j);
                if (s.equals("*")) {
                    q.offer(new int[]{i, j});
                    dist[i][j] = 0;
                }
                else if (s.equals("S")) start = new int[]{i, j};
                else if (s.equals("D")) end = new int[]{i, j};
            }
        }

        int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] t = q.poll();
            int x = t[0], y = t[1];
            for (int i = 0; i < 4; i++) {
                int a = x + dx[i], b = y + dy[i];
                if (a < 0 || a >= m || b < 0 || b >= n) continue;
                if (!land.get(a).get(b).equals(".")) continue;
                if (dist[a][b] != INF) continue;
                dist[a][b] = Math.min(dist[a][b], dist[x][y] + 1);
                q.offer(new int[]{a, b});
            }
        }

        q.clear();
        q.offer(start);
        boolean[][] st = new boolean[m][n];
        st[start[0]][start[1]] = true;

        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] t = q.poll();
                int x = t[0], y = t[1];
                if (x == end[0] && y == end[1]) return step;
                for (int i = 0; i < 4; i++) {
                    int a = x + dx[i], b = y + dy[i];
                    if (a < 0|| a >= m || b < 0 || b >= n) continue;
                    if (st[a][b]) continue;
                    if (land.get(a).get(b).equals("X") || land.get(a).get(b).equals("*")) continue;
                    if (dist[a][b] <= step + 1) continue;
                    q.offer(new int[]{a, b});
                    st[a][b] = true;
                }
            }
            step++;
        }
        return -1;
    }
}