package LC3001_3300;
import java.util.*;
public class LC3237_AltandTabSimulation {
    /**
     * There are n windows open numbered from 1 to n, we want to simulate using alt + tab to navigate between the
     * windows.
     *
     * You are given an array windows which contains the initial order of the windows (the first element is at the top
     * and the last one is at the bottom).
     *
     * You are also given an array queries where for each query, the window queries[i] is brought to the top.
     *
     * Return the final state of the array windows.
     *
     * Input: windows = [1,2,3], queries = [3,3,2]
     * Output: [2,3,1]
     *
     * Input: windows = [1,4,2,3], queries = [4,1,3]
     * Output: [3,1,4,2]
     *
     * Constraints:
     *
     * 1 <= n == windows.length <= 10^5
     * windows is a permutation of [1, n].
     * 1 <= queries.length <= 10^5
     * 1 <= queries[i] <= n
     * @param windows
     * @param queries
     * @return
     */
    // time = O(n), space = O(n)
    public int[] simulationResult(int[] windows, int[] queries) {
        int n = windows.length, m = queries.length, idx = 0;
        int[] res = new int[n];
        boolean[] st = new boolean[n + 1];
        for (int i = m - 1; i >= 0; i--) {
            int x = queries[i];
            if (!st[x]) {
                res[idx++] = x;
                st[x] = true;
            }
        }
        for (int i = 0; i < n; i++) {
            int x = windows[i];
            if (!st[x]) {
                res[idx++] = x;
                st[x] = true;
            }
        }
        return res;
    }
}