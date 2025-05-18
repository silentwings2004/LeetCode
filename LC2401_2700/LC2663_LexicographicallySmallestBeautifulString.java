package LC2401_2700;

public class LC2663_LexicographicallySmallestBeautifulString {
    /**
     * A string is beautiful if:
     *
     * It consists of the first k letters of the English lowercase alphabet.
     * It does not contain any substring of length 2 or more which is a palindrome.
     * You are given a beautiful string s of length n and a positive integer k.
     *
     * Return the lexicographically smallest string of length n, which is larger than s and is beautiful. If there is
     * no such string, return an empty string.
     *
     * A string a is lexicographically larger than a string b (of the same length) if in the first position where a and
     * b differ, a has a character strictly larger than the corresponding character in b.
     *
     * For example, "abcd" is lexicographically larger than "abcc" because the first position they differ is at the
     * fourth character, and d is greater than c.
     *
     * Input: s = "abcz", k = 26
     * Output: "abda"
     *
     * Input: s = "dc", k = 4
     * Output: ""
     *
     * Constraints:
     *
     * 1 <= n == s.length <= 10^5
     * 4 <= k <= 26
     * s is a beautiful string.
     * @param s
     * @param k
     * @return
     */
    // S1
    // time = O(26 * n), space = O(n)
    public String smallestBeautifulString(String s, int k) {
        char[] chars = s.toCharArray();
        int n = chars.length, t = -1;
        for (int i = n - 1; i >= 0; i--) {
            int u = chars[i] - 'a';
            if (u > k - 1) continue;
            for (int j = u + 1; j < k; j++) {
                if (i - 1 >= 0 && chars[i - 1] - 'a' == j) continue;
                if (i - 2 >= 0 && chars[i - 2] - 'a' == j) continue;
                chars[i] = (char)(j + 'a');
                t = i;
                break;
            }
            if (t != -1) break;
        }
        if (t == -1) return "";
        for (int i = t + 1; i < n; i++) {
            for (int j = 0; j < 26; j++) {
                if (i - 1 >= 0 && chars[i - 1] - 'a' == j) continue;
                if (i - 2 >= 0 && chars[i - 2] - 'a' == j) continue;
                chars[i] = (char)(j + 'a');
                break;
            }
        }
        return String.valueOf(chars);
    }

    // S2
    // time = O(n), space = O(n)
    public String smallestBeautifulString2(String s, int k) {
        char[] chars = s.toCharArray();
        int n = chars.length, i = n - 1;
        chars[i]++;
        while (i >= 0 && i < n) {
            if (chars[i] - 'a' == k) {
                if (i == 0) return "";
                chars[i] = 'a';
                i--;
                chars[i]++;
            } else if (i > 0 && chars[i] == chars[i - 1] || i > 1 && chars[i] == chars[i - 2]) chars[i]++;
            else i++;
        }
        return String.valueOf(chars);
    }
}
/**
 * 字符中不能能有长为2的和长为3的回文串
 * next_permutation
 * 从右到左去修改
 * 用进位的概念，把字符串看成一个 k 进制数
 * dacd
 * dada
 * dbaa
 * dbab
 * dbac
 */