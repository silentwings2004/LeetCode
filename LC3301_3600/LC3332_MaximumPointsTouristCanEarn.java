package LC3301_3600;

public class LC3332_MaximumPointsTouristCanEarn {
    /**
     * You are given two integers, n and k, along with two 2D integer arrays, stayScore and travelScore.
     *
     * A tourist is visiting a country with n cities, where each city is directly connected to every other city. The
     * tourist's journey consists of exactly k 0-indexed days, and they can choose any city as their starting point.
     *
     * Each day, the tourist has two choices:
     *
     * Stay in the current city: If the tourist stays in their current city curr during day i, they will earn
     * stayScore[i][curr] points.
     * Move to another city: If the tourist moves from their current city curr to city dest, they will earn
     * travelScore[curr][dest] points.
     * Return the maximum possible points the tourist can earn.
     *
     * Input: n = 2, k = 1, stayScore = [[2,3]], travelScore = [[0,2],[1,0]]
     * Output: 3
     *
     * Input: n = 3, k = 2, stayScore = [[3,4,2],[2,1,2]], travelScore = [[0,2,1],[2,0,4],[3,2,0]]
     * Output: 8
     *
     *Constraints:
     *
     * 1 <= n <= 200
     * 1 <= k <= 200
     * n == travelScore.length == travelScore[i].length == stayScore[i].length
     * k == stayScore.length
     * 1 <= stayScore[i][j] <= 100
     * 0 <= travelScore[i][j] <= 100
     * travelScore[i][i] == 0
     * @param n
     * @param k
     * @param stayScore
     * @param travelScore
     * @return
     */
    // time = O(k * n^2), space = O(k * n)
    public int maxScore(int n, int k, int[][] stayScore, int[][] travelScore) {
        int[][] f = new int[k + 1][n];
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                for (int u = 0; u < n; u++) {
                    if (j == u) f[i][j] = Math.max(f[i][j], f[i - 1][j] + stayScore[i - 1][j]);
                    else f[i][j] = Math.max(f[i][j], f[i - 1][u] + travelScore[u][j]);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < n; i++) res = Math.max(res, f[k][i]);
        return res;
    }
}