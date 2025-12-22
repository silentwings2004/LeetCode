package LC3601_3900;

public class LC3784_MinimumDeletionCosttoMakeAllCharactersEqual {
    /**
     * You are given a string s of length n and an integer array cost of the same length, where cost[i] is the cost to
     * delete the ith character of s.
     *
     * You may delete any number of characters from s (possibly none), such that the resulting string is non-empty and
     * consists of equal characters.
     *
     * Return an integer denoting the minimum total deletion cost required.
     *
     * Input: s = "aabaac", cost = [1,2,3,4,1,10]
     * Output: 11
     *
     * Input: s = "abc", cost = [10,5,8]
     * Output: 13
     *
     * Input: s = "zzzzz", cost = [67,67,67,67,67]
     * Output: 0
     *
     * Constraints:
     *
     * n == s.length == cost.length
     * 1 <= n <= 10^5
     * 1 <= cost[i] <= 10^9
     * s consists of lowercase English letters.
     * @param s
     * @param cost
     * @return
     */
    // time = O(n), space = O(1)
    public long minCost(String s, int[] cost) {
        int n = s.length();
        long[] w = new long[26];
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            w[u] += cost[i];
        }
        long tot = 0, mx = 0;
        for (int i = 0; i < 26; i++) {
            mx = Math.max(mx, w[i]);
            tot += w[i];
        }
        return tot - mx;
    }
}