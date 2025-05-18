package LC3001_3300;

public class LC3015_CounttheNumberofHousesataCertainDistanceI {
    /**
     * You are given three positive integers n, x, and y.
     *
     * In a city, there exist houses numbered 1 to n connected by n streets. There is a street connecting the house
     * numbered i with the house numbered i + 1 for all 1 <= i <= n - 1 . An additional street connects the house
     * numbered x with the house numbered y.
     *
     * For each k, such that 1 <= k <= n, you need to find the number of pairs of houses (house1, house2) such that the
     * minimum number of streets that need to be traveled to reach house2 from house1 is k.
     *
     * Return a 1-indexed array result of length n where result[k] represents the total number of pairs of houses such
     * that the minimum streets required to reach one house from the other is k.
     *
     * Note that x and y can be equal.
     *
     * Input: n = 3, x = 1, y = 3
     * Output: [6,0,0]
     *
     * Input: n = 5, x = 2, y = 4
     * Output: [10,8,2,0,0]
     *
     * Input: n = 4, x = 1, y = 1
     * Output: [6,4,2,0]
     *
     * Constraints:
     *
     * 2 <= n <= 100
     * 1 <= x, y <= n
     * @param n
     * @param x
     * @param y
     * @return
     */
    // time = O(n^3), space = O(n^2)
    public int[] countOfPairs(int n, int x, int y) {
        int[][] dist = new int[n][n];
        int inf = 0x3f3f3f3f;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) dist[i][j] = 0;
                else dist[i][j] = inf;
            }
        }
        for (int i = 0; i + 1 < n; i++) dist[i][i + 1] = dist[i + 1][i] = 1;
        x--;
        y--;
        if (x != y) dist[x][y] = dist[y][x] = 1;

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (dist[i][j] > 0 && dist[i][j] != inf) {
                    res[dist[i][j] - 1]++;
                }
            }
        }
        return res;
    }
}