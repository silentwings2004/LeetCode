package LC2401_2700;
import java.util.*;
public class LC2506_CountPairsOfSimilarStrings {
    /**
     * You are given a 0-indexed string array words.
     *
     * Two strings are similar if they consist of the same characters.
     *
     * For example, "abca" and "cba" are similar since both consist of characters 'a', 'b', and 'c'.
     * However, "abacba" and "bcfd" are not similar since they do not consist of the same characters.
     * Return the number of pairs (i, j) such that 0 <= i < j <= word.length - 1 and the two strings words[i] and
     * words[j] are similar.
     *
     * Input: words = ["aba","aabb","abcd","bac","aabc"]
     * Output: 2
     *
     * Input: words = ["aabb","ab","ba"]
     * Output: 3
     *
     * Input: words = ["nba","cba","dba"]
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= words.length <= 100
     * 1 <= words[i].length <= 100
     * words[i] consist of only lowercase English letters.
     * @param words
     * @return
     */
    // time = O(n), space = O(n)
    public int similarPairs(String[] words) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            int[] cnt = new int[26];
            for (char c : word.toCharArray()) cnt[c - 'a']++;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (cnt[i] > 0) sb.append((char)(i + 'a'));
            }
            String key = sb.toString();
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        int res = 0;
        for (int v : map.values()) res += v * (v - 1) / 2;
        return res;
    }
}
