package LC1501_1800;
import java.util.*;
public class LC1704_DetermineifStringHalvesAreAlike {
    /**
     * You are given a string s of even length. Split this string into two halves of equal lengths, and let a be the
     * first half and b be the second half.
     *
     * Two strings are alike if they have the same number of vowels ('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U').
     * Notice that s contains uppercase and lowercase letters.
     *
     * Return true if a and b are alike. Otherwise, return false.
     *
     * Input: s = "book"
     * Output: true
     *
     * Constraints:
     *
     * 2 <= s.length <= 1000
     * s.length is even.
     * s consists of uppercase and lowercase letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public boolean halvesAreAlike(String s) {
        String vowel = "aeiouAEIOU";
        int a = 0, b = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (vowel.indexOf(c) != -1) {
                if (i < n / 2) a++;
                else b++;
            }
        }
        return a == b;
    }
}