package LC3901_4200;

public class LC4030_CheckASCIIPalindromic {
    /**
     * You are given a string s consisting of lowercase English letters.
     *
     * Construct a binary string by replacing each character in s with the 8-bit binary representation of its ASCII
     * value, including leading zeros, while preserving the original order of the characters.
     *
     * Return true if the resulting binary string is a palindrome. Otherwise, return false.
     *
     * A binary string is a string which contains only the characters '0' and '1'.
     *
     * A palindrome is a string that reads the same forward and backward.
     *
     * Input: s = "ff"
     * Output: true
     *
     * Input: s = "leet"
     * Output: false
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * s consists of lowercase English letters.
     * @param s
     * @return
     */
    // time = O(n), space = O(n)
    public boolean isPalindromic(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            int x = c - 'a' + 97;
            String t = Integer.toBinaryString(x);
            int m = t.length();
            if (m < 8) t = "0".repeat(8 - m) + t;
            sb.append(t);
        }
        s = sb.toString();
        n = s.length();
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }
}