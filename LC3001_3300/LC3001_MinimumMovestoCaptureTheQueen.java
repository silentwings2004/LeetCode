package LC3001_3300;
import java.util.*;
public class LC3001_MinimumMovestoCaptureTheQueen {
    /**
     * There is a 1-indexed 8 x 8 chessboard containing 3 pieces.
     *
     * You are given 6 integers a, b, c, d, e, and f where:
     *
     * (a, b) denotes the position of the white rook.
     * (c, d) denotes the position of the white bishop.
     * (e, f) denotes the position of the black queen.
     * Given that you can only move the white pieces, return the minimum number of moves required to capture the black
     * queen.
     *
     * Note that:
     *
     * Rooks can move any number of squares either vertically or horizontally, but cannot jump over other pieces.
     * Bishops can move any number of squares diagonally, but cannot jump over other pieces.
     * A rook or a bishop can capture the queen if it is located in a square that they can move to.
     *
     * The queen does not move.
     *
     * Input: a = 1, b = 1, c = 8, d = 8, e = 2, f = 3
     * Output: 2
     *
     * Input: a = 5, b = 3, c = 3, d = 4, e = 5, f = 2
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= a, b, c, d, e, f <= 8
     * No two pieces are on the same square.
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param f
     * @return
     */
    // S1
    // time = O(1), space = O(1)
    public int minMovesToCaptureTheQueen(int a, int b, int c, int d, int e, int f) {
        if (a == e) {
            if (a == c) {
                int l = Math.min(b, f), r = Math.max(b, f);
                if (d < l || d > r) return 1;
            } else return 1;
        }
        if (b == f) {
            if (b == d) {
                int l = Math.min(a, e), r = Math.max(a, e);
                if (c < l || c > r) return 1;
            } else return 1;
        }
        int[] dx = new int[]{-1, -1, 1, 1}, dy = new int[]{-1, 1, 1, -1};
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 8; j++) {
                int x = c + dx[i] * j, y = d + dy[i] * j;
                if (x < 1 || x > 8 || y < 1 || y > 8) break;
                if (x == a && y == b) break;
                if (x == e && y == f) return 1;
            }
        }
        return 2;
    }

    // S2
    // time = O(1), space = O(1)
    public int minMovesToCaptureTheQueen2(int a, int b, int c, int d, int e, int f) {
        if (a == e && (c != e || not_inside(b, d, f))) return 1;
        if (b == f && (d != f || not_inside(a, c, e))) return 1;
        if (c + d == e + f && (a + b != e + f || not_inside(c, a, e))) return 1;
        if (c - d == e - f && (a - b != e - f || not_inside(c, a, e))) return 1;
        return 2;
    }

    private boolean not_inside(int l, int mid, int r) {
        int a = Math.min(l, r), b = Math.max(l, r);
        return !(a < mid && mid < b);
    }
}
/**
 * 1.白车可以直接攻击到黑后
 * 2.白象可以直接攻击到黑后
 * 3.闪击：白车被白象挡住，那么移走白象，白车就可以直接攻击到黑后 -> 2
 * 4.闪击：白象被白车挡住，那么移走白车，白车象就可以直接攻击到黑后 -> 2
 * 5.其他情况：移动白车，总有一个方向不会被挡住
 */