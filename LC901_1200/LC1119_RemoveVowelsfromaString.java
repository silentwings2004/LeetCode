package LC901_1200;

public class LC1119_RemoveVowelsfromaString {
    /**
     * Given a string s, remove the vowels 'a', 'e', 'i', 'o', and 'u' from it, and return the new string.
     *
     * Input: s = "leetcodeisacommunityforcoders"
     * Output: "ltcdscmmntyfrcdrs"
     *
     * Input: s = "aeiou"
     * Output: ""
     *
     * Constraints:
     *
     * 1 <= s.length <= 1000
     * s consists of only lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public String removeVowels(String s) {
        String vowel = "aeiou";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (vowel.indexOf(c) == -1) sb.append(c);
        }
        return sb.toString();
    }
}