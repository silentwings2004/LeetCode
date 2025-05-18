package LC2700_3000;
import java.util.*;
public class LC2830_MaximizetheProfitastheSalesman {
    /**
     * You are given an integer n representing the number of houses on a number line, numbered from 0 to n - 1.
     *
     * Additionally, you are given a 2D integer array offers where offers[i] = [starti, endi, goldi], indicating that
     * ith buyer wants to buy all the houses from starti to endi for goldi amount of gold.
     *
     * As a salesman, your goal is to maximize your earnings by strategically selecting and selling houses to buyers.
     *
     * Return the maximum amount of gold you can earn.
     *
     * Note that different buyers can't buy the same house, and some houses may remain unsold.
     *
     * Input: n = 5, offers = [[0,0,1],[0,2,2],[1,3,2]]
     * Output: 3
     *
     * Input: n = 5, offers = [[0,0,1],[0,2,10],[1,3,2]]
     * Output: 10
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * 1 <= offers.length <= 10^5
     * offers[i].length == 3
     * 0 <= starti <= endi <= n - 1
     * 1 <= goldi <= 10^3
     * @param n
     * @param offers
     * @return
     */
    // S1
    // time = O(mlogm + n), space = O(n)
    public int maximizeTheProfit(int n, List<List<Integer>> offers) {
        Collections.sort(offers, (o1, o2) -> o1.get(0) - o2.get(0));
        int m = offers.size(), j = 0;
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            f[i] = Math.max(f[i], f[i - 1]);
            while (j < m && offers.get(j).get(0) == i - 1) {
                int t = offers.get(j).get(1), c = offers.get(j).get(2);
                f[t + 1] = Math.max(f[t + 1], f[i - 1] + c);
                j++;
            }
        }
        return f[n];
    }

    // S2
    // time = O(n + m), space = O(n + m)
    public int maximizeTheProfit2(int n, List<List<Integer>> offers) {
        int m = offers.size();
        int[] f = new int[n + 1];
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) g[offers.get(i).get(1)].add(i);

        for (int i = 1; i <= n; i++) {
            f[i] = f[i - 1];
            for (int j : g[i - 1]) {
                f[i] = Math.max(f[i], f[offers.get(j).get(0)] + offers.get(j).get(2));
            }
        }
        return f[n];
    }
}