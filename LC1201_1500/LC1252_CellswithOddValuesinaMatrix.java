package LC1201_1500;

public class LC1252_CellswithOddValuesinaMatrix {
    /**
     * There is an m x n matrix that is initialized to all 0's. There is also a 2D array indices where each
     * indices[i] = [ri, ci] represents a 0-indexed location to perform some increment operations on the matrix.
     *
     * For each location indices[i], do both of the following:
     *
     * Increment all the cells on row ri.
     * Increment all the cells on column ci.
     * Given m, n, and indices, return the number of odd-valued cells in the matrix after applying the increment to all
     * locations in indices.
     *
     * Input: m = 2, n = 3, indices = [[0,1],[1,1]]
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= m, n <= 50
     * 1 <= indices.length <= 100
     * 0 <= ri < m
     * 0 <= ci < n
     *
     * Follow up: Could you solve this in O(n + m + indices.length) time with only O(n + m) extra space?
     * @param n
     * @param m
     * @param indices
     * @return
     */
    // time = (m + n + k), space = O(m + n)
    public int oddCells(int m, int n, int[][] indices) {
        boolean[] row = new boolean[m], col = new boolean[n];
        for (int[] x : indices) {
            int r = x[0], c = x[1];
            row[r] ^= true;
            col[c] ^= true;
        }

        int cr = 0, cc = 0;
        for (boolean r : row) cr += r ? 1 : 0;
        for (boolean c : col) cc += c ? 1 : 0;
        return cr * (n - cc) + cc * (m - cr);
    }
}
