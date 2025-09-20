package LC3601_3900;
import java.util.*;
public class LC3680_GenerateSchedule {
    /**
     * You are given an integer n representing n teams. You are asked to generate a schedule such that:
     *
     * Each team plays every other team exactly twice: once at home and once away.
     * There is exactly one match per day; the schedule is a list of consecutive days and schedule[i] is the match on
     * day i.
     * No team plays on consecutive days.
     * Return a 2D integer array schedule, where schedule[i][0] represents the home team and schedule[i][1] represents
     * the away team. If multiple schedules meet the conditions, return any one of them.
     *
     * If no schedule exists that meets the conditions, return an empty array.
     *
     * Input: n = 3
     * Output: []
     *
     * Input: n = 5
     * Output: [[0,1],[2,3],[0,4],[1,2],[3,4],[0,2],[1,3],[2,4],[0,3],[1,4],[2,0],[3,1],[4,0],[2,1],[4,3],[1,0],[3,2],[4,1],[3,0],[4,2]]
     *
     * Constraints:
     *
     * 2 <= n <= 50
     * @param n
     * @return
     */
    // S1: dfs
    // time = O(n! * n^2), space = O(n^2)
    int[][] res;
    boolean[][] st;
    int[] cnt;
    int n;
    public int[][] generateSchedule(int n) {
        this.n = n;
        int m = n * (n - 1);
        res = new int[m][2];
        res[0] = new int[]{0, 1};
        st = new boolean[n][n];
        st[0][1] = true;
        cnt = new int[n];
        cnt[0] = cnt[1] = 1;
        if (dfs(1)) return res;
        return new int[0][];
    }

    private boolean dfs(int u) {
        if (u == res.length) return true;
        Integer[] p = new Integer[n];
        for (int i = 0; i < n; i++) p[i] = i;
        Arrays.sort(p, (o1, o2) -> cnt[o1] - cnt[o2]);
        for (int i = 0; i < n; i++) {
            if (res[u - 1][0] != p[i] && res[u - 1][1] != p[i]) {
                int a = -1, b = -1;
                for (int j = 0; j < n; j++) {
                    if (i != j && !st[p[i]][p[j]] && res[u - 1][0] != p[j] && res[u - 1][1] != p[j]) {
                        a = p[i];
                        b = p[j];
                        break;
                    }
                }
                if (a != -1) {
                    res[u] = new int[]{a, b};
                    st[a][b] = true;
                    cnt[a]++;
                    cnt[b]++;
                    if (dfs(u + 1)) return true;
                    st[a][b] = false;
                    res[u] = new int[]{0, 0};
                    cnt[a]--;
                    cnt[b]--;
                }
            }
        }
        return false;
    }

    // S2
    // time = O(n^2), space = O(1)
    public int[][] generateSchedule2(int n) {
        if (n < 5) return new int[0][];
        int m = n * (n - 1);
        int[][] res = new int[m][2];
        int idx = 0;
        for (int d = 2; d < n - 1; d++) {
            for (int i = 0; i < n; i++) {
                res[idx++] = new int[]{i, (i + d) % n};
            }
        }

        for (int i = 0; i < n; i++) {
            res[idx++] = new int[]{i, (i + 1) % n};
            res[idx++] = new int[]{(i - 1 + n) % n, (i - 2 + n) % n};
        }
        return res;
    }
}
/**
 * 环形数组 =>
 * d = 1
 * 0 1
 * 1 2
 * 2 3
 * 3 4
 * 4 0
 * d = 2
 * 0 2
 * 1 3
 * 2 4
 * 3 0
 * 4 1
 * d = 3
 * 0 3
 * 1 4
 * 2 0
 * 3 1
 * 4 2
 * d = 4
 * 0 4
 * 1 0
 * 2 1
 * 3 2
 * 4 3
 * 刚好20场比赛
 * 组间不会重复
 * (n-1-d,n-1), (n-d,0)
 * d < n - 1
 * [2,n-2] 组内是不可能出现相邻相同的情况 => 按这样去分组非常合适
 * 这一组最后一个会不会和下一组第一个重复？
 * (n-1,d-1)
 * (0,d+1)
 * d < n - 2, d + 1 < n - 1
 * d = 1 or d = n - 1 => 错开选，但 if n = even，
 * 01 12 23 34 45 50
 * => o1 23 45 12 34 50 => switch 34 and 50
 * similar for d = n - 1
 */