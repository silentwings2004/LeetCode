package LC2401_2700;
import java.util.*;
public class LC2661_FirstCompletelyPaintedRoworColumn {
    /**
     * You are given a 0-indexed integer array arr, and an m x n integer matrix mat. arr and mat both contain all the
     * integers in the range [1, m * n].
     *
     * Go through each index i in arr starting from index 0 and paint the cell in mat containing the integer arr[i].
     *
     * Return the smallest index i at which either a row or a column will be completely painted in mat.
     *
     * Input: arr = [1,3,4,2], mat = [[1,4],[2,3]]
     * Output: 2
     *
     * Input: arr = [2,8,7,4,1,3,5,6,9], mat = [[3,2,5],[1,4,6],[8,7,9]]
     * Output: 3
     *
     * Constraints:
     *
     * m == mat.length
     * n = mat[i].length
     * arr.length == m * n
     * 1 <= m, n <= 10^5
     * 1 <= m * n <= 10^5
     * 1 <= arr[i], mat[r][c] <= m * n
     * All the integers of arr are unique.
     * All the integers of mat are unique.
     * @param arr
     * @param mat
     * @return
     */
    // time = O(m * n), space = O(m * n)
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] r = new int[m], c = new int[n];
        HashMap<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map.put(mat[i][j], new int[]{i, j});
            }
        }
        for (int i = 0; i < arr.length; i++) {
            int[] t = map.get(arr[i]);
            int x = t[0], y = t[1];
            r[x]++;
            c[y]++;
            if (r[x] == n || c[y] == m) return i;
        }
        return -1;
    }
}