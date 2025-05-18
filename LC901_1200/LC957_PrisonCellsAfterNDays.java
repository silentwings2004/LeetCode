package LC901_1200;
import java.util.*;
public class LC957_PrisonCellsAfterNDays {
    /**
     * There are 8 prison cells in a row and each cell is either occupied or vacant.
     *
     * Each day, whether the cell is occupied or vacant changes according to the following rules:
     *
     * If a cell has two adjacent neighbors that are both occupied or both vacant, then the cell becomes occupied.
     * Otherwise, it becomes vacant.
     * Note that because the prison is a row, the first and the last cells in the row can't have two adjacent neighbors.
     *
     * You are given an integer array cells where cells[i] == 1 if the ith cell is occupied and cells[i] == 0 if the ith
     * cell is vacant, and you are given an integer n.
     *
     * Return the state of the prison after n days (i.e., n such changes described above).
     *
     * Input: cells = [0,1,0,1,1,0,0,1], n = 7
     * Output: [0,0,1,1,0,0,0,0]
     *
     * Input: cells = [1,0,0,1,0,0,1,0], n = 1000000000
     * Output: [0,0,1,1,1,1,1,0]
     *
     * Constraints:
     *
     * cells.length == 8
     * cells[i] is either 0 or 1.
     * 1 <= n <= 10^9
     * @param cells
     * @param n
     * @return
     */
    // time = O(n * 2^m), space = O(2^m)
    int m;
    public int[] prisonAfterNDays(int[] cells, int n) {
        m = cells.length;
        int state = 0;
        for (int i = 0; i < m; i++) state |= cells[i] << i;
        int[] f = new int[1 << m];
        Arrays.fill(f, -1);
        f[state] = 0;

        for (int i = 1; n > 0; i++) {
            int next = get(state);
            n--;
            if (f[next] != -1) n %= i - f[next];
            else f[next] = i;
            state = next;
        }
        return output(state);
    }

    private int get(int state) {
        int res = 0;
        for (int i = m - 1; i >= 0; i--) {
            res *= 2;
            if (i != m - 1 && i != 0) {
                if ((state >> i - 1 & 1) == (state >> i + 1 & 1)) res++;
            }
        }
        return res;
    }

    private int[] output(int state) {
        int[] res = new int[m];
        for (int i = m - 1; i >= 0; i--) res[i] = state >> i & 1;
        return res;
    }
}