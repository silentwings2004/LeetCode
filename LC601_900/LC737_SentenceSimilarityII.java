package LC601_900;
import java.util.*;
public class LC737_SentenceSimilarityII {
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
     * Notice that a word is always similar to itself, also notice that the similarity relation is transitive. For
     * example, if the words a and b are similar, and the words b and c are similar, then a and c are similar.
     *
     * Input: sentence1 = ["great","acting","skills"], sentence2 = ["fine","drama","talent"],
     * similarPairs = [["great","good"],["fine","good"],["drama","acting"],["skills","talent"]]
     * Output: true
     *
     * Input: sentence1 = ["I","love","leetcode"], sentence2 = ["I","love","onepiece"],
     * similarPairs = [["manga","onepiece"],["platform","anime"],["leetcode","platform"],["anime","manga"]]
     * Output: true
     *
     * Input: sentence1 = ["I","love","leetcode"], sentence2 = ["I","love","onepiece"],
     * similarPairs = [["manga","hunterXhunter"],["platform","anime"],["leetcode","platform"],["anime","manga"]]
     * Output: false
     *
     * Constraints:
     *
     * 1 <= sentence1.length, sentence2.length <= 1000
     * 1 <= sentence1[i].length, sentence2[i].length <= 20
     * sentence1[i] and sentence2[i] consist of lower-case and upper-case English letters.
     * 0 <= similarPairs.length <= 2000
     * similarPairs[i].length == 2
     * 1 <= xi.length, yi.length <= 20
     * xi and yi consist of English letters.
     * @param sentence1
     * @param sentence2
     * @param similarPairs
     * @return
     */
    final int N = 4010;
    int[] p;
    HashMap<String, Integer> map;
    int idx;
    public boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        map = new HashMap<>();
        idx = 0;
        p = new int[N];
        for (int i = 0; i < N; i++) p[i] = i;
        for (List<String> x : similarPairs) {
            String s = x.get(0), t = x.get(1);
            if (!map.containsKey(s)) map.put(s, idx++);
            if (!map.containsKey(t)) map.put(t, idx++);
            int a = map.get(s), b = map.get(t);
            if (find(a) != find(b)) p[find(a)] = find(b);
        }

        int m = sentence1.length, n = sentence2.length;
        if (m != n) return false;
        for (int i = 0; i < n; i++) {
            String s = sentence1[i], t = sentence2[i];
            if (s.equals(t)) continue;
            if (!map.containsKey(s) || !map.containsKey(t)) return false;
            int a = map.get(s), b = map.get(t);
            if (find(a) != find(b)) return false;
        }
        return true;
    }

    private int find(int x) {
        if (x != p[x]) p[x] = find(p[x]);
        return p[x];
    }
}