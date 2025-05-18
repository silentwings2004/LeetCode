package LC3001_3300;
import java.util.*;
public class LC3045_CountPrefixandSuffixPairsII {
    /**
     * You are given a 0-indexed string array words.
     *
     * Let's define a boolean function isPrefixAndSuffix that takes two strings, str1 and str2:
     *
     * isPrefixAndSuffix(str1, str2) returns true if str1 is both a
     * prefix
     *  and a
     * suffix
     *  of str2, and false otherwise.
     * For example, isPrefixAndSuffix("aba", "ababa") is true because "aba" is a prefix of "ababa" and also a suffix,
     * but isPrefixAndSuffix("abc", "abcd") is false.
     *
     * Return an integer denoting the number of index pairs (i, j) such that i < j, and
     * isPrefixAndSuffix(words[i], words[j]) is true.
     *
     * Input: words = ["a","aba","ababa","aa"]
     * Output: 4
     *
     * Input: words = ["pa","papa","ma","mama"]
     * Output: 2
     *
     * Input: words = ["abab","ab"]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= words.length <= 10^5
     * 1 <= words[i].length <= 10^5
     * words[i] consists only of lowercase English letters.
     * The sum of the lengths of all words[i] does not exceed 5 * 10^5.
     * @param words
     * @return
     */
    // time = O(L), space = O(L) L: words[i]的长度之和
    final int P = 131, N = 100010, mod = (int)1e9 + 7;
    long[] p;
    public long countPrefixSuffixPairs(String[] words) {
        long res = 0;
        if (words.length == 1) return 0;

        HashMap<Long, List<Integer>> map = new HashMap<>();
        HashMap<Long, HashSet<Integer>> hm = new HashMap<>();

        int n = words.length;
        long[] h = new long[n];
        p = new long[N];
        p[0] = 1;
        List<long[]> hp = new ArrayList<>();
        for (int i = 1; i < N; i++) p[i] = p[i - 1] * P % mod;
        for (int i = 0; i < words.length; i++) {
            n = words[i].length();
            long hash = 0;
            long[] hk = new long[n + 1];
            for (int j = 0; j < n; j++) {
                hash = (hash * P + words[i].charAt(j)) % mod;
                hm.putIfAbsent(hash, new HashSet<>());
                hm.get(hash).add(i);
                hk[j + 1] = hash;
            }
            hp.add(hk);
            h[i] = hash;
            map.putIfAbsent(hash, new ArrayList<>());
            map.get(hash).add(i);
        }

        for (int i = 0; i < words.length; i++) {
            n = words[i].length();
            for (int j = n - 1; j >= 0; j--) {
                long hash = get(hp.get(i), j, n - 1);
                if (map.containsKey(hash) && hm.get(hash).contains(i)) {
                    int idx = work(map.get(hash), i);
                    res += idx + 1;
                }
            }
        }
        return res;
    }

    private int work(List<Integer> q, int t) {
        int l = 0, r = q.size() - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (q.get(mid) < t) l = mid;
            else r = mid - 1;
        }
        return q.get(r) < t ? r : r - 1;
    }

    private long get(long[] h, int l, int r) {
        l++;
        r++;
        return (h[r] - h[l - 1] * p[r - l + 1] % mod + mod) % mod;
    }

    // S2: Trie
    // time = O(L), space = O(L)  L: 所有words[i]的长度之和
    public long countPrefixSuffixPairs2(String[] words) {
        Node root = new Node();
        long res = 0;
        for (String s : words) {
            int n = s.length();
            Node p = root;
            for (int i = 0; i < n; i++) {
                int x = (s.charAt(i) - 'a') << 5 | (s.charAt(n - 1 - i) - 'a');
                p.next.putIfAbsent(x, new Node());
                p = p.next.get(x);
                res += p.cnt;
            }
            p.cnt++;
        }
        return res;
    }

    private class Node {
        private HashMap<Integer, Node> next;
        private int cnt;
        public Node() {
            this.next = new HashMap<>();
            this.cnt = 0;
        }
    }

    // S3: Z + Trie
    // time = O(L), space = O(L)  L: 所有words[i]的长度之和
    public long countPrefixSuffixPairs3(String[] words) {
        TrieNode root = new TrieNode();
        long res = 0;
        for (String w : words) {
            int n = w.length();
            int[] z = new int[n];
            int l = 0, r = 0;
            for (int i = 1; i < n; i++) {
                if (i <= r) z[i] = Math.min(z[i - l], r - i + 1);
                while (i + z[i] < n && w.charAt(z[i]) == w.charAt(i + z[i])) {
                    l = i;
                    r = i + z[i];
                    z[i]++;
                }
            }
            z[0] = n;

            TrieNode node = root;
            for (int i = 0; i < n; i++) {
                int u = w.charAt(i) - 'a';
                if (node.next[u] == null) node.next[u] = new TrieNode();
                node = node.next[u];
                if (z[n - 1 - i] == i + 1) res += node.cnt;
            }
            node.cnt++;
        }
        return res;
    }

    private class TrieNode {
        private TrieNode[] next;
        private int cnt;
        public TrieNode() {
            this.next = new TrieNode[26];
            this.cnt = 0;
        }
    }
}
/**
 * 字典树
 * 1. 把字符串按照前缀分组，第一个字母相同的，分到同一组...
 * 2. 用一棵树来实现
 * 把字母对应到一棵树上的某个节点
 * 保证对于每个字符串是s, s[i]一定是s[i+1]的父节点
 * 3. 本题的做法
 * 把 s 转成一个 pair 列表
 * [(s[0], s[n-1]), (s[1], s[n-2]), (s[2],s[n-3]), ..., (s[n-1], s[0])]
 * 判断 words[i] 对应的 pair 列表，是不是 words[j] 对应的 pair 列表的前缀
 * s = abcd
 * t = abcdzabcd
 * [(a,d),(b,c),(c,b),(d,a)]
 * [(a,d),(b,c),(c,b),(d,a),(z,z),...]
 * 比2个信息的话，可以把这2个信息整合成一个 pair 再来比较
 * 用哈希表来存
 *
 * 4. 其它做法
 * s 如果是 t 的前缀和后缀的话，对于 t 来说，长为 |s| 的前后缀必须是一样的
 * z 函数
 * z[i] = |LCP(s[i:],s)| = n-i
 * 在遍历 t 的时候，发现 t[0]...t[i] 这一段前后缀是一样的
 * 那么只需要看前面有多少个 t[0]...t[i] 字符串
 */