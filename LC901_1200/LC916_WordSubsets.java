package LC901_1200;
import java.util.*;
public class LC916_WordSubsets {
    /**
     * We are given two arrays A and B of words.  Each word is a string of lowercase letters.
     *
     * Now, say that word b is a subset of word a if every letter in b occurs in a, including multiplicity.
     * For example, "wrr" is a subset of "warrior", but is not a subset of "world".
     *
     * Now say a word a from A is universal if for every b in B, b is a subset of a.
     *
     * Return a list of all universal words in A.  You can return the words in any order.
     *
     * Input: A = ["amazon","apple","facebook","google","leetcode"], B = ["e","o"]
     * Output: ["facebook","google","leetcode"]
     *
     * Note:
     *
     * 1 <= A.length, B.length <= 10000
     * 1 <= A[i].length, B[i].length <= 10
     * A[i] and B[i] consist only of lowercase letters.
     * All words in A[i] are unique: there isn't i != j with A[i] == A[j].
     * @param words1
     * @param words2
     * @return
     */
    // time = O(m + n), space = O(1)
    public List<String> wordSubsets(String[] words1, String[] words2) {
        List<String> res = new ArrayList<>();
        int[] f = new int[26];
        for (String s : words2) {
            int[] count = new int[26];
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
                f[c - 'a'] = Math.max(f[c - 'a'], count[c - 'a']);
            }
        }

        for (String s : words1) {
            int[] count = f.clone();
            for (char c : s.toCharArray()) {
                if (count[c - 'a'] > 0) count[c - 'a']--;
            }
            boolean flag = true;
            for (int i = 0; i < 26; i++) {
                if (count[i] > 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) res.add(s);
        }
        return res;
    }
}