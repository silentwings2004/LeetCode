package LC3001_3300;
import java.util.*;
public class LC3121_CounttheNumberofSpecialCharactersII {
    /**
     * You are given a string word. A letter c is called special if it appears both in lowercase and uppercase in word,
     *and every lowercase occurrence of c appears before the first uppercase occurrence of c.
     *
     * Return the number of special letters in word.
     *
     * Input: word = "aaAbcBC"
     * Output: 3
     *
     * Input: word = "abc"
     * Output: 0
     *
     * Input: word = "AbBCab"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= word.length <= 2 * 10^5
     * word consists of only lowercase and uppercase English letters.
     * @param word
     * @return
     */
    // time = O(n), space = O(1)
    public int numberOfSpecialChars(String word) {
        int n = word.length();
        int[] pos = new int[26];
        Arrays.fill(pos, -1);
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (Character.isLowerCase(c)) pos[c - 'a'] = i;
        }

        int x = 0, y = 0;
        for (int i = 0; i < n; i++) {
            char c = word.charAt(i);
            if (Character.isUpperCase(c)) {
                int u = c - 'A';
                if (pos[u] != -1 && pos[u] < i && (y >> u & 1) == 0) {
                    x |= 1 << u;
                } else y |= 1 << u;
            }
        }
        return Integer.bitCount(x);
    }
}