package LC3301_3600;
import java.util.*;
public class LC3485_LongestCommonPrefixofKStringsAfterRemoval {
    /**
     * You are given an array of strings words and an integer k.
     *
     * For each index i in the range [0, words.length - 1], find the length of the longest common prefix among any k
     * strings (selected at distinct indices) from the remaining array after removing the ith element.
     *
     * Return an array answer, where answer[i] is the answer for ith element. If removing the ith element leaves the
     * array with fewer than k strings, answer[i] is 0.
     *
     * A prefix of a string is a substring that starts from the beginning of the string and extends to any point within
     * it.
     *
     * A substring is a contiguous sequence of characters within a string.
     *
     * Input: words = ["jump","run","run","jump","run"], k = 2
     * Output: [3,4,4,3,4]
     *
     * Input: words = ["dog","racer","car"], k = 2
     * Output: [0,0,0]
     *
     * Constraints:
     *
     * 1 <= k <= words.length <= 10^5
     * 1 <= words[i].length <= 10^4
     * words[i] consists of lowercase English letters.
     * The sum of words[i].length is smaller than or equal 105.
     * @param words
     * @param k
     * @return
     */
    // S1: Trie
    // time = O(n), space = O(n)
    TrieNode root;
    HashMap<Integer, Integer> cm;
    TreeMap<Integer, HashSet<Integer>> map;
    int[][] nodes;
    int idx, k;
    public int[] longestCommonPrefix(String[] words, int k) {
        root = new TrieNode();
        cm = new HashMap<>();
        map = new TreeMap<>();
        idx = 0;
        this.k = k;
        for (String w : words) insert(w);
        int n = words.length;
        nodes = new int[n][2];
        for (int i = 0; i < n; i++) work(words[i], i);
        for (int i = 0; i < n; i++) {
            int v = nodes[i][0], p = nodes[i][1];
            if (v == 0) continue;
            map.putIfAbsent(v, new HashSet<>());
            map.get(v).add(p);
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int[] x = nodes[i];
            if (x[0] > 0) remove(x);
            if (map.size() > 0) res[i] = map.lastKey();
            if (x[0] > 0) add(x);
        }
        return res;
    }

    private void insert(String s) {
        TrieNode p = root;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            if (p.next[u] == null) {
                p.next[u] = new TrieNode();
                p.next[u].id = idx++;
            }
            p = p.next[u];
            p.cnt++;
        }
    }

    private void work(String s, int x) {
        TrieNode p = root;
        int n = s.length(), mx = 0, node = -1;
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            p = p.next[u];
            if (p.cnt >= k) {
                if (i + 1 > mx) {
                    mx = i + 1;
                    node = p.id;
                }
            }
        }
        nodes[x] = new int[]{mx, node};
        if (mx > 0) cm.put(node, cm.getOrDefault(node, 0) + 1);
    }

    private void remove(int[] x) {
        int v = x[0], p = x[1];
        cm.put(p, cm.get(p) - 1);
        if (cm.get(p) < k) {
            map.get(v).remove(p);
            if (map.get(v).size() == 0) map.remove(v);
        }
    }

    private void add(int[] x) {
        int v = x[0], p = x[1];
        cm.put(p, cm.getOrDefault(p, 0) + 1);
        map.putIfAbsent(v, new HashSet<>());
        map.get(v).add(p);
    }

    class TrieNode {
        TrieNode[] next;
        int cnt, id;
        public TrieNode() {
            this.next = new TrieNode[26];
            this.cnt = 0;
            this.id = 0;
        }
    }

    // S2
    // time = O(Llogn), space = O(n)  L: sum of words[i].length()
    public int[] longestCommonPrefix2(String[] words, int k) {
        int n = words.length;
        if (k >= n) return new int[n];
        Integer[] p = new Integer[n];
        for (int i = 0; i < n; i++) p[i] = i;
        Arrays.sort(p, (o1, o2) -> words[o1].compareTo(words[o2]));

        int mx = -1, mx2 = -1, midx = -1;
        for (int i = 0; i <= n - k; i++) {
            int lcp = cal(words[p[i]], words[p[i + k - 1]]);
            if (lcp > mx) {
                mx2 = mx;
                mx = lcp;
                midx = i;
            } else if (lcp > mx2) {
                mx2 = lcp;
            }
        }

        int[] res = new int[n];
        Arrays.fill(res, mx);
        for (int i = midx; i < midx + k; i++) res[p[i]] = mx2;
        return res;
    }

    private int cal(String s, String t) {
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != t.charAt(i)) return i;
        }
        return n;
    }
}
/**
 * LCP
 * 任意 k 个 -> 连续 k 个
 * 1. 排序，那么只需要考虑长为 k 的连续子数组的 LCP
 * 2. 排序后，子数组的 LCP = LCP (子数组第一个字符串，子数组最后一个字符串)
 *    把 k 个字符串的问题转换成 2 个字符串的问题
 * 3. 如果不删除，那么答案是多少？
 *    暴力枚举所有长为 k 的子数组，根据 (2)
 *    计算所有的 LCP(words[i], words[i+k-1])，取最大值，即为不删除时的答案
 * 4. 记录最大的 LCP 对应的子数组是 [mx_i, mx_i+k-1]
 *    记录次大的 LCP 对应的子数组是 [mx2_i, mx2_i+k-1]
 * 5. 考虑删除一个字符串
 *    分类讨论：
 *    a. 如果删除的字符串不在 [mx_i, mx_i+k-1] 中，那么答案就是不删除时的答案, 即最大 LCP
 *    剩下的问题就是删除在 [mx_i, mx_i+k-1] 中的字符串
 *    b. 如果删除的字符串在 [mx2_i, mx2_i+k-1] 中，那么答案就是次大的LCP
 *    c. 如果删除的字符串既在 [mx_i, mx_i+k-1] 中，又在 [mx2_i, mx2_i+k-1] 中，那么答案是多少?
 *       意味着这两个数组是重叠的，重叠的字符串(也是我们删除的字符串) 即有最大 LCP 又有次大 LCP
 *       围绕重叠的字符串讨论，那么次大 LCP 也是最大 LCP 的前缀
 *       去掉字符串 s,可以再加一个交际中的其他字符串进来，仍然是 k 个字符串，且次大 LCP 是不变的
 *       那么答案就是次大的 LCP
 * 结论：如果 i 不在 [mx_i, mx_i+k-1] 中，那么答案是最大 LCP 的长度
 *      否则，答案是次大 LCP 的长度
 */