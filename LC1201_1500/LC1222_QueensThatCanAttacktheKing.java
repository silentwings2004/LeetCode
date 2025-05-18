package LC1201_1500;
import java.util.*;
public class LC1222_QueensThatCanAttacktheKing {
    /**
     * On a 0-indexed 8 x 8 chessboard, there can be multiple black queens ad one white king.
     *
     * You are given a 2D integer array queens where queens[i] = [xQueeni, yQueeni] represents the position of the ith
     * black queen on the chessboard. You are also given an integer array king of length 2 where king = [xKing, yKing]
     * represents the position of the white king.
     *
     * Return the coordinates of the black queens that can directly attack the king. You may return the answer in any
     * order.
     *
     * Input: queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
     * Output: [[0,1],[1,0],[3,3]]
     *
     * Input: queens = [[0,0],[1,1],[2,2],[3,4],[3,5],[4,4],[4,5]], king = [3,3]
     * Output: [[2,2],[3,4],[4,4]]
     *
     * Constraints:
     *
     * 1 <= queens.length < 64
     * queens[i].length == king.length == 2
     * 0 <= xQueeni, yQueeni, xKing, yKing < 8
     * All the given positions are unique.
     * @param queens
     * @param king
     * @return
     */
    // time = O(1), space = O(1)
    int[] dx = new int[]{-1, 0, 1, 0, -1, 1, 1, -1};
    int[] dy = new int[]{0, 1, 0, -1, 1, 1, -1, -1};
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        List<List<Integer>> res = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        int n = 8;
        for (int[] q : queens) set.add(q[0] * n + q[1]);
        int x = king[0], y = king[1];
        for (int i = 0; i < 8; i++) {
            for (int j = 1;; j++) {
                int a = x + dx[i] * j, b = y + dy[i] * j;
                if (a < 0 || a >= n || b < 0 || b >= n) break;
                if (set.contains(a * n + b)) {
                    res.add(Arrays.asList(a, b));
                    break;
                }
            }
        }
        return res;
    }
}