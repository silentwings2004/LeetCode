package LC601_900;
import java.util.*;
public class LC749_ContainVirus {
    /**
     * A virus is spreading rapidly, and your task is to quarantine the infected area by installing walls.
     *
     * The world is modeled as an m x n binary grid isInfected, where isInfected[i][j] == 0 represents uninfected cells,
     * and isInfected[i][j] == 1 represents cells contaminated with the virus. A wall (and only one wall) can be
     * installed between any two 4-directionally adjacent cells, on the shared boundary.
     *
     * Every night, the virus spreads to all neighboring cells in all four directions unless blocked by a wall.
     * Resources are limited. Each day, you can install walls around only one region (i.e., the affected area (continuous
     * block of infected cells) that threatens the most uninfected cells the following night). There will never be a tie.
     *
     * Return the number of walls used to quarantine all the infected regions. If the world will become fully infected,
     * return the number of walls used.
     *
     * Input: isInfected = [[0,1,0,0,0,0,0,1],[0,1,0,0,0,0,0,1],[0,0,0,0,0,0,0,1],[0,0,0,0,0,0,0,0]]
     * Output: 10
     *
     * Input: isInfected = [[1,1,1],[1,0,1],[1,1,1]]
     * Output: 4
     *
     * Constraints:
     *
     * m == isInfected.length
     * n == isInfected[i].length
     * 1 <= m, n <= 50
     * isInfected[i][j] is either 0 or 1.
     * There is always a contiguous viral region throughout the described process that will infect strictly more
     * uncontaminated squares in the next round.
     * @param isInfected
     * @return
     */
    // time = O(m * n * (m + n)), space = O(m * n)
    int[][] g;
    int m, n;
    boolean[][] st;
    List<int[]> path; // flood fill中所有被遍历到的已被感染的点
    HashSet<Integer> set; // flood fill中所有影响到的之前未被感染到的点
    private int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int containVirus(int[][] isInfected) {
        g = isInfected;
        m = g.length;
        n = g[0].length;
        st = new boolean[m][n];
        path = new ArrayList<>();
        set = new HashSet<>();

        int res = 0;
        while (true) {
            int cnt = find();
            if (cnt == 0) break;
            res += cnt;
        }
        return res;
    }

    private int find() {
        for (int i = 0; i < m; i++) Arrays.fill(st[i], false);
        int cnt = 0, res = 0; // cnt: 明天要感染的区域的数量，res: 我们需要的防火墙数量
        List<int[]> ps = new ArrayList<>(); // 被选出区域的所有点
        List<HashSet<Integer>> ss = new ArrayList<>(); // 所有明天会感染的区域

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == 1 && !st[i][j]) {
                    path.clear();
                    set.clear();
                    int t = dfs(i, j); // dfs返回防火墙的数量
                    if (set.size() > cnt) {
                        cnt = set.size();
                        res = t;
                        ps = new ArrayList<>(path);
                    }
                    ss.add(new HashSet<>(set));
                }
            }
        }

        for (int[] p : ps) g[p[0]][p[1]] = -1; // 将选出马上隔离的已经感染的区域标记为-1
        for (HashSet<Integer> s : ss) {
            if (s.size() != cnt) {
                for (int p : s) {
                    g[p / n][p % n] = 1; // 其他未被隔离仍然在扩散的区域点都标记为1
                }
            }
        }
        return res;
    }

    private int dfs(int x, int y) {
        st[x][y] = true;
        path.add(new int[]{x, y});

        int res = 0; // 需要放的隔板数量
        for (int[] dir : directions) {
            int a = x + dir[0], b = y + dir[1];
            if (a >= 0 && a < m && b >= 0 && b < n) {
                if (g[a][b] == 0) {
                    set.add(a * n + b);
                    res++;
                } else if (g[a][b] == 1 && !st[a][b]) res += dfs(a, b);
            }
        }
        return res;
    }
}
/**
 * 找连通块 -> flood fill
 * 把明天会影响到的点全部丢到一个set里，方便判重
 * 同时统计下一共放多少堵墙，凡是碰到0，就是要放隔板
 * 找到最大值，把隔离的区域全部改成-1，再把其他病毒区域向外扩展一圈
 */