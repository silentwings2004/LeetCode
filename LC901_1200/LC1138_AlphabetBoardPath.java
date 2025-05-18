package LC901_1200;
import java.util.*;
public class LC1138_AlphabetBoardPath {
    /**
     * On an alphabet board, we start at position (0, 0), corresponding to character board[0][0].
     *
     * Here, board = ["abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"], as shown in the diagram below.
     *
     * We may make the following moves:
     *
     * 'U' moves our position up one row, if the position exists on the board;
     * 'D' moves our position down one row, if the position exists on the board;
     * 'L' moves our position left one column, if the position exists on the board;
     * 'R' moves our position right one column, if the position exists on the board;
     * '!' adds the character board[r][c] at our current position (r, c) to the answer.
     * (Here, the only positions that exist on the board are positions with letters on them.)
     *
     * Return a sequence of moves that makes our answer equal to target in the minimum number of moves.  You may return
     * any path that does so.
     *
     * Input: target = "leet"
     * Output: "DDR!UURRR!!DDD!"
     *
     * Input: target = "code"
     * Output: "RR!DDRR!UUL!R!"
     *
     * Constraints:
     *
     * 1 <= target.length <= 100
     * target consists only of English lowercase letters.
     * @param target
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public String alphabetBoardPath(String target) {
        StringBuilder sb = new StringBuilder();
        int n = target.length();
        int[] p = new int[]{0, 0};
        for (int i = 0; i < n; i++) {
            char c = target.charAt(i);
            int[] t = get(c);
            if (c == 'z') {
                if (t[1] > p[1]) sb.append("R".repeat(t[1] - p[1]));
                else sb.append("L".repeat(p[1] - t[1]));
                if (t[0] > p[0]) sb.append("D".repeat(t[0] - p[0]));
                else sb.append("U".repeat(p[0] - t[0]));
            } else {
                if (t[0] > p[0]) sb.append("D".repeat(t[0] - p[0]));
                else sb.append("U".repeat(p[0] - t[0]));
                if (t[1] > p[1]) sb.append("R".repeat(t[1] - p[1]));
                else sb.append("L".repeat(p[1] - t[1]));
            }
            sb.append('!');
            p = t;
        }
        return sb.toString();
    }

    private int[] get(char c) {
        int t = c - 'a';
        return new int[]{t / 5, t % 5};
    }

    // S2
    // time = O(n), space = O(n)
    public String alphabetBoardPath2(String target) {
        String[] board = new String[]{"abcde", "fghij", "klmno", "pqrst", "uvwxy", "z"};
        int m = board.length, n = target.length();
        HashMap<Character, int[]> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < board[i].length(); j++) {
                map.put(board[i].charAt(j), new int[]{i, j});
            }
        }

        StringBuilder sb = new StringBuilder();
        int x = 0, y = 0;
        for (int i = 0; i < n; i++) {
            char c = target.charAt(i);
            int[] t = map.get(c);
            int dx = t[0] - x, dy = t[1] - y;
            boolean flag = false;
            for (int j = 0; j < Math.abs(dx); j++) sb.append(dx < 0 ? 'U' : 'D');
            if (c == 'z' && dx > 0 && y != 0) {
                sb.setLength(sb.length() - 1);
                flag = true;
            }
            for (int j = 0; j < Math.abs(dy); j++) sb.append(dy < 0 ? 'L' : 'R');
            if (flag) sb.append('D');
            int j = i;
            while (j < n && target.charAt(j) == c) j++;
            int len = j - i;
            for (int k = 0; k < len; k++) sb.append('!');
            i = j - 1;
            x = t[0];
            y = t[1];
        }
        return sb.toString();
    }
}
/**
 * 每一步都是独立的，走的是曼哈顿距离
 * 特判下，如果目标点是z的话，只能先横再竖
 * 如果不是z的话，可以先竖再横
 */