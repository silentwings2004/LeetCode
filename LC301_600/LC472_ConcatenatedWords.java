package LC301_600;
import java.util.*;
public class LC472_ConcatenatedWords {
    /**
     * Given a list of words (without duplicates), please write a program that returns all concatenated words in the
     * given list of words.
     *
     * A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given
     * array.
     *
     * @param words
     * @return
     */
    // S1: DP
    // time = O(n^3 * m), space = O(n)
    HashSet<String> set;
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        set = new HashSet<>();
        for (String word : words) set.add(word);
        List<String> res = new ArrayList<>();
        for (String word : words) { // O(m)
            if (helper(word)) res.add(word);
        }
        return res;
    }

    private boolean helper(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MIN_VALUE);
        f[0] = 0;

        for (int i = 0; i < n; i++) { // O(n^3)
            if (f[i] < 0) continue;
            for (int j = n - i; j > 0; j--) {
                if (set.contains(s.substring(i, i + j))) {
                    f[i + j] = Math.max(f[i + j], f[i] + 1);
                    if (f[n] > 1) return true;
                }
            }
        }
        return false;
    }

    // We iterate through each word and see if it can be formed by using other words.
    // It is also obvious that a word can only be formed by words shorter than it. So we can first sort the input by
    // length of each word, and only try to form one word by using words in front of it.

    // S2: Trie
    // time = O(nlogn + sum(k)), space = O(sum(k) * 26), k: 单词words[i] 的长度
    TrieNode root;
    public List<String> findAllConcatenatedWordsInADict2(String[] words) {
        List<String> res = new ArrayList<>();
        root = new TrieNode();
        Arrays.sort(words, (o1, o2) -> o1.length() - o2.length()); // O(nlogn)

        for (String word : words) { // O(n)
            if (word.length() != 0 && check(word)) res.add(word);

            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                if (node.next[ch - 'a'] == null) {
                    node.next[ch - 'a'] = new TrieNode();
                }
                node = node.next[ch - 'a'];
            }
            node.isEnd = true;
        }
        return res;
    }

    private boolean check(String word) {
        boolean[] memo = new boolean[word.length()];
        return dfs(word, 0, memo);
    }

    private boolean dfs(String word, int cur, boolean[] memo) {
        // base case
        if (cur == word.length()) return true;
        if (memo[cur]) return false;

        TrieNode node = root;
        for (int i = cur; i < word.length(); i++) {
            if (node.next[word.charAt(i) - 'a'] != null) {
                node = node.next[word.charAt(i) - 'a'];
                if (node.isEnd && dfs(word, i + 1, memo)) return true;
            } else break;
        }
        memo[cur] = true;
        return false;
    }

    private class TrieNode {
        private TrieNode[] next;
        private boolean isEnd;
        public TrieNode() {
            next = new TrieNode[26];
            isEnd = false;
        }
    }
}
/**
 * 本题与word break高度类似,就是word break的翻版
 * 先从小到大排个序，先看之前的单词组成的字典树跟某个word是否匹配，然后把这个word加入字典树，不断增大字典树，而不是每次重新构造trie，效率高。
 * 找比它长度小的单词放入字典树里去查看
 * 所有单词按照单词长度排个序
 * trie + dfs + pruning
 *
 * dp
 * 状态表示；
 * 集合：前i个单词可以拆分成哪些单词
 * 属性：单词数量的最大值
 * 状态计算：f(i) 按照最后一个单词的长度来划分 j
 * f(i-j) + 1 => O(n^3 * m)
 * 用字符串哈希 => O(n^2 * m)
 */
