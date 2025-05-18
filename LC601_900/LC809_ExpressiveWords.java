package LC601_900;
import java.util.*;
public class LC809_ExpressiveWords {
    /**
     * Sometimes people repeat letters to represent extra feeling. For example:
     *
     * "hello" -> "heeellooo"
     * "hi" -> "hiiii"
     * In these strings like "heeellooo", we have groups of adjacent letters that are all the same: "h", "eee", "ll",
     * "ooo".
     *
     * You are given a string s and an array of query strings words. A query word is stretchy if it can be made to be
     * equal to s by any number of applications of the following extension operation: choose a group consisting of
     * characters c, and add some number of characters c to the group so that the size of the group is three or more.
     *
     * For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot
     * get "helloo" since the group "oo" has a size less than three. Also, we could do another extension like
     * "ll" -> "lllll" to get "helllllooo". If s = "helllllooo", then the query word "hello" would be stretchy because
     * of these two extension operations: query = "hello" -> "hellooo" -> "helllllooo" = s.
     * Return the number of query strings that are stretchy.
     *
     * Input: s = "heeellooo", words = ["hello", "hi", "helo"]
     * Output: 1
     *
     * Input: s = "zzzzzyyyyy", words = ["zzyy","zy","zyy"]
     * Output: 3
     *
     * Constraints:
     *
     * 1 <= s.length, words.length <= 100
     * 1 <= words[i].length <= 100
     * s and words[i] consist of lowercase letters.
     * @param s
     * @param words
     * @return
     */
    // S1
    // time = O(S), space = O(1)  S: the length of string s and all of the words in the array words
    public int expressiveWords(String s, String[] words) {
        int n = s.length(), res = 0;
        for (String t : words) {
            int m = t.length(), i = 0, j = 0;
            boolean flag = true;
            while (i < n && j < m) {
                if (s.charAt(i) != t.charAt(j)) {
                    flag = false;
                    break;
                }
                int a = i + 1, b = j + 1;
                while (a < n && s.charAt(a) == s.charAt(i)) a++;
                while (b < m && t.charAt(b) == t.charAt(j)) b++;
                int len1 = a - i, len2 = b - j;
                if (len1 != len2 && (len1 < len2 || len1 < 3)) {
                    flag = false;
                    break;
                }
                i = a;
                j = b;
            }
            if (i != n || j != m) continue;
            if (flag) res++;
        }
        return res;
    }

    // S2
    // time = O(n), space = O(n)
    public int expressiveWords2(String s, String[] words) {
        if (s.length() == 0) {
            int res = 0;
            for (String w : words) {
                if (w.length() == 0) res++;
            }
            return res;
        }

        List<int[]> q = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            int j = i + 1;
            while (j < s.length() && s.charAt(i) == s.charAt(j)) j++;
            q.add(new int[]{s.charAt(i) - 'a', j - i});
            i = j - 1;
        }

        int res = 0;
        for (String w : words) {
            int k = 0;
            for (int i = 0; i < w.length(); i++) {
                if (k == q.size()) {
                    k = -1;
                    break;
                }
                if (q.get(k)[0] != w.charAt(i) - 'a') break;
                int j = i + 1;
                while (j < w.length() && w.charAt(i) == w.charAt(j)) j++;
                int c1 = q.get(k)[1], c2 = j - i;
                if (c1 < c2) break;
                if (c1 < 3 && c1 != c2) break;
                k++;
                i = j - 1;
            }
            if (k == q.size()) res++;
        }
        return res;
    }
}
/**
 * c1 < c2 => false
 * c1 >= c2
 * 1. c1 < 3 => 只能取c1 = c2
 * 2. c1 >= 3 => 有解
 */
