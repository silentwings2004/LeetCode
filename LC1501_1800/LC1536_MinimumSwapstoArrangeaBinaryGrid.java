package LC1501_1800;
import java.util.*;
public class LC1536_MinimumSwapstoArrangeaBinaryGrid {
    /**
     * Given an n x n binary grid, in one step you can choose two adjacent rows of the grid and swap them.
     *
     * A grid is said to be valid if all the cells above the main diagonal are zeros.
     *
     * Return the minimum number of steps needed to make the grid valid, or -1 if the grid cannot be valid.
     *
     * The main diagonal of a grid is the diagonal that starts at cell (1, 1) and ends at cell (n, n).
     *
     * Input: grid = [[0,0,1],[1,1,0],[1,0,0]]
     * Output: 3
     *
     * Input: grid = [[0,1,1,0],[0,1,1,0],[0,1,1,0],[0,1,1,0]]
     * Output: -1
     *
     * Input: grid = [[1,0,0],[1,1,0],[1,1,1]]
     * Output: 0
     *
     * Constraints:
     *
     * n == grid.length == grid[i].length
     * 1 <= n <= 200
     * grid[i][j] is either 0 or 1
     * @param grid
     * @return
     */
    // S1
    // time = O(n^2), space = O(n)
    public int minSwaps(int[][] grid) {
        int n = grid.length, res = 0;
        int[] d = new int[n];
        boolean[] st = new boolean[n];
        for (int u = n - 1, k = 0; u > 0; u--, k++) {
            int t = -1;
            for (int i = 0; i < n; i++) {
                if (st[i]) continue;
                if (grid[i][n - 1] == 0) {
                    int j = n - 1;
                    while (j >= 0 && grid[i][j] == 0) j--;
                    int len = n - 1 - j;
                    if (len >= u) {
                        res += i + d[i] - k;
                        t = i;
                        st[i] = true; // mark the used row
                        break;
                    }
                }
            }
            if (t == -1) return -1; // not found
            for (int i = 0; i < n; i++) {
                if (st[i]) continue;
                if (i + d[i] < t + d[t]) d[i]++; // update the shifted rows
            }
        }
        return res;
    }

    // S2
    // time = O(n^2), space = O(n)
    public int minSwaps2(int[][] grid) {
        int n = grid.length;
        int[] f = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 1) { // 记录每一行里面从右边开始数第一个1的位置
                    f[i] = j; // 要满足对角线以上的格子全部都是 0，必须满足:f[i] <= i
                    break;
                }
            }
        }

        int res = 0, pos = 0;
        for (int i = 0; i < n; i++) {
            if (f[i] <= i) continue; // 这行已经符合条件了, 右边第一个1的位置在对角线外侧
            int j = 0;
            for (j = i + 1; j < n; j++) { // 往下遍历后面的行，找到[最先]满足条件的，一行行换上去
                if (f[j] <= i) {
                    pos = j; // pos为最先满足条件的行数
                    break;
                }
            }
            if (j == n) return -1; // 找不到满足条件的行
            for (int k = pos; k > i; k--) { // 交换并计算交换次数
                int t = f[k - 1];
                f[k - 1] = f[k];
                f[k] = t;
            }
            res += pos - i;
        }
        return res;
    }
}