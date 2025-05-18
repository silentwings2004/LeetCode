package LC301_600;
import java.util.*;
public class LC500_KeyboardRow {
    /**
     * Given an array of strings words, return the words that can be typed using letters of the alphabet on only one
     * row of American keyboard like the image below.
     *
     * In the American keyboard:
     *
     * the first row consists of the characters "qwertyuiop",
     * the second row consists of the characters "asdfghjkl", and
     * the third row consists of the characters "zxcvbnm".
     *
     * Input: words = ["Hello","Alaska","Dad","Peace"]
     * Output: ["Alaska","Dad"]
     *
     * Constraints:
     *
     * 1 <= words.length <= 20
     * 1 <= words[i].length <= 100
     * words[i] consists of English letters (both lowercase and uppercase).
     * @param words
     * @return
     */
    // time = O(n * k), space = O(n * k)
    public String[] findWords(String[] words) {
        String[] line = new String[]{
                "qwertyuiop",
                "asdfghjkl",
                "zxcvbnm"
        };

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            for (char c : line[i].toCharArray()) {
                map.put(c, i);
            }
        }

        List<String> res = new ArrayList<>();
        for (String word : words) {
            HashSet<Integer> set = new HashSet<>();
            for (char c : word.toCharArray()) {
                set.add(map.get(Character.toLowerCase(c)));
            }
            if (set.size() == 1) res.add(word);
        }
        return res.toArray(new String[res.size()]);
    }

    // S2
    // time = O(n * k), space = O(n * k)
    public String[] findWords2(String[] words) {
        String[] dict = new String[]{"qwertyuiop", "asdfghjkl", "zxcvbnm"};
        List<String> res = new ArrayList<>();
        for (String word : words) {
            for (String s : dict) {
                if (check(word, s)) res.add(word);
            }
        }
        return res.toArray(new String[res.size()]);
    }

    private boolean check(String s, String t) {
        for (char c : s.toCharArray()) {
            if (t.indexOf(Character.toLowerCase(c)) == -1) return false;
        }
        return true;
    }
}
