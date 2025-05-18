package LC3301_3600;
import java.util.*;
public class LC3310_RemoveMethodsFromProject {
    /**
     * You are maintaining a project that has n methods numbered from 0 to n - 1.
     *
     * You are given two integers n and k, and a 2D integer array invocations, where invocations[i] = [ai, bi] indicates
     * that method ai invokes method bi.
     *
     * There is a known bug in method k. Method k, along with any method invoked by it, either directly or indirectly,
     * are considered suspicious and we aim to remove them.
     *
     * A group of methods can only be removed if no method outside the group invokes any methods within it.
     *
     * Return an array containing all the remaining methods after removing all the suspicious methods. You may return
     * the answer in any order. If it is not possible to remove all the suspicious methods, none should be removed.
     *
     * Input: n = 4, k = 1, invocations = [[1,2],[0,1],[3,2]]
     *
     * Output: [0,1,2,3]
     *
     * Input: n = 5, k = 0, invocations = [[1,2],[0,2],[0,1],[3,4]]
     *
     * Output: [3,4]
     *
     * Input: n = 3, k = 2, invocations = [[1,2],[0,1],[2,0]]
     *
     * Output: []
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * 0 <= k <= n - 1
     * 0 <= invocations.length <= 2 * 10^5
     * invocations[i] == [ai, bi]
     * 0 <= ai, bi <= n - 1
     * ai != bi
     * invocations[i] != invocations[j]
     * @param n
     * @param k
     * @param invocations
     * @return
     */
    // time = O(n + m), space = O(n)
    boolean[] st;
    List<Integer>[] adj;
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        st = new boolean[n];
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] x : invocations) {
            int a = x[0], b = x[1];
            adj[a].add(b);
        }
        st[k] = true;
        dfs(k);

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) res.add(i);
        for (int[] x : invocations) {
            int a = x[0], b = x[1];
            if (st[a] != st[b]) return res;
        }
        res.clear();
        for (int i = 0; i < n; i++) {
            if (!st[i]) res.add(i);
        }
        return res;
    }

    private void dfs(int u) {
        for (int v : adj[u]) {
            if (st[v]) continue;
            st[v] = true;
            dfs(v);
        }
    }
}