package LC3901_4200;

public class LC3968_MaximumManhattanDistanceAfterAllMoves {
    /**
     * You are given a string moves consisting of the characters 'U', 'D', 'L', 'R', and '_'.
     *
     * Starting from the origin (0, 0), each character represents one move on a 2D plane:
     *
     * 'U': Move up by 1 unit.
     * 'D': Move down by 1 unit.
     * 'L': Move left by 1 unit.
     * 'R': Move right by 1 unit.
     * '_': Can be independently replaced with any one of 'U', 'D', 'L', or 'R'.
     * Return the maximum Manhattan distance from the origin that can be achieved after all moves have been performed.
     *
     * Input: moves = "L_D_"
     * Output: 4
     *
     * Input: moves = "U_R"
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= moves.length <= 10^5
     * moves consists of only 'U', 'D', 'L', 'R', and '_'.
     * @param moves
     * @return
     */
    // time = O(n), space = O(1)
    public int maxDistance(String moves) {
        int n = moves.length(), x = 0, y = 0, t = 0;
        for (int i = 0; i < n; i++) {
            char c = moves.charAt(i);
            if (c == 'U') y++;
            else if (c == 'D') y--;
            else if (c == 'L') x--;
            else if (c == 'R') x++;
            else t++;
        }
        return Math.abs(x) + Math.abs(y) + t;
    }
}