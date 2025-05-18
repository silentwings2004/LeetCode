package LC3301_3600;
import java.util.*;
public class LC3548_EqualSumGridPartitionII {
    /**
     * You are given an m x n matrix grid of positive integers. Your task is to determine if it is possible to make
     * either one horizontal or one vertical cut on the grid such that:
     *
     * Each of the two resulting sections formed by the cut is non-empty.
     * The sum of elements in both sections is equal, or can be made equal by discounting at most one single cell in
     * total (from either section).
     * If a cell is discounted, the rest of the section must remain connected.
     * Return true if such a partition exists; otherwise, return false.
     *
     * Note: A section is connected if every cell in it can be reached from any other cell by moving up, down, left, or
     * right through other cells in the section.
     *
     * Input: grid = [[1,4],[2,3]]
     * Output: true
     *
     * Input: grid = [[1,2],[3,4]]
     * Output: true
     *
     * Input: grid = [[1,2,4],[2,3,5]]
     * Output: false
     *
     * Input: grid = [[4,1,8],[3,2,6]]
     * Output: false
     *
     * Constraints:
     *
     * 1 <= m == grid.length <= 10^5
     * 1 <= n == grid[i].length <= 10^5
     * 2 <= m * n <= 10^5
     * 1 <= grid[i][j] <= 10^5
     * @param grid
     * @return
     */
    // time = O(m * n), space = O(m * n)
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        long tot = 0;
        long[] rs = new long[m], cs = new long[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int v = grid[i][j];
                tot += v;
                rs[i] += v;
                cs[j] += v;
            }
        }

        long[] s1 = new long[m + 1], s2 = new long[n + 1];
        for (int i = 1; i <= m; i++) s1[i] = s1[i - 1] + rs[i - 1];
        for (int j = 1; j <= n; j++) s2[j] = s2[j - 1] + cs[j - 1];

        HashMap<Integer, List<Integer>> rm = new HashMap<>();
        HashMap<Integer, List<Integer>> cm = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int v = grid[i][j];
                rm.putIfAbsent(v, new ArrayList<>());
                cm.putIfAbsent(v, new ArrayList<>());
                rm.get(v).add(i);
                cm.get(v).add(j);
            }
        }

        for (List<Integer> v : rm.values()) Collections.sort(v);
        for (List<Integer> v : cm.values()) Collections.sort(v);

        // horizontal split
        for (int i = 0; i < m - 1; i++) {
            long su = s1[i + 1], sd = tot - su;
            if (su == sd) return true;

            long d = Math.abs(su - sd);
            int r = su > sd ? i + 1 : m - (i + 1);
            int st = su > sd ? 0 : i + 1;
            int ed = su > sd ? i : m - 1;
            if (r >= 2 && n >= 2) {
                List<Integer> q = rm.getOrDefault((int)d, new ArrayList<>());
                if (check(q, st, ed)) return true;
            } else if (r == 1) {
                if (grid[st][0] == d || grid[st][n - 1] == d) return true;
            } else if (n == 1) {
                if (grid[st][0] == d || grid[ed][0] == d) return true;
            }
        }

        // vertical split
        for (int j = 0; j < n - 1; j++) {
            long sl = s2[j + 1], sr = tot - sl;
            if (sl == sr) return true;

            long d = Math.abs(sl - sr);
            int c = sl > sr ? j + 1 : n - (j + 1);
            int st = sl > sr ? 0 : j + 1;
            int ed = sl > sr ? j : n - 1;
            if (c >= 2 && m >= 2) {
                List<Integer> q = cm.getOrDefault((int)d, new ArrayList<>());
                if (check(q, st, ed)) return true;
            } else if (c == 1) {
                if (grid[0][st] == d || grid[m - 1][st] == d) return true;
            } else if (m == 1) {
                if (grid[0][st] == d || grid[0][ed] == d) return true;
            }
        }
        return false;
    }

    private boolean check(List<Integer> q, int st, int ed) {
        int m = q.size();
        if (m == 0) return false;
        int l = 0, r = m - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (q.get(mid) >= st) r = mid;
            else l = mid + 1;
        }
        return q.get(r) >= st && q.get(r) <= ed;
    }

    // S2
    // time = O(m * n), space = O(m * n)
    public boolean canPartitionGrid2(int[][] grid) {
        long tot = 0;
        for (int[] row : grid) {
            for (int x : row) {
                tot += x;
            }
        }
        return check(grid, tot) || check(transpose(grid), tot);
    }

    private boolean check(int[][] g, long tot) {
        if (f(g, tot)) return true;
        reverse(g);
        return f(g, tot);
    }

    private boolean f(int[][] g, long tot) {
        int m = g.length, n = g[0].length;
        HashSet<Long> set = new HashSet<>();
        set.add(0L);
        long s = 0;
        for (int i = 0; i < m - 1; i++) {
            int[] row = g[i];
            for (int j = 0; j < n; j++) {
                int x = row[j];
                s += x;
                if (i > 0 || j == 0 || j == n - 1) set.add(1L * x);
            }
            if (n == 1) {
                if (s * 2 == tot || s * 2 - tot == g[0][0] || s * 2 - tot == row[0]) return true;
                continue;
            }
            if (set.contains(s * 2 - tot)) return true;
            if (i == 0) {
                for (int x : row) set.add(1L * x);
            }
        }
        return false;
    }

    private int[][] transpose(int[][] a) {
        int m = a.length, n = a[0].length;
        int[][] b = new int[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                b[j][i] = a[i][j];
            }
        }
        return b;
    }

    private void reverse(int[][] g) {
        for (int i = 0, j = g.length - 1; i < j; i++, j--) {
            int[] t = g[i];
            g[i] = g[j];
            g[j] = t;
        }
    }
}
/**
 * 设整个 grid 的元素和为 total。
 * 设第一部分的元素和为 s，那么第二部分的元素和为 total−s。
 * 不删元素：s=total−s，即 2s−total=0。
 * 删第一部分中的元素 x：s−x=total−s，即 2s−total=x。
 * 据此，我们可以一边遍历 grid，一边计算第一部分的元素和 s，一边用哈希集合记录遍历过的元素
 * 每一行/列遍历结束后，判断 x=2s−total 是否在哈希集合中，如果在，就说明存在 x，使得 s−x=total−s 成立
 * 小技巧：预先把 0 加到哈希集合中，这样可以把不删和删合并成一种情况
 * 对于删第二部分中的元素的情况，可以把 grid 上下翻转，复用删第一部分中的元素的代码。
 * 分类讨论：
 * 对于只有一列（n=1）的情况，只能删除第一个数或者分割线上那个数
 * 对于只有一行（分割线在第一行与第二行之间）的情况，只能删除第一个数或者最后一个数。删除中间的数会导致第一部分不连通
 * 对于垂直分割，可以把 grid 旋转 90 度，复用上述代码
 */