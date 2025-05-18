package practice;

/**
 * Project Name: Leetcode
 * Package Name: leetcode
 * File Name: ReverseWordsinaString
 * Creater: Silentwings
 * Date: Mar, 2020
 * Description: 151. Reverse Words in a String
 */
public class LC151_ReverseWordsinaString {
    /**
     * Given an input string, reverse the string word by word.
     *
     *
     *
     * Example 1:
     *
     * Input: "the sky is blue"
     * Output: "blue is sky the"
     * Example 2:
     *
     * Input: "  hello world!  "
     * Output: "world! hello"
     * Explanation: Your reversed string should not contain leading or trailing spaces.
     * Example 3:
     *
     * Input: "a good   example"
     * Output: "example good a"
     * Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.
     *
     *
     * Note:
     *
     * A word is defined as a sequence of non-space characters.
     * Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or
     * trailing spaces.
     * You need to reduce multiple spaces between two words to a single space in the reversed string.
     *
     *
     * Follow up:
     *
     * For C programmers, try to solve it in-place in O(1) extra space.
     *
     * =================================================================================================================
     *
     * x = x.replaceAll("\\s", "");
     * x = x.replaceAll("\\s+", "");
     * The first one matches a single whitespace, whereas the second one matches one or many whitespaces.
     * They're the so-called regular expression quantifiers.
     *
     * string.trim() is a built-in function that eliminates leading and trailing spaces.
     *
     *
     *
     * @param s
     * @return
     */
    // S1: StringBuilder --> String[] array   split(" ") trim()  regex
    // time = O(n), space = O(n)
    public String reverseWords(String s) {
        // corner case
        if (s == null || s.length() == 0) return s;
        StringBuilder sb = new StringBuilder();
        String[] words = s.trim().split("\\s+"); // \\s代表的是空格，回车或者是换行符，\\s+代表是有1个或者多个 "a     b"
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i] + " ");
        }
        return sb.toString().trim();
    }

    // S2: 先全局reverse，再找出其中一个又一个word,再分别reverse下 ⇒ 抽出reverse功能单独写
    public String reverseWords2(String s) {
        // corner case
        if (s == null || s.length() == 0) return s;
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        reverse(chars, 0, chars.length - 1);

        int slow = 0, fast = 0;
        while (slow < chars.length && fast < chars.length) {
            //注意：这里&&前后的项目不能调换，一定要先check slow有无出界再check是否为空格，否则会报IndexOutOfBound的错误
            while (slow < chars.length && chars[slow] == ' ') slow++;
            if (slow == chars.length) break;
            else fast = slow;
            while (fast < chars.length && chars[fast] != ' ') fast++;
            reverse(chars, slow, fast - 1);
            String str = new String(chars, slow, fast - slow);
            sb.append(str + " ");
            slow = fast;
        }
        return sb.toString().trim();
    }

    private void reverse(char[] chars, int start, int end) {
        while (start < end) {
            char count = chars[start];
            chars[start] = chars[end];
            chars[end] = count;
            start++;
            end--;
        }
    }
}
