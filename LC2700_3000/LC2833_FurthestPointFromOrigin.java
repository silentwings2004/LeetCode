package LC2700_3000;

public class LC2833_FurthestPointFromOrigin {
    /**
     * You are given a string moves of length n consisting only of characters 'L', 'R', and '_'. The string represents
     * your movement on a number line starting from the origin 0.
     *
     * In the ith move, you can choose one of the following directions:
     *
     * move to the left if moves[i] = 'L' or moves[i] = '_'
     * move to the right if moves[i] = 'R' or moves[i] = '_'
     * Return the distance from the origin of the furthest point you can get to after n moves.
     *
     * Input: moves = "L_RL__R"
     * Output: 3
     *
     * Input: moves = "_R__LL_"
     * Output: 5
     *
     * Input: moves = "_______"
     * Output: 7
     *
     * Constraints:
     *
     * 1 <= moves.length == n <= 50
     * moves consists only of characters 'L', 'R' and '_'.
     * @param moves
     * @return
     */
    // time = O(n), space = O(1)
    public int furthestDistanceFromOrigin(String moves) {
        int cnt = 0, t = 0;
        for (int i = 0; i < moves.length(); i++) {
            if (moves.charAt(i) == 'L') t--;
            else if (moves.charAt(i) == 'R') t++;
            else cnt++;
        }
        return Math.abs(t) + cnt;
    }
}