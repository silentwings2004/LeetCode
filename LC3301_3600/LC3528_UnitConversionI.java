package LC3301_3600;
import java.util.*;
public class LC3528_UnitConversionI {
    /**
     * There are n types of units indexed from 0 to n - 1. You are given a 2D integer array conversions of length n - 1,
     * where conversions[i] = [sourceUniti, targetUniti, conversionFactori]. This indicates that a single unit of type
     * sourceUniti is equivalent to conversionFactori units of type targetUniti.
     *
     * Return an array baseUnitConversion of length n, where baseUnitConversion[i] is the number of units of type i
     * equivalent to a single unit of type 0. Since the answer may be large, return each baseUnitConversion[i]
     * modulo 10^9 + 7.
     *
     * Input: conversions = [[0,1,2],[1,2,3]]
     * Output: [1,2,6]
     *
     * Input: conversions = [[0,1,2],[0,2,3],[1,3,4],[1,4,5],[2,5,2],[4,6,3],[5,7,4]]
     *
     * Output: [1,2,3,8,10,6,30,24]
     * Constraints:
     *
     * 2 <= n <= 10^5
     * conversions.length == n - 1
     * 0 <= sourceUniti, targetUniti < n
     * 1 <= conversionFactori <= 10^9
     * It is guaranteed that unit 0 can be converted into any other unit through a unique combination of conversions
     * without using any conversions in the opposite direction.
     * @param conversions
     * @return
     */
    // time = O(n), space = O(n)
    long mod = (long)(1e9 + 7);
    List<int[]>[] adj;
    int[] res;
    public int[] baseUnitConversions(int[][] conversions) {
        int n = conversions.length + 1;
        adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] x : conversions) {
            int a = x[0], b = x[1], c = x[2];
            adj[a].add(new int[]{b, c});
        }
        res = new int[n];
        dfs(0, 1);
        return res;
    }

    private void dfs(int u, int t) {
        res[u] = t;
        for (int[] x : adj[u]) {
            int v = x[0], w = x[1];
            if (res[v] != 0) continue;
            dfs(v, (int)(1L * t * w % mod));
        }
    }
}
/**
 * 注意题目的这句话：保证单位 0 可以通过唯一的转换路径（不需要反向转换）转换为任何其他单位.
 * 这意味着输入的是 n 个点和 n−1 条边的连通图，说明输入的是一棵树
 * 建树。然后从 0 开始 DFS 这棵树，同时把从 0 到 i 的边权乘起来，即为 ans[i]。
 * 为了计算乘积，递归的过程中额外传入参数 mul. 注意取模。
 */