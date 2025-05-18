package LC901_1200;
import java.util.*;
public class LC953_VerifyinganAlienDictionary {
    /**
     * In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order.
     * The order of the alphabet is some permutation of lowercase letters.
     *
     * Given a sequence of words written in the alien language, and the order of the alphabet, return true if and
     * only if the given words are sorted lexicographicaly in this alien language.
     *
     * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
     * Output: true
     * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
     *
     * Constraints:
     *
     * 1 <= words.length <= 100
     * 1 <= words[i].length <= 20
     * order.length == 26
     * All characters in words[i] and order are English lowercase letters.
     *
     * @param words
     * @param order
     * @return
     */
    // time = O(C), space = O(1)   C: total contents of words
    public boolean isAlienSorted(String[] words, String order) {
        int[] chars = new int[26];
        for (int i = 0; i < order.length(); i++) {
            char c = order.charAt(i);
            chars[c - 'a'] = i;
        }

        for (int i = 0; i < words.length - 1; i++) { // O(n)
            String str1 = words[i], str2 = words[i + 1];
            int idx1 = 0, idx2 = 0;
            boolean flag = false;
            while (idx1 < str1.length() && idx2 < str2.length()) {
                char c1 = str1.charAt(idx1++), c2 = str2.charAt(idx2++);
                if (c1 != c2) {
                    if (chars[c1 - 'a'] > chars[c2 - 'a']) return false;
                    else {
                        flag = true; // 表明出loop是因为两者出现不同字符且满足order条件
                        break;
                    }
                }
            }
            if (!flag && idx1 < str1.length()) return false; // 表明是以上loop中两者所有表的字符都相等才到达这里的
        }
        return true;
    }

    // S2
    // time = O(n), space = O(1)
    public boolean isAlienSorted2(String[] words, String order) {
        int n = words.length;
        for (int i = 0; i + 1 < n; i++) {
            String a = words[i], b = words[i + 1];
            int len1 = a.length(), len2 = b.length();
            if (len1 > len2 && a.substring(0, len2).equals(b)) return false;

            for (int j = 0; j < Math.min(len1, len2); j++) {
                char c1 = a.charAt(j), c2 = b.charAt(j);
                if (c1 == c2) continue;
                if (order.indexOf(c1) > order.indexOf(c2)) return false;
                break;
            }
        }
        return true;
    }

    // S3
    // time = O(n), space = O(n)
    public boolean isAlienSorted3(String[] words, String order) {
        HashMap<Character, Integer> map = new HashMap<>();
        int n = words.length, m = order.length();
        for (int i = 0; i < m; i++) map.put(order.charAt(i), i);
        for (int i = 1; i < n; i++) {
            String a = words[i - 1], b = words[i];
            int x = 0, y = 0;
            while (x < a.length() && y < b.length()) {
                int t1 = map.getOrDefault(a.charAt(x), 0);
                int t2 = map.getOrDefault(b.charAt(x), 0);
                if (t1 > t2) return false;
                if (t1 < t2) break;
                x++;
                y++;
            }
            if (x < a.length() && y == b.length()) return false;
        }
        return true;
    }
}