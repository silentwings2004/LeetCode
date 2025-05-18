package LC3301_3600;

public class LC3360_StoneRemovalGame {
    /**
     * Alice and Bob are playing a game where they take turns removing stones from a pile, with Alice going first.
     *
     * Alice starts by removing exactly 10 stones on her first turn.
     * For each subsequent turn, each player removes exactly 1 fewer stone than the previous opponent.
     * The player who cannot make a move loses the game.
     *
     * Given a positive integer n, return true if Alice wins the game and false otherwise.
     *
     * Input: n = 12
     * Output: true
     *
     * Input: n = 1
     * Output: false
     *
     * Constraints:
     *
     * 1 <= n <= 50
     * @param n
     * @return
     */
    // S1: Simulation
    // time = O(n), space = O(1)
    public boolean canAliceWin(int n) {
        boolean flag = false;
        int x = 10;
        while (n >= x) {
            n -= x;
            x--;
            flag = !flag;
        }
        return flag;
    }

    // S2: Math
    // time = O(1), space = O(1)
    public boolean canAliceWin2(int n) {
        int x = ((int)Math.sqrt(1 - 4 * (2 * n - 110)) + 1) / 2;
        int s = (10 + x) * (10 - x + 1) / 2;
        if (s > n) x++;
        if (x % 2 == 0) return true;
        return false;
    }
}