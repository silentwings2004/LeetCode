package LC301_600;

public class LC544_OutputContestMatches {
    /**
     * During the NBA playoffs, we always set the rather strong team to play with the rather weak team, like make the
     * rank 1 team play with the rank nth team, which is a good strategy to make the contest more interesting.
     *
     * Given n teams, return their final contest matches in the form of a string.
     *
     * The n teams are labeled from 1 to n, which represents their initial rank (i.e., Rank 1 is the strongest team and
     * Rank n is the weakest team).
     *
     * We will use parentheses '(', and ')' and commas ',' to represent the contest team pairing. We use the parentheses
     * for pairing and the commas for partition. During the pairing process in each round, you always need to follow
     * the strategy of making the rather strong one pair with the rather weak one.
     *
     * Input: n = 4
     * Output: "((1,4),(2,3))"
     *
     * Input: n = 8
     * Output: "(((1,8),(4,5)),((2,7),(3,6)))"
     *
     * Constraints:
     *
     * n == 2^x where x in in the range [1, 12].
     * @param n
     * @return
     */
    // time = O(nlogn), space = O(n)
    StringBuilder sb;
    public String findContestMatch(int n) {
        String[] strs = new String[n];
        for (int i = 0; i < n; i++) strs[i] = String.valueOf(i + 1);
        sb = new StringBuilder();
        dfs(strs);
        return sb.toString();
    }

    private void dfs(String[] strs) {
        if (strs.length == 2) {
            sb.append('(').append(strs[0]).append(',').append(strs[1]).append(')');
            return;
        }
        int n = strs.length;
        String[] arr = new String[n / 2];
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            arr[i] = "(" + strs[i] + "," + strs[j] + ")";
        }
        dfs(arr);
    }
}