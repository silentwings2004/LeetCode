package LC2700_3000;
import java.util.*;
public class LC2785_SortVowelsinaString {
    /**
     * Given a 0-indexed string s, permute s to get a new string t such that:
     *
     * All consonants remain in their original places. More formally, if there is an index i with 0 <= i < s.length such
     * that s[i] is a consonant, then t[i] = s[i].
     * The vowels must be sorted in the nondecreasing order of their ASCII values. More formally, for pairs of indices
     * i, j with 0 <= i < j < s.length such that s[i] and s[j] are vowels, then t[i] must not have a higher ASCII value
     * than t[j].
     * Return the resulting string.
     *
     * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in lowercase or uppercase. Consonants comprise
     * all letters that are not vowels.
     *
     * Input: s = "lEetcOde"
     * Output: "lEOtcede"
     *
     * Input: s = "lYmpH"
     * Output: "lYmpH"
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s consists only of letters of the English alphabet in uppercase and lowercase.
     * @param s
     * @return
     */
    // time = O(nlogn), space = O(n)
    public String sortVowels(String s) {
        List<Integer> pos = new ArrayList<>();
        List<Character> q = new ArrayList<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (check(c)) {
                pos.add(i);
                q.add(c);
            }
        }
        Collections.sort(q);
        char[] chars = s.toCharArray();
        for (int i = 0; i < q.size(); i++) chars[pos.get(i)] = q.get(i);
        return String.valueOf(chars);
    }

    private boolean check(char c) {
        String vowel = "aeiou";
        c = Character.toLowerCase(c);
        return vowel.indexOf(c) != -1;
    }
}