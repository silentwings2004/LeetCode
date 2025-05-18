package LC3001_3300;
import java.util.*;
public class LC3044_MostFrequentPrime {
    /**
     * You are given a m x n 0-indexed 2D matrix mat. From every cell, you can create numbers in the following way:
     *
     * There could be at most 8 paths from the cells namely: east, south-east, south, south-west, west, north-west,
     * north, and north-east.
     * Select a path from them and append digits in this path to the number being formed by traveling in this direction.
     * Note that numbers are generated at every step, for example, if the digits along the path are 1, 9, 1, then there
     * will be three numbers generated along the way: 1, 19, 191.
     * Return the most frequent
     * prime number
     *  greater than 10 out of all the numbers created by traversing the matrix or -1 if no such prime number exists.
     *  If there are multiple prime numbers with the highest frequency, then return the largest among them.
     *
     * Note: It is invalid to change the direction during the move.
     *
     * Input: mat = [[1,1],[9,9],[1,1]]
     * Output: 19
     *
     * Input: mat = [[7]]
     * Output: -1
     *
     * Input: mat = [[9,7,8],[4,6,5],[2,8,6]]
     * Output: 97
     *
     * Constraints:
     *
     * m == mat.length
     * n == mat[i].length
     * 1 <= m, n <= 6
     * 1 <= mat[i][j] <= 9
     * @param mat
     * @return
     */
    // time = O(m * n * k * 10^(k/2), space = O(m * n * k), k = max(m, n)
    int[] dx = new int[]{-1, 0, 1, 0, -1, -1, 1, 1}, dy = new int[]{0, 1, 0, -1, 1, -1, -1, 1};
    int[][] mat;
    int m, n;
    HashMap<Integer, Integer> map;
    public int mostFrequentPrime(int[][] mat) {
        this.mat = mat;
        m = mat.length;
        n = mat[0].length;
        map = new HashMap<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int d = 0; d < 8; d++) {
                    work(i, j, d);
                }
            }
        }

        int mc = 0, res = -1;
        for (int v : map.values()) mc = Math.max(mc, v);
        for (int key : map.keySet()) {
            if (map.get(key) == mc) res = Math.max(res, key);
        }
        return res;
    }

    private void work(int x, int y, int d) {
        int t = mat[x][y];
        if (t > 10 && check(t)) map.put(t, map.getOrDefault(t, 0) + 1);
        while (true) {
            int a = x + dx[d], b = y + dy[d];
            if (a < 0 || a >= m || b < 0 || b >= n) break;
            t = t * 10 + mat[a][b];
            if (t > 10 && check(t)) map.put(t, map.getOrDefault(t, 0) + 1);
            x = a;
            y = b;
        }
    }

    private boolean check(int x) {
        if (x < 2) return false;
        for (int i = 2; i <= x / i; i++) {
            if (x % i == 0) return false;
        }
        return true;
    }
}