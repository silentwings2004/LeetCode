package LC601_900;
import java.util.*;
public class LC734_SentenceSimilarity {
    /**
     * We can represent a sentence as an array of words, for example, the sentence "I am happy with leetcode" can be
     * represented as arr = ["I","am",happy","with","leetcode"].
     *
     * Given two sentences sentence1 and sentence2 each represented as a string array and given an array of string pairs
     * similarPairs where similarPairs[i] = [xi, yi] indicates that the two words xi and yi are similar.
     *
     * Return true if sentence1 and sentence2 are similar, or false if they are not similar.
     *
     * Two sentences are similar if:
     *
     * They have the same length (i.e., the same number of words)
     * sentence1[i] and sentence2[i] are similar.
     * Notice that a word is always similar to itself, also notice that the similarity relation is not transitive.
     * For example, if the words a and b are similar, and the words b and c are similar, a and c are not necessarily
     * similar.
     *
     * Input: sentence1 = ["great","acting","skills"], sentence2 = ["fine","drama","talent"],
     * similarPairs = [["great","fine"],["drama","acting"],["skills","talent"]]
     * Output: true
     *
     * Input: sentence1 = ["great"], sentence2 = ["great"], similarPairs = []
     * Output: true
     *
     * Input: sentence1 = ["great"], sentence2 = ["doubleplus","good"], similarPairs = [["great","doubleplus"]]
     * Output: false
     *
     * Constraints:
     *
     * 1 <= sentence1.length, sentence2.length <= 1000
     * 1 <= sentence1[i].length, sentence2[i].length <= 20
     * sentence1[i] and sentence2[i] consist of English letters.
     * 0 <= similarPairs.length <= 1000
     * similarPairs[i].length == 2
     * 1 <= xi.length, yi.length <= 20
     * xi and yi consist of lower-case and upper-case English letters.
     * All the pairs (xi, yi) are distinct.
     * @param sentence1
     * @param sentence2
     * @param similarPairs
     * @return
     */
    // time = O(n), space = O(n)
    public boolean areSentencesSimilar(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        int m = sentence1.length, n = sentence2.length;
        if (m != n) return false;

        HashMap<String, HashSet<String>> map = new HashMap<>();
        for (List<String> p : similarPairs) {
            String a = p.get(0), b = p.get(1);
            map.putIfAbsent(a, new HashSet<>());
            map.putIfAbsent(b, new HashSet<>());
            map.get(a).add(b);
            map.get(b).add(a);
        }

        for (int i = 0; i < n; i++) {
            String a = sentence1[i], b = sentence2[i];
            if (a.equals(b)) continue;
            if (!map.containsKey(a) || !map.get(a).contains(b)) return false;
        }
        return true;
    }
}