package LC2700_3000;
import java.util.*;
public class LC2949_CountBeautifulSubstringsII {
    /**
     * You are given a string s and a positive integer k.
     *
     * Let vowels and consonants be the number of vowels and consonants in a string.
     *
     * A string is beautiful if:
     *
     * vowels == consonants.
     * (vowels * consonants) % k == 0, in other terms the multiplication of vowels and consonants is divisible by k.
     * Return the number of non-empty beautiful substrings in the given string s.
     *
     * A substring is a contiguous sequence of characters in a string.
     *
     * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
     *
     * Consonant letters in English are every letter except vowels.
     *
     * Input: s = "baeyh", k = 2
     * Output: 2
     *
     * Input: s = "abba", k = 1
     * Output: 3
     *
     * Input: s = "bcdf", k = 1
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= s.length <= 5 * 10^4
     * 1 <= k <= 1000
     * s consists of only English lowercase letters.
     * @param s
     * @param k
     * @return
     */
    // time = O(n), space = O(n)
    public long beautifulSubstrings(String s, int k) {
        int n = s.length();
        for (int i = 1;; i++) {
            if (i * i % (4 * k) == 0) {
                k = i;
                break;
            }
        }

        String vowel = "aeiou";
        HashMap<String, Integer> map = new HashMap<>();
        map.put("0#0", 1);
        long res = 0;
        for (int i = 1, sum = 0; i <= n; i++) {
            char c = s.charAt(i - 1);
            if (vowel.indexOf(c) != -1) sum++;
            else sum--;
            String h = sum + "#" + i % k;
            res += map.getOrDefault(h, 0);
            map.put(h, map.getOrDefault(h, 0) + 1);
        }
        return res;
    }
}