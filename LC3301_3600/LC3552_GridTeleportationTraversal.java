package LC3301_3600;
import java.util.*;
public class LC3552_GridTeleportationTraversal {
    /**
     * You are given a 2D character grid matrix of size m x n, represented as an array of strings, where matrix[i][j]
     * represents the cell at the intersection of the ith row and jth column. Each cell is one of the following:
     *
     * '.' representing an empty cell.
     * '#' representing an obstacle.
     * An uppercase letter ('A'-'Z') representing a teleportation portal.
     * You start at the top-left cell (0, 0), and your goal is to reach the bottom-right cell (m - 1, n - 1). You can
     * move from the current cell to any adjacent cell (up, down, left, right) as long as the destination cell is within
     * the grid bounds and is not an obstacle.
     *
     * If you step on a cell containing a portal letter and you haven't used that portal letter before, you may
     * instantly teleport to any other cell in the grid with the same letter. This teleportation does not count as a
     * move, but each portal letter can be used at most once during your journey.
     *
     * Return the minimum number of moves required to reach the bottom-right cell. If it is not possible to reach the
     * destination, return -1.
     *
     * Input: matrix = ["A..",".A.","..."]
     * Output: 2
     *
     * Input: matrix = [".#...",".#.#.",".#.#.","...#."]
     * Output: 13
     *
     * Constraints:
     *
     * 1 <= m == matrix.length <= 10^3
     * 1 <= n == matrix[i].length <= 10^3
     * matrix[i][j] is either '#', '.', or an uppercase English letter.
     * matrix[0][0] is not an obstacle.
     * @return
     */
    // time = O(m * n), space = O(M * n)
    final int inf = 0x3f3f3f3f;
    int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
    public int minMoves(String[] matrix) {
        int m = matrix.length, n = matrix[0].length();
        List<int[]>[] pos = new List[26];
        for (int i = 0; i < 26; i++) pos[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = matrix[i].charAt(j);
                if (Character.isUpperCase(c)) {
                    int u = c - 'A';
                    pos[u].add(new int[]{i, j});
                }
            }
        }

        Deque<int[]> dq = new LinkedList<>();
        dq.offerLast(new int[]{0, 0});
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(dist[i], inf);
        dist[0][0] = 0;
        boolean[] st = new boolean[26];

        while (!dq.isEmpty()) {
            int[] t = dq.pollFirst();
            int x = t[0], y = t[1];
            if (x == m - 1 && y == n - 1) return dist[x][y];

            char c = matrix[x].charAt(y);
            if (Character.isUpperCase(c)) {
                int u = c - 'A';
                if (!st[u]) {
                    st[u] = true;
                    for (int[] p : pos[u]) {
                        int a = p[0], b = p[1];
                        if (dist[a][b] > dist[x][y]) {
                            dist[a][b] = dist[x][y];
                            dq.offerFirst(new int[]{a, b});
                        }
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                int a = x + dx[i], b = y + dy[i];
                if (a < 0 || a >= m || b < 0 || b >= n) continue;
                if (matrix[a].charAt(b) == '#') continue;
                if (dist[a][b] > dist[x][y] + 1) {
                    dist[a][b] = dist[x][y] + 1;
                    dq.offerLast(new int[]{a, b});
                }
            }
        }
        return -1;
    }
}
/**
 * 本质上是计算如下图的最短路
 * 所有相同字母之间都有一条边权为 0 的边
 * 所有相邻格子之间都有一条边权为 1 的边
 * 这可以用 Dijkstra 算法解决。不过，对于边权只有 0 和 1 的特殊图，可以用 0-1 BFS（双端队列）解决，做到线性时间:
 * 如果当前格子是非字母格子，那么像普通 BFS 那样遍历四方向的相邻格子。
 * 如果当前格子是字母格子，那么除了像普通 BFS 那样遍历四方向的相邻格子以外，还需要遍历该字母的所有传送门，传送过去（边权为 0）。
 * 使用所有传送门后，清空传送门的位置列表，避免反复使用传送门
 */