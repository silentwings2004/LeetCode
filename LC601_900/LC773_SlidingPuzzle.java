package LC601_900;
import java.util.*;
public class LC773_SlidingPuzzle {
    /**
     * On an 2 x 3 board, there are five tiles labeled from 1 to 5, and an empty square represented by 0. A move
     * consists of choosing 0 and a 4-directionally adjacent number and swapping it.
     *
     * The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
     *
     * Given the puzzle board board, return the least number of moves required so that the state of the board is solved.
     * If it is impossible for the state of the board to be solved, return -1.
     *
     * Input: board = [[1,2,3],[4,0,5]]
     * Output: 1
     *
     * Input: board = [[1,2,3],[5,4,0]]
     * Output: -1
     *
     * Input: board = [[4,1,2],[5,0,3]]
     * Output: 5
     *
     * Constraints:
     *
     * board.length == 2
     * board[i].length == 3
     * 0 <= board[i][j] <= 5
     * Each value board[i][j] is unique.
     * @param board
     * @return
     */
    // time = O(m * n * (m * n)!), space = O(m * n * (m * n)!)
    public int slidingPuzzle(int[][] board) {
        StringBuilder sb = new StringBuilder();
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j]);
            }
        }
        String start = sb.toString(), end = "123450";
        Queue<String> q = new LinkedList<>();
        q.offer(start);
        HashSet<String> set = new HashSet<>();
        set.add(start);

        int step = 0;
        int[] dx = new int[]{-1, 0, 1, 0}, dy = new int[]{0, 1, 0, -1};
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                String t = q.poll();
                if (t.equals(end)) return step;
                int pos = t.indexOf('0');
                int x = pos / n, y = pos % n;
                for (int i = 0; i < 4; i++) {
                    int a = x + dx[i], b = y + dy[i];
                    if (a < 0 || a >= m || b < 0 || b >= n) continue;
                    String nt = swap(t, pos, a * n + b);
                    if (set.contains(nt)) continue;
                    q.offer(nt);
                    set.add(nt);
                }
            }
            step++;
        }
        return -1;
    }

    private String swap(String str, int i, int j) {
        char[] s = str.toCharArray();
        char t = s[i];
        s[i] = s[j];
        s[j] = t;
        return String.valueOf(s);
    }
}