package LC3001_3300;
import java.util.*;
public class LC3292_MinimumNumberofValidStringstoFormTargetII {
    /**
     * You are given an array of strings words and a string target.
     *
     * A string x is called valid if x is a
     * prefix
     *  of any string in words.
     *
     * Return the minimum number of valid strings that can be concatenated to form target. If it is not possible to form
     * target, return -1.
     *
     * A prefix of a string is a substring that starts from the beginning of the string and extends to any point within
     * it.
     *
     * Input: words = ["abc","aaaaa","bcdef"], target = "aabcdabc"
     *
     * Output: 3
     *
     * Input: words = ["abababab","ab"], target = "ababaababa"
     *
     * Output: 2
     *
     * Input: words = ["abcdef"], target = "xyz"
     *
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= words.length <= 100
     * 1 <= words[i].length <= 5 * 10^4
     * The input is generated such that sum(words[i].length) <= 10^5.
     * words[i] consists only of lowercase English letters.
     * 1 <= target.length <= 5 * 10^4
     * target consists only of lowercase English letters.
     * @param words
     * @param target
     * @return
     */
    // S1: string hash
    // time = O(L + nlogn), space = O(L + n)
    final int P = (int)8e8 + new Random().nextInt((int)1e8);
    long mod = (long)(1e9 + 7);
    long[] h, p;
    HashSet<Long>[] sets;
    int n;
    public int minValidStrings(String[] words, String target) {
        n = target.length();
        p = new long[n + 1];
        h = new long[n + 1];
        p[0] = 1;
        for (int i = 1; i <= n; i++) {
            p[i] = p[i - 1] * P % mod;
            h[i] = (h[i - 1] * P + target.charAt(i - 1)) % mod;
        }
        int maxLen = 0;
        for (String w : words) maxLen = Math.max(maxLen, w.length());
        sets = new HashSet[maxLen];
        for (int i = 0; i < maxLen; i++) sets[i] = new HashSet<>();
        for (String w : words) {
            int m = w.length();
            long v = 0;
            for (int i = 0; i < m; i++) {
                v = (v * P + w.charAt(i)) % mod;
                sets[i].add(v);
            }
        }

        int cr = 0, nr = 0, res = 0;
        for (int i = 0; i < n; i++) {
            int sz = cal(i);
            nr = Math.max(nr, i + sz);
            if (i == cr) {
                if (i == nr) return -1;
                cr = nr;
                res++;
            }
        }
        return res;
    }

    private int cal(int x) {
        int l = 0, r = Math.min(n - x, sets.length);
        while (l < r) {
            int mid = l + r + 1 >> 1;
            long v = get(x, x + mid - 1);
            if (sets[mid - 1].contains(v)) l = mid;
            else r = mid - 1;
        }
        return r;
    }

    private long get(int l, int r) {
        l++;
        r++;
        return (h[r] - h[l - 1] * p[r - l + 1] % mod + mod) % mod;
    }

    // S2: AC Automation
    // time = O(L + n), space = O(L + n)
    public int minValidStrings2(String[] words, String target) {
        ACAutomator ac = new ACAutomator();
        for (String w : words) ac.insert(w);
        ac.build();

        int n = target.length();
        int[] f = new int[n + 1];
        Node p = ac.root;
        for (int i = 0; i < n; i++) {
            p = p.next[target.charAt(i) - 'a'];
            if (p == ac.root) return -1;
            f[i + 1] = f[i + 1 - p.len] + 1;
        }
        return f[n];
    }

    class ACAutomator {
        Node root = new Node(0);

        private void insert(String s) {
            Node p = root;
            for (int i = 0; i < s.length(); i++) {
                int u = s.charAt(i) - 'a';
                if (p.next[u] == null) p.next[u] = new Node(p.len + 1);
                p = p.next[u];
            }
        }

        private void build() {
            root.fail = root;
            Queue<Node> q = new LinkedList<>();
            for (int i = 0; i < root.next.length; i++) {
                Node t = root.next[i];
                if (t == null) root.next[i] = root;
                else {
                    t.fail = root;
                    q.add(t);
                }
            }

            while (!q.isEmpty()) {
                Node p = q.poll();
                for (int i = 0; i < 26; i++) {
                    Node t = p.next[i];
                    if (t == null) {
                        p.next[i] = p.fail.next[i];
                        continue;
                    }
                    t.fail = p.fail.next[i];
                    q.add(t);
                }
            }
        }
    }

    class Node {
        Node[] next = new Node[26];
        Node fail;
        int len;
        Node (int len) {
            this.len = len;
        }
    }
}