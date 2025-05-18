package LC1801_2100;
import java.util.*;
public class LC1857_LargestColorValueinaDirectedGraph {
    /**
     * There is a directed graph of n colored nodes and m edges. The nodes are numbered from 0 to n - 1.
     *
     * You are given a string colors where colors[i] is a lowercase English letter representing the color of the ith
     * node in this graph (0-indexed). You are also given a 2D array edges where edges[j] = [aj, bj] indicates that
     * there is a directed edge from node aj to node bj.
     *
     * A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk such that there is a directed edge
     * from xi to xi+1 for every 1 <= i < k. The color value of the path is the number of nodes that are colored the
     * most frequently occurring color along that path.
     *
     * Return the largest color value of any valid path in the given graph, or -1 if the graph contains a cycle.
     *
     * Input: colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
     * Output: 3
     *
     * Constraints:
     *
     * n == colors.length
     * m == edges.length
     * 1 <= n <= 10^5
     * 0 <= m <= 10^5
     * colors consists of lowercase English letters.
     * 0 <= aj, bj < n
     * @param colors
     * @param edges
     * @return
     */
    // S1: BFS
    // time = O(V + E), space = O(V + E)
    List<Integer>[] graph;
    int[] d;
    String s;
    int n;
    public int largestPathValue(String colors, int[][] edges) {
        s = colors;
        n = colors.length();
        graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        d = new int[n];
        boolean[] st = new boolean[26];
        for (int[] x : edges) {
            int a = x[0], b = x[1];
            st[s.charAt(a) - 'a'] = true;
            st[s.charAt(b) - 'a'] = true;
            graph[a].add(b);
            d[b]++;
        }

        int res = 1;
        for (int i = 0; i < 26; i++) {
            if (!st[i]) continue;
            int ans = helper(i);
            if (ans == -1) return -1;
            res = Math.max(res, ans);
        }
        return res;
    }

    private int helper(int k) {
        int ans = 0;
        int[] dc = d.clone();
        Queue<Integer> queue = new LinkedList<>();
        // count[i]: how many color k at most are there along the path from a start to the i-th node
        // count[i] 表示的是针对当前的一种color "k"， 到达目前node i为止路径上存在的color "k"的最大值！
        int[] count = new int[n];
        for (int i = 0; i < n; i++) {
            if (dc[i] == 0) {
                queue.offer(i);
                count[i] += s.charAt(i) - 'a' == k ? 1 : 0;
                ans = Math.max(ans, count[i]);
            }
        }

        int total = 0;
        while (!queue.isEmpty()) {
            int t = queue.poll();
            total++;
            for (int j : graph[t]) {
                count[j] = Math.max(count[j], count[t] + (s.charAt(j) - 'a' == k ? 1 : 0));
                ans = Math.max(ans, count[j]);
                if (--dc[j] == 0) queue.offer(j);
            }
        }
        return total == n ? ans : -1;
    }

    // S2: 递推
    // time = O(26n) = O(n), space = O(n)
    public int largestPathValue2(String colors, int[][] edges) {
        int n = colors.length();
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        int[] d = new int[n];
        for (int[] x : edges) {
            int a = x[0], b = x[1];
            graph[a].add(b);
            d[b]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (d[i] == 0) queue.offer(i);
        }

        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            list.add(cur);

            for (int next : graph[cur]) {
                if (--d[next] == 0) queue.offer(next);
            }
        }

        if (list.size() != n) return -1;

        int[][] f = new int[n][26];
        int res = 0;
        for (int x : list) {
            int c = colors.charAt(x) - 'a';
            f[x][c] = Math.max(f[x][c], 1);
            for (int k = 0; k < 26; k++) {
                for (int y : graph[x]) {
                    int t = 0;
                    if (colors.charAt(y) - 'a' == k) t = 1;
                    f[y][k] = Math.max(f[y][k], f[x][k] + t);
                }
                res = Math.max(res, f[x][k]);
            }
        }
        return res;
    }
}
/**
 * 显然，为了最大化题目所求的最大元素频次，我们显然会希望这个路径尽量地长，这样才能经过更多的节点，增多每个元素的频次。
 * 所以我们必然会找那些入度为零的节点作为路径起点。
 * 多线汇聚处的频次表是没法决定的，只能分情况讨论
 * 将原来的问题拆成"26个子问题" -> BFS拓扑遍历，O(26n)，n ~ 10^5 还是可以接受的
 * 如果只考虑color 2，希望找到一条路径，这路径上的color 2频次数最多 -> 可以确定继承那个频次表，比如继承于A
 * => 不管图多么复杂，整张图都可以这么处理，每次只看一个color，只要记录一个关于颜色2的频次,重复26次就可以了
 * 每次找起点是入度为0的点 -> bfs的起点
 *
 * f(i) => f(j) = max{f(j), f(i) + t}
 */
