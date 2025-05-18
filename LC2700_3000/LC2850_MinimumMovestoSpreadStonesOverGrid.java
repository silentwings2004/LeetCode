package LC2700_3000;
import java.util.*;
public class LC2850_MinimumMovestoSpreadStonesOverGrid {
    /**
     * You are given a 0-indexed 2D integer matrix grid of size 3 * 3, representing the number of stones in each cell.
     * The grid contains exactly 9 stones, and there can be multiple stones in a single cell.
     *
     * In one move, you can move a single stone from its current cell to any other cell if the two cells share a side.
     *
     * Return the minimum number of moves required to place one stone in each cell.
     *
     * Input: grid = [[1,1,0],[1,1,1],[1,2,1]]
     * Output: 3
     *
     * Input: grid = [[1,3,0],[1,0,0],[1,0,3]]
     * Output: 4
     *
     * Constraints:
     *
     * grid.length == grid[i].length == 3
     * 0 <= grid[i][j] <= 9
     * Sum of grid is equal to 9.
     * @param grid
     * @return
     */
    // S1: dfs
    // time = O(m * n * (m * n)!), space = O(m * n)
    int res;
    int[][] g;
    public int minimumMoves(int[][] grid) {
        res = 0x3f3f3f3f;
        g = grid;
        dfs(0, 0);
        return res;
    }

    private void dfs(int u, int step) {
        if (step >= res) return;
        if (u == 9) {
            res = step;
            return;
        }

        int x = u / 3, y = u % 3;
        if (g[x][y] > 0) dfs(u + 1, step);
        else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (g[i][j] <= 1) continue;
                    g[i][j]--;
                    g[x][y]++;
                    dfs(u + 1, step + Math.abs(x - i) + Math.abs(y - j));
                    g[x][y]--;
                    g[i][j]++;
                }
            }
        }
    }

    // S2: bfs
    // time = O(m * n * (m * n)!), space = O((m * n)!)
    HashSet<String> set;
    int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
    public int minimumMoves2(int[][] grid) {
        Queue<String> q = new LinkedList<>();
        String start = get(grid), end = "111111111";
        set = new HashSet<>();
        q.offer(start);
        set.add(start);

        int step = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            while (sz-- > 0) {
                String x = q.poll();
                if (x.equals(end)) return step;
                List<String> nexts = convert(x);
                for (String y : nexts) {
                    q.offer(y);
                }
            }
            step++;
        }
        return -1;
    }

    private List<String> convert(String s) {
        List<String> res = new ArrayList<>();
        int[][] g = new int[3][3];
        for (int i = 0; i < 9; i++) {
            int x = i / 3, y = i % 3;
            g[x][y] = s.charAt(i) - '0';
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (g[i][j] > 1) {
                    for (int d = 0; d < 4; d++) {
                        int a = i + dx[d], b = j + dy[d];
                        if (a < 0 || a >= 3 || b < 0 || b >= 3) continue;
                        g[a][b]++;
                        g[i][j]--;
                        String str = get(g);
                        if (set.add(str)) res.add(str);
                        g[a][b]--;
                        g[i][j]++;
                    }

                }
            }
        }
        return res;
    }

    private String get(int[][] g) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(g[i][j]);
            }
        }
        return sb.toString();
    }
}