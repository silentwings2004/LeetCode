package practice;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: RansomNote
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 383. Ransom Note
 */
public class LC383_RansomNote {
    /**
     * Given an arbitrary ransom note string and another string containing letters from all the magazines, write a
     * function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will
     * return false.
     *
     * Each letter in the magazine string can only be used once in your ransom note.
     *
     * Note:
     * You may assume that both strings contain only lowercase letters.
     *
     * canConstruct("a", "b") -> false
     * canConstruct("aa", "ab") -> false
     * canConstruct("aa", "aab") -> true
     * @param ransomNote
     * @param magazine
     * @return
     */
    // time = O(m + n), space = O(1)
    public boolean canConstruct(String ransomNote, String magazine) {
        // corner case
        if (ransomNote == null || magazine == null || ransomNote.length() > magazine.length()) {
            return false;
        }
        int[] count = new int[128];
        for (char c : magazine.toCharArray()) count[c]++;
        for (char c : ransomNote.toCharArray()) {
            if (--count[c] < 0) return false;
        }
        return true;
    }
}
