package LC2401_2700;
import java.util.*;
public class LC2416_SumofPrefixScoresofStrings {
    /**
     * You are given an array words of size n consisting of non-empty strings.
     *
     * We define the score of a string word as the number of strings words[i] such that word is a prefix of words[i].
     *
     * For example, if words = ["a", "ab", "abc", "cab"], then the score of "ab" is 2, since "ab" is a prefix of both
     * "ab" and "abc".
     * Return an array answer of size n where answer[i] is the sum of scores of every non-empty prefix of words[i].
     *
     * Note that a string is considered as a prefix of itself.
     *
     * Input: words = ["abc","ab","bc","b"]
     * Output: [5,4,3,2]
     *
     * Input: words = ["abcd"]
     * Output: [4]
     *
     * Constraints:
     *
     * 1 <= words.length <= 1000
     * 1 <= words[i].length <= 1000
     * words[i] consists of lowercase English letters.
     */
    // time = O(n * k), space = O(n)
    TrieNode root;
    public int[] sumPrefixScores(String[] words) {
        root = new TrieNode();
        int n = words.length;

        for (String word : words) {
            TrieNode node = root;
            long h = 0;
            for (char c : word.toCharArray()) {
                if (node.next[c - 'a'] == null) {
                    node.next[c - 'a'] = new TrieNode();
                }
                node = node.next[c - 'a'];
                node.cnt++;
            }
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            TrieNode node = root;
            for (char c : words[i].toCharArray()) {
                res[i] += node.next[c - 'a'].cnt;
                node = node.next[c - 'a'];
            }
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