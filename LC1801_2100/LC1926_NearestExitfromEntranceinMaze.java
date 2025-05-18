package LC1801_2100;
import java.util.*;
public class LC1926_NearestExitfromEntranceinMaze {
    /**
     * You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls
     * (represented as '+'). You are also given the entrance of the maze, where entrance = [entrancerow, entrancecol]
     * denotes the row and column of the cell you are initially standing at.
     *
     * In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a wall, and you
     * cannot step outside the maze. Your goal is to find the nearest exit from the entrance. An exit is defined as an
     * empty cell that is at the border of the maze. The entrance does not count as an exit.
     *
     * Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.
     *
     * Input: maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]], entrance = [1,2]
     * Output: 1
     *
     * Constraints:
     *
     * maze.length == m
     * maze[i].length == n
     * 1 <= m, n <= 100
     * maze[i][j] is either '.' or '+'.
     * entrance.length == 2
     * 0 <= entrancerow < m
     * 0 <= entrancecol < n
     * entrance will always be an empty cell.
     * @param maze
     * @param entrance
     * @return
     */
    // time = O(m * n) space = O(m * n)
    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length, n = maze[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(entrance);
        boolean[][] st = new boolean[m][n];
        st[entrance[0]][entrance[1]] = true;

        int step = 0;
        int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int[] t = queue.poll();
                int x = t[0], y = t[1];
                if (isDest(maze, entrance, x, y)) return step;

                for (int k = 0; k < 4; k++) {
                    int i = x + dx[k];
                    int j = y + dy[k];
                    if (i < 0 || i >= m || j < 0 || j >= n) continue;
                    if (maze[i][j] == '+') continue;
                    if (st[i][j]) continue;
                    queue.offer(new int[]{i, j});
                    st[i][j] = true;
                }
            }
            step++;
        }
        return -1;
    }

    private boolean isDest(char[][] maze, int[] entrance, int x, int y) {
        int m = maze.length, n = maze[0].length;
        if (maze[x][y] == '+') return false;
        if (x == entrance[0] && y == entrance[1]) return false;
        if (x == 0 || x == m - 1 || y == 0 || y == n - 1) return true;
        return false;
    }
}
