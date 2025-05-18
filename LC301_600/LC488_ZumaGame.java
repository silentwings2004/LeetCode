package LC301_600;
import java.util.*;
public class LC488_ZumaGame {
    /**
     * You are playing a variation of the game Zuma.
     *
     * In this variation of Zuma, there is a single row of colored balls on a board, where each ball can be colored
     * red 'R', yellow 'Y', blue 'B', green 'G', or white 'W'. You also have several colored balls in your hand.
     *
     * Your goal is to clear all of the balls from the board. On each turn:
     *
     * Pick any ball from your hand and insert it in between two balls in the row or on either end of the row.
     * If there is a group of three or more consecutive balls of the same color, remove the group of balls from the board.
     * If this removal causes more groups of three or more of the same color to form, then continue removing each group
     * until there are none left.
     * If there are no more balls on the board, then you win the game.
     * Repeat this process until you either win or do not have any more balls in your hand.
     * Given a string board, representing the row of balls on the board, and a string hand, representing the balls in
     * your hand, return the minimum number of balls you have to insert to clear all the balls from the board. If you
     * cannot clear all the balls from the board using the balls in your hand, return -1.
     *
     * Input: board = "WRRBBW", hand = "RB"
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= board.length <= 16
     * 1 <= hand.length <= 5
     * board and hand consist of the characters 'R', 'Y', 'B', 'G', and 'W'.
     * The initial row of balls on the board will not have any groups of three or more consecutive balls of the same color.
     * @param board
     * @param hand
     * @return
     */
    // time = O(m * n * A(m + n, m), space = O((m + n) * A(m + n, m))
    final int INF = 0x3f3f3f3f;
    String a, b;
    int m;
    HashMap<String, Integer> map;
    public int findMinStep(String board, String hand) {
        a = board;
        b = hand;
        m = b.length();
        map = new HashMap<>();
        int res = dfs(a, 1 << m);
        return res == INF ? -1 : res;
    }

    private int dfs(String a, int state) {
        int n = a.length();
        if (n == 0) return 0;
        String key = a + "#" + state;
        if (map.containsKey(key)) return map.get(key);

        int res = INF;
        for (int i = 0; i < m; i++) { // pick the ball
            if ((state >> i & 1) == 1) continue; // has been picked already
            int next = state | (1 << i);
            for (int j = 0; j < n; j++) { // pick the target location
                boolean flag = false;
                if (j > 0 && j < n && a.charAt(j - 1) == a.charAt(j) && a.charAt(j) != b.charAt(i)) flag = true;
                if (j < n && a.charAt(j) == b.charAt(i)) flag = true;
                if (!flag) continue; // didn't find the right target to hit

                // if find the target to hit, then record the result
                StringBuilder sb = new StringBuilder();
                sb.append(a.substring(0, j)).append(b.substring(i, i + 1)); // place the ball from b between a[j-1,j]
                if (j < n) sb.append(a.substring(j));

                // check the current state and eliminate the balls if available
                int k = j;
                while (k >= 0 && k < sb.length()) {
                    char c = sb.charAt(k);
                    int l = k, r = k; // two pointers
                    while (l >= 0 && sb.charAt(l) == c) l--;
                    while (r < sb.length() && sb.charAt(r) == c) r++;
                    if (r - l - 1 >= 3) {
                        sb.delete(l + 1, r); // [ )
                        k = l >= 0 ? l : r;
                    } else break; // not able to eliminate any balls
                }
                res = Math.min(res, dfs(sb.toString(), next) + 1);
            }
        }
        map.put(key, res);
        return res;
    }
}
/**
 * dfs
 */
