package LC3001_3300;

public class LC3071_MinimumOperationstoWritetheLetterYonaGrid {
    /**
     * You are given a 0-indexed n x n grid where n is odd, and grid[r][c] is 0, 1, or 2.
     *
     * We say that a cell belongs to the Letter Y if it belongs to one of the following:
     *
     * The diagonal starting at the top-left cell and ending at the center cell of the grid.
     * The diagonal starting at the top-right cell and ending at the center cell of the grid.
     * The vertical line starting at the center cell and ending at the bottom border of the grid.
     * The Letter Y is written on the grid if and only if:
     *
     * All values at cells belonging to the Y are equal.
     * All values at cells not belonging to the Y are equal.
     * The values at cells belonging to the Y are different from the values at cells not belonging to the Y.
     * Return the minimum number of operations needed to write the letter Y on the grid given that in one operation you
     * can change the value at any cell to 0, 1, or 2.
     *
     * Input: grid = [[1,2,2],[1,1,0],[0,1,0]]
     * Output: 3
     *
     * Input: grid = [[0,1,0,1,0],[2,1,0,1,2],[2,2,2,0,1],[2,2,2,2,2],[2,1,2,2,2]]
     * Output: 12
     *
     * Constraints:
     *
     * 3 <= n <= 49
     * n == grid.length == grid[i].length
     * 0 <= grid[i][j] <= 2
     * n is odd.
     * @param grid
     * @return
     */
    // time = O(m * n), space = O(m * n)
    int[][] g;
    int m, n;
    public int minimumOperationsToWriteY(int[][] grid) {
        g = grid;
        m = g.length;
        n = g[0].length;
        return Math.min(work(0), Math.min(work(1), work(2)));
    }

    private int work(int t) {
        int mx = m / 2, my = n / 2;
        int ans = 0;
        boolean[][] st = new boolean[m][n];
        for (int i = 0; i <= mx; i++) {
            if (g[i][i] != t) ans++;
            if (i != n - 1 - i && g[i][n - 1 - i] != t) ans++;
            st[i][i] = true;
            st[i][n - 1 - i] = true;
        }

        for (int i = mx + 1; i < m; i++) {
            if (g[i][my] != t) ans++;
            st[i][my] = true;
        }

        int a = 0, b = 0, c = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!st[i][j]) {
                    if (g[i][j] == t) ans++;
                    else if (g[i][j] == 0) a++;
                    else if (g[i][j] == 1) b++;
                    else if (g[i][j] == 2) c++;
                }
            }
        }

        if (a != 0 && b != 0) ans += Math.min(a, b);
        else if (a != 0 && c != 0) ans += Math.min(a, c);
        else if (c != 0 && b != 0) ans += Math.min(b, c);
        return ans;
    }

    // S2
    // time = O(m * n), space = O(m * n)
    public int minimumOperationsToWriteY2(int[][] grid) {
        int[] cnt1 = new int[3], cnt2 = new int[3];
        int n = grid.length, m = n / 2;
        for (int i = 0; i < m; i++) {
            cnt1[grid[i][i]]++;
            cnt1[grid[i][n - 1 - i]]++;
            for (int j = 0; j < n; j++) {
                if (j != i && j != n - 1 - i) {
                    cnt2[grid[i][j]]++;
                }
            }
        }
        for (int i = m; i < n; i++) {
            cnt1[grid[i][m]]++;
            for (int j = 0; j < n; j++) {
                if (j != m) cnt2[grid[i][j]]++;
            }
        }

        int maxNotChange = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != j) maxNotChange = Math.max(maxNotChange, cnt1[i] + cnt2[j]);
            }
        }
        return n * n - maxNotChange;
    }
}
/**
 * 如果元素不仅仅是 0,1,20,1,20,1,2 要怎么做？
 * maxNotChange 只有三种情况：
 * cnt1中的最大值+cnt2中的最大值，前提是这2个最大值对应的元素不同
 * 如果对应的元素相同，我们可取cnt1中的最大值+cnt2中的次大值，或者cnt1中的次大值+cnt2中的最大值
 * 用hashmap即可
 */