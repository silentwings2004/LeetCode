package LC3301_3600;
import java.util.*;
public class LC3535_UnitConversionII {
    /**
     * There are n types of units indexed from 0 to n - 1.
     *
     * You are given a 2D integer array conversions of length n - 1, where conversions[i] = [sourceUniti, targetUniti,
     * conversionFactori]. This indicates that a single unit of type sourceUniti is equivalent to conversionFactori
     * units of type targetUniti.
     *
     * You are also given a 2D integer array queries of length q, where queries[i] = [unitAi, unitBi].
     *
     * Return an array answer of length q where answer[i] is the number of units of type unitBi equivalent to 1 unit of
     * type unitAi. Return each answer[i] as pq-1 modulo 109 + 7, where q-1 represents the multiplicative inverse of q
     * modulo 10^9 + 7.
     *
     * Input: conversions = [[0,1,2],[0,2,6]], queries = [[1,2],[1,0]]
     *
     * Output: [3,500000004]
     *
     * Input: conversions = [[0,1,2],[0,2,6],[0,3,8],[2,4,2],[2,5,4],[3,6,3]], queries = [[1,2],[0,4],[6,5],[4,6],[6,1]]
     *
     * Output: [3,12,1,2,83333334]
     *
     * Constraints:
     *
     * 2 <= n <= 10^5
     * conversions.length == n - 1
     * 0 <= sourceUniti, targetUniti < n
     * 1 <= conversionFactori <= 10^9
     * 1 <= q <= 10^5
     * queries.length == q
     * 0 <= unitAi, unitBi < n
     * It is guaranteed that unit 0 can be uniquely converted into any other unit through a combination of forward or backward conversions.
     * @param conversions
     * @param queries
     * @return
     */
    // time = O(n + m), space = O(n)
    long mod = (long)(1e9 + 7);
    List<long[]>[] adj;
    long[] d, ind;
    boolean[] st;
    public int[] queryConversions(int[][] conversions, int[][] queries) {
        int n = conversions.length + 1;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] x : conversions) {
            int a = x[0], b = x[1], c = x[2];
            long inv = qmi(c, mod - 2);
            adj[a].add(new long[]{b, c, inv});
            adj[b].add(new long[]{a, inv, c});
        }

        d = new long[n];
        ind = new long[n];
        st = new boolean[n];
        d[0] = 1;
        ind[0] = 1;
        dfs(0);

        int m = queries.length;
        int[] res = new int[m];
        for (int i = 0; i < m; i++) {
            int a = queries[i][0], b = queries[i][1];
            res[i] = (int)(d[b] * ind[a] % mod);
        }
        return res;
    }

    private void dfs(int u) {
        st[u] = true;
        for (long[] x : adj[u]) {
            int v = (int)x[0];
            if (st[v]) continue;
            long a = x[1], b = x[2];
            d[v] = d[u] * a % mod;
            ind[v] = ind[u] * b % mod;
            dfs(v);
        }
    }

    private long qmi(long a, long k) {
        long res = 1;
        while (k > 0) {
            if ((k & 1) == 1) res = res * a % mod;
            a = a * a % mod;
            k >>= 1;
        }
        return res;
    }
}
