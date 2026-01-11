package LC3601_3900;
import java.util.*;
public class LC3805_CountCaesarCipherPairs {
    /**
     * You are given an array words of n strings. Each string has length m and contains only lowercase English letters.
     *
     * Two strings s and t are similar if we can apply the following operation any number of times (possibly zero times)
     * so that s and t become equal.
     *
     * Choose either s or t.
     * Replace every letter in the chosen string with the next letter in the alphabet cyclically. The next letter after
     * 'z' is 'a'.
     * Count the number of pairs of indices (i, j) such that:
     *
     * i < j
     * words[i] and words[j] are similar.
     * Return an integer denoting the number of such pairs.
     *
     * Input: words = ["fusion","layout"]
     * Output: 1
     *
     * Input: words = ["ab","aa","za","aa"]
     * Output: 2
     *
     * Constraints:
     *
     * 1 <= n == words.length <= 10^5
     * 1 <= m == words[i].length <= 10^5
     * 1 <= n * m <= 10^5
     * words[i] consists only of lowercase English letters.
     * @param words
     * @return
     */
    // time = O(n * k), space = O(n * k)
    public long countPairs(String[] words) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String w : words) {
            String t = convert(w);
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        long res = 0;
        for (int v : map.values()) res += v * (v - 1L) / 2;
        return res;
    }

    private String convert(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length(), shift = s.charAt(0) - 'a';
        for (int i = 0; i < n; i++) {
            int u = s.charAt(i) - 'a';
            int v = (u - shift + 26) % 26;
            sb.append((char)(v + 'a'));
        }
        return sb.toString();
    }
}