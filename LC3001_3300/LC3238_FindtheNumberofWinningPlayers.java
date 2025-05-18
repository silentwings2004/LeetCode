package LC3001_3300;

public class LC3238_FindtheNumberofWinningPlayers {
    /**
     * You are given an integer n representing the number of players in a game and a 2D array pick where pick[i] =
     * [xi, yi] represents that the player xi picked a ball of color yi.
     *
     * Player i wins the game if they pick strictly more than i balls of the same color. In other words,
     *
     * Player 0 wins if they pick any ball.
     * Player 1 wins if they pick at least two balls of the same color.
     * ...
     * Player i wins if they pick at leasti + 1 balls of the same color.
     * Return the number of players who win the game.
     *
     * Note that multiple players can win the game.
     *
     * Input: n = 4, pick = [[0,0],[1,0],[1,0],[2,1],[2,1],[2,0]]
     * Output: 2
     *
     * Input: n = 5, pick = [[1,1],[1,2],[1,3],[1,4]]
     * Output: 0
     *
     * Input: n = 5, pick = [[1,1],[2,4],[2,4],[2,4]]
     * Output: 1
     *
     * Constraints:
     *
     * 2 <= n <= 10
     * 1 <= pick.length <= 100
     * pick[i].length == 2
     * 0 <= xi <= n - 1
     * 0 <= yi <= 10
     * @param n
     * @param pick
     * @return
     */
    // S1
    // time = O(n + m), space = O(n)
    public int winningPlayerCount(int n, int[][] pick) {
        int[][] cnt = new int[n][11];
        for (int[] x : pick) {
            int u = x[0], v = x[1];
            cnt[u][v]++;
        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= 10; j++) {
                if (cnt[i][j] > i) {
                    res++;
                    break;
                }
            }
        }
        return res;
    }
}