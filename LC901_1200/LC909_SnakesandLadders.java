package LC901_1200;
import java.util.*;
public class LC909_SnakesandLadders {
    /**
     * You are given an n x n integer matrix board where the cells are labeled from 1 to n2 in a Boustrophedon style
     * starting from the bottom left of the board (i.e. board[n - 1][0]) and alternating direction each row.
     *
     * You start on square 1 of the board. In each move, starting from square curr, do the following:
     *
     * Choose a destination square next with a label in the range [curr + 1, min(curr + 6, n^2)].
     * This choice simulates the result of a standard 6-sided die roll: i.e., there are always at most 6 destinations,
     * regardless of the size of the board.
     * If next has a snake or ladder, you must move to the destination of that snake or ladder. Otherwise, you move to
     * next.
     * The game ends when you reach the square n2.
     * A board square on row r and column c has a snake or ladder if board[r][c] != -1. The destination of that snake
     * or ladder is board[r][c]. Squares 1 and n2 do not have a snake or ladder.
     *
     * Note that you only take a snake or ladder at most once per move. If the destination to a snake or ladder is the
     * start of another snake or ladder, you do not follow the subsequent snake or ladder.
     *
     * For example, suppose the board is [[-1,4],[-1,3]], and on the first move, your destination square is 2. You
     * follow the ladder to square 3, but do not follow the subsequent ladder to 4.
     * Return the least number of moves required to reach the square n2. If it is not possible to reach the square,
     * return -1.
     *
     * Input: board = [[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,35,-1,-1,13,-1],
     * [-1,-1,-1,-1,-1,-1],[-1,15,-1,-1,-1,-1]]
     * Output: 4
     *
     * Constraints:
     *
     * n == board.length == board[i].length
     * 2 <= n <= 20
     * grid[i][j] is either -1 or in the range [1, n^2].
     * The squares labeled 1 and n2 do not have any ladders or snakes.
     * @param board
     * @return
     */
    // time = O(n^2), space = O(n^2)
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        boolean[] st = new boolean[n * n + 1];
        st[1] = true;

        int step = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            while (sz-- > 0) {
                int t = q.poll();
                if (t == n * n) return step;
                for (int i = 1; i <= 6; i++) {
                    if (t + i > n * n) break;
                    int nxt = t + i;
                    int[] c = get(nxt, n);
                    int x = c[0], y = c[1];
                    if (board[x][y] != -1) nxt = board[x][y];
                    if (st[nxt]) continue;
                    st[nxt] = true;
                    q.offer(nxt);
                }
            }
            step++;
        }
        return -1;
    }

    private int[] get(int x, int n) {
        int r = (x - 1) / n, c = (x - 1) % n;
        if (r % 2 == 1) c = n - 1 - c;
        return new int[]{n - 1 - r, c};
    }
}