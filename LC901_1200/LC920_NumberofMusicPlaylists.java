package LC901_1200;

public class LC920_NumberofMusicPlaylists {
    /**
     * Your music player contains n different songs. You want to listen to goal songs (not necessarily different) during
     * your trip. To avoid boredom, you will create a playlist so that:
     *
     * Every song is played at least once.
     * A song can only be played again only if k other songs have been played.
     * Given n, goal, and k, return the number of possible playlists that you can create. Since the answer can be very
     * large, return it modulo 10^9 + 7.
     *
     * Input: n = 3, goal = 3, k = 1
     * Output: 6
     *
     * Input: n = 2, goal = 3, k = 0
     * Output: 6
     *
     * Input: n = 2, goal = 3, k = 1
     * Output: 2
     *
     * Constraints:
     *
     * 0 <= k < n <= goal <= 100
     * @param n
     * @param goal
     * @param k
     * @return
     */
    // time = O(n * m), space = O(n * m)
    final int N = 110, mod = (int)1e9 + 7;
    public int numMusicPlaylists(int n, int goal, int k) {
        long[][] f = new long[N][N];
        f[0][0] = 1;
        int m = goal;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n && j <= i; j++) {
                f[i][j] = (f[i - 1][j - 1] * (n - j + 1) + (f[i - 1][j] * Math.max(0, j - k))) % mod;
            }
        }
        return (int)f[m][n];
    }
}
/**
 * dp
 * 状态表示：f(i,j)
 * 1.集合：前i首歌共j种的所有合法方案的集合
 * 2.属性：方案数
 * 状态计算：以最后一首歌为不同点
 * 1. 第i首为全新的歌 -> f(i-1,j-1) * (n-(j-1))
 * 2. 第i首为重复的歌 -> f(i-1,j) * (j-k)
 */