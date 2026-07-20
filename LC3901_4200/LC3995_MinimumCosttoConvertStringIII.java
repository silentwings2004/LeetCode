package LC3901_4200;
import java.util.*;
public class LC3995_MinimumCosttoConvertStringIII {
    /**
     * You are given two strings, source and target.
     *
     * You are also given a 2D string array rules, where rules[i] = [patterni, replacementi], and an integer array costs,
     * where costs[i] is the base cost of applying rules[i]. Both arrays have the same length. Additionally, patterni and
     * replacementi have the same length.
     *
     * You may apply any rule any number of times. Each rule application works as follows:
     *
     * Choose an index l such that the range of positions from l to l + patterni.length - 1 exists in the current string
     * and none of these positions has been used in a previous rule application.
     * For each index j, the character patterni[j] must either be equal to the current character at position l + j, or
     * be '*'.
     * Replace the characters in this range with replacementi. The replacement is used exactly as given and does not
     * contain wildcards.
     * The cost of this rule application is costs[i] plus the number of '*' characters in patterni.
     * Once a character position has been used in a rule application, it cannot be used in any later rule application.
     * Since every patterni and replacementi have the same length, character positions are preserved after every rule
     * application.
     *
     * Return the minimum total cost required to transform source into target. If it is impossible, return -1.
     *
     * Input: source = "hello", target = "world", rules = [["he","wo"],["llo","rld"]], costs = [3,4]
     * Output: 7
     *
     * Input: source = "cat", target = "dog", rules = [["c*t","dog"]], costs = [2]
     * Output: 3
     *
     * Input: source = "test", target = "next", rules = [["*e*t","next"]], costs = [4]
     * Output: 6
     *
     * Input: source = "ab", target = "bc", rules = [["a*","bd"]], costs = [9]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= source.length == target.length <= 5000
     * source and target consist of lowercase English letters.
     * 1 <= rules.length == costs.length <= 200
     * rules[i] = [patterni, replacementi]
     * 1 <= patterni.length == replacementi.length <= 20
     * patterni contains at least one lowercase English letter and at most 5 '*' characters.
     * replacementi contains only lowercase English letters.
     * 1 <= costs[i] <= 1000
     * @param source
     * @param target
     * @param rules
     * @param costs
     * @return
     */
    // time = O(n * L), space = O(n), L: sum(pattern)
    final int inf = 0x3f3f3f3f;
    String s, t;
    List<List<String>> rules;
    int[] costs, memo;
    public int minCost(String source, String target, List<List<String>> rules, int[] costs) {
        this.s = source;
        this.t = target;
        this.rules = rules;
        this.costs = costs;
        int m = rules.size();
        for (int i = 0; i < m; i++) {
            int cnt = 0;
            String p = rules.get(i).get(0);
            for (int j = 0; j < p.length(); j++) {
                if (p.charAt(j) == '*') cnt++;
            }
            costs[i] += cnt;
        }

        int n = source.length();
        memo = new int[n];
        Arrays.fill(memo, -1);

        int res = dfs(n - 1);
        return res < inf ? res : -1;
    }

    private int dfs(int u) {
        if (u < 0) return 0;
        if (memo[u] != -1) return memo[u];

        int res = inf;

        // 不替换下标 u
        if (s.charAt(u) == t.charAt(u)) res = dfs(u - 1);

        // 替换子串 [u - m + 1, u]
        for (int i = 0; i < rules.size(); i++) {
            String r = rules.get(i).get(1);
            int l = u - r.length() + 1; // 子串左端点
            if (l >= 0 && r.equals(t.substring(l, u + 1)) && isMatch(rules.get(i).get(0), s, l)) {
                res = Math.min(res, dfs(l - 1) + costs[i]);
            }
        }
        return memo[u] = res;
    }

    private boolean isMatch(String p, String s, int l) {
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (c != '*' && c != s.charAt(l + i)) return false;
        }
        return true;
    }
}