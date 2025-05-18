package LC3001_3300;
import java.util.*;
public class LC3291_MinimumNumberofValidStringstoFormTargetI {
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
     * Output: 3
     *
     * Input: words = ["abababab","ab"], target = "ababaababa"
     * Output: 2
     *
     * Input: words = ["abcdef"], target = "xyz"
     * Output: -1
     *
     * Constraints:
     *
     * 1 <= words.length <= 100
     * 1 <= words[i].length <= 5 * 10^3
     * The input is generated such that sum(words[i].length) <= 105^.
     * words[i] consists only of lowercase English letters.
     * 1 <= target.length <= 5 * 10^3
     * target consists only of lowercase English letters.
     * @param words
     * @param target
     * @return
     */
    // time = O(L + nlogn), space = O(L + n)
    final int inf = 0x3f3f3f3f;
    TrieNode root;
    public int minValidStrings(String[] words, String target) {
        root = new TrieNode();
        int n = target.length();
        int[] f = new int[n + 1];
        Arrays.fill(f, inf);
        f[n] = 0;

        for (String s : words) insert(s);
        for (int i = n - 1; i >= 0; i--) {
            TrieNode p = root;
            for (int j = i; j < n; j++) {
                int u = target.charAt(j) - 'a';
                if (p.next[u] == null) break;
                p = p.next[u];
                f[i] = Math.min(f[i], f[j + 1] + 1);
            }
        }
        return f[0] == inf ? -1 : f[0];
    }

    private void insert(String s) {
        TrieNode p = root;
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            if (p.next[u] == null) p.next[u] = new TrieNode();
            p = p.next[u];
        }
    }

    class TrieNode {
        TrieNode[] next;
        public TrieNode() {
            this.next = new TrieNode[26];
        }
    }

    // S1.2
    // time = O(L + nlogn), space = O(L + n)
    class Solution {
        final int inf = 0x3f3f3f3f;
        int[][] son;
        int idx;
        public int minValidStrings(String[] words, String target) {
            int m = 0;
            for (String w : words) m += w.length();
            son = new int[m + 1][26];
            int n = target.length();
            for (String w : words) insert(w);
            int[] f = new int[n + 1];
            Arrays.fill(f, inf);
            f[n] = 0;

            for (int i = n - 1; i >= 0; i--) {
                int p = 0;
                for (int j = i; j < n; j++) {
                    int u = target.charAt(j) - 'a';
                    if (son[p][u] == 0) break;
                    p = son[p][u];
                    f[i] = Math.min(f[i], f[j + 1] + 1);
                }
            }
            return f[0] == inf ? -1 : f[0];
        }

        private void insert(String s) {
            int p = 0;
            for (int i = 0; i < s.length(); i++) {
                int u = s.charAt(i) - 'a';
                if (son[p][u] == 0) son[p][u] = ++idx;
                p = son[p][u];
            }
        }
    }
}