package LC3001_3300;
import java.util.*;
public class LC3213_ConstructStringwithMinimumCost {
    /**
     * You are given a string target, an array of strings words, and an integer array costs, both arrays of the same
     * length.
     *
     * Imagine an empty string s.
     *
     * You can perform the following operation any number of times (including zero):
     *
     * Choose an index i in the range [0, words.length - 1].
     * Append words[i] to s.
     * The cost of operation is costs[i].
     * Return the minimum cost to make s equal to target. If it's not possible, return -1.
     *
     * Input: target = "abcdef", words = ["abdef","abc","d","def","ef"], costs = [100,1,1,10,5]
     *
     * Output: 7
     *
     * Input: target = "aaaa", words = ["z","zz","zzz"], costs = [1,10,100]
     *
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= target.length <= 5 * 10^4
     * 1 <= words.length == costs.length <= 5 * 10^4
     * 1 <= words[i].length <= target.length
     * The total sum of words[i].length is less than or equal to 5 * 10^4.
     * target and words[i] consist only of lowercase English letters.
     * 1 <= costs[i] <= 10^4
     * @param target
     * @param words
     * @param costs
     * @return
     */
    // S1: Trie (TLE!)
    // time = O(n^2), space = O(n)
    final int inf = 0x3f3f3f3f;
    TrieNode root;
    public int minimumCost(String target, String[] words, int[] costs) {
        root = new TrieNode();
        for (int i = 0; i < words.length; i++) insert(words[i], costs[i]);
        int n = target.length();
        int[] f = new int[n + 1];
        Arrays.fill(f, inf);
        f[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            TrieNode p = root;
            for (int j = i; j < n; j++) {
                if (p.next[target.charAt(j) - 'a'] == null) break;
                p = p.next[target.charAt(j) - 'a'];
                if (p.cost != inf) f[i] = Math.min(f[i], f[j + 1] + p.cost);
            }
        }
        return f[0] == inf ? -1 : f[0];
    }

    private void insert(String s, int c) {
        TrieNode p = root;
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            if (p.next[u] == null) p.next[u] = new TrieNode();
            p = p.next[u];
        }
        p.cost = Math.min(p.cost, c);
    }

    class TrieNode {
        TrieNode[] next;
        int cost;
        public TrieNode() {
            this.next = new TrieNode[26];
            this.cost = inf;
        }
    }

    // S2
    // time = O(L + n * sqrt(L)), space = O(26 * L + n)
    public int minimumCost2(String target, String[] words, int[] costs) {
        AhoCorasick ac = new AhoCorasick();
        for (int i = 0; i < words.length; i++) ac.put(words[i], costs[i]);
        ac.buildFail();

        char[] t = target.toCharArray();
        int n = t.length;
        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE / 2);
        f[0] = 0;
        Node cur = ac.root;

        for (int i = 1; i <= n; i++) {
            cur = cur.son[t[i - 1] - 'a'];
            if (cur.len > 0) f[i] = Math.min(f[i], f[i - cur.len] + cur.cost);
            for (Node fail = cur.last; fail != ac.root; fail = fail.last) {
                f[i] = Math.min(f[i], f[i - fail.len] + fail.cost);
            }
        }
        return f[n] == Integer.MAX_VALUE / 2 ? -1 : f[n];
    }

    class AhoCorasick {
        Node root = new Node();

        private void put(String s, int cost) {
            Node cur = root;
            for (char b : s.toCharArray()) {
                b -= 'a';
                if (cur.son[b] == null) cur.son[b] = new Node();
                cur = cur.son[b];
            }
            cur.len = s.length();
            cur.cost = Math.min(cur.cost, cost);
        }

        private void buildFail() {
            root.fail = root.last = root;
            Queue<Node> q = new LinkedList<>();
            for (int i = 0; i < root.son.length; i++) {
                Node son = root.son[i];
                if (son == null) root.son[i] = root;
                else {
                    son.fail = son.last = root;
                    q.add(son);
                }
            }

            while (!q.isEmpty()) {
                Node cur = q.poll();
                for (int i = 0; i < 26; i++) {
                    Node son = cur.son[i];
                    if (son == null) {
                        cur.son[i] = cur.fail.son[i];
                        continue;
                    }
                    son.fail = cur.fail.son[i];
                    son.last = son.fail.len > 0 ? son.fail : son.fail.last;
                    q.add(son);
                }
            }
        }
    }

    class Node {
        int len, cost;
        Node fail, last;
        Node[] son;
        public Node() {
            this.son = new Node[26];
            this.cost = Integer.MAX_VALUE;
        }
    }
}