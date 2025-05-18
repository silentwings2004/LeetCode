package LC2401_2700;

public class LC2698_FindthePunishmentNumberofanInteger {
    /**
     * Given a positive integer n, return the punishment number of n.
     *
     * The punishment number of n is defined as the sum of the squares of all integers i such that:
     *
     * 1 <= i <= n
     * The decimal representation of i * i can be partitioned into contiguous substrings such that the sum of the
     * integer values of these substrings equals i.
     *
     * Input: n = 10
     * Output: 182
     *
     * Input: n = 37
     * Output: 1478
     *
     * Constraints:
     *
     * 1 <= n <= 1000
     * @param n
     * @return
     */
    // time = O(2^6 * n), space = O(2^6)
    public int punishmentNumber(int n) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (check(i * i, i)) res += i * i;
        }
        return res;
    }

    private boolean check(int x, int t) {
        String s = String.valueOf(x);
        return dfs(s, 0, 0, t);
    }

    private boolean dfs(String s, int u, int sum, int t) {
        if (u == s.length()) {
            if (sum == t) return true;
            return false;
        }

        for (int i = u; i < s.length(); i++) {
            int x = Integer.parseInt(s.substring(u, i + 1));
            if (dfs(s, i + 1, sum + x, t)) return true;
        }
        return false;
    }
}