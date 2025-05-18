package LC301_600;

public class LC383_RansomNote {
    /**
     * Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters
     * from magazine and false otherwise.
     *
     * Each letter in magazine can only be used once in ransomNote.
     *
     * Input: ransomNote = "a", magazine = "b"
     * Output: false
     *
     * Constraints:
     *
     * 1 <= ransomNote.length, magazine.length <= 10^5
     * ransomNote and magazine consist of lowercase English letters.
     * @param ransomNote
     * @param magazine
     * @return
     */
    // time = O(1), space = O(1)
    public boolean canConstruct(String ransomNote, String magazine) {
        char[] cnt = new char[26];
        int m = ransomNote.length(), n = magazine.length();
        for (int i = 0; i < n; i++) cnt[magazine.charAt(i) - 'a']++;

        for (int i = 0; i < m; i++) {
            char c = ransomNote.charAt(i);
            if (cnt[c - 'a'] == 0) return false;
            cnt[c - 'a']--;
        }
        return true;
    }
}