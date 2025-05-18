package LC2401_2700;

public class LC2660_DeterminetheWinnerofaBowlingGame {
    /**
     * You are given two 0-indexed integer arrays player1 and player2, that represent the number of pins that player 1
     * and player 2 hit in a bowling game, respectively.
     *
     * The bowling game consists of n turns, and the number of pins in each turn is exactly 10.
     *
     * Assume a player hit xi pins in the ith turn. The value of the ith turn for the player is:
     *
     * 2xi if the player hit 10 pins in any of the previous two turns.
     * Otherwise, It is xi.
     * The score of the player is the sum of the values of their n turns.
     *
     * Return
     *
     * 1 if the score of player 1 is more than the score of player 2,
     * 2 if the score of player 2 is more than the score of player 1, and
     * 0 in case of a draw.
     *
     * Input: player1 = [4,10,7,9], player2 = [6,5,2,3]
     * Output: 1
     *
     * Input: player1 = [3,5,7,6], player2 = [8,10,10,2]
     * Output: 2
     *
     * Input: player1 = [2,3], player2 = [4,1]
     * Output: 0
     *
     * Constraints:
     *
     * n == player1.length == player2.length
     * 1 <= n <= 1000
     * 0 <= player1[i], player2[i] <= 10
     * @param player1
     * @param player2
     * @return
     */
    // time = O(n), space = O(1)
    public int isWinner(int[] player1, int[] player2) {
        int n = player1.length, s1 = 0, s2 = 0;
        for (int i = 0; i < n; i++) {
            int t1 = 1, t2 = 1;
            if (i >= 1 && player1[i - 1] == 10 || i >= 2 && player1[i - 2] == 10) t1 = 2;
            if (i >= 1 && player2[i - 1] == 10 || i >= 2 && player2[i - 2] == 10) t2 = 2;
            s1 += player1[i] * t1;
            s2 += player2[i] * t2;
        }
        if (s1 == s2) return 0;
        return s1 > s2 ? 1 : 2;
    }
}