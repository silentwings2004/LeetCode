package practice;
import java.util.*;
/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: PermutationinString
 * Creater: Silentwings
 * Date: May, 2020
 * Description: 567. Permutation in String
 */
public class LC567_PermutationinString {
    /**
     * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other
     * words, one of the first string's permutations is the substring of the second string.
     *
     *
     *
     * Example 1:
     *
     * Input: s1 = "ab" s2 = "eidbaooo"
     * Output: True
     * Explanation: s2 contains one permutation of s1 ("ba").
     * Example 2:
     *
     * Input:s1= "ab" s2 = "eidboaoo"
     * Output: False
     *
     *
     * Note:
     *
     * The input strings only contain lower case letters.
     * The length of both given strings is in range [1, 10,000].
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        // corner case
        if (s1 == null || s1.length() == 0) return true;
        if (s2 == null || s2.length() == 0 || s2.length() < s1.length()) return false;

        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();

        for (char c : s1.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;

        while (right < s2.length()) {
            char c = s2.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) valid++;
            }
            while (right - left >= s1.length()) {
                if (valid == need.size()) return true;
                char d = s2.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) valid--;
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return false;
    }
}
