package LC3901_4200;

public class LC4061_MinimumQueenMovestoReachTarget {
    /**
     * There is an 8 x 8 empty chessboard with 1-indexed rows and columns.
     *
     * You are given an array source = [sr, sc] representing the starting position of a queen, and an array target =
     * [tr, tc] representing the target position.
     *
     * In one move, the queen travels one or more squares along a single row, column, or diagonal, staying within the board.
     *
     * Return the minimum number of moves for the queen to land exactly on target.
     *
     * Input: source = [8,1], target = [1,8]
     * Output: 1
     *
     * Input: source = [4,2], target = [1,3]
     * Output: 2
     *
     * Input: source = [1,1], target = [1,1]
     * Output: 0
     *
     * Constraints:
     *
     * source == [sr, sc]
     * target == [tr, tc]
     * 1 <= sr, sc, tr, tc <= 8
     * @param source
     * @param target
     * @return
     */
    // time = O(1), space = O(1)
    public int minQueenMoves(int[] source, int[] target) {
        int sx = source[0], sy = source[1];
        int tx = target[0], ty = target[1];
        if (sx == tx && sy == ty) return 0;
        if (sx == tx || sy == ty || Math.abs(sx - tx) == Math.abs(sy - ty)) return 1;
        return 2;
    }
}