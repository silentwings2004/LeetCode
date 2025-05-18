package LC2401_2700;
import java.util.*;
public class LC2617_MinimumNumberofVisitedCellsinaGrid {
    /**
     * You are given a 0-indexed m x n integer matrix grid. Your initial position is at the top-left cell (0, 0).
     *
     * Starting from the cell (i, j), you can move to one of the following cells:
     *
     * Cells (i, k) with j < k <= grid[i][j] + j (rightward movement), or
     * Cells (k, j) with i < k <= grid[i][j] + i (downward movement).
     * Return the minimum number of cells you need to visit to reach the bottom-right cell (m - 1, n - 1). If there is
     * no valid path, return -1.
     *
     * Input: grid = [[3,4,2,1],[4,2,3,1],[2,1,0,0],[2,4,0,0]]
     * Output: 4
     *
     * Input: grid = [[3,4,2,1],[4,2,1,1],[2,1,1,0],[3,4,1,0]]
     * Output: 3
     *
     * Input: grid = [[2,1,0],[1,0,0]]
     * Output: -1
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 10^5
     * 1 <= m * n <= 10^5
     * 0 <= grid[i][j] < m * n
     * grid[m - 1][n - 1] == 0
     * @param grid
     * @return
     */
    // S1
    // time = O(m * n * log(m * n)), space = O(m * n)
    public int minimumVisitedCells(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        int[][] dist = new int[m][n];
        dist[0][0] = 1;
        TreeSet<Integer>[] r = new TreeSet[m];
        TreeSet<Integer>[] c = new TreeSet[n];
        for (int i = 0; i < m; i++) {
            r[i] = new TreeSet<>();
            for (int j = 0; j < n; j++) r[i].add(j);
        }
        for (int j = 0; j < n; j++) {
            c[j] = new TreeSet<>();
            for (int i = 0; i < m; i++) c[j].add(i);
        }
        r[0].remove(0);
        c[0].remove(0);

        while (!q.isEmpty()) {
            int[] t = q.poll();
            int x = t[0], y = t[1];
            if (x == m - 1 && y == n - 1) return dist[x][y];

            int a = Math.min(grid[x][y] + y, n - 1), b = Math.min(grid[x][y] + x, m - 1);
            while (true) {
                Integer hk = r[x].higher(y);
                if (hk != null && hk <= a && dist[x][hk] == 0) {
                    q.offer(new int[]{x, hk});
                    dist[x][hk] = dist[x][y] + 1;
                    r[x].remove(hk);
                    c[hk].remove(x);
                } else break;
            }

            while (true) {
                Integer hk = c[y].higher(x);
                if (hk != null && hk <= b && dist[hk][y] == 0) {
                    q.offer(new int[]{hk, y});
                    dist[hk][y] = dist[x][y] + 1;
                    c[y].remove(hk);
                    r[hk].remove(y);
                } else break;
            }
        }
        return -1;
    }

    // S2
    // time = O(m * n * log(m * n)), space = O(m * n)
    public int minimumVisitedCells2(int[][] grid) {
        int m = grid.length, n = grid[0].length, inf = 0x3f3f3f3f;
        List<int[]>[] cs = new List[n];
        for (int i = 0; i < n; i++) cs[i] = new ArrayList<>();
        List<int[]> rs = new ArrayList<>();
        int minv = 0;
        for (int i = m - 1; i >= 0; i--) {
            rs.clear();
            for (int j = n - 1; j >= 0; j--) {
                int v = grid[i][j];
                minv = i < m - 1 || j < n - 1 ? inf : 1;
                if (v > 0) {
                    int k = find(rs, v + j);
                    if (k < rs.size()) minv = Math.min(minv, rs.get(k)[0] + 1);
                    k = find(cs[j], v + i);
                    if (k < cs[j].size()) minv = Math.min(minv, cs[j].get(k)[0] + 1);
                }
                if (minv < inf) {
                    while (!rs.isEmpty() && rs.get(rs.size() - 1)[0] >= minv) rs.remove(rs.size() - 1);
                    rs.add(new int[]{minv, j});
                    while (!cs[j].isEmpty() && cs[j].get(cs[j].size() - 1)[0] >= minv) cs[j].remove(cs[j].size() - 1);
                    cs[j].add(new int[]{minv, i});
                }
            }
        }
        return minv < inf ? minv : -1;
    }

    private int find(List<int[]> stk, int t) {
        if (stk.size() == 0) return 0;
        int l = 0, r = stk.size() - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (stk.get(mid)[1] <= t) r = mid;
            else l = mid + 1;
        }
        return stk.get(r)[1] <= t ? r : r + 1;
    }
}
/**
 * 从(0,0)->(0,1)->(1,1)
 * 从(0,0)->(1,0)->(1,1)
 * 有重叠子问题 => 用dp来优化
 * dfs(0,0) -> dfs(x,y)
 * g = grid[i][j]
 * dfs(i,j) 枚举 k, 计算 min(dfs(i,k), for k in [j+1,j+g])
 *                 计算 min(dfs(k,j), for k in [i+1,i+g])
 * 优化前：O(mn(m+n))
 * f[i][j] = min{
 *                min(f[i][k], k in [j+1,j+g])
 *                min(f[k][j], k in [i+1,i+g])
 *          }
 * i 和 j 倒序枚举
 * 从右到左计算的时候，如果计算出的f[i][j] <= 之前的数，那么之前的数就没用了
 * => 单调栈，从底往顶值和下标不断变大的单调栈
 * [j+1,j+g]
 * j+1一定在栈顶左侧
 * j+g? 在单调栈上二分
 * m, n = len(grid), len(grid[0])
 * 需要 n + 1 个单调栈
 */