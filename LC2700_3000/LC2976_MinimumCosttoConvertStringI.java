package LC2700_3000;
import java.util.*;
public class LC2976_MinimumCosttoConvertStringI {
    /**
     * You are given two 0-indexed strings source and target, both of length n and consisting of lowercase English
     * letters. You are also given two 0-indexed character arrays original and changed, and an integer array cost, where
     * cost[i] represents the cost of changing the character original[i] to the character changed[i].
     *
     * You start with the string source. In one operation, you can pick a character x from the string and change it to
     * the character y at a cost of z if there exists any index j such that cost[j] == z, original[j] == x, and
     * changed[j] == y.
     *
     * Return the minimum cost to convert the string source to the string target using any number of operations. If it
     * is impossible to convert source to target, return -1.
     *
     * Note that there may exist indices i, j such that original[j] == original[i] and changed[j] == changed[i].
     *
     * Input: source = "abcd", target = "acbe", original = ["a","b","c","c","e","d"], changed = ["b","c","b","e","b","e"],
     * cost = [2,5,5,1,2,20]
     * Output: 28
     *
     * Input: source = "aaaa", target = "bbbb", original = ["a","c"], changed = ["c","b"], cost = [1,2]
     * Output: 12
     *
     * Input: source = "abcd", target = "abce", original = ["a"], changed = ["e"], cost = [10000]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= source.length == target.length <= 10^5
     * source, target consist of lowercase English letters.
     * 1 <= cost.length == original.length == changed.length <= 2000
     * original[i], changed[i] are lowercase English letters.
     * 1 <= cost[i] <= 10^6
     * original[i] != changed[i]
     * @param source
     * @param target
     * @param original
     * @param changed
     * @param cost
     * @return
     */
    // time = O(n + m^3), space = O(1)
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        final int inf = 0x3f3f3f3f;
        int[][] d = new int[26][26];
        for (int i = 0; i < 26; i++) Arrays.fill(d[i], inf);
        for (int i = 0; i < 26; i++) d[i][i] = 0;

        int n = source.length(), m = cost.length;
        for (int i = 0; i < m; i++) {
            int a = original[i] - 'a', b = changed[i] - 'a', c = cost[i];
            d[a][b] = Math.min(d[a][b], c);
        }

        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }

        long res = 0;
        for (int i = 0; i < n; i++) {
            if (source.charAt(i) == target.charAt(i)) continue;
            int a = source.charAt(i) - 'a', b = target.charAt(i) - 'a';
            if (d[a][b] == inf) return -1;
            res += d[a][b];
        }
        return res;
    }
}