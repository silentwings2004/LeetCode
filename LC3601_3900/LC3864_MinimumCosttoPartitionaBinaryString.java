package LC3601_3900;

public class LC3864_MinimumCosttoPartitionaBinaryString {
    /**
     * You are given a binary string s and two integers encCost and flatCost.
     *
     * For each index i, s[i] = '1' indicates that the ith element is sensitive, and s[i] = '0' indicates that it is not.
     *
     * The string must be partitioned into segments. Initially, the entire string forms a single segment.
     *
     * For a segment of length L containing X sensitive elements:
     *
     * If X = 0, the cost is flatCost.
     * If X > 0, the cost is L * X * encCost.
     * If a segment has even length, you may split it into two contiguous segments of equal length and the cost of this
     * split is the sum of costs of the resulting segments.
     *
     * Return an integer denoting the minimum possible total cost over all valid partitions.
     *
     * Input: s = "1010", encCost = 2, flatCost = 1
     * Output: 6
     *
     * Input: s = "1010", encCost = 3, flatCost = 10
     * Output: 12
     *
     * Input: s = "00", encCost = 1, flatCost = 2
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists only of '0' and '1'.
     * 1 <= encCost, flatCost <= 10^5
     * @param s
     * @param encCost
     * @param flatCost
     * @return
     */
    // time = O(n), space = O(n)
    int[] pre;
    public long minCost(String s, int encCost, int flatCost) {
        int n = s.length();
        pre = new int[n + 1];
        for (int i = 1; i <= n; i++) pre[i] = pre[i - 1] + (s.charAt(i - 1) - '0');
        return dfs(0, n, encCost, flatCost);
    }

    private long dfs(int u, int len, int encCost, int flatCost) {
        long t = pre[u + len] - pre[u];
        long v = t == 0 ? flatCost : t * len * encCost;
        if (len % 2 != 0) return v;
        long nv = dfs(u, len / 2, encCost, flatCost) + dfs(u + len / 2, len / 2, encCost, flatCost);
        return Math.min(v, nv);
    }
}