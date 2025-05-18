package LC3001_3300;

public class LC3021_AliceandBobPlayingFlowerGame {
    /**
     * Alice and Bob are playing a turn-based game on a circular field surrounded by flowers. The circle represents the
     * field, and there are x flowers in the clockwise direction between Alice and Bob, and y flowers in the
     * anti-clockwise direction between them.
     *
     * The game proceeds as follows:
     *
     * Alice takes the first turn.
     * In each turn, a player must choose either the clockwise or anti-clockwise direction and pick one flower from that
     * side.
     * At the end of the turn, if there are no flowers left at all, the current player captures their opponent and
     * wins the game.
     * Given two integers, n and m, the task is to compute the number of possible pairs (x, y) that satisfy the conditions:
     *
     * Alice must win the game according to the described rules.
     * The number of flowers x in the clockwise direction must be in the range [1,n].
     * The number of flowers y in the anti-clockwise direction must be in the range [1,m].
     * Return the number of possible pairs (x, y) that satisfy the conditions mentioned in the statement.
     *
     * Input: n = 3, m = 2
     * Output: 3
     *
     * Input: n = 1, m = 1
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= n, m <= 10^5
     * @param n
     * @param m
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public long flowerGame(int n, int m) {
        long res = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) res += m / 2 + m % 2;
            else res += m / 2;
        }
        return res;
    }

    // S2
    // time = O(1), space = O(1)
    public long flowerGame2(int n, int m) {
        return (long)n * m / 2;
    }
}
/**
 * 1.分类讨论
 * x 奇数 y 偶数
 * x 偶数 y 奇数
 * 2. 更一般的想法 思考技巧
 */