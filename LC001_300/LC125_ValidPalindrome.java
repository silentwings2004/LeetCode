package LC001_300;
import java.util.*;
public class LC125_ValidPalindrome {
    /**
     * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
     *
     * Note: For the purpose of this problem, we define empty string as valid palindrome.
     *
     * Input: "A man, a plan, a canal: Panama"
     * Output: true
     *
     * Input: "race a car"
     * Output: false
     *
     * Constraints:
     *
     * s consists only of printable ASCII characters.
     *
     * @param s
     * @return
     */
    // time = O(n), space = O(1)
    public boolean isPalindrome(String s) {
        int n = s.length();
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            while (i < j && !check(s.charAt(i))) i++;
            while (i < j && !check(s.charAt(j))) j--;
            if (i < j && Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) return false;
        }
        return true;
    }

    private boolean check(char c) {
        return Character.isLetter(c) || Character.isDigit(c);
    }
}