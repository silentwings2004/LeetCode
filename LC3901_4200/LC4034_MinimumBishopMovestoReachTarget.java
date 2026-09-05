package LC3901_4200;

public class LC4034_MinimumBishopMovestoReachTarget {
    /**
     * There is an 8 x 8 empty chessboard with 1-indexed rows and columns.
     *
     * You are given an array source = [sr, sc] representing the starting position of a bishop, and an array target =
     * [tr, tc]. In one move, the bishop travels any number of squares along a single diagonal direction, staying within
     * the board.
     *
     * Return the minimum number of moves for the bishop to land exactly on target. If it can never reach target,
     * return -1.
     *
     * Input: source = [8,1], target = [1,8]
     * Output: 1
     *
     * Input: source = [4,2], target = [1,3]
     * Output: 2
     *
     * Input: source = [1,1], target = [3,4]
     * Output: -1
     *
     * Constraints:
     *
     * source.length == target.length == 2
     * 1 <= sr, sc, tr, tc <= 8
     * source != target
     * @param source
     * @param target
     * @return
     */
    // time = O(1), space = O(1)
    public int minBishopMoves(int[] source, int[] target) {
        int sx = source[0], sy = source[1];
        int tx = target[0], ty = target[1];
        if ((sx + sy) % 2 != (tx + ty) % 2) return -1; // 颜色不同
        // 两点之间的直线，如果斜率是 -1 或者 1，那么两点可以直接到达，否则要走两步
        return sx + sy == tx + ty || sx - sy == tx - ty ? 1 : 2;
    }
}