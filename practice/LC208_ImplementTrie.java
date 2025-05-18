package practice;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ImplementTrie
 * Creater: Silentwings
 * Date: Apr, 2020
 * Description: 208. Implement Trie (Prefix Tree)
 */
class TrieNode {
    public char ch;
    public TrieNode[] nexts;
    public boolean isWord;
    public TrieNode(char ch) {
        this.ch = ch;
        this.nexts = new TrieNode[26];
        this.isWord = false;
    }
}

public class LC208_ImplementTrie {
    /**
     * Implement a trie with insert, search, and startsWith methods.
     *
     * Example:
     *
     * Trie trie = new Trie();
     *
     * trie.insert("apple");
     * trie.search("apple");   // returns true
     * trie.search("app");     // returns false
     * trie.startsWith("app"); // returns true
     * trie.insert("app");
     * trie.search("app");     // returns true
     *
     * Note:
     *
     * You may assume that all inputs are consist of lowercase letters a-z.
     * All inputs are guaranteed to be non-empty strings.
     */
    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
    /** Initialize your data structure here. */
    private TrieNode root;
    public LC208_ImplementTrie() {
        this.root = new TrieNode('\0');
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode cur = root;
        for (char ch : word.toCharArray()) {
            TrieNode next = cur.nexts[ch - 'a'];
            if (next == null) cur.nexts[ch - 'a'] = new TrieNode(ch);
            cur = cur.nexts[ch - 'a'];
        }
        cur.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode cur = root;
        for (char ch : word.toCharArray()) {
            if (cur.nexts[ch - 'a'] == null) return false;
            cur = cur.nexts[ch - 'a'];
        }
        return cur.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (char ch : prefix.toCharArray()) {
            if (cur.nexts[ch - 'a'] == null) return false;
            cur = cur.nexts[ch - 'a'];
        }
        return true;
    }
}
