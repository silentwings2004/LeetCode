package LC301_600;
import java.util.*;
public class LC527_WordAbbreviation {
    /**
     * Given an array of distinct strings words, return the minimal possible abbreviations for every word.
     *
     * The following are the rules for a string abbreviation:
     *
     * The initial abbreviation for each word is: the first character, then the number of characters in between,
     * followed by the last character.
     * If more than one word shares the same abbreviation, then perform the following operation:
     * Increase the prefix (characters in the first part) of each of their abbreviations by 1.
     * For example, say you start with the words ["abcdef","abndef"] both initially abbreviated as "a4f". Then, a
     * sequence of operations would be ["a4f","a4f"] -> ["ab3f","ab3f"] -> ["abc2f","abn2f"].
     * This operation is repeated until every abbreviation is unique.
     * At the end, if an abbreviation did not make a word shorter, then keep it as the original word.
     *
     * Input: words = ["like","god","internal","me","internet","interval","intension","face","intrusion"]
     * Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
     *
     * Input: words = ["aa","aaa"]
     * Output: ["aa","aaa"]
     *
     * Constraints:
     *
     * 1 <= words.length <= 400
     * 2 <= words[i].length <= 400
     * words[i] consists of lowercase English letters.
     * All the strings of words are unique.
     * @param words
     * @return
     */
    // time = O(C), space = O(C)  C: the number of characters across all words in the given array
    public List<String> wordsAbbreviation(List<String> words) {
        List<Integer> ids = new ArrayList<>();
        int n = words.size();
        for (int i = 0; i < n; i++) ids.add(i);
        String[] res = new String[n];

        int abbrNum = 1;
        while (true) {
            HashMap<String, List<Integer>> map = new HashMap<>();
            for (int idx : ids) {
                String abbr = getAbbr(words.get(idx), abbrNum);
                map.putIfAbsent(abbr, new ArrayList<>());
                map.get(abbr).add(idx);
            }
            ids.clear();
            for (String key : map.keySet()) {
                if (map.get(key).size() > 1) ids.addAll(map.get(key));
                else res[map.get(key).get(0)] = key;
            }
            abbrNum++;
            if (ids.size() == 0) break;
        }
        return Arrays.asList(res);
    }

    private String getAbbr(String s, int abbrNum) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(0, abbrNum));
        sb.append(n - abbrNum - 1);
        sb.append(s.charAt(n - 1));
        return sb.length() >= n ? s : sb.toString();
    }
}
