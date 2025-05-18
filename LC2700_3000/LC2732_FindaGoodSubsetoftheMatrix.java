package LC2700_3000;
import java.util.*;
public class LC2732_FindaGoodSubsetoftheMatrix {
    /**
     * You are given a 0-indexed m x n binary matrix grid.
     *
     * Let us call a non-empty subset of rows good if the sum of each column of the subset is at most half of the length
     * of the subset.
     *
     * More formally, if the length of the chosen subset of rows is k, then the sum of each column should be at most
     * floor(k / 2).
     *
     * Return an integer array that contains row indices of a good subset sorted in ascending order.
     *
     * If there are multiple good subsets, you can return any of them. If there are no good subsets, return an empty
     * array.
     *
     * A subset of rows of the matrix grid is any matrix that can be obtained by deleting some (possibly none or all)
     * rows from grid.
     *
     * Input: grid = [[0,1,1,0],[0,0,0,1],[1,1,1,1]]
     * Output: [0,1]
     *
     * Input: grid = [[0]]
     * Output: [0]
     *
     * Input: grid = [[1,1,1],[1,1,1]]
     * Output: []
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m <= 10^4
     * 1 <= n <= 5
     * grid[i][j] is either 0 or 1.
     * @param grid
     * @return
     */
    // S1
    // time = O(m * 2^n), space = O(n)
    public List<Integer> goodSubsetofBinaryMatrix(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int state = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) state |= 1 << j;
            }
            if (state == 0) return Arrays.asList(i);

            for (int j = 0; j < 1 << n; j++) {
                if ((state & j) == 0) {
                    if (map.containsKey(j)) {
                        return Arrays.asList(map.get(j).get(0), i);
                    }
                }
            }
            map.putIfAbsent(state, new ArrayList<>());
            map.get(state).add(i);
        }
        return new ArrayList<>();
    }

    // S2
    // time = O(m * n), space = O(m)
    public List<Integer> goodSubsetofBinaryMatrix2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            int x = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) x |= 1 << j;
            }
            if (x == 0) res.add(i);
            else {
                map.putIfAbsent(x, new ArrayList<>());
                map.get(x).add(i);
            }
        }
        if (res.size() > 0) return res;

        for (int x : map.keySet()) {
            for (int y : map.keySet()) {
                if (x == y) continue;
                int t = Integer.bitCount(x & y);
                if (t == 0) {
                    int v = Math.min(map.get(x).size(), map.get(y).size());
                    for (int i = 0; i < v; i++) res.add(map.get(x).get(i));
                    for (int i = 0; i < v; i++) res.add(map.get(y).get(i));
                    Collections.sort(res);
                    return res;
                }
            }
        }
        return res;
    }
}