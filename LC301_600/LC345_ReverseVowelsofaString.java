package LC301_600;
import java.util.*;
public class LC345_ReverseVowelsofaString {
    /**
     * Given a string s, reverse only all the vowels in the string and return it.
     *
     * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both cases
     *
     * Input: s = "hello"
     * Output: "holle"
     *
     * Constraints:
     *
     * 1 <= s.length <= 3 * 10^5
     * s consist of printable ASCII characters.
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public String reverseVowels(String s) {
        String vowel = "aeiouAEIOU";
        char[] chars = s.toCharArray();
        int n = chars.length, i = 0, j = n - 1;
        while (i < j) {
            while (i < j && vowel.indexOf(chars[i]) == -1) i++;
            while (i < j && vowel.indexOf(chars[j]) == -1) j--;
            char c = chars[i];
            chars[i++] = chars[j];
            chars[j--] = c;
        }
        return String.valueOf(chars);
    }
}