package LC901_1200;
import java.util.*;
public class LC1002_FindCommonCharacters {
    /**
     * Given a string array words, return an array of all characters that show up in all strings within the words
     * (including duplicates). You may return the answer in any order.
     *
     * Input: words = ["bella","label","roller"]
     * Output: ["e","l","l"]
     *
     * Input: words = ["cool","lock","cook"]
     * Output: ["c","o"]
     *
     * Constraints:
     *
     * 1 <= words.length <= 100
     * 1 <= words[i].length <= 100
     * words[i] consists of lowercase English letters.
     * @param words
     * @return
     */
    // time = O(n * k), space = O(1)
    public List<String> commonChars(String[] words) {
        List<String> res = new ArrayList<>();
        int[] cnt = new int[26];
        Arrays.fill(cnt, (int)1e8);
        for (String word : words) {
            int[] s = new int[26];
            for (char c : word.toCharArray()) s[c - 'a']++;
            for (int i = 0; i < 26; i++) cnt[i] = Math.min(cnt[i], s[i]);
        }

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < cnt[i]; j++) {
                res.add(String.valueOf((char)(i + 'a')));
            }
        }
        return res;
    }
}