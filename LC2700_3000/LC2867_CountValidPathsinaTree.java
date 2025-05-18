package LC2700_3000;
import java.util.*;
public class LC2867_CountValidPathsinaTree {
    /**
     * There is an undirected tree with n nodes labeled from 1 to n. You are given the integer n and a 2D integer array
     * edges of length n - 1, where edges[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the
     * tree.
     *
     * Return the number of valid paths in the tree.
     *
     * A path (a, b) is valid if there exists exactly one prime number among the node labels in the path from a to b.
     *
     * Note that:
     *
     * The path (a, b) is a sequence of distinct nodes starting with node a and ending with node b such that every two
     * adjacent nodes in the sequence share an edge in the tree.
     * Path (a, b) and path (b, a) are considered the same and counted only once.
     *
     * Input: n = 5, edges = [[1,2],[1,3],[2,4],[2,5]]
     * Output: 4
     *
     * Input: n = 6, edges = [[1,2],[1,3],[2,4],[3,5],[3,6]]
     * Output: 6
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * edges.length == n - 1
     * edges[i].length == 2
     * 1 <= ui, vi <= n
     * The input is generated such that edges represent a valid tree.
     * @param n
     * @param edges
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    final int N = 100010, M = N * 2;
    int[] h, e, ne;
    int[] primes;
    boolean[] st;
    int idx, cnt;
    HashSet<Integer> set;
    public long countPaths(int n, int[][] edges) {
        h = new int[N];
        e = new int[M];
        ne = new int[M];
        primes = new int[N];
        st = new boolean[N];
        Arrays.fill(h, -1);
        idx = 0;
        cnt = 0;
        set = new HashSet<>();

        for (int[] e : edges) {
            int a = e[0], b = e[1];
            add(a, b);
            add(b, a);
        }

        get_primes(n);

        long[] f = new long[n + 1];
        Arrays.fill(f, -1);
        long res = 0;
        for (int u = 0; u < cnt; u++) {
            int t = primes[u];
            long s = 0;
            for (int i = h[t]; i != -1; i = ne[i]) {
                int j = e[i];
                if (f[j] == -1) f[j] = dfs(j, t);
                res += s * f[j];
                s += f[j];
            }
            res += s;
        }
        return res;
    }

    private long dfs(int u, int fa) {
        if (set.contains(u)) return 0;
        long t = 1;
        for (int i = h[u]; i != -1; i = ne[i]) {
            int j = e[i];
            if (j == fa) continue;
            t += dfs(j, u);
        }
        return t;
    }

    private void get_primes(int n) {
        for (int i = 2; i <= n; i++) {
            if (!st[i]) {
                primes[cnt++] = i;
                set.add(i);
            }
            for (int j = 0; i * primes[j] <= n; j++) {
                st[i * primes[j]] = true;
                if (i % primes[j] == 0) break;
            }
        }
    }

    private void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    // S1.2
    class Solution {
        // time = O(n), space = O(n)
        final int N = 100010, M = N * 2;
        int[] h, e, ne;
        int[] primes;
        boolean[] st;
        int idx, cnt;
        HashSet<Integer> set;
        long[][] f;
        long res;
        public long countPaths(int n, int[][] edges) {
            h = new int[N];
            e = new int[M];
            ne = new int[M];
            primes = new int[N];
            st = new boolean[N];
            Arrays.fill(h, -1);
            idx = 0;
            cnt = 0;
            set = new HashSet<>();

            for (int[] e : edges) {
                int a = e[0], b = e[1];
                add(a, b);
                add(b, a);
            }

            get_primes(n);

            f = new long[n + 1][2];
            dfs(1, -1);
            return res;
        }

        private void dfs(int u, int fa) {
            if (set.contains(u)) f[u][1]++;
            else f[u][0]++;
            for (int i = h[u]; i != -1; i = ne[i]) {
                int j = e[i];
                if (j == fa) continue;
                dfs(j, u);
                res += f[j][0] * f[u][1] + f[j][1] * f[u][0];
                if (set.contains(u)) f[u][1] += f[j][0];
                else {
                    f[u][0] += f[j][0];
                    f[u][1] += f[j][1];
                }
            }
        }

        private void get_primes(int n) {
            for (int i = 2; i <= n; i++) {
                if (!st[i]) {
                    primes[cnt++] = i;
                    set.add(i);
                }
                for (int j = 0; i * primes[j] <= n; j++) {
                    st[i * primes[j]] = true;
                    if (i % primes[j] == 0) break;
                }
            }
        }

        private void add(int a, int b) {
            e[idx] = b;
            ne[idx] = h[a];
            h[a] = idx++;
        }
    }
}
/**
 * 1. 枚举？选一个点当做根
 * 2. 如果从非质数开始枚举的话，必须经过一个质数，比较麻烦
 * 3. 能否从质数开始枚举？
 * 避免相同路径算2次：只和左边的点乘
 * count path by primes
 * 只会看合数，路径可能会很长，取任意一个起点都可以
 * sum = 0;
 * for (int i = 1; i <= M; i++) {
 *     sum += m_i * (total - m_i);
 * }
 * res += sum / 2 + total (以 p 为起点)
 * 把所有合数的节点并起来
 */