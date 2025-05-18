package LC3001_3300;
import java.util.*;
public class LC3211_GenerateBinaryStringsWithoutAdjacentZeros {
    /**
     * You are given a positive integer n.
     *
     * A binary string x is valid if all substrings of x of length 2 contain at least one "1".
     *
     * Return all valid strings with length n, in any order.
     *
     * Input: n = 3
     * Output: ["010","011","101","110","111"]
     *
     * Input: n = 1
     * Output: ["0","1"]
     *
     * Constraints:
     *
     * 1 <= n <= 18
     * @param n
     * @return
     */
    // S1: dfs
    // time = O(2^n), space = O(n)
    List<String> res;
    int n;
    public List<String> validStrings(int n) {
        this.n = n;
        res = new ArrayList<>();
        dfs(0, new StringBuilder());
        return res;
    }

    private void dfs(int u, StringBuilder sb) {
        if (u == n) res.add(sb.toString());
        else {
            if (u == 0 || sb.charAt(u - 1) == '1') {
                sb.append(0);
                dfs(u + 1, sb);
                sb.setLength(sb.length() - 1);
                sb.append(1);
                dfs(u + 1, sb);
                sb.setLength(sb.length() - 1);
            } else {
                sb.append(1);
                dfs(u + 1, sb);
                sb.setLength(sb.length() - 1);
            }
        }
    }

    // S2
    // time = O(2^n), space = O(1)
    public List<String> validStrings2(int n) {
         List<String> res = new ArrayList<>();
         int mask = (1 << n) - 1;
         for (int i = 0; i < 1 << n; i++) {
             int x = mask ^ i;
             if (((x >> 1) & x) == 0) {
                 res.add(Integer.toBinaryString((1 << n) | i).substring(1));
             }
         }
         return res;
    }
}
/**
 * [0, 2^n - 1]
 */