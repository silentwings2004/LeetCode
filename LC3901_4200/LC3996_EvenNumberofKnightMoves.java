package LC3901_4200;

public class LC3996_EvenNumberofKnightMoves {
    /**
     * You are given two integer arrays start and target, where each array is of the form [x, y] representing a cell on
     * a standard 8 x 8 chessboard.
     *
     * Return true if a knight can move from start to target in an even number of moves. Otherwise, return false.
     *
     * Note: A valid knight move consists of moving two squares in one direction and one square perpendicular to it. The
     * figure below illustrates all eight possible moves from a cell.
     *
     * Input: start = [1,1], target = [2,2]
     * Output: true
     *
     * Input: start = [4,5], target = [6,6]
     * Output: false
     *
     * Constraints:
     *
     * start.length == target.length == 2
     * 0 <= start[i], target[i] <= 7
     * @param start
     * @param target
     * @return
     */
    // time = O(1), space = O(1)
    public boolean canReach(int[] start, int[] target) {
        return (start[0] + start[1]) % 2 == (target[0] + target[1]) % 2;
    }
}
/**
 * 一般地，马走奇数步，格子颜色会变；走偶数步，格子颜色不变。
 * 所以 start 和 target 的格子颜色必须相同。
 * 如何计算格子的颜色？对于格子 (x,y)，x+y 相同的格子，都在同一条斜线上，颜色相同。
 * 由于 x+y 每增加 1，格子颜色切换，所以可以用 (x+y) % 2 区分格子的颜色。
 */