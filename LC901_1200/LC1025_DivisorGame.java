package LC901_1200;

public class LC1025_DivisorGame {
    /**
     * Alice and Bob take turns playing a game, with Alice starting first.
     *
     * Initially, there is a number n on the chalkboard. On each player's turn, that player makes a move consisting of:
     *
     * Choosing any x with 0 < x < n and n % x == 0.
     * Replacing the number n on the chalkboard with n - x.
     * Also, if a player cannot make a move, they lose the game.
     *
     * Return true if and only if Alice wins the game, assuming both players play optimally.
     *
     * Input: n = 2
     * Output: true
     *
     * Input: n = 3
     * Output: false
     *
     * Constraints:
     *
     * 1 <= n <= 1000
     * @param n
     * @return
     */
    // time = O(1), space = O(1)
    public boolean divisorGame(int n) {
        return n % 2 == 0;
    }
}
/**
 * 结论：奇数必败，偶数必胜 => 数学归纳法
 * 边界：n = 1 成立
 * 设 k < n 时，成立 (n > 1)
 * 证: k = n 时也成立
 * 情况1：k为奇数，x 为奇数 => k - x必为偶数, k - x < n => k为必败
 * 情况2：k为偶数，k为必胜
 */