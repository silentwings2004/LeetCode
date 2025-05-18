package LC3001_3300;
import java.util.*;
public class LC3283_MaximumNumberofMovestoKillAllPawns {
    /**
     * There is a 50 x 50 chessboard with one knight and some pawns on it. You are given two integers kx and ky where
     * (kx, ky) denotes the position of the knight, and a 2D array positions where positions[i] = [xi, yi] denotes the
     * position of the pawns on the chessboard.
     *
     * Alice and Bob play a turn-based game, where Alice goes first. In each player's turn:
     *
     * The player selects a pawn that still exists on the board and captures it with the knight in the fewest possible
     * moves. Note that the player can select any pawn, it might not be one that can be captured in the least number of
     * moves.
     * In the process of capturing the selected pawn, the knight may pass other pawns without capturing them. Only the
     * selected pawn can be captured in this turn.
     * Alice is trying to maximize the sum of the number of moves made by both players until there are no more pawns on
     * the board, whereas Bob tries to minimize them.
     *
     * Return the maximum total number of moves made during the game that Alice can achieve, assuming both players play
     * optimally.
     *
     * Note that in one move, a chess knight has eight possible positions it can move to, as illustrated below. Each
     * move is two cells in a cardinal direction, then one cell in an orthogonal direction.
     *
     * Input: kx = 1, ky = 1, positions = [[0,0]]
     * Output: 4
     *
     * Input: kx = 0, ky = 2, positions = [[1,1],[2,2],[3,3]]
     * Output: 8
     *
     * Input: kx = 0, ky = 0, positions = [[1,2],[2,4]]
     * Output: 3
     *
     * Constraints:
     *
     * 0 <= kx, ky <= 49
     * 1 <= positions.length <= 15
     * positions[i].length == 2
     * 0 <= positions[i][0], positions[i][1] <= 49
     * All positions[i] are unique.
     * The input is generated such that positions[i] != [kx, ky] for all 0 <= i < positions.length.
     * @param kx
     * @param ky
     * @param positions
     * @return
     */
    // time = O(n * L^2 + n^2 * 2^n), space = O(n * L^2 + n * 2^n), L = 50
    final int N = 50, inf = 0x3f3f3f3f;
    int[] dx = new int[]{-2, -1, 1, 2, 2, 1, -1, -2}, dy = new int[]{1, 2, 2, 1, -1, -2, -2, -1};
    HashMap<Long, Integer> f;
    int[][] p, dist;
    int n;
    public int maxMoves(int kx, int ky, int[][] positions) {
        f = new HashMap<>();
        n = positions.length;
        p = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++) p[i] = new int[]{positions[i][0], positions[i][1]};
        p[n] = new int[]{kx, ky};
        dist = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                dist[i][j] = dist[j][i] = bfs(i, j);
            }
        }
        return dfs(n, 0);
    }

    private int bfs(int u, int v) {
        boolean[][] st = new boolean[N][N];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{p[u][0], p[u][1]});
        st[p[u][0]][p[u][1]] = true;

        int step = 0;
        while (!q.isEmpty()) {
            int sz = q.size();
            while (sz-- > 0) {
                int[] t = q.poll();
                int x = t[0], y = t[1];
                if (x == p[v][0] && y == p[v][1]) return step;
                for (int i = 0; i < 8; i++) {
                    int a = x + dx[i], b = y + dy[i];
                    if (a < 0 || a >= N || b < 0 || b >= N) continue;
                    if (st[a][b]) continue;
                    st[a][b] = true;
                    q.offer(new int[]{a, b});
                }
            }
            step++;
        }
        return inf;
    }

    private int dfs(int u, int state) {
        if (state == (1 << n) - 1) return 0;

        long key = (1L << 4) * state + u;
        if (f.containsKey(key)) return f.get(key);

        boolean flag = Integer.bitCount(state) % 2 == 0;
        int res = flag ? -inf : inf;

        for (int i = 0; i < n; i++) {
            if ((state >> i & 1) == 1) continue;
            int step = dist[u][i];

            int next = dfs(i, state | (1 << i));
            if (flag) res = Math.max(res, step + next);
            else res = Math.min(res, step + next);
        }
        f.put(key, res);
        return res;
    }
}