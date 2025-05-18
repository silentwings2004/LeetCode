package LC3301_3600;
import java.util.*;
public class LC3491_PhoneNumberPrefix {
    /**
     * You are given a string array numbers that represents phone numbers. Return true if no phone number is a prefix
     * of any other phone number; otherwise, return false.
     *
     * Input: numbers = ["1","2","4","3"]
     * Output: true
     *
     * Input: numbers = ["001","007","15","00153"]
     * Output: false
     *
     * Constraints:
     *
     * 2 <= numbers.length <= 50
     * 1 <= numbers[i].length <= 50
     * All numbers contain only digits '0' to '9'.
     * @param numbers
     * @return
     */
    // S1
    // time = O(n^2 * m), space = O(logn + m)
    public boolean phonePrefix(String[] numbers) {
        Arrays.sort(numbers, (o1, o2) -> o1.length() - o2.length());
        int n = numbers.length;
        for (int i = 0; i < n; i++) {
            int m = numbers[i].length();
            for (int j = i + 1; j < n; j++) {
                if (numbers[j].substring(0, m).equals(numbers[i])) return false;
            }
        }
        return true;
    }

    // S2: Trie
    // time = O(nlogn + n * m), space = O(logn + n * m)
    TrieNode root;
    public boolean phonePrefix2(String[] numbers) {
        Arrays.sort(numbers);
        root = new TrieNode();
        for (String s : numbers) {
            if (insert(s)) return false;
        }
        return true;
    }

    private boolean insert(String s) {
        TrieNode p = root;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - '0';
            if (p.next[u] == null) p.next[u] = new TrieNode();
            p = p.next[u];
            if (p.isEnd) return true;
        }
        p.isEnd = true;
        return false;
    }

    class TrieNode {
        TrieNode[] next;
        boolean isEnd;
        public TrieNode() {
            this.next = new TrieNode[10];
            this.isEnd = false;
        }
    }
}