package LC1201_1500;
import java.util.*;
public class LC1370_IncreasingDecreasingString {
    /**
     * You are given a string s. Reorder the string using the following algorithm:
     *
     * Pick the smallest character from s and append it to the result.
     * Pick the smallest character from s which is greater than the last appended character to the result and append it.
     * Repeat step 2 until you cannot pick more characters.
     * Pick the largest character from s and append it to the result.
     * Pick the largest character from s which is smaller than the last appended character to the result and append it.
     * Repeat step 5 until you cannot pick more characters.
     * Repeat the steps from 1 to 6 until you pick all characters from s.
     * In each step, If the smallest or the largest character appears more than once you can choose any occurrence and
     * append it to the result.
     *
     * Return the result string after sorting s with this algorithm.
     *
     * Input: s = "aaaabbbbcccc"
     * Output: "abccbaabccba"
     *
     * Constraints:
     *
     * 1 <= s.length <= 500
     * s consists of only lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public String sortString(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) cnt[c - 'a']++;
        int n = s.length();
        char[] res = new char[n];
        int idx = 0;

        while (idx < n) {
            for (int i = 0; i < 26; i++) {
                if (cnt[i] != 0) {
                    res[idx++] = (char)(i + 'a');
                    cnt[i]--;
                }
            }
            for (int i = 25; i >= 0; i--) {
                if (cnt[i] != 0) {
                    res[idx++] = (char)(i + 'a');
                    cnt[i]--;
                }
            }
        }
        return String.valueOf(res);
    }
}