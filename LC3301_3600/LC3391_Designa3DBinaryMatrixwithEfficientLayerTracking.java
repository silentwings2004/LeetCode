package LC3301_3600;
import java.util.*;
public class LC3391_Designa3DBinaryMatrixwithEfficientLayerTracking {
    /**
     * You are given a n x n x n binary 3D array matrix.
     *
     * Implement the matrix3D class:
     *
     * matrix3D(int n) Initializes the object with the 3D binary array matrix, where all elements are initially set to 0.
     * void setCell(int x, int y, int z) Sets the value at matrix[x][y][z] to 1.
     * void unsetCell(int x, int y, int z) Sets the value at matrix[x][y][z] to 0.
     * int largestMatrix() Returns the index x where matrix[x] contains the most number of 1's. If there are multiple
     * such indices, return the largest x.
     *
     * Input:
     * ["matrix3D", "setCell", "largestMatrix", "setCell", "largestMatrix", "setCell", "largestMatrix"]
     * [[3], [0, 0, 0], [], [1, 1, 2], [], [0, 0, 1], []]
     *
     * Output:
     * [null, null, 0, null, 1, null, 0]
     *
     * Input:
     * ["matrix3D", "setCell", "largestMatrix", "unsetCell", "largestMatrix"]
     * [[4], [2, 1, 1], [], [2, 1, 1], []]
     *
     * Output:
     * [null, null, 2, null, 3]
     *
     * Constraints:
     *
     * 1 <= n <= 100
     * 0 <= x, y, z < n
     * At most 105 calls are made in total to setCell and unsetCell.
     * At most 104 calls are made to largestMatrix.
     * @param n
     */
    boolean[][][] st;
    TreeSet<int[]> set;
    int[] cnt;
    public LC3391_Designa3DBinaryMatrixwithEfficientLayerTracking(int n) {
        st = new boolean[n][n][n];
        cnt = new int[n];
        set = new TreeSet<>((o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1]);
        for (int i = 0; i < n; i++) set.add(new int[]{0, i});
    }
    // time = O(logn), space = O(n^3)
    public void setCell(int x, int y, int z) {
        if (st[x][y][z]) return;
        st[x][y][z] = true;
        int v = cnt[x];
        cnt[x]++;
        set.remove(new int[]{v, x});
        set.add(new int[]{cnt[x], x});
    }
    // time = O(logn), space = O(n^3)
    public void unsetCell(int x, int y, int z) {
        if (!st[x][y][z]) return;
        st[x][y][z] = false;
        int v = cnt[x];
        cnt[x]--;
        set.remove(new int[]{v, x});
        set.add(new int[]{cnt[x], x});
    }
    // time = O(logn), space = O(n^3)
    public int largestMatrix() {
        return set.first()[1];
    }
}