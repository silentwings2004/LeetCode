package LC601_900;
import java.util.*;
public class LC720_LongestWordinDictionary {
    /**
     * Given an array of strings words representing an English Dictionary, return the longest word in words that can be
     * built one character at a time by other words in words.
     *
     * If there is more than one possible answer, return the longest word with the smallest lexicographical order. If
     * there is no answer, return the empty string.
     *
     * Input: words = ["w","wo","wor","worl","world"]
     * Output: "world"
     *
     * Constraints:
     *
     * 1 <= words.length <= 1000
     * 1 <= words[i].length <= 30
     * words[i] consists of lowercase English letters.
     * @param words
     * @return
     */
    // time = O(nlogn), space = O(n)
    public String longestWord(String[] words) {
        Arrays.sort(words, (o1, o2) -> o1.length() != o2.length() ? o2.length() - o1.length() : o1.compareTo(o2));

        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.next[c - 'a'] == null) {
                    node.next[c - 'a'] = new TrieNode();
                }
                node = node.next[c - 'a'];
            }
            node.isEnd = true;
        }

        for (String word : words) {
            TrieNode node = root;
            boolean flag = true;
            for (char c : word.toCharArray()) {
                if (node.next[c - 'a'] != null && !node.next[c - 'a'].isEnd) {
                    flag = false;
                    break;
                }
                node = node.next[c - 'a'];
            }
            if (flag) return word;
        }
        return "";
    }

    private class TrieNode {
        private TrieNode[] next;
        private boolean isEnd;
        public TrieNode() {
            this.next = new TrieNode[26];
            this.isEnd = false;
        }
    }

    // S2
    // time = O(n * k), space = O(n * k)
    final int N = 30010;
    int[][] son;
    int[] id;
    int idx;
    public String longestWord2(String[] words) {
        son = new int[N][26];
        id = new int[N];
        Arrays.fill(id, -1);
        idx = 0;

        int n = words.length;
        for (int i = 0; i < n; i++) insert(words[i], i);

        int[] t = dfs(0, 0);
        if (t[1] != -1) return words[t[1]];
        return "";
    }

    private int[] dfs(int p, int len) {
        int[] res = new int[]{len, id[p]};
        for (int i = 0; i < 26; i++) {
            int j = son[p][i];
            if (j > 0 && id[j] != -1) {
                int[] t = dfs(j, len + 1);
                if (res[0] < t[0]) res = t;
            }
        }
        return res;
    }

    private void insert(String s, int k) {
        int p = 0;
        for (char c : s.toCharArray()) {
            int u = c - 'a';
            if (son[p][u] == 0) son[p][u] = ++idx;
            p = son[p][u];
        }
        id[p] = k;
    }
}