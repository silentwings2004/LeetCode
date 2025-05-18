package LC601_900;
import java.util.*;
public class LC886_PossibleBipartition {
    /**
     * We want to split a group of n people (labeled from 1 to n) into two groups of any size. Each person may dislike
     * some other people, and they should not go into the same group.
     *
     * Given the integer n and the array dislikes where dislikes[i] = [ai, bi] indicates that the person labeled ai does
     * not like the person labeled bi, return true if it is possible to split everyone into two groups in this way.
     *
     * Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
     * Output: true
     *
     * Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
     * Output: false
     *
     * Input: n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
     * Output: false
     *
     * Constraints:
     *
     * 1 <= n <= 2000
     * 0 <= dislikes.length <= 10^4
     * dislikes[i].length == 2
     * 1 <= dislikes[i][j] <= n
     * ai < bi
     * All the pairs of dislikes are unique.
     * @param n
     * @param dislikes
     * @return
     */
    // S1: 染色法
    // time = O(n + m), space = O(n + m)
    List<Integer>[] graph;
    int[] color;
    public boolean possibleBipartition(int n, int[][] dislikes) {
        graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        color = new int[n];
        Arrays.fill(color, -1);

        for (int[] x : dislikes) {
            int a = x[0] - 1, b = x[1] - 1;
            graph[a].add(b);
            graph[b].add(a);
        }

        for (int i = 0; i < n; i++) {
            if (color[i] != -1) continue;
            if (!dfs(i, 0)) return false;
        }
        return true;
    }

    private boolean dfs(int u, int c) {
        color[u] = c;
        for (int v : graph[u]) {
            if (color[v] != -1) {
                if (color[v] == color[u]) return false;
            } else if (!dfs(v, 1 - c)) return false;
        }
        return true;
    }

    // S2: Union Find
    // time = O(n + m * alpah(n), space = O(n + m)
    int[] p;
    public boolean possibleBipartition2(int n, int[][] dislikes) {
        p = new int[n + 1];
        for (int i = 0; i <= n; i++) p[i] = i;
        List<Integer>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();
        for (int[] x : dislikes) {
            int a = x[0], b = x[1];
            graph[a].add(b);
            graph[b].add(a);
        }

        for (int i = 1; i <= n; i++) {
            int fa = -1;
            if (graph[i].size() > 0) {
                fa = graph[i].get(0);
                if (find(fa) == find(i)) return false;
                for (int x : graph[i]) p[find(x)] = find(fa);
            }
        }
        return true;
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}
