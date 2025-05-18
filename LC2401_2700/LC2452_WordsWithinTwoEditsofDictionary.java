package LC2401_2700;
import java.util.*;
public class LC2452_WordsWithinTwoEditsofDictionary {
    /**
     * You are given two string arrays, queries and dictionary. All words in each array comprise of lowercase English
     * letters and have the same length.
     *
     * In one edit you can take a word from queries, and change any letter in it to any other letter. Find all words
     * from queries that, after a maximum of two edits, equal some word from dictionary.
     *
     * Return a list of all words from queries, that match with some word from dictionary after a maximum of two edits.
     * Return the words in the same order they appear in queries.
     *
     * Input: queries = ["word","note","ants","wood"], dictionary = ["wood","joke","moat"]
     * Output: ["word","note","wood"]
     *
     * Input: queries = ["yes"], dictionary = ["not"]
     * Output: []
     *
     * Constraints:
     *
     * 1 <= queries.length, dictionary.length <= 100
     * n == queries[i].length == dictionary[j].length
     * 1 <= n <= 100
     * All queries[i] and dictionary[j] are composed of lowercase English letters.
     * @param queries
     * @param dictionary
     * @return
     */
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> res = new ArrayList<>();
        for (String a : queries) {
            for (String b : dictionary) {
                if (check(a, b)) {
                    res.add(a);
                    break;
                }
            }
        }
        return res;
    }

    private boolean check(String a, String b) {
        int n = a.length(), cnt = 0;
        for (int i = 0; i < n; i++) {
            if (a.charAt(i) != b.charAt(i)) cnt++;
            if (cnt > 2) return false;
        }
        return true;
    }
}
