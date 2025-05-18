package LC1201_1500;
import java.util.*;
public class LC1210_MinimumMovestoReachTargetwithRotations {
    /**
     * In an n*n grid, there is a snake that spans 2 cells and starts moving from the top left corner at (0, 0) and
     * (0, 1). The grid has empty cells represented by zeros and blocked cells represented by ones. The snake wants to
     * reach the lower right corner at (n-1, n-2) and (n-1, n-1).
     *
     * In one move the snake can:
     *
     * Move one cell to the right if there are no blocked cells there. This move keeps the horizontal/vertical position
     * of the snake as it is.
     * Move down one cell if there are no blocked cells there. This move keeps the horizontal/vertical position of the
     * snake as it is.
     * Rotate clockwise if it's in a horizontal position and the two cells under it are both empty. In that case the
     * snake moves from (r, c) and (r, c+1) to (r, c) and (r+1, c).
     *
     * Rotate counterclockwise if it's in a vertical position and the two cells to its right are both empty. In that
     * case the snake moves from (r, c) and (r+1, c) to (r, c) and (r, c+1).
     *
     * Return the minimum number of moves to reach the target.
     *
     * If there is no way to reach the target, return -1.
     *
     * Input: grid = [[0,0,0,0,0,1],
     *                [1,1,0,0,1,0],
     *                [0,0,0,0,1,1],
     *                [0,0,1,0,1,0],
     *                [0,1,1,0,0,0],
     *                [0,1,1,0,0,0]]
     * Output: 11
     *
     * Input: grid = [[0,0,1,1,1,1],
     *                [0,0,0,0,1,1],
     *                [1,1,0,0,0,1],
     *                [1,1,1,0,0,1],
     *                [1,1,1,0,0,1],
     *                [1,1,1,0,0,0]]
     * Output: 9
     *
     * Constraints:
     *
     * 2 <= n <= 100
     * 0 <= grid[i][j] <= 1
     * It is guaranteed that the snake starts at empty cells.
     * @param grid
     * @return
     */
    // S1
    // time = O(n^2), space = O(n^2)
    int[][][] dist;
    Queue<Node> q;
    public int minimumMoves(int[][] grid) {
        int n = grid.length, INF = (int) 1e8;
        dist = new int[n][n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j][0] = dist[i][j][1] = INF;
            }
        }
        q = new LinkedList<>();
        q.offer(new Node(0, 0, 0));
        dist[0][0][0] = 0;

        int[] dx = new int[]{0, 1}, dy = new int[]{1, 0};
        while (!q.isEmpty()) {
            Node t = q.poll();
            int distance = dist[t.x][t.y][t.d];
            if (t.x == n - 1 && t.y == n - 2 && t.d == 0) return distance;
            Node a = new Node(t.x, t.y, t.d), b = new Node(t.x + dx[t.d], t.y + dy[t.d], t.d);

            // 向右平移
            if (a.y + 1 < n && grid[a.x][a.y + 1] == 0 && b.y + 1 < n && grid[b.x][b.y + 1] == 0) {
                update(t.x, t.y + 1, t.d, distance + 1);
            }

            // 向下平移
            if (a.x + 1 < n && grid[a.x + 1][a.y] == 0 && b.x + 1 < n && grid[b.x + 1][b.y] == 0) {
                update(t.x + 1, t.y, t.d, distance + 1);
            }

            // 顺时针旋转90度
            if (t.d == 0 && t.x + 1 < n && grid[t.x + 1][t.y] == 0 && grid[t.x + 1][t.y + 1] == 0) {
                update(t.x, t.y, 1, distance + 1);
            }

            // 逆时针旋转90度
            if (t.d == 1 && t.y + 1 < n && grid[t.x][t.y + 1] == 0 && grid[t.x + 1][t.y + 1] == 0) {
                update(t.x, t.y, 0, distance + 1);
            }
        }
        return -1;
    }

    private void update(int x, int y, int d, int distance) {
        if (dist[x][y][d] > distance) {
            dist[x][y][d] = distance;
            q.offer(new Node(x, y, d));
        }
    }

    private class Node {
        int x, y, d;
        public Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    // S2
    // time = O(n^2), space = O(n^2)
    public int minimumMoves2(int[][] grid) {
        int n = grid.length;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 0, 1});
        HashSet<String> set = new HashSet<>();
        set.add(0 + "#" + 0 + "#" + 0 + "#" + 1);

        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] t = q.poll();
                int x1 = t[0], y1 = t[1], x2 = t[2], y2 = t[3];
                if (x1 == n - 1 && y1 == n - 2 && x2 == n - 1 && y2 == n - 1) return step;

                // case 1: move right
                if (y2 + 1 < n) {
                    if (isFlat(x1, y1, x2, y2) && grid[x2][y2 + 1] == 0 || isVert(x1, y1, x2, y2) && grid[x1][y1 + 1] == 0 && grid[x2][y2 + 1] == 0) {
                        String h = x1 + "#" + (y1 + 1) + "#" + x2 + "#" + (y2 + 1);
                        if (set.add(h)) q.offer(new int[]{x1, y1 + 1, x2, y2 + 1});
                    }
                }

                // case 2: move down
                if (x2 + 1 < n) {
                    if (isFlat(x1, y1, x2, y2) && grid[x1 + 1][y1] == 0 && grid[x2 + 1][y2] == 0 || isVert(x1, y1, x2, y2) && grid[x2 + 1][y2] == 0) {
                        String h = (x1 + 1) + "#" + y1 + (x2 + 1) + "#" + y2;
                        if (set.add(h)) q.offer(new int[]{x1 + 1, y1, x2 + 1, y2});
                    }
                }

                // case 3: rotate clockwise
                if (isFlat(x1, y1, x2, y2) && x1 + 1 < n && grid[x1 + 1][y1] == 0 && grid[x2 + 1][y2] == 0) {
                    String h = x1 + "#" + y1 + "#" + (x1 + 1) + "#" + y1;
                    if (set.add(h)) q.offer(new int[]{x1, y1, x1 + 1, y1});
                }

                // case 4 : rotate counter-clockwise
                if (isVert(x1, y1, x2, y2) && y1 + 1 < n && grid[x1][y1 + 1] == 0 && grid[x2][y2 + 1] == 0) {
                    String h = x1 + "#" + y1 + "#" + x1 + "#" + (y1 + 1);
                    if (set.add(h)) q.offer(new int[]{x1, y1, x1, y1 + 1});
                }
            }
            step++;
        }
        return -1;
    }

    private boolean isFlat(int x1, int y1, int x2, int y2) {
        return x1 == x2 && y1 + 1 == y2;
    }

    private boolean isVert(int x1, int y1, int x2, int y2) {
        return y1 == y2 && x1 + 1 == x2;
    }
}
/**
 * (x,y,d)
 * (0,0,0) -> (n-1,n-2,0)
 * (x,y,d) -> (x,y+1,d)
 * (x,y,d) -> (x+1,y,d)
 * if d = 0 -> (x,y,1)
 * if d = 1 -> (x,y,0)
 * 边权=1的最短路问题 => bfs
 *
 */

