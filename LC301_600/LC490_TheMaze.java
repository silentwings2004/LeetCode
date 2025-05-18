package LC301_600;
import java.util.*;
public class LC490_TheMaze {
    /**
     * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). The ball can go
     * through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall.
     * When the ball stops, it could choose the next direction.
     *
     * Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] and
     * estination = [destinationrow, destinationcol], return true if the ball can stop at the destination, otherwise
     * return false.
     *
     * You may assume that the borders of the maze are all walls (see examples).
     *
     * Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
     * Output: true
     *
     * Constraints:
     *
     * m == maze.length
     * n == maze[i].length
     * 1 <= m, n <= 100
     * maze[i][j] is 0 or 1.
     * start.length == 2
     * destination.length == 2
     * 0 <= startrow, destinationrow <= m
     * 0 <= startcol, destinationcol <= n
     * Both the ball and the destination exist in an empty space, and they will not be in the same position initially.
     * The maze contains at least 2 empty spaces.
     * @param maze
     * @param start
     * @param destination
     * @return
     */
    // time = O(m * n), space = O(m * n)
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length;
        boolean[][] st = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();
        q.offer(start);
        st[start[0]][start[1]] = true;

        int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] t = q.poll();
            if (t[0] == destination[0] && t[1] == destination[1]) return true;
            for (int i = 0; i < 4; i++) {
                int x = t[0], y = t[1];
                while (true) {
                    int a = x + dx[i], b = y + dy[i];
                    if (a < 0 || a >= m || b < 0 || b >= n) break;
                    if (maze[a][b] == 1) break;
                    x = a;
                    y = b;
                }
                if (st[x][y]) continue;
                st[x][y] = true;
                q.offer(new int[]{x, y});
            }
        }
        return false;
    }
}