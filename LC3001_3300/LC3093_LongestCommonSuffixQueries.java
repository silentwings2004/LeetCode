package LC3001_3300;
import java.util.*;
public class LC3093_LongestCommonSuffixQueries {
    /**
     * You are given two arrays of strings wordsContainer and wordsQuery.
     *
     * For each wordsQuery[i], you need to find a string from wordsContainer that has the longest common suffix with
     * wordsQuery[i]. If there are two or more strings in wordsContainer that share the longest common suffix, find the
     * string that is the smallest in length. If there are two or more such strings that have the same smallest length,
     * find the one that occurred earlier in wordsContainer.
     *
     * Return an array of integers ans, where ans[i] is the index of the string in wordsContainer that has the longest
     * common suffix with wordsQuery[i].
     *
     * Input: wordsContainer = ["abcd","bcd","xbcd"], wordsQuery = ["cd","bcd","xyz"]
     *
     * Output: [1,1,1]
     *
     * Input: wordsContainer = ["abcdefgh","poiuygh","ghghgh"], wordsQuery = ["gh","acbfgh","acbfegh"]
     *
     * Output: [2,0,2]
     *
     * Constraints:
     *
     * 1 <= wordsContainer.length, wordsQuery.length <= 10^4
     * 1 <= wordsContainer[i].length <= 5 * 10^3
     * 1 <= wordsQuery[i].length <= 5 * 10^3
     * wordsContainer[i] consists only of lowercase English letters.
     * wordsQuery[i] consists only of lowercase English letters.
     * Sum of wordsContainer[i].length is at most 5 * 10^5.
     * Sum of wordsQuery[i].length is at most 5 * 10^5.
     * @param wordsContainer
     * @param wordsQuery
     * @return
     */
    // time = O(max(m * k, n * k)), space = O(max(m * k, n * k))
    TrieNode root;
    int minIdx;
    String[] w;
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        root = new TrieNode();
        w = wordsContainer;
        int m = w.length, n = wordsQuery.length;
        int minLen = w[0].length();
        minIdx = 0;
        for (int i = 0; i < m; i++) {
            if (minLen > w[i].length()) {
                minLen = w[i].length();
                minIdx = i;
            }
            insert(w[i], i);
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) res[i] = find(wordsQuery[i]);
        return res;
    }

    private void insert(String s, int idx) {
        TrieNode p = root;
        int len = s.length();
        for (int i = s.length() - 1; i >= 0; i--) {
            int u = s.charAt(i) - 'a';
            if (p.next[u] == null) p.next[u] = new TrieNode();
            p = p.next[u];
            if (p.idx == -1 || len < w[p.idx].length()) p.idx = idx;
        }
    }

    private int find(String s) {
        TrieNode p = root;
        for (int i = s.length() - 1; i >= 0; i--) {
            int u = s.charAt(i) - 'a';
            if (p.next[u] == null) break;
            p = p.next[u];
        }
        return p.idx == -1 ? minIdx : p.idx;
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
 * 匹配多串前缀/后缀
 * => 字典树 trie
 */