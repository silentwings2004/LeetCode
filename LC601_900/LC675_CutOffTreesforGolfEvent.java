package LC601_900;
import java.util.*;
public class  LC675_CutOffTreesforGolfEvent {
    /**
     * You are asked to cut off all the trees in a forest for a golf event. The forest is represented as an m x n matrix.
     * In this matrix:
     *
     * 0 means the cell cannot be walked through.
     * 1 represents an empty cell that can be walked through.
     * A number greater than 1 represents a tree in a cell that can be walked through, and this number is the tree's height.
     * In one step, you can walk in any of the four directions: north, east, south, and west. If you are standing in a
     * cell with a tree, you can choose whether to cut it off.
     *
     * You must cut off the trees in order from shortest to tallest. When you cut off a tree, the value at its cell
     * becomes 1 (an empty cell).
     *
     * Starting from the point (0, 0), return the minimum steps you need to walk to cut off all the trees. If you
     * cannot cut off all the trees, return -1.
     *
     * You are guaranteed that no two trees have the same height, and there is at least one tree needs to be cut off.
     *
     * Input: forest = [[1,2,3],[0,0,4],[7,6,5]]
     * Output: 6
     *
     * Constraints:
     *
     * m == forest.length
     * n == forest[i].length
     * 1 <= m, n <= 50
     * 0 <= forest[i][j] <= 10^9
     * @param forest
     * @return
     */
    // time = O(m^2 * n^2), space = O(m * n)
    final int INF = (int) 1e8;
    List<List<Integer>> g;
    int m, n;
    public int cutOffTree(List<List<Integer>> forest) {
        g = forest;
        m = g.size();
        n = g.get(0).size();

        List<int[]> trs = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g.get(i).get(j) > 1) trs.add(new int[]{i, j, g.get(i).get(j)});
            }
        }
        Collections.sort(trs, (o1, o2) -> o1[2] - o2[2]);

        int res = 0;
        int[] last = new int[]{0, 0};
        for (int[] tr : trs) {
            int t = bfs(last, tr);
            if (t == -1) return -1;
            res += t;
            last = tr;
        }
        return res;
    }

    private int bfs(int[] start, int[] end) {
        if (start[0] == end[0] && start[1] == end[1]) return 0;
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) Arrays.fill(dist[i], INF);
        dist[start[0]][start[1]] = 0;
        Queue<int[]> q = new LinkedList<>();
        q.offer(start);

        int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] t = q.poll();
            int x = t[0], y = t[1];
            if (x == end[0] && y == end[1]) return dist[x][y];
            for (int i = 0; i < 4; i++) {
                int a = x + dx[i], b = y + dy[i];
                if (a < 0 || a >= m || b < 0 || b >= n) continue;
                if (g.get(a).get(b) == 0) continue;
                if (dist[a][b] > dist[x][y] + 1) {
                    dist[a][b] = dist[x][y] + 1;
                    q.offer(new int[]{a, b});
                }
            }
        }
        return -1;
    }
}
/**
 * 每步求一个最短路径 -> bfs
 * 任意2点找最小路径
 * A* 启发式算法
 * O(m * n) * O(m * n) => 2500^2 -> 10^6 -> 直接暴力bfs
 * O(nlogn) -> n = 1e4
 * O(n^2) -> n = 1e3
 */
