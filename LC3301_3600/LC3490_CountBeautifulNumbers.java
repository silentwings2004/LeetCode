package LC3301_3600;
import java.util.*;
public class LC3490_CountBeautifulNumbers {
    /**
     * You are given two positive integers, l and r. A positive integer is called beautiful if the product of its
     * digits is divisible by the sum of its digits.
     *
     * Return the count of beautiful numbers between l and r, inclusive.
     *
     * Input: l = 10, r = 20
     * Output: 2
     *
     * Input: l = 1, r = 15
     * Output: 10
     *
     * Constraints:
     *
     * 1 <= l <= r < 10^9
     * @param l
     * @param r
     * @return
     */
    // time = O(n), space = O(n)
    HashMap<String, Integer> f;
    public int beautifulNumbers(int l, int r) {
        return cal(String.valueOf(r)) - cal(String.valueOf(l - 1));
    }

    private int cal(String s) {
        f = new HashMap<>();
        return dfs(s, 0, true, false, 0, 1);
    }

    private int dfs(String s, int u, boolean isLimit, boolean isNum, int sum, int prod) {
        if (u == s.length()) {
            if (isNum) return (prod == 0 || (sum != 0 && prod % sum == 0)) ? 1 : 0;
            return 0;
        }
        String h = u + "#" + (isLimit ? 1 : 0) + "#" + (isNum ? 1 : 0) + "#" + sum + "#" + prod;
        if (f.containsKey(h)) return f.get(h);

        int res = 0;
        if (!isNum) res += dfs(s, u + 1, false, false, 0, 1);
        int down = isNum ? 0 : 1;
        int up = isLimit ? s.charAt(u) - '0' : 9;
        for (int i = down; i <= up; i++) {
            res += dfs(s, u + 1, isLimit && i == up, true, sum + i, prod * i);
        }
        f.put(h, res);
        return res;
    }
}