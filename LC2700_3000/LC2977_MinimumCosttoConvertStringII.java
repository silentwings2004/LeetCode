package LC2700_3000;
import java.util.*;
public class LC2977_MinimumCosttoConvertStringII {
    /**
     * You are given two 0-indexed strings source and target, both of length n and consisting of lowercase English
     * characters. You are also given two 0-indexed string arrays original and changed, and an integer array cost, where
     * cost[i] represents the cost of converting the string original[i] to the string changed[i].
     *
     * You start with the string source. In one operation, you can pick a substring x from the string, and change it to
     * y at a cost of z if there exists any index j such that cost[j] == z, original[j] == x, and changed[j] == y. You
     * are allowed to do any number of operations, but any pair of operations must satisfy either of these two conditions:
     *
     * The substrings picked in the operations are source[a..b] and source[c..d] with either b < c or d < a. In other
     * words, the indices picked in both operations are disjoint.
     * The substrings picked in the operations are source[a..b] and source[c..d] with a == c and b == d. In other words,
     * the indices picked in both operations are identical.
     * Return the minimum cost to convert the string source to the string target using any number of operations. If it
     * is impossible to convert source to target, return -1.
     *
     * Note that there may exist indices i, j such that original[j] == original[i] and changed[j] == changed[i].
     *
     * Input: source = "abcd", target = "acbe", original = ["a","b","c","c","e","d"],
     * changed = ["b","c","b","e","b","e"], cost = [2,5,5,1,2,20]
     * Output: 28
     *
     * Input: source = "abcdefgh", target = "acdeeghh", original = ["bcd","fgh","thh"], changed = ["cde","thh","ghh"],
     * cost = [1,3,5]
     * Output: 9
     *
     * Input: source = "abcdefgh", target = "addddddd", original = ["bcd","defgh"], changed = ["ddd","ddddd"],
     * cost = [100,1578]
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= source.length == target.length <= 1000
     * source, target consist only of lowercase English characters.
     * 1 <= cost.length == original.length == changed.length <= 100
     * 1 <= original[i].length == changed[i].length <= source.length
     * original[i], changed[i] consist only of lowercase English characters.
     * original[i] != changed[i]
     * 1 <= cost[i] <= 106
     * @param source
     * @param target
     * @param original
     * @param changed
     * @param cost
     * @return
     */
    // S1: string hash
    // time = O(n^2 + m^3), space = O(n + m)
    final int P = 131;
    final long inf = (long)1e18;
    long[] h1, h2, p;
    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        int n = source.length(), m = original.length;
        HashMap<Long, Integer> map = new HashMap<>();
        HashMap<String, Long> hash = new HashMap<>();
        for (int i = 0; i < m; i++) {
            long h = convert(original[i]);
            if (!map.containsKey(h)) {
                hash.put(original[i], h);
                map.put(h, map.size());
            }
            h = convert(changed[i]);
            if (!map.containsKey(h)) {
                hash.put(changed[i], h);
                map.put(h, map.size());
            }
        }

        h1 = new long[n + 1];
        h2 = new long[n + 1];
        p = new long[n + 1];
        p[0] = 1;
        for (int i = 1; i <= n; i++) {
            p[i] = p[i - 1] * P;
            h1[i] = h1[i - 1] * P + source.charAt(i - 1);
            h2[i] = h2[i - 1] * P + target.charAt(i - 1);
        }

        int len = map.size();
        long[][] d = new long[len][len];
        for (int i = 0; i < len; i++) Arrays.fill(d[i], inf);
        for (int i = 0; i < len; i++) d[i][i] = 0;
        for (int i = 0; i < m; i++) {
            int a = map.get(hash.get(original[i])), b = map.get(hash.get(changed[i])), c = cost[i];
            d[a][b] = Math.min(d[a][b], c);
        }

        for (int k = 0; k < len; k++) {
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }

        long[] f = new long[n + 1];
        Arrays.fill(f, inf);
        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            if (source.charAt(i - 1) == target.charAt(i - 1)) f[i] = Math.min(f[i], f[i - 1]);
            for (int j = i; j >= 1; j--) {
                long a = get(h1, j, i), b = get(h2, j, i);
                if (map.containsKey(a) && map.containsKey(b)) {
                    int x = map.get(a), y = map.get(b);
                    f[i] = Math.min(f[i], f[j - 1] + d[x][y]);
                }
            }
        }
        return f[n] == inf ? -1 : f[n];
    }

    private long convert(String s) {
        long h = 0;
        for (char c : s.toCharArray()) h = h * P + c;
        return h;
    }

    private long get(long[] h, int l, int r) {
        return h[r] - h[l - 1] * p[r - l + 1];
    }

    // S2: dp + trie
    // time = O(n^2 + m^3), space = O(n + m)
    TrieNode root;
    int idx;
    public long minimumCost2(String source, String target, String[] original, String[] changed, int[] cost) {
        long inf = (long)1e18;
        int n = source.length(), m = original.length;
        for (int i = 0; i < m; i++) original[i] = reverse(original[i]);
        for (int i = 0; i < m; i++) changed[i] = reverse(changed[i]);
        HashSet<String> set = new HashSet<>();
        for (String s : original) set.add(s);
        for (String s : changed) set.add(s);

        root = new TrieNode();
        idx = 0;
        HashMap<String, Integer> map = new HashMap<>();
        for (String s : set) {
            map.put(s, idx);
            insert(s);
            idx++;
        }
        int len = set.size();
        long[][] d = new long[len][len];
        for (int i = 0; i < len; i++) Arrays.fill(d[i], inf);
        for (int i = 0; i < len; i++) d[i][i] = 0;
        for (int i = 0; i < m; i++) {
            int a = map.get(original[i]), b = map.get(changed[i]), c = cost[i];
            d[a][b] = Math.min(d[a][b], c);
        }

        for (int k = 0; k < len; k++) {
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }

        long[] f = new long[n + 1];
        Arrays.fill(f, inf);
        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            if (source.charAt(i - 1) == target.charAt(i - 1)) f[i] = f[i - 1];
            TrieNode node1 = root, node2 = root;
            for (int j = i; j >= 1; j--) {
                int u = source.charAt(j - 1) - 'a', v = target.charAt(j - 1) - 'a';
                if (node1.next[u] == null) break;
                if (node2.next[v] == null) break;
                node1 = node1.next[u];
                node2 = node2.next[v];
                int x = node1.idx, y = node2.idx;
                if (x != -1 && y != -1) {
                    f[i] = Math.min(f[i], f[j - 1] + d[x][y]);
                }
            }
        }
        return f[n] == inf ? -1 : f[n];
    }

    private void insert(String s) {
        TrieNode node = root;
        for (char c : s.toCharArray()) {
            int u = c - 'a';
            if (node.next[u] == null) node.next[u] = new TrieNode();
            node = node.next[u];
        }
        node.idx = idx;
    }

    private String reverse(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }

    private class TrieNode {
        private TrieNode[] next;
        private int idx;
        public TrieNode() {
            this.next = new TrieNode[26];
            this.idx = -1;
        }
    }
}
/**
 * dp[i]: the minimum cost to convert s[1:i] to t[1:i]
 * source x x x x [x x x x]
 *                 j     i
 * target x x x x [x x x x]
 *                j      i
 * d[][]: the minimum distance (cost) from one string to another
 * for (int i = 1; i <= n; i++) {
 *     for (int j = 1; j <= i; j++) {
 *         if (source[j:i] -> target[j:i] exists) {
 *             dp[i] = min(dp[i], dp[j-1] + d[source[j:i]][target[j:i]])
 *         }
 *     }
 * }
 * Floyd: O(V^3)
 */