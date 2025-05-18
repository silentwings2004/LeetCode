package LC2101_2400;

public class LC2375_ConstructSmallestNumberFromDIString {
    /**
     * You are given a 0-indexed string pattern of length n consisting of the characters 'I' meaning increasing and 'D'
     * meaning decreasing.
     *
     * A 0-indexed string num of length n + 1 is created using the following conditions:
     *
     * num consists of the digits '1' to '9', where each digit is used at most once.
     * If pattern[i] == 'I', then num[i] < num[i + 1].
     * If pattern[i] == 'D', then num[i] > num[i + 1].
     * Return the lexicographically smallest possible string num that meets the conditions.
     *
     * Input: pattern = "IIIDIDDD"
     * Output: "123549876"
     *
     * Input: pattern = "DDD"
     * Output: "4321"
     *
     * Constraints:
     *
     * 1 <= pattern.length <= 8
     * pattern consists of only the letters 'I' and 'D'.
     * @param pattern
     * @return
     */
    // S1
    // time = O(n), space = O(n)
    public String smallestNumber(String pattern) {
        int n = pattern.length();
        char[] chars = new char[n + 1];
        for (int i = 0; i <= n; i++) chars[i] = (char)(i + 1 + '0');
        for (int i = 0; i < n; i++) {
            int j = i;
            char c = pattern.charAt(i);
            while (j < n && pattern.charAt(j) == c) j++;
            int k = j - i;
            if (c == 'D') helper(chars, i, k + 1);
            i = j - 1;
        }
        return String.valueOf(chars);
    }

    private void helper(char[] chars, int x, int k) {
        int i = x, j = x + k - 1;
        while (i < j) {
            char t = chars[i];
            chars[i++] = chars[j];
            chars[j--] = t;
        }
    }

    // S2
    // time = O(n), space = O(n)
    public String smallestNumber2(String pattern) {
        pattern = "I" + pattern;
        int n = pattern.length();

        int max = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int j = i + 1;
            while (j < n && pattern.charAt(j) == 'D') j++;
            int count = j - i;
            for (int k = max + count; k >= max + 1; k--) sb.append(k);
            max = max + count;
            i = j - 1;
        }
        return sb.toString();
    }
}
/**
 * same as LC484
 * LC942的follow-up
 *
 * 1 ~ 9,每个字符只能用1次，最长不会超过9
 * 在pattern前面加一个"I"
 *
 * 首先我们知道，必须将尽量小的字符放在前面使用。
 * 我们将pattern前加上一个“I”，这样pattern的长度就与字符串相等。
 * 我们发现，将每个“IDD...D”视作一个section，那么后一个section必然要完全高于前一个section。
 * IDD | I | ID | I | I | IDDD...
 * 我们虚拟地令当前的最大字符是0，然后把后续整个字符串的相对走势都表达出来后（必然都大于0），得到的就是用1~9组成的字典序最小的字符串。
 * 注：本题是942. DI String Match的follow-up，并且和484.Find-Permutation一模一样
 */
