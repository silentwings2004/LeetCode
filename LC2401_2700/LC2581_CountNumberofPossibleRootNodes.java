package LC2401_2700;
import java.util.*;
public class LC2581_CountNumberofPossibleRootNodes {
    /**
     * Alice has an undirected tree with n nodes labeled from 0 to n - 1. The tree is represented as a 2D integer array
     * edges of length n - 1 where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the
     * tree.
     *
     * Alice wants Bob to find the root of the tree. She allows Bob to make several guesses about her tree. In one
     * guess, he does the following:
     *
     * Chooses two distinct integers u and v such that there exists an edge [u, v] in the tree.
     * He tells Alice that u is the parent of v in the tree.
     * Bob's guesses are represented by a 2D integer array guesses where guesses[j] = [uj, vj] indicates Bob guessed uj
     * to be the parent of vj.
     *
     * Alice being lazy, does not reply to each of Bob's guesses, but just says that at least k of his guesses are true.
     *
     * Given the 2D integer arrays edges, guesses and the integer k, return the number of possible nodes that can be the
     * root of Alice's tree. If there is no such tree, return 0.
     *
     * Input: edges = [[0,1],[1,2],[1,3],[4,2]], guesses = [[1,3],[0,1],[1,0],[2,4]], k = 3
     * Output: 3
     *
     * Input: edges = [[0,1],[1,2],[2,3],[3,4]], guesses = [[1,0],[3,4],[2,1],[3,2]], k = 1
     * Output: 5
     *
     * Constraints:
     *
     * edges.length == n - 1
     * 2 <= n <= 10^5
     * 1 <= guesses.length <= 105
     * 0 <= ai, bi, uj, vj <= n - 1
     * ai != bi
     * uj != vj
     * edges represents a valid tree.
     * guesses[j] is an edge of the tree.
     * guesses is unique.
     * 0 <= k <= guesses.length
     * @param edges
     * @param guesses
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    final int N = 100010, M = N * 2;
    int n, k, idx, cnt, res;
    int[] h, e, ne;
    HashSet<String> set;
    public int rootCount(int[][] edges, int[][] guesses, int k) {
        h = new int[N];
        e = new int[M];
        ne = new int[M];
        set = new HashSet<>();
        Arrays.fill(h, -1);
        idx = 0;

        n = edges.length + 1;
        this.k = k;
        for (int[] x : edges) {
            int a = x[0], b = x[1];
            add(a, b);
            add(b, a);
        }

        for (int[] x : guesses) {
            int a = x[0], b = x[1];
            set.add(a + "#" + b);
        }

        dfs1(0, -1);
        dfs2(0, -1, cnt);
        return res;
    }

    private void dfs1(int u, int fa) {
        for (int i = h[u]; i != -1; i = ne[i]) {
            int j = e[i];
            if (j == fa) continue;
            if (set.contains(u + "#" + j)) cnt++;
            dfs1(j, u);
        }
    }

    private void dfs2(int u, int fa, int cnt) {
        if (cnt >= k) res++;
        for (int i = h[u]; i != -1; i = ne[i]) {
            int j = e[i];
            if (j == fa) continue;
            int nct = cnt;
            if (set.contains(u + "#" + j)) nct--;
            if (set.contains(j + "#" + u)) nct++;
            dfs2(j, u, nct);
        }
    }

    private void add(int a, int b) {
        e[idx] = b;
        ne[idx] = h[a];
        h[a] = idx++;
    }

    // S2
    // time = O(n), space = O(n)
    class Solution {
        List<Integer>[] graph;
        HashSet<Integer>[] guess;
        int k, res;
        public int rootCount(int[][] edges, int[][] guesses, int k) {
            int n = edges.length + 1;
            graph = new List[n];
            guess = new HashSet[n];
            this.k = k;
            res = 0;
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
                guess[i] = new HashSet<>();
            }
            for (int[] x : edges) {
                int a = x[0], b = x[1];
                graph[a].add(b);
                graph[b].add(a);
            }
            for (int[] x : guesses) {
                int a = x[0], b = x[1];
                guess[a].add(b);
            }

            int cnt = dfs(0, -1);
            dfs2(0, -1, cnt);
            return res;
        }

        private int dfs(int u, int fa) {
            int cnt = 0;
            for (int next : graph[u]) {
                if (next == fa) continue;
                cnt += dfs(next, u);
                if (guess[u].contains(next)) cnt++;
            }
            return cnt;
        }

        private void dfs2(int u, int fa, int cnt) {
            if (cnt >= k) res++;
            for (int next : graph[u]) {
                if (next == fa) continue;
                int tmp = cnt;
                if (guess[u].contains(next)) tmp--;
                if (guess[next].contains(u)) tmp++;
                dfs2(next, u, tmp);
            }
        }
    }
}
/**
 * 每个节点作为根来考察下`
 * 移根，移动相邻结点
 * 假设当前节点A作为根时，答案为a，那么以A的某个邻接节点B未做根时，答案能否快速从a转化而来呢？
 * 假设当前节点A作为根时，它对应的guesses里面有x个顺序的猜想（猜对了），y个逆序的猜想（猜错了）。
 * 那么我们转而考虑以B为根时，顺逆序唯一改变的边其实就只有AB之间的路径。
 * 所以如果AB边原本是一个顺序的猜想，那么此刻就会变成逆序；
 * 如果AB边原本是一个逆序的猜想，那么此刻就会变成顺序。
 * 所以本题的做法就是，先以任意节点（比如说0）为根，一遍dfs计算有多少正确的guess，假设叫做count。
 * 然后递归处理它相邻的节点作为根的情况，只需要考察这条相邻边的正逆序变化改变了多少猜想，更新count即可。
 *
 * 1. 把以0为根的猜对次数算出来 cnt0 dfs
 * 2. 再跑一次dfs,
 * cnt
 * 0 -> 1
 * cnt - (0,1) in s + (1,0) in s
 */