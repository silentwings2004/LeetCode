package LC3001_3300;
import java.util.*;
public class LC3189_MinimumMovestoGetaPeacefulBoard {
    /**
     * Given a 2D array rooks of length n, where rooks[i] = [xi, yi] indicates the position of a rook on an n x n chess
     * board, your task is to move the rooks 1 cell at a time vertically or horizontally such that the board becomes
     * peaceful.
     *
     * A board is peaceful if there is exactly one rook in each row and each column.
     *
     * Return the minimum number of moves required to get a peaceful board.
     *
     * Note that at no point can there be two rooks in the same cell.
     *
     * Input: rooks = [[0,0],[1,0],[1,1]]
     *
     * Output: 3
     *
     * Input: rooks = [[0,0],[0,1],[0,2],[0,3]]
     *
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= n == rooks.length <= 500
     * 0 <= xi, yi <= n - 1
     * The input is generated such that there are no 2 rooks in the same cell.
     * @param rooks
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public int minMoves(int[][] rooks) {
        int n = rooks.length;
        int[] cx = new int[n], cy = new int[n];
        for (int[] r : rooks) {
            int x = r[0], y = r[1];
            cx[x]++;
            cy[y]++;
        }
        return helper(cx) + helper(cy);
    }

    private int helper(int[] cnt) {
        int n = cnt.length, res = 0, x = 0;
        for (int i = 0; i < n; i++) {
            if (cnt[i] == 0) continue;
            for (int j = 0; j < cnt[i]; j++) {
                res += Math.abs(i - x);
                x++;
            }
        }
        return res;
    }

    // S2
    // time = O(nlogn), space = O(logn)
    public int minMoves2(int[][] rooks) {
        int res = 0;
        Arrays.sort(rooks, (o1, o2) -> o1[0] - o2[0]);
        int n = rooks.length, x = 0, y = 0;
        for (int i = 0; i < n; i++) {
            res += Math.abs(rooks[i][0] - x);
            x++;
        }
        Arrays.sort(rooks, (o1, o2) -> o1[1] - o2[1]);
        for (int i = 0; i < n; i++) {
            res += Math.abs(rooks[i][1] - y);
            y++;
        }
        return res;
    }
}