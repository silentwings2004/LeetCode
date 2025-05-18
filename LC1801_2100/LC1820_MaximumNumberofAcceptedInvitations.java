package LC1801_2100;
import java.util.*;
public class LC1820_MaximumNumberofAcceptedInvitations {
    /**
     * There are m boys and n girls in a class attending an upcoming party.
     *
     * You are given an m x n integer matrix grid, where grid[i][j] equals 0 or 1. If grid[i][j] == 1, then that means
     * the ith boy can invite the jth girl to the party. A boy can invite at most one girl, and a girl can accept at
     * most one invitation from a boy.
     *
     * Return the maximum possible number of accepted invitations.
     *
     * Input: grid = [[1,1,1],
     *                [1,0,1],
     *                [0,0,1]]
     * Output: 3
     *
     * Constraints:
     *
     * grid.length == m
     * grid[i].length == n
     * 1 <= m, n <= 200
     * grid[i][j] is either 0 or 1.
     * @param grid
     * @return
     */
    // time = O(m * n), space = O(m + n)
    List<Integer>[] next;
    int[] match;
    public int maximumInvitations(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        next = new List[m + n];
        for (int i = 0; i < m + n; i++) next[i] = new ArrayList<>();
        match = new int[m + n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    next[i].add(m + j);
                    next[m + j].add(i);
                }
            }
        }

        Arrays.fill(match, -1);
        boolean[] visited = new boolean[m + n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            Arrays.fill(visited, false);
            if (dfs(i, visited)) count++;
        }
        return count;
    }

    private boolean dfs(int i, boolean[] visited) {
        for (int j : next[i]) {
            if (visited[j]) continue;
            visited[j] = true;
            if (match[j] == -1 || dfs(match[j], visited)) {
                match[i] = j;
                match[j] = i;
                return true;
            }
        }
        return false;
    }

    // S2: 匈牙利算法
    // time = O(m * m * n), space = O(m * n)
    class Solution {
        // time = O(m * m * n), space = O(m * n)
        final int N = 210, M = 40010;
        int m, n, idx;
        int[] h, e, ne;
        int[] match;
        boolean[] st;
        public int maximumInvitations(int[][] grid) {
            h = new int[N];
            e = new int[M];
            ne = new int[M];
            match = new int[N];
            st = new boolean[N];

            m = grid.length;
            n = grid[0].length;
            Arrays.fill(h, -1);
            idx = 0;

            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (grid[i - 1][j - 1] == 1) add(i, j);
                }
            }

            int res = 0;
            for (int i = 1; i <= m; i++) {
                Arrays.fill(st, false);
                if (find(i)) res++;
            }
            return res;
        }

        private boolean find(int u) {
            for (int i = h[u]; i != -1; i = ne[i]) {
                int j = e[i];
                if (!st[j]) {
                    st[j] = true;
                    if (match[j] == 0 || find(match[j])) {
                        match[j] = u;
                        return true;
                    }
                }
            }
            return false;
        }

        private void add(int a, int b) {
            e[idx] = b;
            ne[idx] = h[a];
            h[a] = idx++;
        }
    }
}
