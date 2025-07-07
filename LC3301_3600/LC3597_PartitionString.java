package LC3301_3600;
import java.util.*;
public class LC3597_PartitionString {
    /**
     * Given a string s, partition it into unique segments according to the following procedure:
     *
     * Start building a segment beginning at index 0.
     * Continue extending the current segment character by character until the current segment has not been seen before.
     * Once the segment is unique, add it to your list of segments, mark it as seen, and begin a new segment from the
     * next index.
     * Repeat until you reach the end of s.
     * Return an array of strings segments, where segments[i] is the ith segment created.
     *
     * Input: s = "abbccccd"
     * Output: ["a","b","bc","c","cc","d"]
     *
     * Input: s = "aaaa"
     * Output: ["a","aa"]
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s contains only lowercase English letters.
     * @param s
     * @return
     */
    // S1
    // time = O(n * sqrt(n)), space = O(n)
    public List<String> partitionString(String s) {
        List<String> res = new ArrayList<>();
        int n = s.length();
        HashSet<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(s.charAt(i));
            if (set.add(sb.toString())) {
                res.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        return res;
    }

    // S2: Trie
    // time = O(n), space = O(n)
    public List<String> partitionString2(String s) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        TrieNode root = new TrieNode(), p = root;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            sb.append(c);
            int u = c - 'a';
            if (p.next[u] == null) {
                p.next[u] = new TrieNode();
                res.add(sb.toString());
                sb = new StringBuilder();
                p = root;
            } else p = p.next[u];
        }
        return res;
    }

    class TrieNode {
        TrieNode[] next;
        public TrieNode() {
            this.next = new TrieNode[26];
        }
    }
}
/**
 * 最坏情况 a aa aaa ...
 * 1 + 2 + 3 + ... + k <= n
 * (k + 1) * k / 2 <= n  => O(sqrt(n))
 */