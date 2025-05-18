package LC3301_3600;
import java.util.*;
public class LC3387_MaximizeAmountAfterTwoDaysofConversions {
    /**
     * You are given a string initialCurrency, and you start with 1.0 of initialCurrency.
     *
     * You are also given four arrays with currency pairs (strings) and rates (real numbers):
     *
     * pairs1[i] = [startCurrencyi, targetCurrencyi] denotes that you can convert from startCurrencyi to
     * targetCurrencyi at a rate of rates1[i] on day 1.
     * pairs2[i] = [startCurrencyi, targetCurrencyi] denotes that you can convert from startCurrencyi to targetCurrencyi
     * at a rate of rates2[i] on day 2.
     * Also, each targetCurrency can be converted back to its corresponding startCurrency at a rate of 1 / rate.
     * You can perform any number of conversions, including zero, using rates1 on day 1, followed by any number of
     * additional conversions, including zero, using rates2 on day 2.
     *
     * Return the maximum amount of initialCurrency you can have after performing any number of conversions on both
     * days in order.
     *
     * Note: Conversion rates are valid, and there will be no contradictions in the rates for either day. The rates for
     * the days are independent of each other.
     *
     * Input: initialCurrency = "EUR", pairs1 = [["EUR","USD"],["USD","JPY"]], rates1 = [2.0,3.0], pairs2 =
     * [["JPY","USD"],["USD","CHF"],["CHF","EUR"]], rates2 = [4.0,5.0,6.0]
     *
     * Output: 720.00000
     *
     * Input: initialCurrency = "NGN", pairs1 = [["NGN","EUR"]], rates1 = [9.0], pairs2 = [["NGN","EUR"]], rates2 = [6.0]
     *
     * Output: 1.50000
     *
     * Example 3:
     *
     * Input: initialCurrency = "USD", pairs1 = [["USD","EUR"]], rates1 = [1.0], pairs2 = [["EUR","JPY"]], rates2 = [10.0]
     *
     * Output: 1.00000
     *
     * Constraints:
     *
     * 1 <= initialCurrency.length <= 3
     * initialCurrency consists only of uppercase English letters.
     * 1 <= n == pairs1.length <= 10
     * 1 <= m == pairs2.length <= 10
     * pairs1[i] == [startCurrencyi, targetCurrencyi]
     * pairs2[i] == [startCurrencyi, targetCurrencyi]
     * 1 <= startCurrencyi.length, targetCurrencyi.length <= 3
     * startCurrencyi and targetCurrencyi consist only of uppercase English letters.
     * rates1.length == n
     * rates2.length == m
     * 1.0 <= rates1[i], rates2[i] <= 10.0
     * The input is generated such that there are no contradictions or cycles in the conversion graphs for either day.
     * @param initialCurrency
     * @param pairs1
     * @param rates1
     * @param pairs2
     * @param rates2
     * @return
     */
    // time = O(n * m), space = O(n + m)
    public double maxAmount(String initialCurrency, List<List<String>> pairs1, double[] rates1, List<List<String>> pairs2, double[] rates2) {
        HashMap<String, List<Pair>> adj = new HashMap<>();
        int n = pairs1.size();
        for (int i = 0; i < n; i++) {
            String a = pairs1.get(i).get(0), b = pairs1.get(i).get(1);
            double c = rates1[i];
            adj.putIfAbsent(a, new ArrayList<>());
            adj.putIfAbsent(b, new ArrayList<>());
            adj.get(a).add(new Pair(b, c));
            adj.get(b).add(new Pair(a, 1 / c));
        }

        HashMap<String, Double> mp1 = new HashMap<>();
        dfs(adj, initialCurrency, "", 1, mp1);

        adj.clear();
        n = pairs2.size();
        for (int i = 0; i < n; i++) {
            String a = pairs2.get(i).get(0), b = pairs2.get(i).get(1);
            double c = rates2[i];
            adj.putIfAbsent(a, new ArrayList<>());
            adj.putIfAbsent(b, new ArrayList<>());
            adj.get(a).add(new Pair(b, c));
            adj.get(b).add(new Pair(a, 1 / c));
        }
        HashMap<String, Double> mp2 = new HashMap<>();
        dfs(adj, initialCurrency, "", 1, mp2);

        double res = 1;
        for (String k : mp1.keySet()) {
            if (mp2.containsKey(k)) {
                res = Math.max(res, mp1.get(k) / mp2.get(k));
            }
        }
        return res;
    }

    private void dfs(HashMap<String, List<Pair>> adj, String u, String fa, double d, HashMap<String, Double> mp) {
        for (Pair p : adj.getOrDefault(u, new ArrayList<>())) {
            String v = p.s;
            double t = p.v;
            mp.put(u, d);
            if (v.equals(fa)) continue;
            dfs(adj, v, u, d * t, mp);
        }
    }

    class Pair {
        String s;
        double v;
        public Pair(String s, double v) {
            this.s = s;
            this.v = v;
        }
    }
}