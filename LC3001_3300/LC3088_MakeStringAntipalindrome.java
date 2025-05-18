package LC3001_3300;
import java.util.*;
public class LC3088_MakeStringAntipalindrome {
    /**
     * We call a string s of even length n an anti-palindrome if for each index 0 <= i < n, s[i] != s[n - i - 1].
     *
     * Given a string s, your task is to make s an anti-palindrome by doing any number of operations (including zero).
     *
     * In one operation, you can select two characters from s and swap them.
     *
     * Return the resulting string. If multiple strings meet the conditions, return the
     * lexicographically smallest
     *  one. If it can't be made into an anti-palindrome, return "-1".
     *
     * Input: s = "abca"
     * Output: "aabc"
     *
     * Input: s = "abba"
     * Output: "aabb"
     *
     * Input: s = "cccd"
     * Output: "-1"
     *
     * Constraints:
     *
     * 2 <= s.length <= 10^5
     * s.length % 2 == 0
     * s consists only of lowercase English letters.
     * @param s
     * @return
     */
    // time = O(nlogn), space = O(n)
    public String makeAntiPalindrome(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        int n = chars.length;
        for (int i = n / 2 - 1, k = n - 1 - i; i >= 0; i--) {
            int j = n - 1 - i;
            if (chars[j] == chars[i]) {
                while (k < n && chars[k] == chars[i]) k++;
                if (k < n) swap(chars, j, k);
                else return "-1";
            } else break;
        }
        return String.valueOf(chars);
    }

    private void swap(char[] chars, int i, int j) {
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
    }
}