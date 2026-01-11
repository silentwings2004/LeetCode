package LC3601_3900;

public class LC3800_MinimumCosttoMakeTwoBinaryStringsEqual {
    /**
     * You are given two binary strings s and t, both of length n, and three positive integers flipCost, swapCost, and
     * crossCost.
     *
     * You are allowed to apply the following operations any number of times (in any order) to the strings s and t:
     *
     * Choose any index i and flip s[i] or t[i] (change '0' to '1' or '1' to '0'). The cost of this operation is
     * flipCost.
     * Choose two distinct indices i and j, and swap either s[i] and s[j] or t[i] and t[j]. The cost of this operation
     * is swapCost.
     * Choose an index i and swap s[i] with t[i]. The cost of this operation is crossCost.
     * Return an integer denoting the minimum total cost needed to make the strings s and t equal.
     *
     * Input: s = "01000", t = "10111", flipCost = 10, swapCost = 2, crossCost = 2
     * Output: 16
     *
     * Input: s = "001", t = "110", flipCost = 2, swapCost = 100, crossCost = 100
     * Output: 6
     *
     * Input: s = "1010", t = "1010", flipCost = 5, swapCost = 5, crossCost = 5
     * Output: 0
     *
     * Constraints:
     *
     * n == s.length == t.length
     * 1 <= n <= 10^5
     * 1 <= flipCost, swapCost, crossCost <= 10^9
     * s and t consist only of the characters '0' and '1'.
     * @param s
     * @param t
     * @param flipCost
     * @param swapCost
     * @param crossCost
     * @return
     */
    // S1
    // time = O(n), space = O(1)
    public long minimumCost(String s, String t, int flipCost, int swapCost, int crossCost) {
        int n = s.length(), cnt01 = 0, cnt10 = 0;
        for (int i = 0; i < n; i++) {
            char c1 = s.charAt(i), c2 = t.charAt(i);
            if (c1 != c2) {
                if (c1 == '0') cnt01++;
                else cnt10++;
            }
        }
        int m = Math.min(cnt01, cnt10);
        long res = Long.MAX_VALUE;
        for (int i = 0; i <= m; i++) {
            long cost = 1L * i * swapCost;
            int r = cnt01 + cnt10 - i * 2;
            long cost2 = Math.min(2L * flipCost, 1L * crossCost + swapCost); // 修复2个同类型的最小成本
            cost += (r / 2) * cost2;
            if (r % 2 == 1) cost += flipCost;
            res = Math.min(res, cost);
        }
        return res;
    }

    // S2
    // time = O(n), space = O(1)
    public long minimumCost2(String s, String t, int flipCost, int swapCost, int crossCost) {
        int[][] cnt = new int[2][2];
        int n = s.length();
        for (int i = 0; i < n; i++) cnt[s.charAt(i) & 1][t.charAt(i) & 1]++;
        int a = cnt[0][1], b = cnt[1][0];
        if (a > b) {
            int x = a;
            a = b;
            b = x;
        }

        long v1 = 1L * (a + b) * flipCost;
        long v2 = 1L * a * swapCost + 1L * (b - a) * flipCost;
        int avg = (a + b) / 2;
        long v3 = 1L * (avg - a) * crossCost + 1L * avg * swapCost + 1L * (a + b) % 2 * flipCost;
        return Math.min(v1, Math.min(v2, v3));
    }
}
/**
 * case 1: flip 代价很小，只做翻转
 * flip 代价很大，少做翻转，多做交换
 * case 2: 上下交换代价很大：不做上下交换
 * case 3: 做上下交换
 * 操作次数
 */