package LC3001_3300;

public class LC3222_FindtheWinningPlayerinCoinGame {
    /**
     * You are given two positive integers x and y, denoting the number of coins with values 75 and 10 respectively.
     *
     * Alice and Bob are playing a game. Each turn, starting with Alice, the player must pick up coins with a total
     * value 115. If the player is unable to do so, they lose the game.
     *
     * Return the name of the player who wins the game if both players play optimally.
     *
     * Input: x = 2, y = 7
     * Output: "Alice"
     *
     * Input: x = 4, y = 11
     * Output: "Bob"
     *
     * Constraints:
     *
     * 1 <= x, y <= 100
     * @param x
     * @param y
     * @return
     */
    // time = O(1), space = O(1)
    public String losingPlayer(int x, int y) {
        int t = Math.min(x, y / 4);
        return t % 2 == 1 ? "Alice" : "Bob";
    }
}